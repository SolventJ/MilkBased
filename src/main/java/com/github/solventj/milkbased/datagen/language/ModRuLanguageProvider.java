package com.github.solventj.milkbased.datagen.language;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.item.ModItems;
import com.github.solventj.milkbased.world.dimension.ModDimensions;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModRuLanguageProvider extends LanguageProvider {
    public ModRuLanguageProvider(PackOutput output) {
        super(output, MilkBased.MOD_ID, "ru_ru");
    }

    @Override
    protected void addTranslations() {
        addDimension(ModDimensions.MILK_LEVEL_KEY, "Молочное измерение");

        add("fluid_type.milkbased.milk", "Молоко");

        addItem(ModItems.MILK_BUCKET, "Ведро молока");
        addItem(ModItems.CHECHIL, "Чечил");
        addItem(ModItems.SCOOP_OF_PLOMBIR, "Шарик пломбира");
        addItem(ModItems.CHEESE_BOAT, "Сырная лодка");
        addItem(ModItems.CHEESE_CHEST_BOAT, "Сырная лодка с сундуком");
        addItem(ModItems.PLOMBIR_BOAT, "Пломбирная лодка");
        addItem(ModItems.PLOMBIR_CHEST_BOAT, "Пломбирная лодка с сундуком");

        addBlock(ModBlocks.CURD_BLOCK, "Блок творога");
        addBlock(ModBlocks.MILKSTONE, "Молокамень");
        addBlock(ModBlocks.COBBLED_MILKSTONE, "Колотый молокамень");
        addBlock(ModBlocks.CHEESE_BLOCK, "Блок сыра");
        addBlock(ModBlocks.WHEY_BLOCK, "Блок молочной сыворотки");
        addBlock(ModBlocks.MILK, "Молоко");
        addBlock(ModBlocks.MILK_CAULDRON, "Котёл с молоком");
        addBlock(ModBlocks.BLUE_MOLD, "Синяя плесень");
        addBlock(ModBlocks.MOLDY_COBBLED_MILKSTONE, "Плесневелый Колотый молокамень");
        addBlock(ModBlocks.CHEESEWOOD_SAPLING, "Саженец сырного дерева");
        addBlock(ModBlocks.CHEESEWOOD_LEAVES, "Листва сырного дерева");
        addBlock(ModBlocks.CHEESEWOOD_LOG, "Бревно сырного дерева");
        addBlock(ModBlocks.STRIPPED_CHEESEWOOD_LOG, "Обтёсанное бревно сырного дерева");
        addBlock(ModBlocks.CHEESEWOOD, "Сырная древесина");
        addBlock(ModBlocks.STRIPPED_CHEESEWOOD, "Обтёсанная сырная древесина");
        addBlock(ModBlocks.CHEESE_PLANKS, "Сырный доски");
        addBlock(ModBlocks.CHEESE_STAIRS, "Сырные ступени");
        addBlock(ModBlocks.CHEESE_SLAB, "Сырная плита");
        addBlock(ModBlocks.CHEESE_FENCE, "Сырный забор");
        addBlock(ModBlocks.CHEESE_FENCE_GATE, "Сырная калитка");
        addBlock(ModBlocks.CHEESE_DOOR, "Сырная дверь");
        addBlock(ModBlocks.CHEESE_TRAPDOOR, "Сырный люк");
        addBlock(ModBlocks.CHEESE_PRESSURE_PLATE, "Сырная нажимная пластина");
        addBlock(ModBlocks.CHEESE_BUTTON, "Сырная кнопка");
        addBlock(ModBlocks.CHEESE_SIGN, "Сырная табличка");
        addBlock(ModBlocks.CHEESE_HANGING_SIGN, "Сырная подвисная табличка");

        addBlock(ModBlocks.PLOMBIR_SNOW_BLOCK, "Блок пломбирного снега");
        addBlock(ModBlocks.PLOMBIR_SNOW, "Пломбирный снег");

        addBlock(ModBlocks.PLOMBIR_SAPLING, "Пломбирный саженец");
        addBlock(ModBlocks.PLOMBIR_LEAVES, "Пломбирная листва");
        addBlock(ModBlocks.PLOMBIR_LOG, "Пломбирное бревно");
        addBlock(ModBlocks.STRIPPED_PLOMBIR_LOG, "Обтёсанное пломбирное бревно");
        addBlock(ModBlocks.PLOMBIR_WOOD, "Пломбирная древесина");
        addBlock(ModBlocks.STRIPPED_PLOMBIR_WOOD, "Обтёсанная пломбирная древесина");
        addBlock(ModBlocks.PLOMBIR_PLANKS, "Пломбирные доски");
        addBlock(ModBlocks.PLOMBIR_STAIRS, "Пломбирные ступени");
        addBlock(ModBlocks.PLOMBIR_SLAB, "Пломбирная плита");
        addBlock(ModBlocks.PLOMBIR_FENCE, "Пломбирный забор");
        addBlock(ModBlocks.PLOMBIR_FENCE_GATE, "Пломбирная калитка");
        addBlock(ModBlocks.PLOMBIR_DOOR, "Пломбирная дверь");
        addBlock(ModBlocks.PLOMBIR_TRAPDOOR, "Пломбирный люк");
        addBlock(ModBlocks.PLOMBIR_PRESSURE_PLATE, "Пломбирная нажимная пластина");
        addBlock(ModBlocks.PLOMBIR_BUTTON, "Пломбирная кнопка");
        addBlock(ModBlocks.PLOMBIR_SIGN, "Пломбирная табличка");
        addBlock(ModBlocks.PLOMBIR_HANGING_SIGN, "Пломбирная подвисная табличка");

        addBlock(ModBlocks.MILK_PORTAL, "Молочный портал");
        addBlock(ModBlocks.GORGONZOLA, "Горгонзола");
        addBlock(ModBlocks.GORGONZOLA_TURF, "Горгонзольный дёрн");
    }
}
