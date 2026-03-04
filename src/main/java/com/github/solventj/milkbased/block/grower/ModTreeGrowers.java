package com.github.solventj.milkbased.block.grower;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.world.feature.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower CHEESEWOOD = new TreeGrower(MilkBased.MOD_ID + ":cheesewood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.CHEESEWOOD_TREE), Optional.empty());

    public static final TreeGrower PLOMBIR = new TreeGrower(MilkBased.MOD_ID + ":plombir",
            Optional.empty(), Optional.of(ModConfiguredFeatures.PLOMBIR_TREE), Optional.empty());
}
