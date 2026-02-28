package com.github.solventj.milkbased.dimension;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.worldgen.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class ModDimensions {
    public static final ResourceKey<LevelStem> MILK_DIMENSION_STEM_KEY =
            registerKey(Registries.LEVEL_STEM, "milk_dimension");

    public static final ResourceKey<Level> MILK_DIMENSION_LEVEL_KEY =
            registerKey(Registries.DIMENSION, "milk_dimension");

    // TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP
    public static final ResourceKey<NoiseGeneratorSettings> NOISE_SETTINGS_TEMP = registerKey(
            Registries.NOISE_SETTINGS, "milk_noise_settings");
    // TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP TEMP

    public static void bootstrap(BootstrapContext<LevelStem> context) {
        var dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        var biomes = context.lookup(Registries.BIOME);
        var noise_settings = context.lookup(Registries.NOISE_SETTINGS);

        context.register(MILK_DIMENSION_STEM_KEY, new LevelStem(
                dimensionTypes.getOrThrow(ModDimensionTypes.MILK_DIMENSION_TYPE_KEY),
                new NoiseBasedChunkGenerator(
                        new FixedBiomeSource(biomes.getOrThrow(ModBiomes.CHEESE_BIOME)),
                        noise_settings.getOrThrow(NOISE_SETTINGS_TEMP))
        ));
    }

    public static <T> ResourceKey<T> registerKey(ResourceKey<? extends Registry<T>> key, String name) {
        return ResourceKey.create(key, ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}