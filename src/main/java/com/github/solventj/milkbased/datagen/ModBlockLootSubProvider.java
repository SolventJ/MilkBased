package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    public ModBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.CURD_BLOCK.get());
        add(ModBlocks.MILKSTONE.get(), block -> createSingleItemTableWithSilkTouch(
                block, ModBlocks.COBBLED_MILKSTONE.get()));
        dropSelf(ModBlocks.COBBLED_MILKSTONE.get());
        dropSelf(ModBlocks.CHEESE_BLOCK.get());

        dropOther(ModBlocks.MILK_CAULDRON.get(), Blocks.CAULDRON);

        dropSelf(ModBlocks.BLUE_MOLD.get());
        dropSelf(ModBlocks.MOLDY_COBBLED_MILKSTONE.get());

        dropSelf(ModBlocks.CHEESEWOOD_SAPLING.get());
        add(ModBlocks.CHEESEWOOD_LEAVES.get(), leaves -> this.createLeavesDrops(
                leaves, ModBlocks.CHEESEWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.CHEESEWOOD_LOG.get());
        dropSelf(ModBlocks.STRIPPED_CHEESEWOOD_LOG.get());
        dropSelf(ModBlocks.CHEESEWOOD.get());
        dropSelf(ModBlocks.STRIPPED_CHEESEWOOD.get());

        dropSelf(ModBlocks.CHEESE_PLANKS.get());
        dropSelf(ModBlocks.CHEESE_STAIRS.get());
        dropSelf(ModBlocks.CHEESE_SLAB.get());
        dropSelf(ModBlocks.CHEESE_FENCE.get());
        dropSelf(ModBlocks.CHEESE_FENCE_GATE.get());
        add(ModBlocks.CHEESE_DOOR.get(), this::createDoorTable);
        dropSelf(ModBlocks.CHEESE_TRAPDOOR.get());
        dropSelf(ModBlocks.CHEESE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.CHEESE_BUTTON.get());

        dropOther(ModBlocks.CHEESE_SIGN.get(), ModItems.CHEESE_SIGN.get());
        dropOther(ModBlocks.CHEESE_WALL_SIGN.get(), ModItems.CHEESE_SIGN.get());
        dropOther(ModBlocks.CHEESE_HANGING_SIGN.get(), ModItems.CHEESE_HANGING_SIGN.get());
        dropOther(ModBlocks.CHEESE_WALL_HANGING_SIGN.get(), ModItems.CHEESE_HANGING_SIGN.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(holder -> (Block) holder.get())
                .collect(Collectors.toList());
    }
}
