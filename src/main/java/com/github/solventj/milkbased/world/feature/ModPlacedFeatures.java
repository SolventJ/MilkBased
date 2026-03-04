package com.github.solventj.milkbased.world.feature;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.placement.*;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> CHEESEWOOD_TREE =
            registerKey("cheesewood_tree_placed");

    public static final ResourceKey<PlacedFeature> PLOMBIR_TREE =
            registerKey("plombir_tree_placed");

    public static final ResourceKey<PlacedFeature> BLUE_MOLD =
            registerKey("blue_mold_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, CHEESEWOOD_TREE,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.CHEESEWOOD_TREE),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(2, 0.1f, 2),
                        ModBlocks.CHEESEWOOD_SAPLING.get()));

        PlacementUtils.register(context, PLOMBIR_TREE,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PLOMBIR_TREE),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(2, 0.1f, 2),
                        ModBlocks.PLOMBIR_SAPLING.get()));

        PlacementUtils.register(context, BLUE_MOLD, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUE_MOLD),
                CountPlacement.of(UniformInt.of(104, 157)),
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, InSquarePlacement.spread(),
                BiomeFilter.biome());
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}
