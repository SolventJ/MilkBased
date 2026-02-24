package com.github.solventj.milkbased.worldgen;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class ModDimensions {
    // ResourceKey для типа измерения
    public static final ResourceKey<DimensionType> MILK_DIMENSION_TYPE_KEY =
            ResourceKey.create(Registries.DIMENSION_TYPE,
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "milk_dimension_type"));

    // ResourceKey для самого измерения (LevelStem)
    public static final ResourceKey<LevelStem> MILK_DIMENSION_STEM_KEY =
            ResourceKey.create(Registries.LEVEL_STEM,
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "milk_dimension"));

    // ResourceKey для мира (Level)
    public static final ResourceKey<Level> MILK_DIMENSION_LEVEL_KEY =
            ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "milk_dimension"));
}