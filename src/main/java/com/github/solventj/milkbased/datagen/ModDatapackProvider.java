package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.world.dimension.ModDimensionTypes;
import com.github.solventj.milkbased.world.dimension.ModDimensions;
import com.github.solventj.milkbased.world.biome.ModBiomes;
import com.github.solventj.milkbased.world.feature.ModConfiguredFeatures;
import com.github.solventj.milkbased.world.feature.ModPlacedFeatures;
import com.github.solventj.milkbased.world.levelgen.ModNoiseGeneratorSettings;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.DIMENSION_TYPE, ModDimensionTypes::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.NOISE_SETTINGS, ModNoiseGeneratorSettings::bootstrap);

    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MilkBased.MOD_ID));
    }
}
