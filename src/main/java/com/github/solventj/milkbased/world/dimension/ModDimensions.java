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

    private static final Climate.Parameter TEMPERATURE_FULL = Climate.Parameter.span(-1.0f, 1.0f);
    private static final Climate.Parameter TEMPERATURE_PLOMBIR = Climate.Parameter.span(-1.0f, -0.325f);
    private static final Climate.Parameter TEMPERATURE_RIVER = Climate.Parameter.span(-0.325f, 1.0f);
    private static final Climate.Parameter TEMPERATURE_CHEESE = Climate.Parameter.span(-0.325f, 0.325f);
    private static final Climate.Parameter TEMPERATURE_GORGONZOLA = Climate.Parameter.span(0.325f, 1.0f);
    private static final Climate.Parameter HUMIDITY_FULL = Climate.Parameter.span(-1.0f, 1.0f);
    private static final Climate.Parameter CONTINENTALNESS_OCEAN = Climate.Parameter.span(-1.0f, -0.19f);
    private static final Climate.Parameter CONTINENTALNESS_CONTINENT = Climate.Parameter.span(-0.19f, 1.0f);
    private static final Climate.Parameter EROSION_FULL = Climate.Parameter.span(-1.0f, 1.0f);
    private static final Climate.Parameter DEPTH_FULL = Climate.Parameter.span(-1.0f, 1.0f);
    private static final Climate.Parameter WEIRDNESS_FULL = Climate.Parameter.span(-1.0f, 1.0f);
    private static final Climate.Parameter WEIRDNESS_LAND_NEGATIVE = Climate.Parameter.span(-1.0f, -0.05f);
    private static final Climate.Parameter WEIRDNESS_RIVER = Climate.Parameter.span(-0.05f, 0.05f);
    private static final Climate.Parameter WEIRDNESS_LAND_POSITIVE = Climate.Parameter.span(0.05f, 1.0f);

    public static void bootstrap(BootstrapContext<LevelStem> context) {
        var dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        var biomes = context.lookup(Registries.BIOME);
        var noise_settings = context.lookup(Registries.NOISE_SETTINGS);

        context.register(MILK_STEM_KEY, new LevelStem(
                dimensionTypes.getOrThrow(ModDimensionTypes.MILK_KEY),
                new NoiseBasedChunkGenerator(
                        MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(List.of(
                                Pair.of(new Climate.ParameterPoint(
                                                TEMPERATURE_FULL,
                                                HUMIDITY_FULL,
                                                CONTINENTALNESS_OCEAN,
                                                EROSION_FULL,
                                                DEPTH_FULL,
                                                WEIRDNESS_FULL,
                                                0L
                                        ),
                                        biomes.getOrThrow(ModBiomes.MILK_OCEAN)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                TEMPERATURE_PLOMBIR,
                                                HUMIDITY_FULL,
                                                CONTINENTALNESS_CONTINENT,
                                                EROSION_FULL,
                                                DEPTH_FULL,
                                                WEIRDNESS_LAND_NEGATIVE,
                                                0L
                                        ),
                                        biomes.getOrThrow(ModBiomes.PLOMBIR_BIOME)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                TEMPERATURE_CHEESE,
                                                HUMIDITY_FULL,
                                                CONTINENTALNESS_CONTINENT,
                                                EROSION_FULL,
                                                DEPTH_FULL,
                                                WEIRDNESS_LAND_NEGATIVE,
                                                0L
                                        ),
                                        biomes.getOrThrow(ModBiomes.CHEESE_BIOME)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                TEMPERATURE_GORGONZOLA,
                                                HUMIDITY_FULL,
                                                CONTINENTALNESS_CONTINENT,
                                                EROSION_FULL,
                                                DEPTH_FULL,
                                                WEIRDNESS_LAND_NEGATIVE,
                                                0L
                                        ),
                                        biomes.getOrThrow(ModBiomes.GORGONZOLA_BIOME)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                TEMPERATURE_PLOMBIR,
                                                HUMIDITY_FULL,
                                                CONTINENTALNESS_CONTINENT,
                                                EROSION_FULL,
                                                DEPTH_FULL,
                                                WEIRDNESS_RIVER,
                                                0L
                                        ),
                                        biomes.getOrThrow(ModBiomes.FROZEN_MILK_RIVER)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                TEMPERATURE_RIVER,
                                                HUMIDITY_FULL,
                                                CONTINENTALNESS_CONTINENT,
                                                EROSION_FULL,
                                                DEPTH_FULL,
                                                WEIRDNESS_RIVER,
                                                0L
                                        ),
                                        biomes.getOrThrow(ModBiomes.MILK_RIVER)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                TEMPERATURE_PLOMBIR,
                                                HUMIDITY_FULL,
                                                CONTINENTALNESS_CONTINENT,
                                                EROSION_FULL,
                                                DEPTH_FULL,
                                                WEIRDNESS_LAND_POSITIVE,
                                                0L
                                        ),
                                        biomes.getOrThrow(ModBiomes.PLOMBIR_BIOME)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                TEMPERATURE_CHEESE,
                                                HUMIDITY_FULL,
                                                CONTINENTALNESS_CONTINENT,
                                                EROSION_FULL,
                                                DEPTH_FULL,
                                                WEIRDNESS_LAND_POSITIVE,
                                                0L
                                        ),
                                        biomes.getOrThrow(ModBiomes.CHEESE_BIOME)
                                ),
                                Pair.of(new Climate.ParameterPoint(
                                                TEMPERATURE_GORGONZOLA,
                                                HUMIDITY_FULL,
                                                CONTINENTALNESS_CONTINENT,
                                                EROSION_FULL,
                                                DEPTH_FULL,
                                                WEIRDNESS_LAND_POSITIVE,
                                                0L
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