package com.github.solventj.milkbased.world.levelgen;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import java.util.List;

public class ModNoiseGeneratorSettings {
    public static final ResourceKey<NoiseGeneratorSettings> MILK_SETTINGS = registerKey("milk_settings");

//    public static SurfaceRules RULES = SurfaceRules.sequence(
//            SurfaceRules.stoneDepthCheck(1, false, CaveSurface.FLOOR));

    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        var m = NoiseGeneratorSettings.overworld(context, false, false);

        context.register(MILK_SETTINGS, new NoiseGeneratorSettings(
                NoiseSettings.create(-64, 384, 1, 2),
                ModBlocks.MILKSTONE.get().defaultBlockState(),
                ModBlocks.MILK.get().defaultBlockState(),
                m.noiseRouter(),
                SurfaceRuleData.overworld(),
                List.of(),
                63,
                false,
                true,
                true,
                false
        ));
    }

    public static ResourceKey<NoiseGeneratorSettings> registerKey(String name) {
        return ResourceKey.create(Registries.NOISE_SETTINGS,
                ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}
