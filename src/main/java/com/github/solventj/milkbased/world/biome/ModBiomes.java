package com.github.solventj.milkbased.world.biome;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.world.feature.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {
    public static final ResourceKey<Biome> CHEESE_BIOME = registerKey("cheese_biome");
    public static final ResourceKey<Biome> PLOMBIR_BIOME = registerKey("plombir_biome");
    public static final ResourceKey<Biome> GORGONZOLA_BIOME = registerKey("gorgonzola_biome");
    public static final ResourceKey<Biome> MILK_OCEAN = registerKey("milk_ocean");
    public static final ResourceKey<Biome> MILK_RIVER = registerKey("milk_river");
    public static final ResourceKey<Biome> FROZEN_MILK_RIVER = registerKey("frozen_milk_river");

    public static void bootstrap(BootstrapContext<Biome> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        context.register(CHEESE_BIOME, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8f)
                .downfall(0.5f)
                .specialEffects(milkDimBiomeEffects())
                .mobSpawnSettings(new MobSpawnSettings.Builder().addSpawn(MobCategory.CREATURE,
                        new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 4, 4))
                        .build())
                .generationSettings(new BiomeGenerationSettings.PlainBuilder()
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                placedFeatures.getOrThrow(ModPlacedFeatures.CHEESEWOOD_TREE))
                        .build())
                .build());

        context.register(PLOMBIR_BIOME, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(-10.0f)
                .downfall(0.5f)
                .specialEffects(milkDimBiomeEffects())
                .mobSpawnSettings(MobSpawnSettings.EMPTY)
                .generationSettings(new BiomeGenerationSettings.PlainBuilder()
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                placedFeatures.getOrThrow(ModPlacedFeatures.PLOMBIR_TREE))
                        .build())
                .build());

        context.register(GORGONZOLA_BIOME, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8f)
                .downfall(0.5f)
                .specialEffects(milkDimBiomeEffects())
                .mobSpawnSettings(new MobSpawnSettings.Builder().addSpawn(MobCategory.CREATURE,
                        new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 4, 4))
                        .build())
                .generationSettings(new BiomeGenerationSettings.PlainBuilder()
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                placedFeatures.getOrThrow(ModPlacedFeatures.CHEESEWOOD_TREE))
                        .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                placedFeatures.getOrThrow(ModPlacedFeatures.BLUE_MOLD))
                        .build())
                .build());

        context.register(MILK_OCEAN, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8f)
                .downfall(0.5f)
                .specialEffects(milkDimBiomeEffects())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .generationSettings(new BiomeGenerationSettings.PlainBuilder()
                        .build())
                .build());

        context.register(MILK_RIVER, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8f)
                .downfall(0.5f)
                .specialEffects(milkDimBiomeEffects())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .generationSettings(new BiomeGenerationSettings.PlainBuilder()
                        .build())
                .build());

        context.register(FROZEN_MILK_RIVER, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(-10.0f)
                .downfall(0.5f)
                .specialEffects(milkDimBiomeEffects())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .generationSettings(new BiomeGenerationSettings.PlainBuilder()
                        .build())
                .build());
    }

    private static BiomeSpecialEffects milkDimBiomeEffects() {
        return (new BiomeSpecialEffects.Builder())
                .waterColor(4170212)
                .waterFogColor(602708)
                .fogColor(12638463)
                .skyColor(8364543)
                .grassColorOverride(5622079)
                .foliageColorOverride(2865935)
                .build();
    }

    public static ResourceKey<Biome> registerKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}
