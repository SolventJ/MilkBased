package com.github.solventj.milkbased.world.dimension;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.world.biome.ModBiomes;
import com.github.solventj.milkbased.world.levelgen.ModNoiseGeneratorSettings;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

import java.util.List;

public class ModDimensions {
    public static final ResourceKey<LevelStem> MILK_STEM_KEY =
            registerKey(Registries.LEVEL_STEM, "milk_dimension");

    public static final ResourceKey<Level> MILK_LEVEL_KEY =
            registerKey(Registries.DIMENSION, "milk_dimension");

    public static <T> ResourceKey<T> registerKey(ResourceKey<? extends Registry<T>> key, String name) {
        return ResourceKey.create(key, ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}