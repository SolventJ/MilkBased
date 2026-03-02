package com.github.solventj.milkbased.world.dimension.portal;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.block.custom.MilkPortalBlock;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;
import java.util.function.Predicate;

public class ModPortalShape {
    private final LevelAccessor level;
    private final Direction.Axis axis;
    private final Direction rightDir;
    private int numPortalBlocks;
    private BlockPos bottomLeft;
    private int height;
    private final int width;

    private static final Block FRAME_BLOCK = ModBlocks.MILKSTONE.get();

    public static Optional<ModPortalShape> findEmptyPortalShape(
            LevelAccessor level, BlockPos bottomLeft, Direction.Axis axis) {
        return findPortalShape(level, bottomLeft,
                shape -> shape.isValid() && shape.numPortalBlocks == 0, axis);
    }

    public static Optional<ModPortalShape> findPortalShape(
            LevelAccessor level, BlockPos bottomLeft, Predicate<ModPortalShape> predicate, Direction.Axis axis) {
        Optional<ModPortalShape> optional = Optional.of(new ModPortalShape(level, bottomLeft, axis)).filter(predicate);
        if (optional.isPresent()) {
            return optional;
        } else {
            Direction.Axis directionAxis = axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
            return Optional.of(new ModPortalShape(level, bottomLeft, directionAxis)).filter(predicate);
        }
    }

    public ModPortalShape(LevelAccessor level, BlockPos bottomLeft, Direction.Axis axis) {
        this.level = level;
        this.axis = axis;
        this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
        this.bottomLeft = this.calculateBottomLeft(bottomLeft);
        if (this.bottomLeft == null) {
            this.bottomLeft = bottomLeft;
            this.width = 1;
            this.height = 1;
        } else {
            this.width = this.calculateWidth();
            if (this.width > 0) {
                this.height = this.calculateHeight();
            }
        }
    }

    private BlockPos calculateBottomLeft(BlockPos pos) {
        int i = Math.max(level.getMinBuildHeight(), pos.getY() - 21);

        while (pos.getY() > i && isEmpty(level.getBlockState(pos.below()))) {
            pos = pos.below();
        }

        Direction direction = rightDir.getOpposite();
        int j = getDistanceUntilEdgeAboveFrame(pos, direction) - 1;
        return j < 0 ? null : pos.relative(direction, j);
    }

    private int calculateWidth() {
        int i = getDistanceUntilEdgeAboveFrame(bottomLeft, rightDir);
        return i >= 2 && i <= 21 ? i : 0;
    }

    private int getDistanceUntilEdgeAboveFrame(BlockPos pos, Direction direction) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int i = 0; i <= 21; i++) {
            mutablePos.set(pos).move(direction, i);
            BlockState blockState = level.getBlockState(mutablePos);
            if (!isEmpty(blockState)) {
                if (blockState.is(FRAME_BLOCK)) {
                    return i;
                }
                break;
            }

            BlockState blockState1 = level.getBlockState(mutablePos.move(Direction.DOWN));
            if (!blockState1.is(FRAME_BLOCK)) {
                break;
            }
        }

        return 0;
    }

    private int calculateHeight() {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        int i = getDistanceUntilTop(mutablePos);
        return i >= 3 && i <= 21 && hasTopFrame(mutablePos, i) ? i : 0;
    }

    private boolean hasTopFrame(BlockPos.MutableBlockPos pos, int distanceToTop) {
        for (int i = 0; i < width; i++) {
            BlockPos.MutableBlockPos mutablePos = pos.set(bottomLeft)
                    .move(Direction.UP, distanceToTop).move(rightDir, i);
            if (!level.getBlockState(mutablePos).is(FRAME_BLOCK)) {
                return false;
            }
        }

        return true;
    }

    private int getDistanceUntilTop(BlockPos.MutableBlockPos pos) {
        for (int i = 0; i < 21; i++) {
            pos.set(bottomLeft).move(Direction.UP, i).move(rightDir, -1);
            if (!level.getBlockState(pos).is(FRAME_BLOCK)) {
                return i;
            }

            pos.set(bottomLeft).move(Direction.UP, i).move(rightDir, width);
            if (!level.getBlockState(pos).is(FRAME_BLOCK)) {
                return i;
            }

            for (int j = 0; j < width; j++) {
                pos.set(bottomLeft).move(Direction.UP, i).move(rightDir, j);
                BlockState blockstate = level.getBlockState(pos);
                if (!isEmpty(blockstate)) {
                    return i;
                }

                if (blockstate.is(ModBlocks.MILK_PORTAL.get())) {
                    numPortalBlocks++;
                }
            }
        }

        return 21;
    }

    private static boolean isEmpty(BlockState state) {
        return state.isAir() || state.is(ModBlocks.MILK_PORTAL.get());
    }

    public boolean isValid() {
        return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && height <= 21;
    }

    public void createPortalBlocks() {
        BlockState blockstate = ModBlocks.MILK_PORTAL.get().defaultBlockState().setValue(MilkPortalBlock.AXIS, axis);
        BlockPos.betweenClosed(bottomLeft, bottomLeft
                        .relative(Direction.UP, height - 1)
                        .relative(rightDir, width - 1))
                .forEach(pos -> level.setBlock(pos, blockstate, 18));
    }

    public boolean isComplete() {
        return isValid() && numPortalBlocks == width * height;
    }

    public static Vec3 getRelativePosition(BlockUtil.FoundRectangle foundRectangle, Direction.Axis axis, Vec3 pos, EntityDimensions entityDimensions) {
        double d0 = (double) foundRectangle.axis1Size - (double) entityDimensions.width();
        double d1 = (double) foundRectangle.axis2Size - (double) entityDimensions.height();
        BlockPos blockpos = foundRectangle.minCorner;
        double d2;
        if (d0 > 0.0) {
            double d3 = (double) blockpos.get(axis) + (double) entityDimensions.width() / 2.0;
            d2 = Mth.clamp(Mth.inverseLerp(pos.get(axis) - d3, 0.0, d0), 0.0, 1.0);
        } else {
            d2 = 0.5;
        }

        double d5;
        if (d1 > 0.0) {
            Direction.Axis direction$axis = Direction.Axis.Y;
            d5 = Mth.clamp(Mth.inverseLerp(pos.get(direction$axis) - (double) blockpos.get(direction$axis), 0.0, d1), 0.0, 1.0);
        } else {
            d5 = 0.0;
        }

        Direction.Axis direction$axis1 = axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
        double d4 = pos.get(direction$axis1) - ((double) blockpos.get(direction$axis1) + 0.5);
        return new Vec3(d2, d5, d4);
    }

    public static Vec3 findCollisionFreePosition(
            Vec3 pos, ServerLevel level, Entity entity, EntityDimensions dimensions) {

        if (dimensions.width() > 4.0f || dimensions.height() > 4.0f) return pos;
        else {
            double halfHeight = (double) dimensions.height() / 2.0;
            Vec3 center = pos.add(0.0, halfHeight, 0.0);

            VoxelShape voxelshape = Shapes.create(
                    AABB.ofSize(center, dimensions.width(), 0.0,
                            dimensions.width()).expandTowards(0.0, 1.0, 0.0).inflate(1.0E-6)
            );

            Optional<Vec3> freePosition = level.findFreePosition(
                    entity, voxelshape, center, dimensions.width(), dimensions.height(), dimensions.width()
            );
            Optional<Vec3> optional = freePosition.map(vec3 -> vec3.subtract(0.0, halfHeight, 0.0));

            return optional.orElse(pos);
        }
    }
}
