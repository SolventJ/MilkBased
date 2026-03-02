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

        addBlock(ModBlocks.CURD_BLOCK, "Блок творога");
        addBlock(ModBlocks.MILKSTONE, "Молокамень");
        addBlock(ModBlocks.COBBLED_MILKSTONE, "Колотый молокамень");
        addBlock(ModBlocks.CHEESE_BLOCK, "Блок сыра");
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
        addBlock(ModBlocks.CHEESE_PRESSURE_PLATE, "Сырная нажимная плита");
        addBlock(ModBlocks.CHEESE_BUTTON, "Сырная кнопка");
        addBlock(ModBlocks.CHEESE_SIGN, "Сырная табличка");
        addBlock(ModBlocks.CHEESE_HANGING_SIGN, "Сырная подвисная табличка");
        addBlock(ModBlocks.MILK_PORTAL, "Молочный портал");
    }
}
