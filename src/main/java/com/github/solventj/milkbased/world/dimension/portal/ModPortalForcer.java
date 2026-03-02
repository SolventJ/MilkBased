package com.github.solventj.milkbased.world.dimension.portal;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.block.custom.MilkPortalBlock;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ModPortalForcer {
    private static ServerLevel level;

    private static final Block FRAME_BLOCK = ModBlocks.CHEESE_BLOCK.get();

    public ModPortalForcer(ServerLevel worldIn) {
        level = worldIn;
    }

    public @NotNull Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.@NotNull Axis axis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0F;
        BlockPos blockPos0 = null;
        double d1 = -1.0F;
        BlockPos blockPos1 = null;
        WorldBorder worldborder = level.getWorldBorder();
        int i = Math.min(level.getMaxBuildHeight(), level.getMinBuildHeight() + level.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos mutablePos = pos.mutable();

        for(BlockPos.MutableBlockPos spiralBlockPos : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int k = Math.min(i, level.getHeight(Heightmap.Types.MOTION_BLOCKING, spiralBlockPos.getX(), spiralBlockPos.getZ()));
            if (worldborder.isWithinBounds(spiralBlockPos) && worldborder.isWithinBounds(spiralBlockPos.move(direction, 1))) {
                spiralBlockPos.move(direction.getOpposite(), 1);

                for(int l = k; l >= level.getMinBuildHeight(); --l) {
                    spiralBlockPos.setY(l);
                    if (this.canPortalReplaceBlock(spiralBlockPos)) {
                        int i1 = l;
                        while (l > level.getMinBuildHeight() && this.canPortalReplaceBlock(
                                spiralBlockPos.move(Direction.DOWN))) {
                            --l;
                        }

                        if (l + 4 <= i) {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3) {
                                spiralBlockPos.setY(l);
                                if (canHostFrame(spiralBlockPos, mutablePos, direction, 0)) {
                                    double d2 = pos.distSqr(spiralBlockPos);
                                    if (canHostFrame(spiralBlockPos, mutablePos, direction, -1) &&
                                            canHostFrame(spiralBlockPos, mutablePos, direction, 1) &&
                                            (d0 == (double)-1.0F || d0 > d2)) {
                                        d0 = d2;
                                        blockPos0 = spiralBlockPos.immutable();
                                    }

                                    if (d0 == (double)-1.0F && (d1 == (double)-1.0F || d1 > d2)) {
                                        d1 = d2;
                                        blockPos1 = spiralBlockPos.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d0 == -1.0d && d1 != -1.0d) {
            blockPos0 = blockPos1;
            d0 = d1;
        }

        if (d0 == -1.0d) {
            int k1 = Math.max(level.getMinBuildHeight() + 1, 70);
            int i2 = i - 9;
            if (i2 < k1) {
                return Optional.empty();
            }

            blockPos0 = (new BlockPos(pos.getX() - direction.getStepX(), Mth.clamp(pos.getY(), k1, i2), pos.getZ() - direction.getStepZ())).immutable();
            blockPos0 = worldborder.clampToBounds(blockPos0);
            Direction direction1 = direction.getClockWise();

            for(int i3 = -1; i3 < 2; ++i3) {
                for(int j3 = 0; j3 < 2; ++j3) {
                    for(int k3 = -1; k3 < 3; ++k3) {
                        BlockState blockState = k3 < 0
                                ? FRAME_BLOCK.defaultBlockState() : Blocks.AIR.defaultBlockState();
                        mutablePos.setWithOffset(blockPos0,
                                j3 * direction.getStepX() + i3 * direction1.getStepX(), k3,
                                j3 * direction.getStepZ() + i3 * direction1.getStepZ());
                        level.setBlockAndUpdate(mutablePos, blockState);
                    }
                }
            }
        }

        for(int l1 = -1; l1 < 3; ++l1) {
            for(int j2 = -1; j2 < 4; ++j2) {
                if (l1 == -1 || l1 == 2 || j2 == -1 || j2 == 3) {
                    mutablePos.setWithOffset(blockPos0, l1 * direction.getStepX(), j2, l1 * direction.getStepZ());
                    level.setBlock(mutablePos, FRAME_BLOCK.defaultBlockState(), 3);
                }
            }
        }

        BlockState blockstate = ModBlocks.MILK_PORTAL.get().defaultBlockState().setValue(MilkPortalBlock.AXIS, axis);

        for(int k2 = 0; k2 < 2; ++k2) {
            for(int l2 = 0; l2 < 3; ++l2) {
                mutablePos.setWithOffset(blockPos0, k2 * direction.getStepX(), l2, k2 * direction.getStepZ());
                level.setBlock(mutablePos, blockstate, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(blockPos0.immutable(), 2, 3));
    }

    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        return blockstate.canBeReplaced() && blockstate.getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos originalPos, BlockPos.MutableBlockPos offsetPos, Direction p_direction,
                                int offsetScale) {
        Direction direction = p_direction.getClockWise();

        for(int i = -1; i < 3; ++i) {
            for(int j = -1; j < 4; ++j) {
                offsetPos.setWithOffset(originalPos, p_direction.getStepX() * i +
                        direction.getStepX() * offsetScale, j, p_direction.getStepZ() * i +
                        direction.getStepZ() * offsetScale);
                if (j < 0 && !level.getBlockState(offsetPos).isSolid()) {
                    return false;
                }

                if (j >= 0 && !canPortalReplaceBlock(offsetPos)) {
                    return false;
                }
            }
        }

        return true;
    }
}
