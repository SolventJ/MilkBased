package com.github.solventj.milkbased.block.custom;

import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CheesewoodSapling extends SaplingBlock {
    public CheesewoodSapling(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockState belowBlockState = level.getBlockState(pos.below());
        return belowBlockState.is(ModBlocks.CHEESE_BLOCK);
    }
}
