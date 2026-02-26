package com.github.solventj.milkbased.worldgen.tree;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower CHEESEWOOD = new TreeGrower(MilkBased.MOD_ID + ":cheesewood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.CHEESEWOOD_KEY), Optional.empty());
}
