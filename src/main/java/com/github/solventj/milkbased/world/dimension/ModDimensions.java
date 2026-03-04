package com.github.solventj.milkbased.world.dimension;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.world.biome.ModBiomes;
import com.github.solventj.milkbased.world.levelgen.ModNoiseGeneratorSettings;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;

public class ModDimensions {
    public static final ResourceKey<LevelStem> MILK_STEM_KEY =
            registerKey(Registries.LEVEL_STEM, "milk_dimension");

    public static final ResourceKey<Level> MILK_LEVEL_KEY =
            registerKey(Registries.DIMENSION, "milk_dimension");

    public static void bootstrap(BootstrapContext<LevelStem> context) {
        var dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        var biomes = context.lookup(Registries.BIOME);
        var noise_settings = context.lookup(Registries.NOISE_SETTINGS);

        context.register(MILK_STEM_KEY, new LevelStem(
                dimensionTypes.getOrThrow(ModDimensionTypes.MILK_KEY),
                new NoiseBasedChunkGenerator(
                        MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(List.of(
                                Pair.of(new Climate.ParameterPoint(
                                                Climate.Parameter.span(-0.5f, 0.5f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                10L
                                        ),
                                        biomes.getOrThrow(ModBiomes.CHEESE_BIOME)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                Climate.Parameter.span(-2.0f, -0.5f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                10L
                                        ),
                                        biomes.getOrThrow(ModBiomes.PLOMBIR_BIOME)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                Climate.Parameter.span(0.5f, 2.0f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                Climate.Parameter.span(0.1f, 0.8f),
                                                10L
                                        ),
                                        biomes.getOrThrow(ModBiomes.GORGONZOLA_BIOME)
                                )
                        ))),
                        noise_settings.getOrThrow(ModNoiseGeneratorSettings.MILK_SETTINGS))
        ));
    }

    public static <T> ResourceKey<T> registerKey(ResourceKey<? extends Registry<T>> key, String name) {
        return ResourceKey.create(key, ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}