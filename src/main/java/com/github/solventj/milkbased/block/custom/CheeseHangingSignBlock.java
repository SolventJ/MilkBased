package com.github.solventj.milkbased.block.custom;

import com.github.solventj.milkbased.block_entity.custom.ModHangingSignBlockEntity;
import com.github.solventj.milkbased.block_entity.custom.ModSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class CheeseHangingSignBlock extends CeilingHangingSignBlock {
    public CheeseHangingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ModHangingSignBlockEntity(pos, state);
    }
}
