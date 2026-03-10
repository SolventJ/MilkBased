package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.datagen.tags.ModItemTags;
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
        woodFromLogs(output, ModBlocks.CHEESEWOOD, ModBlocks.CHEESEWOOD_LOG);
        woodFromLogs(output, ModBlocks.STRIPPED_CHEESEWOOD, ModBlocks.STRIPPED_CHEESEWOOD_LOG);

        planksFromLog(output, ModBlocks.PLOMBIR_PLANKS.asItem(), ModItemTags.PLOMBIR_LOGS, 4);
        woodFromLogs(output, ModBlocks.PLOMBIR_WOOD, ModBlocks.PLOMBIR_LOG);
        woodFromLogs(output, ModBlocks.STRIPPED_PLOMBIR_WOOD, ModBlocks.STRIPPED_PLOMBIR_LOG);

        smeltingResultFromBase(output, ModBlocks.MILKSTONE, ModBlocks.COBBLED_MILKSTONE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MILKSTONE, 3)
                .requires(Items.STONE, 3).requires(Items.MILK_BUCKET)
                .unlockedBy("has_milk_bucket", has(Items.MILK_BUCKET))
                .save(output, getConversionRecipeName(ModBlocks.MILKSTONE, Items.STONE));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOLDY_COBBLED_MILKSTONE)
                .requires(ModBlocks.COBBLED_MILKSTONE).requires(ModBlocks.BLUE_MOLD)
                .unlockedBy("has_blue_mold", has(ModBlocks.BLUE_MOLD))
                .save(output, getConversionRecipeName(
                        ModBlocks.MOLDY_COBBLED_MILKSTONE, ModBlocks.BLUE_MOLD));

        hangingSign(output, ModItems.CHEESE_HANGING_SIGN, ModBlocks.STRIPPED_CHEESEWOOD_LOG);
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

        woodenBoat(output, ModItems.CHEESE_BOAT, ModBlocks.CHEESE_PLANKS);
        chestBoat(output, ModItems.CHEESE_CHEST_BOAT, ModItems.CHEESE_BOAT);

        hangingSign(output, ModItems.PLOMBIR_HANGING_SIGN, ModBlocks.STRIPPED_PLOMBIR_LOG);
        slab(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PLOMBIR_SLAB, ModBlocks.PLOMBIR_PLANKS);
        pressurePlate(output, ModBlocks.PLOMBIR_PRESSURE_PLATE, ModBlocks.PLOMBIR_PLANKS);

        Ingredient plombirPlanks = Ingredient.of(ModBlocks.PLOMBIR_PLANKS);
        signBuilder(ModItems.PLOMBIR_SIGN, plombirPlanks)
                .unlockedBy("has_planks", has(ModBlocks.PLOMBIR_PLANKS)).save(output);
        stairBuilder(ModBlocks.PLOMBIR_STAIRS, plombirPlanks)
                .unlockedBy("has_planks", has(ModBlocks.PLOMBIR_PLANKS)).save(output);
        fenceBuilder(ModBlocks.PLOMBIR_FENCE, plombirPlanks)
                .unlockedBy("has_planks", has(ModBlocks.PLOMBIR_PLANKS)).save(output);
        fenceGateBuilder(ModBlocks.PLOMBIR_FENCE_GATE, plombirPlanks)
                .unlockedBy("has_planks", has(ModBlocks.PLOMBIR_PLANKS)).save(output);
        doorBuilder(ModBlocks.PLOMBIR_DOOR, plombirPlanks)
                .unlockedBy("has_planks", has(ModBlocks.PLOMBIR_PLANKS)).save(output);
        trapdoorBuilder(ModBlocks.PLOMBIR_TRAPDOOR, plombirPlanks)
                .unlockedBy("has_planks", has(ModBlocks.PLOMBIR_PLANKS)).save(output);
        buttonBuilder(ModBlocks.PLOMBIR_BUTTON, plombirPlanks)
                .unlockedBy("has_planks", has(ModBlocks.PLOMBIR_PLANKS)).save(output);

        woodenBoat(output, ModItems.PLOMBIR_BOAT, ModBlocks.PLOMBIR_PLANKS);
        chestBoat(output, ModItems.PLOMBIR_CHEST_BOAT, ModItems.PLOMBIR_BOAT);

        twoByTwoPacker(output, RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.PLOMBIR_SNOW_BLOCK, ModItems.SCOOP_OF_PLOMBIR);
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
