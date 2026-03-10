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
        addItem(ModItems.SCOOP_OF_PLOMBIR, "Scoop of Plombir");
        addItem(ModItems.CHEESE_BOAT, "Cheese Boat");
        addItem(ModItems.CHEESE_CHEST_BOAT, "Cheese Boat with Chest");
        addItem(ModItems.PLOMBIR_BOAT, "Plombir Boat");
        addItem(ModItems.PLOMBIR_CHEST_BOAT, "Plombir Boat with Chest");

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

        addBlock(ModBlocks.PLOMBIR_SNOW_BLOCK, "Plombir Snow Block");
        addBlock(ModBlocks.PLOMBIR_SNOW, "Plombir Snow");

        addBlock(ModBlocks.PLOMBIR_SAPLING, "Plombir Sapling");
        addBlock(ModBlocks.PLOMBIR_LEAVES, "Plombir Leaves");
        addBlock(ModBlocks.PLOMBIR_LOG, "Plombir Log");
        addBlock(ModBlocks.STRIPPED_PLOMBIR_LOG, "Stripped Plombir Log");
        addBlock(ModBlocks.PLOMBIR_WOOD, "Plombir Wood");
        addBlock(ModBlocks.STRIPPED_PLOMBIR_WOOD, "Stripped Plombir Wood");
        addBlock(ModBlocks.PLOMBIR_PLANKS, "Plombir Planks");
        addBlock(ModBlocks.PLOMBIR_STAIRS, "Plombir Stairs");
        addBlock(ModBlocks.PLOMBIR_SLAB, "Plombir Slab");
        addBlock(ModBlocks.PLOMBIR_FENCE, "Plombir Fence");
        addBlock(ModBlocks.PLOMBIR_FENCE_GATE, "Plombir Fence Gate");
        addBlock(ModBlocks.PLOMBIR_DOOR, "Plombir Door");
        addBlock(ModBlocks.PLOMBIR_TRAPDOOR, "Plombir Trapdoor");
        addBlock(ModBlocks.PLOMBIR_PRESSURE_PLATE, "Plombir Pressure Plate");
        addBlock(ModBlocks.PLOMBIR_BUTTON, "Plombir Button");
        addBlock(ModBlocks.PLOMBIR_SIGN, "Plombir Sign");
        addBlock(ModBlocks.PLOMBIR_HANGING_SIGN, "Plombir Hanging Sign");

        addBlock(ModBlocks.MILK_PORTAL, "Milk Portal");
        addBlock(ModBlocks.GORGONZOLA, "Gorgonzola");
        addBlock(ModBlocks.GORGONZOLA_TURF, "Gorgonzola Turf");
    }
}
