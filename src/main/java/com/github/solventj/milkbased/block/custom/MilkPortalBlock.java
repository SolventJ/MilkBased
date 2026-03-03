package com.github.solventj.milkbased.block.custom;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.world.dimension.ModDimensions;
import com.github.solventj.milkbased.world.dimension.portal.ModPortalForcer;
import com.github.solventj.milkbased.world.dimension.portal.ModPortalShape;
import com.github.solventj.milkbased.particle.ModParticleTypes;
import com.github.solventj.milkbased.util.ModPoiTypes;
import com.mojang.serialization.MapCodec;

import java.util.Comparator;
import java.util.Optional;
import javax.annotation.Nullable;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class MilkPortalBlock extends Block implements Portal {
    public static final MapCodec<MilkPortalBlock> CODEC = simpleCodec(MilkPortalBlock::new);
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);

    @Override
    public @NotNull MapCodec<MilkPortalBlock> codec() {
        return CODEC;
    }

    public MilkPortalBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NetherPortalBlock.AXIS, Direction.Axis.X));
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos,
                                           @NotNull CollisionContext context) {
        if (state.getValue(NetherPortalBlock.AXIS) == Direction.Axis.Z) {
            return Z_AXIS_AABB;
        }
        return X_AXIS_AABB;
    }

    @Override
    protected void randomTick(@NotNull BlockState state, ServerLevel level, @NotNull BlockPos pos,
                              @NotNull RandomSource random) {
        if (level.dimensionType().natural()
                && level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)
                && random.nextInt(2000) < level.getDifficulty().getId()) {
            while (level.getBlockState(pos).is(this)) {
                pos = pos.below();
            }

            if (level.getBlockState(pos).isValidSpawn(level, pos, EntityType.ZOMBIFIED_PIGLIN)) {
                Entity entity = EntityType.ZOMBIFIED_PIGLIN.spawn(level, pos.above(), MobSpawnType.STRUCTURE);
                if (entity != null) {
                    entity.setPortalCooldown();
                }
            }
        }
    }

    @Override
    protected @NotNull BlockState updateShape(
            BlockState state, Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level,
            @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        Direction.Axis facingAxis = facing.getAxis();
        Direction.Axis stateAxis = state.getValue(NetherPortalBlock.AXIS);
        boolean flag = stateAxis != facingAxis && facingAxis.isHorizontal();
        return !flag && !facingState.is(this) && !new ModPortalShape(level, currentPos, stateAxis).isComplete()
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected void entityInside(
            @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Entity entity) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }
    }

    @Override
    public int getPortalTransitionTime(@NotNull ServerLevel level, @NotNull Entity entity) {
        return entity instanceof Player player
                ? Math.max(
                1,
                level.getGameRules()
                        .getInt(
                                player.getAbilities().invulnerable
                                        ? GameRules.RULE_PLAYERS_NETHER_PORTAL_CREATIVE_DELAY
                                        : GameRules.RULE_PLAYERS_NETHER_PORTAL_DEFAULT_DELAY
                        )
        )
                : 0;
    }

    @Nullable
    @Override
    public DimensionTransition getPortalDestination(
            ServerLevel level, @NotNull Entity entity, @NotNull BlockPos pos) {
        ResourceKey<Level> resourcekey = level.dimension() == ModDimensions.MILK_LEVEL_KEY
                ? Level.OVERWORLD : ModDimensions.MILK_LEVEL_KEY;
        ServerLevel serverlevel = level.getServer().getLevel(resourcekey);
        if (serverlevel == null) {
            return null;
        } else {
            boolean flag = serverlevel.dimension() == ModDimensions.MILK_LEVEL_KEY;
            WorldBorder worldborder = serverlevel.getWorldBorder();
            double d0 = DimensionType.getTeleportationScale(level.dimensionType(), serverlevel.dimensionType());
            BlockPos blockpos = worldborder.clampToBounds(entity.getX() * d0, entity.getY(), entity.getZ() * d0);
            return getExitPortal(serverlevel, entity, pos, blockpos, flag, worldborder);
        }
    }

    @Nullable
    private DimensionTransition getExitPortal(
            ServerLevel level, Entity entity, BlockPos pos, BlockPos exitPos,
            boolean isMilkDim, WorldBorder worldBorder
    ) {
        Optional<BlockPos> closestPortalPosition = findClosestPortalPosition(exitPos, isMilkDim, worldBorder, level);
        BlockUtil.FoundRectangle foundRectangle;
        DimensionTransition.PostDimensionTransition dimensionTransition;
        if (closestPortalPosition.isPresent()) {
            BlockPos blockpos = closestPortalPosition.get();
            BlockState blockstate = level.getBlockState(blockpos);
            foundRectangle = BlockUtil.getLargestRectangleAround(
                    blockpos,
                    blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS),
                    21,
                    Direction.Axis.Y,
                    21,
                    p_351970_ -> level.getBlockState(p_351970_) == blockstate
            );
            dimensionTransition = DimensionTransition.PLAY_PORTAL_SOUND
                    .then(e -> e.placePortalTicket(blockpos));
        } else {
            Direction.Axis axis = entity.level().getBlockState(pos).getOptionalValue(NetherPortalBlock.AXIS)
                    .orElse(Direction.Axis.X);
            Optional<BlockUtil.FoundRectangle> portal = new ModPortalForcer(level).createPortal(exitPos, axis);
            if (portal.isEmpty()) {
                MilkBased.LOGGER.error("Unable to create a portal, likely target out of world border");
                return null;
            }

            foundRectangle = portal.get();
            dimensionTransition = DimensionTransition.PLAY_PORTAL_SOUND
                    .then(DimensionTransition.PLACE_PORTAL_TICKET);
        }

        return getDimensionTransitionFromExit(entity, pos, foundRectangle,
                level, dimensionTransition);
    }

    public Optional<BlockPos> findClosestPortalPosition(
            BlockPos exitPos, boolean isMilkDim, WorldBorder worldBorder, ServerLevel level) {
        PoiManager poimanager = level.getPoiManager();
//        int i = isNether ? 16 : 128;
        int distance = 16;
        poimanager.ensureLoadedAndValid(level, exitPos, distance);
        return poimanager.getInSquare(poiTypeHolder -> poiTypeHolder.equals(ModPoiTypes.MILK_PORTAL),
                        exitPos, distance, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(worldBorder::isWithinBounds)
                .filter(blockPos -> level.getBlockState(blockPos)
                        .hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .min(Comparator.<BlockPos>comparingDouble(blockPos ->
                        blockPos.distSqr(exitPos)).thenComparingInt(Vec3i::getY));
    }

    private static DimensionTransition getDimensionTransitionFromExit(
            Entity entity, BlockPos pos, BlockUtil.FoundRectangle rectangle, ServerLevel level,
            DimensionTransition.PostDimensionTransition postDimensionTransition
    ) {
        BlockState blockstate = entity.level().getBlockState(pos);
        Direction.Axis axis;
        Vec3 vec3;
        if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle portal = BlockUtil.getLargestRectangleAround(
                    pos, axis, 21, Direction.Axis.Y, 21,
                    blockPos -> entity.level().getBlockState(blockPos) == blockstate
            );
            vec3 = ModPortalShape.getRelativePosition(portal, axis, entity.position(),
                    entity.getDimensions(entity.getPose()));
        } else {
            axis = Direction.Axis.X;
            vec3 = new Vec3(0.5, 0.0, 0.0);
        }

        return createDimensionTransition(
                level, rectangle, axis, vec3, entity, entity.getDeltaMovement(),
                entity.getYRot(), entity.getXRot(), postDimensionTransition
        );
    }

    private static DimensionTransition createDimensionTransition(
            ServerLevel level,
            BlockUtil.FoundRectangle rectangle,
            Direction.Axis axis,
            Vec3 offset,
            Entity entity,
            Vec3 speed,
            float yRot,
            float xRot,
            DimensionTransition.PostDimensionTransition postDimensionTransition
    ) {
        BlockPos blockpos = rectangle.minCorner;
        BlockState blockstate = level.getBlockState(blockpos);
        Direction.Axis portalAxis = blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS)
                .orElse(Direction.Axis.X);
        double d0 = rectangle.axis1Size;
        double d1 = rectangle.axis2Size;
        EntityDimensions entitydimensions = entity.getDimensions(entity.getPose());
        int i = axis == portalAxis ? 0 : 90;
        Vec3 vec3 = axis == portalAxis ? speed : new Vec3(speed.z, speed.y, -speed.x);
        double d2 = (double) entitydimensions.width() / 2.0 + (d0 - (double) entitydimensions.width()) * offset.x();
        double d3 = (d1 - (double) entitydimensions.height()) * offset.y();
        double d4 = 0.5 + offset.z();
        boolean flag = portalAxis == Direction.Axis.X;
        Vec3 vec31 = new Vec3(
                (double) blockpos.getX() + (flag ? d2 : d4),
                (double) blockpos.getY() + d3,
                (double) blockpos.getZ() + (flag ? d4 : d2));
        Vec3 vec32 = ModPortalShape.findCollisionFreePosition(vec31, level, entity, entitydimensions);
        return new DimensionTransition(level, vec32, vec3, yRot + (float) i, xRot, postDimensionTransition);
    }

    @Override
    public Portal.@NotNull Transition getLocalTransition() {
        return Portal.Transition.CONFUSION;
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                            RandomSource random) {
        if (random.nextInt(100) == 0) {
            level.playLocalSound(
                    (double) pos.getX() + 0.5,
                    (double) pos.getY() + 0.5,
                    (double) pos.getZ() + 0.5,
                    SoundEvents.BEACON_AMBIENT,
                    SoundSource.BLOCKS,
                    0.5F,
                    random.nextFloat() * 0.4F + 0.8F,
                    false
            );
        }

        for (int i = 0; i < 4; i++) {
            double x = (double) pos.getX() + random.nextDouble();
            double y = (double) pos.getY() + random.nextDouble();
            double z = (double) pos.getZ() + random.nextDouble();
            double xSpeed = ((double) random.nextFloat() - 0.5) * 0.5;
            double ySpeed = ((double) random.nextFloat() - 0.5) * 0.5;
            double zSpeed = ((double) random.nextFloat() - 0.5) * 0.5;
            int j = random.nextInt(2) * 2 - 1;
            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                x = (double) pos.getX() + 0.5 + 0.25 * (double) j;
                xSpeed = (random.nextFloat() * 2.0F * (float) j);
            } else {
                z = (double) pos.getZ() + 0.5 + 0.25 * (double) j;
                zSpeed = (random.nextFloat() * 2.0F * (float) j);
            }

            level.addParticle(ModParticleTypes.MILK_PORTAL.get(), x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(
            @NotNull BlockState state, @NotNull HitResult target, @NotNull LevelReader level,
            @NotNull BlockPos pos, @NotNull Player player) {
        return ItemStack.EMPTY;
    }

    @Override
    protected @NotNull BlockState rotate(@NotNull BlockState state, Rotation rot) {
        return switch (rot) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(NetherPortalBlock.AXIS)) {
                case Z -> state.setValue(NetherPortalBlock.AXIS, Direction.Axis.X);
                case X -> state.setValue(NetherPortalBlock.AXIS, Direction.Axis.Z);
                default -> state;
            };
            default -> state;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NetherPortalBlock.AXIS);
    }
}
