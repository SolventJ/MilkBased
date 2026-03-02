package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.data.tags.ModItemTags;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        planksFromLog(output, ModBlocks.CHEESE_PLANKS.asItem(), ModItemTags.CHEESEWOOD_LOGS, 4);
        smeltingResultFromBase(output, ModBlocks.MILKSTONE, ModBlocks.COBBLED_MILKSTONE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MILKSTONE, 3)
                .requires(Items.STONE, 3).requires(Items.MILK_BUCKET)
                .unlockedBy("has_milk_bucket", has(ModItems.MILK_BUCKET))
                .save(output, getConversionRecipeName(ModBlocks.MILKSTONE, Items.STONE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOLDY_COBBLED_MILKSTONE)
                .requires(ModBlocks.COBBLED_MILKSTONE).requires(ModBlocks.BLUE_MOLD)
                .unlockedBy("has_blue_mold", has(ModBlocks.BLUE_MOLD))
                .save(output, getConversionRecipeName(
                        ModBlocks.MOLDY_COBBLED_MILKSTONE, ModBlocks.BLUE_MOLD));

        hangingSign(output, ModItems.CHEESE_HANGING_SIGN, ModBlocks.CHEESE_PLANKS);
        slab(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHEESE_SLAB, ModBlocks.CHEESE_PLANKS);
        pressurePlate(output, ModBlocks.CHEESE_PRESSURE_PLATE, ModBlocks.CHEESE_PLANKS);

        Ingredient cheesePlanks = Ingredient.of(ModBlocks.CHEESE_PLANKS);
        signBuilder(ModItems.CHEESE_SIGN, cheesePlanks)
                .unlockedBy("has_planks", has(ModBlocks.CHEESE_PLANKS)).save(output);
        stairBuilder(ModBlocks.CHEESE_STAIRS, cheesePlanks)
                .unlockedBy("has_planks", has(ModBlocks.CHEESE_PLANKS)).save(output);
        fenceBuilder(ModBlocks.CHEESE_FENCE, cheesePlanks)
                .unlockedBy("has_planks", has(ModBlocks.CHEESE_PLANKS)).save(output);
        fenceGateBuilder(ModBlocks.CHEESE_FENCE_GATE, cheesePlanks)
                .unlockedBy("has_planks", has(ModBlocks.CHEESE_PLANKS)).save(output);
        doorBuilder(ModBlocks.CHEESE_DOOR, cheesePlanks)
                .unlockedBy("has_planks", has(ModBlocks.CHEESE_PLANKS)).save(output);
        trapdoorBuilder(ModBlocks.CHEESE_TRAPDOOR, cheesePlanks)
                .unlockedBy("has_planks", has(ModBlocks.CHEESE_PLANKS)).save(output);
        buttonBuilder(ModBlocks.CHEESE_BUTTON, cheesePlanks)
                .unlockedBy("has_planks", has(ModBlocks.CHEESE_PLANKS)).save(output);
    }

    protected static void smeltingResultFromBase(@NotNull RecipeOutput recipeOutput, ItemLike result,
                                                 ItemLike ingredient) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient),
                RecipeCategory.BUILDING_BLOCKS, result, 0.1F, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput, getSmeltingRecipeName(result));
    }

    protected static String getConversionRecipeName(ItemLike result, ItemLike ingredient) {
        return modLoc(RecipeProvider.getConversionRecipeName(result, ingredient));
    }

    protected static String getSmeltingRecipeName(ItemLike item) {
        return modLoc(RecipeProvider.getSmeltingRecipeName(item));
    }

    private static String modLoc(String string) {
        return MilkBased.MOD_ID + ":" + string;
    }
}
