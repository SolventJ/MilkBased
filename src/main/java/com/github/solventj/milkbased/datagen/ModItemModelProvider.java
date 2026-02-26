package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MilkBased.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(ModBlocks.CURD_BLOCK.get());
        simpleBlockItem(ModBlocks.CHEESE_BLOCK.get());

        simpleBlockItem(ModBlocks.CHEESE_LOG.get());
        simpleBlockItem(ModBlocks.STRIPPED_CHEESE_LOG.get());
        simpleBlockItem(ModBlocks.CHEESE_LEAVES.get());
        flatItemFromBlockTexture(ModBlocks.CHEESE_SAPLING.getId());
        simpleBlockItem(ModBlocks.CHEESE_PLANKS.get());

        flatItemFromBlockTexture(ModBlocks.BLUE_MOLD.getId());
    }

    private void flatItemFromBlockTexture(ResourceLocation block) {
        this.getBuilder(block.toString()).parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(
                        block.getNamespace(), "block/" + block.getPath()));
    }
}
