package com.github.solventj.milkbased;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.datagen.ModBlockStateProvider;
import com.github.solventj.milkbased.datagen.ModBlockTagsProvider;
import com.github.solventj.milkbased.datagen.ModDatapackProvider;
import com.github.solventj.milkbased.datagen.ModItemModelProvider;
import com.github.solventj.milkbased.fluid.ModFluidTypes;
import com.github.solventj.milkbased.fluid.ModFluids;
import com.github.solventj.milkbased.item.ModCreativeModeTabs;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MilkBased.MOD_ID)
public class MilkBased {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "milkbased";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public MilkBased(IEventBus eventBus) {
        eventBus.addListener(this::gatherData);

        ModFluids.register(eventBus);
        ModFluidTypes.register(eventBus);

        ModCreativeModeTabs.register(eventBus);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(),
                new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModDatapackProvider(packOutput, lookupProvider));
    }
}
