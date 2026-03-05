package com.github.solventj.milkbased.datagen.tags;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(
            PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, blockTags, MilkBased.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy(ModBlockTags.CHEESEWOOD_LOGS, ModItemTags.CHEESEWOOD_LOGS);
        copy(ModBlockTags.PLOMBIR_LOGS, ModItemTags.PLOMBIR_LOGS);
        tag(ItemTags.LOGS).addTag(ModItemTags.CHEESEWOOD_LOGS);
        tag(ItemTags.LOGS).addTag(ModItemTags.PLOMBIR_LOGS);

        tag(ItemTags.STONE_TOOL_MATERIALS).add(ModBlocks.COBBLED_MILKSTONE.asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(ModBlocks.COBBLED_MILKSTONE.asItem());
        tag(ItemTags.PLANKS).add(
                ModBlocks.CHEESE_PLANKS.asItem(),
                ModBlocks.PLOMBIR_PLANKS.asItem()
        );
        tag(ItemTags.WOODEN_SLABS).add(
                ModBlocks.CHEESE_SLAB.asItem(),
                ModBlocks.PLOMBIR_SLAB.asItem()
        );
    }
}
