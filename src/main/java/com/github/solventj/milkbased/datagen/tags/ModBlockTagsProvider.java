package com.github.solventj.milkbased.datagen.tags;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MilkBased.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ModBlockTags.CHEESEWOOD_LOGS).add(
                ModBlocks.CHEESEWOOD_LOG.get(),
                ModBlocks.STRIPPED_CHEESEWOOD_LOG.get(),
                ModBlocks.CHEESEWOOD.get(),
                ModBlocks.STRIPPED_CHEESEWOOD.get()
        );

        tag(ModBlockTags.CHEESEWOOD_GROWABLE_ON).add(
                ModBlocks.CHEESE_BLOCK.get(),
                ModBlocks.GORGONZOLA.get(),
                ModBlocks.GORGONZOLA_TURF.get()
        );

        tag(ModBlockTags.PLOMBIR_LOGS).add(
                ModBlocks.PLOMBIR_LOG.get(),
                ModBlocks.STRIPPED_PLOMBIR_LOG.get(),
                ModBlocks.PLOMBIR_WOOD.get(),
                ModBlocks.STRIPPED_PLOMBIR_WOOD.get()
        );

        tag(BlockTags.LOGS).addTag(ModBlockTags.CHEESEWOOD_LOGS);
        tag(BlockTags.LOGS).addTag(ModBlockTags.PLOMBIR_LOGS);

        tag(ModBlockTags.PLOMBIR_FURNITURE).add(
                ModBlocks.PLOMBIR_PLANKS.get(),
                ModBlocks.PLOMBIR_STAIRS.get(),
                ModBlocks.PLOMBIR_SLAB.get(),
                ModBlocks.PLOMBIR_FENCE.get(),
                ModBlocks.PLOMBIR_FENCE_GATE.get(),
                ModBlocks.PLOMBIR_DOOR.get(),
                ModBlocks.PLOMBIR_TRAPDOOR.get(),
                ModBlocks.PLOMBIR_PRESSURE_PLATE.get(),
                ModBlocks.PLOMBIR_BUTTON.get()
        );

        tag(BlockTags.MINEABLE_WITH_AXE).add(
                ModBlocks.CHEESE_PLANKS.get(),
                ModBlocks.CHEESE_STAIRS.get(),
                ModBlocks.CHEESE_SLAB.get(),
                ModBlocks.CHEESE_FENCE.get(),
                ModBlocks.CHEESE_FENCE_GATE.get(),
                ModBlocks.CHEESE_DOOR.get(),
                ModBlocks.CHEESE_TRAPDOOR.get(),
                ModBlocks.CHEESE_PRESSURE_PLATE.get(),
                ModBlocks.CHEESE_BUTTON.get()
        ).addTag(ModBlockTags.PLOMBIR_FURNITURE);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.MILKSTONE.get(),
                ModBlocks.COBBLED_MILKSTONE.get(),
                ModBlocks.MOLDY_COBBLED_MILKSTONE.get()
        );

        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                ModBlocks.CHEESE_BLOCK.get(),
                ModBlocks.CURD_BLOCK.get(),
                ModBlocks.GORGONZOLA.get(),
                ModBlocks.GORGONZOLA_TURF.get(),
                ModBlocks.PLOMBIR_SNOW_BLOCK.get(),
                ModBlocks.PLOMBIR_SNOW.get()
        ).addTag(ModBlockTags.PLOMBIR_FURNITURE);

        tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(
                ModBlocks.CHEESE_BLOCK.get(),
                ModBlocks.GORGONZOLA_TURF.get()
        );
    }
}
