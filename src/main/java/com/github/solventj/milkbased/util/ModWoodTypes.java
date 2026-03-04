package com.github.solventj.milkbased.util;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType CHEESEWOOD = WoodType.register(new WoodType(
            MilkBased.MOD_ID + ":cheesewood",
            BlockSetType.OAK
    ));

    public static final WoodType PLOMBIR = WoodType.register(new WoodType(
            MilkBased.MOD_ID + ":plombir",
            BlockSetType.SPRUCE
    ));
}
