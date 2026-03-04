package com.github.solventj.milkbased.block.custom.saplings;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.block.grower.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PlombirSaplingBlock extends SaplingBlock {
    public PlombirSaplingBlock(Properties properties) {
        super(ModTreeGrowers.PLOMBIR, properties);
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockState belowBlockState = level.getBlockState(pos.below());
        return belowBlockState.is(ModBlocks.PLOMBIR_SNOW);
    }
}
