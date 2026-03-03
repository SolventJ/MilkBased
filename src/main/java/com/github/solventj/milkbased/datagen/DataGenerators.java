package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.datagen.tags.ModBlockTagsProvider;
import com.github.solventj.milkbased.datagen.tags.ModFluidTagsProvider;
import com.github.solventj.milkbased.datagen.tags.ModItemTagsProvider;
import com.github.solventj.milkbased.datagen.language.ModEnLanguageProvider;
import com.github.solventj.milkbased.datagen.language.ModRuLanguageProvider;
import com.github.solventj.milkbased.datagen.model.ModBlockStateProvider;
import com.github.solventj.milkbased.datagen.model.ModItemModelProvider;
import com.github.solventj.milkbased.particle.ModParticleProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = MilkBased.MOD_ID)
public class DataGenerators {
    @SubscribeEvent
    private static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ModParticleProvider(packOutput, fileHelper));

        generator.addProvider(event.includeClient(), new ModEnLanguageProvider(packOutput));
        generator.addProvider(event.includeClient(), new ModRuLanguageProvider(packOutput));

        var blockTagsProvider = new ModBlockTagsProvider(packOutput, lookupProvider, fileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);

        generator.addProvider(event.includeServer(), new ModItemTagsProvider(
                packOutput, lookupProvider, blockTagsProvider.contentsGetter(), fileHelper));
        generator.addProvider(event.includeServer(), new ModFluidTagsProvider(packOutput, lookupProvider, fileHelper));

        generator.addProvider(event.includeServer(), new ModDatapackProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new LootTableProvider(
                packOutput,
                Collections.emptySet(),
                List.of(
                        new LootTableProvider.SubProviderEntry(
                                ModBlockLootSubProvider::new,
                                LootContextParamSets.BLOCK
                        )
                ),
                lookupProvider
        ));

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));
    }
}
