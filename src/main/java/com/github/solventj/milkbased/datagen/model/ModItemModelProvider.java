package com.github.solventj.milkbased.datagen.model;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MilkBased.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.MILK_BUCKET.get());
        basicItem(ModItems.CHEESE_SIGN.get());
        basicItem(ModItems.CHEESE_HANGING_SIGN.get());
        basicItem(ModItems.PLOMBIR_SIGN.get());
        basicItem(ModItems.PLOMBIR_HANGING_SIGN.get());
        basicItem(ModItems.CHECHIL.get());
        basicItem(ModItems.SCOOP_OF_PLOMBIR.get());

        basicItem(ModItems.CHEESE_BOAT.get());
        basicItem(ModItems.CHEESE_CHEST_BOAT.get());
        basicItem(ModItems.PLOMBIR_BOAT.get());
        basicItem(ModItems.PLOMBIR_CHEST_BOAT.get());

        simpleBlockItem(ModBlocks.CURD_BLOCK.get());
        simpleBlockItem(ModBlocks.MILKSTONE.get());
        simpleBlockItem(ModBlocks.COBBLED_MILKSTONE.get());
        simpleBlockItem(ModBlocks.CHEESE_BLOCK.get());
        simpleBlockItem(ModBlocks.WHEY_BLOCK.get());

        flatBlockItem(ModBlocks.BLUE_MOLD.get());
        simpleBlockItem(ModBlocks.MOLDY_COBBLED_MILKSTONE.get());

        flatBlockItem(ModBlocks.CHEESEWOOD_SAPLING.get());
        simpleBlockItem(ModBlocks.CHEESEWOOD_LEAVES.get());
        simpleBlockItem(ModBlocks.CHEESEWOOD_LOG.get());
        simpleBlockItem(ModBlocks.STRIPPED_CHEESEWOOD_LOG.get());
        simpleBlockItem(ModBlocks.CHEESEWOOD.get());
        simpleBlockItem(ModBlocks.STRIPPED_CHEESEWOOD.get());

        simpleBlockItem(ModBlocks.CHEESE_PLANKS.get());
        simpleBlockItem(ModBlocks.CHEESE_STAIRS.get());
        simpleBlockItem(ModBlocks.CHEESE_SLAB.get());
        basicFence(ModBlocks.CHEESE_FENCE.get(), ModBlocks.CHEESE_PLANKS.get());
        simpleBlockItem(ModBlocks.CHEESE_FENCE_GATE.get());
        basicItem(ModBlocks.CHEESE_DOOR.asItem());
        basicTrapdoor(ModBlocks.CHEESE_TRAPDOOR.get());
        simpleBlockItem(ModBlocks.CHEESE_PRESSURE_PLATE.get());
        basicButton(ModBlocks.CHEESE_BUTTON.get(), ModBlocks.CHEESE_PLANKS.get());

        simpleBlockItem(ModBlocks.GORGONZOLA.get());
        simpleBlockItem(ModBlocks.GORGONZOLA_TURF.get());

        simpleBlockItem(ModBlocks.PLOMBIR_SNOW_BLOCK.get());
        withExistingParent(blockKey(ModBlocks.PLOMBIR_SNOW.get()).getPath(), modLoc("block/plombir_snow_height2"));

        flatBlockItem(ModBlocks.PLOMBIR_SAPLING.get());
        simpleBlockItem(ModBlocks.PLOMBIR_LEAVES.get());
        simpleBlockItem(ModBlocks.PLOMBIR_LOG.get());
        simpleBlockItem(ModBlocks.STRIPPED_PLOMBIR_LOG.get());
        simpleBlockItem(ModBlocks.PLOMBIR_WOOD.get());
        simpleBlockItem(ModBlocks.STRIPPED_PLOMBIR_WOOD.get());

        simpleBlockItem(ModBlocks.PLOMBIR_PLANKS.get());
        simpleBlockItem(ModBlocks.PLOMBIR_STAIRS.get());
        simpleBlockItem(ModBlocks.PLOMBIR_SLAB.get());
        basicFence(ModBlocks.PLOMBIR_FENCE.get(), ModBlocks.PLOMBIR_PLANKS.get());
        simpleBlockItem(ModBlocks.PLOMBIR_FENCE_GATE.get());
        basicItem(ModBlocks.PLOMBIR_DOOR.asItem());
        basicTrapdoor(ModBlocks.PLOMBIR_TRAPDOOR.get());
        simpleBlockItem(ModBlocks.PLOMBIR_PRESSURE_PLATE.get());
        basicButton(ModBlocks.PLOMBIR_BUTTON.get(), ModBlocks.PLOMBIR_PLANKS.get());
    }

    private void flatBlockItem(Block block) {
        singleTexture(blockKey(block).toString(), mcLoc("item/generated"),
                "layer0", blockTexture(block));
    }

    private void basicFence(FenceBlock block, Block reference) {
        fenceInventory(blockKey(block).toString(), blockTexture(reference));
    }

    private void basicButton(ButtonBlock block, Block reference) {
        buttonInventory(blockKey(block).toString(), blockTexture(reference));
    }

    private void basicTrapdoor(TrapDoorBlock block) {
        withExistingParent(blockKey(block).toString(), blockTexture(block).withSuffix("_bottom"));
    }

    private ResourceLocation blockKey(Block block) {
        return Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block));
    }

    private ResourceLocation blockTexture(Block block) {
        return modLoc("block/" + blockKey(block).getPath());
    }
}
