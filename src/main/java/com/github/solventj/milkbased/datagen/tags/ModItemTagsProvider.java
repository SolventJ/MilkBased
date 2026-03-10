package com.github.solventj.milkbased.datagen.tags;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
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
        copy(ModBlockTags.PLOMBIR_FURNITURE, ModItemTags.PLOMBIR_FURNITURE);

        tag(ItemTags.LOGS).addTag(ModItemTags.CHEESEWOOD_LOGS);
        tag(ItemTags.LOGS).addTag(ModItemTags.PLOMBIR_LOGS);

        tag(ItemTags.LOGS_THAT_BURN).addTag(ModItemTags.CHEESEWOOD_LOGS);

        tag(ItemTags.NON_FLAMMABLE_WOOD)
                .addTag(ModItemTags.PLOMBIR_LOGS)
                .addTag(ModItemTags.PLOMBIR_FURNITURE)
                .add(
                        ModItems.PLOMBIR_SIGN.get(),
                        ModItems.PLOMBIR_HANGING_SIGN.get(),
                        ModItems.PLOMBIR_BOAT.get(),
                        ModItems.PLOMBIR_CHEST_BOAT.get(),
                        ModBlocks.PLOMBIR_SAPLING.asItem()
                );

        tag(ItemTags.PLANKS).add(
                ModBlocks.CHEESE_PLANKS.asItem(),
                ModBlocks.PLOMBIR_PLANKS.asItem()
        );

        tag(ItemTags.WOODEN_STAIRS).add(
                ModBlocks.CHEESE_STAIRS.asItem(),
                ModBlocks.PLOMBIR_STAIRS.asItem()
        );

        tag(ItemTags.WOODEN_SLABS).add(
                ModBlocks.CHEESE_SLAB.asItem(),
                ModBlocks.PLOMBIR_SLAB.asItem()
        );

        tag(ItemTags.WOODEN_FENCES).add(
                ModBlocks.CHEESE_FENCE.asItem(),
                ModBlocks.PLOMBIR_FENCE.asItem()
        );

        tag(ItemTags.FENCE_GATES).add(
                ModBlocks.CHEESE_FENCE_GATE.asItem(),
                ModBlocks.PLOMBIR_FENCE_GATE.asItem()
        );

        tag(ItemTags.WOODEN_DOORS).add(
                ModBlocks.CHEESE_DOOR.asItem(),
                ModBlocks.PLOMBIR_DOOR.asItem()
        );

        tag(ItemTags.WOODEN_TRAPDOORS).add(
                ModBlocks.CHEESE_TRAPDOOR.asItem(),
                ModBlocks.PLOMBIR_TRAPDOOR.asItem()
        );

        tag(ItemTags.WOODEN_PRESSURE_PLATES).add(
                ModBlocks.CHEESE_PRESSURE_PLATE.asItem(),
                ModBlocks.PLOMBIR_PRESSURE_PLATE.asItem()
        );

        tag(ItemTags.WOODEN_BUTTONS).add(
                ModBlocks.CHEESE_BUTTON.asItem(),
                ModBlocks.PLOMBIR_BUTTON.asItem()
        );

        tag(ItemTags.SIGNS).add(
                ModBlocks.CHEESE_SIGN.asItem(),
                ModBlocks.PLOMBIR_SIGN.asItem()
        );

        tag(ItemTags.HANGING_SIGNS).add(
                ModBlocks.CHEESE_HANGING_SIGN.asItem(),
                ModBlocks.PLOMBIR_HANGING_SIGN.asItem()
        );

        tag(ItemTags.BOATS).add(
                ModItems.CHEESE_BOAT.get(),
                ModItems.PLOMBIR_BOAT.get()
        );

        tag(ItemTags.CHEST_BOATS).add(
                ModItems.CHEESE_CHEST_BOAT.get(),
                ModItems.PLOMBIR_CHEST_BOAT.get()
        );

        tag(ItemTags.SAPLINGS).add(
                ModBlocks.CHEESEWOOD_SAPLING.asItem(),
                ModBlocks.PLOMBIR_SAPLING.asItem()
        );

        tag(ItemTags.LEAVES).add(
                ModBlocks.CHEESEWOOD_LEAVES.asItem(),
                ModBlocks.PLOMBIR_LEAVES.asItem()
        );


        tag(ItemTags.STONE_TOOL_MATERIALS).add(ModBlocks.COBBLED_MILKSTONE.asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(ModBlocks.COBBLED_MILKSTONE.asItem());
    }
}
