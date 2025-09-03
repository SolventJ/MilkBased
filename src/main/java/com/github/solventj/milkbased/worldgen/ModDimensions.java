package com.github.solventj.milkbased.worldgen;

import com.github.solventj.milkbased.MilkBased;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {
    public static final DeferredRegister<LevelStem> LEVEL_STEMS = DeferredRegister.create(Registries.LEVEL_STEM, MilkBased.MOD_ID);
    public static final DeferredRegister<Level> LEVEL = DeferredRegister.create(Registries.DIMENSION, MilkBased.MOD_ID);
    public static final DeferredRegister<DimensionType> DIMENSION_TYPES = DeferredRegister.create(Registries.DIMENSION_TYPE, MilkBased.MOD_ID);

    // Ключи ресурсов для вашего измерения
    public static final ResourceKey<LevelStem> EXAMPLE_DIM_STEM = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "example_dim"));
    public static final ResourceKey<Level> EXAMPLE_DIM = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "example_dim"));
    public static final ResourceKey<DimensionType> EXAMPLE_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "example_dim"));

    // Регистрация DimensionType
    public static final DeferredHolder<DimensionType, DimensionType> EXAMPLE_DIM_TYPE_REG = DIMENSION_TYPES.register("example_dim", () ->
            new DimensionType(
                    OptionalLong.empty(), // fixedTime - пусто, чтобы был нормальный цикл дня и ночи
                    false, // hasSkylight
                    false, // hasCeiling
                    false, // ultraWarm
                    true,  // natural
                    1.0,   // coordinateScale
                    true,  // bedWorks
                    false, // respawnAnchorWorks
                    0,     // minY
                    256,   // height
                    256,   // logicalHeight
                    BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                    BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                    0.1f,  // ambientLight - низкая освещенность для атмосферности
                    new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 0)
            )
    );

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(Biomes.THE_VOID)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.THE_VOID)),
                                Pair.of(
                                        Climate.parameters(0.1F, 0.2F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.BIRCH_FOREST)),
                                Pair.of(
                                        Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.OCEAN)),
                                Pair.of(
                                        Climate.parameters(0.4F, 0.3F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.DARK_FOREST))

                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.EXAMPLE_DIM_TYPE), wrappedChunkGenerator);

        context.register(EXAMPLE_DIM_STEM, stem);
    }

    public static void register(IEventBus modEventBus) {
        LEVEL_STEMS.register(modEventBus);
        LEVEL.register(modEventBus);
        DIMENSION_TYPES.register(modEventBus);
    }
}