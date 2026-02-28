package com.github.solventj.milkbased.block.custom;

import com.github.solventj.milkbased.util.ModCauldronInteractions;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MilkCauldronBlock extends AbstractCauldronBlock {
    public static final MapCodec<MilkCauldronBlock> CODEC = simpleCodec(MilkCauldronBlock::new);

    @Override
    protected @NotNull MapCodec<MilkCauldronBlock> codec() {
        return CODEC;
    }

    public MilkCauldronBlock(Properties properties) {
        super(properties, ModCauldronInteractions.MILK);
    }

    @Override
    protected double getContentHeight(@NotNull BlockState state) {
        return 0.9375f;
    }

    @Override
    public boolean isFull(@NotNull BlockState blockState) {
        return true;
    }

    @Override
    protected void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                @NotNull Entity entity) {
        if (this.isEntityInsideContent(state, pos, entity) && entity instanceof LivingEntity livingEntity) {
            livingEntity.removeAllEffects();
        }
    }

    @Override
    protected int getAnalogOutputSignal(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return 3;
    }
}
