package com.github.solventj.milkbased.datagen.language;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.item.ModItems;
import com.github.solventj.milkbased.world.dimension.ModDimensions;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnLanguageProvider extends LanguageProvider {
    public ModEnLanguageProvider(PackOutput output) {
        super(output, MilkBased.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addDimension(ModDimensions.MILK_LEVEL_KEY, "Milk Dimension");

        add("fluid_type.milkbased.milk", "Milk");

        addItem(ModItems.MILK_BUCKET, "Milk Bucket");
        addItem(ModItems.CHECHIL, "Chechil");

        addBlock(ModBlocks.CURD_BLOCK, "Curd Block");
        addBlock(ModBlocks.MILKSTONE, "Milkstone");
        addBlock(ModBlocks.COBBLED_MILKSTONE, "Cobbled Milkstone");
        addBlock(ModBlocks.CHEESE_BLOCK, "Cheese Block");
        addBlock(ModBlocks.WHEY_BLOCK, "Whey Block");
        addBlock(ModBlocks.MILK, "Milk");
        addBlock(ModBlocks.MILK_CAULDRON, "Milk Cauldron");
        addBlock(ModBlocks.BLUE_MOLD, "Blue Mold");
        addBlock(ModBlocks.MOLDY_COBBLED_MILKSTONE, "Moldy Cobbled Milkstone");
        addBlock(ModBlocks.CHEESEWOOD_SAPLING, "Cheesewood Sapling");
        addBlock(ModBlocks.CHEESEWOOD_LEAVES, "Cheesewood Leaves");
        addBlock(ModBlocks.CHEESEWOOD_LOG, "Cheesewood Log");
        addBlock(ModBlocks.STRIPPED_CHEESEWOOD_LOG, "Stripped Cheesewood Log");
        addBlock(ModBlocks.CHEESEWOOD, "Cheesewood");
        addBlock(ModBlocks.STRIPPED_CHEESEWOOD, "Stripped Cheesewood");
        addBlock(ModBlocks.CHEESE_PLANKS, "Cheese Planks");
        addBlock(ModBlocks.CHEESE_STAIRS, "Cheese Stairs");
        addBlock(ModBlocks.CHEESE_SLAB, "Cheese Slab");
        addBlock(ModBlocks.CHEESE_FENCE, "Cheese Fence");
        addBlock(ModBlocks.CHEESE_FENCE_GATE, "Cheese Fence Gate");
        addBlock(ModBlocks.CHEESE_DOOR, "Cheese Door");
        addBlock(ModBlocks.CHEESE_TRAPDOOR, "Cheese Trapdoor");
        addBlock(ModBlocks.CHEESE_PRESSURE_PLATE, "Cheese Pressure Plate");
        addBlock(ModBlocks.CHEESE_BUTTON, "Cheese Button");
        addBlock(ModBlocks.CHEESE_SIGN, "Cheese Sign");
        addBlock(ModBlocks.CHEESE_HANGING_SIGN, "Cheese Hanging Sign");
        addBlock(ModBlocks.MILK_PORTAL, "Milk Portal");
        addBlock(ModBlocks.GORGONZOLA, "Gorgonzola");
        addBlock(ModBlocks.GORGONZOLA_TURF, "Gorgonzola Turf");
    }
}
