package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
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
        otherWhenNotSilkTouch(ModBlocks.MILKSTONE.get(), ModBlocks.COBBLED_MILKSTONE.get());
        dropSelf(ModBlocks.COBBLED_MILKSTONE.get());
        dropSelf(ModBlocks.CHEESE_BLOCK.get());
        dropSelf(ModBlocks.WHEY_BLOCK.get());

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

        dropSelf(ModBlocks.GORGONZOLA.get());
        otherWhenNotSilkTouch(ModBlocks.GORGONZOLA_TURF.get(), ModBlocks.GORGONZOLA.get());

        add(ModBlocks.PLOMBIR_SNOW_BLOCK.get(), block ->
                createSingleItemTableWithSilkTouch(block, ModItems.SCOOP_OF_PLOMBIR, ConstantValue.exactly(4.0F)));
        dropSelf(ModBlocks.PLOMBIR_SNOW.get());

        add(ModBlocks.PLOMBIR_SNOW.get(), block -> LootTable.lootTable().withPool(LootPool.lootPool()
                .when(LootItemEntityPropertyCondition.entityPresent(LootContext.EntityTarget.THIS))
                .add(AlternativesEntry.alternatives(
                        AlternativesEntry.alternatives(SnowLayerBlock.LAYERS.getPossibleValues(),
                                integer -> LootItem.lootTableItem(ModItems.SCOOP_OF_PLOMBIR)
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(
                                                        StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(SnowLayerBlock.LAYERS, integer)
                                                )
                                        ).apply(SetItemCountFunction.setCount(ConstantValue.exactly(integer)))
                        ).when(this.doesNotHaveSilkTouch()),
                        AlternativesEntry.alternatives(SnowLayerBlock.LAYERS.getPossibleValues(),
                                integer -> integer == 8
                                        ? LootItem.lootTableItem(ModBlocks.PLOMBIR_SNOW_BLOCK)
                                        : LootItem.lootTableItem(ModBlocks.PLOMBIR_SNOW)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(integer)))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(SnowLayerBlock.LAYERS, integer)
                                                )
                                        )
                        )
                ))));

        dropSelf(ModBlocks.PLOMBIR_SAPLING.get());
        add(ModBlocks.PLOMBIR_LEAVES.get(), leaves -> this.createLeavesDrops(
                leaves, ModBlocks.PLOMBIR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.PLOMBIR_LOG.get());
        dropSelf(ModBlocks.STRIPPED_PLOMBIR_LOG.get());
        dropSelf(ModBlocks.PLOMBIR_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_PLOMBIR_WOOD.get());

        dropSelf(ModBlocks.PLOMBIR_PLANKS.get());
        dropSelf(ModBlocks.PLOMBIR_STAIRS.get());
        dropSelf(ModBlocks.PLOMBIR_SLAB.get());
        dropSelf(ModBlocks.PLOMBIR_FENCE.get());
        dropSelf(ModBlocks.PLOMBIR_FENCE_GATE.get());
        add(ModBlocks.PLOMBIR_DOOR.get(), this::createDoorTable);
        dropSelf(ModBlocks.PLOMBIR_TRAPDOOR.get());
        dropSelf(ModBlocks.PLOMBIR_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PLOMBIR_BUTTON.get());

        dropOther(ModBlocks.PLOMBIR_SIGN.get(), ModItems.PLOMBIR_SIGN.get());
        dropOther(ModBlocks.PLOMBIR_WALL_SIGN.get(), ModItems.PLOMBIR_SIGN.get());
        dropOther(ModBlocks.PLOMBIR_HANGING_SIGN.get(), ModItems.PLOMBIR_HANGING_SIGN.get());
        dropOther(ModBlocks.PLOMBIR_WALL_HANGING_SIGN.get(), ModItems.PLOMBIR_HANGING_SIGN.get());
    }

    private void otherWhenNotSilkTouch(Block self, Block other) {
        add(self, createSingleItemTableWithSilkTouch(self, other));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(holder -> (Block) holder.get())
                .collect(Collectors.toList());
    }
}
