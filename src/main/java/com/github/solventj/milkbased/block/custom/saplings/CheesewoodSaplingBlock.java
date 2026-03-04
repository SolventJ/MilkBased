package com.github.solventj.milkbased.block.custom.saplings;

import com.github.solventj.milkbased.block.grower.ModTreeGrowers;
import com.github.solventj.milkbased.datagen.tags.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CheesewoodSaplingBlock extends SaplingBlock {
    public CheesewoodSaplingBlock(Properties properties) {
        super(ModTreeGrowers.CHEESEWOOD, properties);
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockState belowBlockState = level.getBlockState(pos.below());
        return belowBlockState.is(ModBlockTags.CHEESEWOOD_GROWABLE_ON);
    }
}
