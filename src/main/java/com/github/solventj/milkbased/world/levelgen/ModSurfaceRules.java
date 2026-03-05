package com.github.solventj.milkbased.world.levelgen;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.world.biome.ModBiomes;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModSurfaceRules {
    public static SurfaceRules.RuleSource milk() {
        SurfaceRules.ConditionSource bedrock_floor = SurfaceRules.verticalGradient(
                "minecraft:bedrock_floor",
                VerticalAnchor.bottom(),
                VerticalAnchor.aboveBottom(5));

        SurfaceRules.ConditionSource deepslate = SurfaceRules.verticalGradient(
                "minecraft:deepslate",
                VerticalAnchor.absolute(0),
                VerticalAnchor.absolute(8));

        SurfaceRules.RuleSource topBiomeBlocks = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.CHEESE_BIOME),
                        makeStateRule(ModBlocks.CHEESE_BLOCK.get())
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.PLOMBIR_BIOME),
                        makeStateRule(ModBlocks.PLOMBIR_SNOW_BLOCK.get())
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.GORGONZOLA_BIOME),
                        makeStateRule(ModBlocks.GORGONZOLA_TURF.get())
                )
        );

        SurfaceRules.RuleSource dirtBiomeBlocks = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.CHEESE_BIOME),
                        makeStateRule(ModBlocks.CURD_BLOCK.get())
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.PLOMBIR_BIOME),
                        makeStateRule(ModBlocks.PLOMBIR_SNOW_BLOCK.get())
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ModBiomes.GORGONZOLA_BIOME),
                        makeStateRule(ModBlocks.GORGONZOLA.get())
                )
        );

        SurfaceRules.RuleSource surface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                        SurfaceRules.ifTrue(
                                SurfaceRules.waterBlockCheck(-1, 0),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.waterBlockCheck(0, 0),
                                                topBiomeBlocks
                                        ), makeStateRule(Blocks.RED_CONCRETE)
                                )
                        )
                ),

                SurfaceRules.ifTrue(
                        SurfaceRules.waterBlockCheck(-6, -1),
                        SurfaceRules.ifTrue(
                                SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                                dirtBiomeBlocks
                        )
                ),

                SurfaceRules.ifTrue(
                        SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                        makeStateRule(Blocks.YELLOW_CONCRETE)
                )
        );

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        builder.add(SurfaceRules.ifTrue(bedrock_floor, makeStateRule(Blocks.BEDROCK)));
        builder.add(SurfaceRules.ifTrue(deepslate, makeStateRule(Blocks.DEEPSLATE)));
        builder.add(SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), surface));

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
