package com.github.solventj.milkbased.datagen.tags;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MilkBased.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ModBlockTags.CHEESEWOOD_LOGS).add(
                ModBlocks.CHEESEWOOD_LOG.get(),
                ModBlocks.STRIPPED_CHEESEWOOD_LOG.get(),
                ModBlocks.CHEESEWOOD.get(),
                ModBlocks.STRIPPED_CHEESEWOOD.get()
        );

        tag(ModBlockTags.CHEESEWOOD_GROWABLE_ON).add(
                ModBlocks.CHEESE_BLOCK.get(),
                ModBlocks.GORGONZOLA.get(),
                ModBlocks.GORGONZOLA_TURF.get()
        );

        tag(ModBlockTags.PLOMBIR_LOGS).add(
                ModBlocks.PLOMBIR_LOG.get(),
                ModBlocks.STRIPPED_PLOMBIR_LOG.get(),
                ModBlocks.PLOMBIR_WOOD.get(),
                ModBlocks.STRIPPED_PLOMBIR_WOOD.get()
        );

        tag(ModBlockTags.CHEESE_FURNITURE).add(
                ModBlocks.CHEESE_PLANKS.get(),
                ModBlocks.CHEESE_STAIRS.get(),
                ModBlocks.CHEESE_SLAB.get(),
                ModBlocks.CHEESE_FENCE.get(),
                ModBlocks.CHEESE_FENCE_GATE.get(),
                ModBlocks.CHEESE_DOOR.get(),
                ModBlocks.CHEESE_TRAPDOOR.get(),
                ModBlocks.CHEESE_PRESSURE_PLATE.get(),
                ModBlocks.CHEESE_BUTTON.get()
        );

        tag(ModBlockTags.CHEESE_SIGNS).add(
                ModBlocks.CHEESE_SIGN.get(),
                ModBlocks.CHEESE_WALL_SIGN.get(),
                ModBlocks.CHEESE_HANGING_SIGN.get(),
                ModBlocks.CHEESE_WALL_HANGING_SIGN.get()
        );

        tag(ModBlockTags.PLOMBIR_FURNITURE).add(
                ModBlocks.PLOMBIR_PLANKS.get(),
                ModBlocks.PLOMBIR_STAIRS.get(),
                ModBlocks.PLOMBIR_SLAB.get(),
                ModBlocks.PLOMBIR_FENCE.get(),
                ModBlocks.PLOMBIR_FENCE_GATE.get(),
                ModBlocks.PLOMBIR_DOOR.get(),
                ModBlocks.PLOMBIR_TRAPDOOR.get(),
                ModBlocks.PLOMBIR_PRESSURE_PLATE.get(),
                ModBlocks.PLOMBIR_BUTTON.get()
        );

        tag(ModBlockTags.PLOMBIR_SIGNS).add(
                ModBlocks.PLOMBIR_SIGN.get(),
                ModBlocks.PLOMBIR_WALL_SIGN.get(),
                ModBlocks.PLOMBIR_HANGING_SIGN.get(),
                ModBlocks.PLOMBIR_WALL_HANGING_SIGN.get()
        );

        tag(BlockTags.LOGS)
                .addTag(ModBlockTags.CHEESEWOOD_LOGS)
                .addTag(ModBlockTags.PLOMBIR_LOGS);

        tag(BlockTags.LOGS_THAT_BURN).addTag(ModBlockTags.CHEESEWOOD_LOGS);

        tag(BlockTags.PLANKS).add(
                ModBlocks.CHEESE_PLANKS.get(),
                ModBlocks.PLOMBIR_PLANKS.get()
        );

        tag(BlockTags.WOODEN_STAIRS).add(
                ModBlocks.CHEESE_STAIRS.get(),
                ModBlocks.PLOMBIR_STAIRS.get()
        );

        tag(BlockTags.WOODEN_SLABS).add(
                ModBlocks.CHEESE_SLAB.get(),
                ModBlocks.PLOMBIR_SLAB.get()
        );

        tag(BlockTags.WOODEN_FENCES).add(
                ModBlocks.CHEESE_FENCE.get(),
                ModBlocks.PLOMBIR_FENCE.get()
        );

        tag(BlockTags.FENCE_GATES).add(
                ModBlocks.CHEESE_FENCE_GATE.get(),
                ModBlocks.PLOMBIR_FENCE_GATE.get()
        );

        tag(BlockTags.WOODEN_DOORS).add(
                ModBlocks.CHEESE_DOOR.get(),
                ModBlocks.PLOMBIR_DOOR.get()
        );

        tag(BlockTags.WOODEN_TRAPDOORS).add(
                ModBlocks.CHEESE_TRAPDOOR.get(),
                ModBlocks.PLOMBIR_TRAPDOOR.get()
        );

        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(
                ModBlocks.CHEESE_PRESSURE_PLATE.get(),
                ModBlocks.PLOMBIR_PRESSURE_PLATE.get()
        );

        tag(BlockTags.WOODEN_BUTTONS).add(
                ModBlocks.CHEESE_BUTTON.get(),
                ModBlocks.PLOMBIR_BUTTON.get()
        );

        tag(BlockTags.STANDING_SIGNS).add(
                ModBlocks.CHEESE_SIGN.get(),
                ModBlocks.PLOMBIR_SIGN.get()
        );

        tag(BlockTags.WALL_SIGNS).add(
                ModBlocks.CHEESE_WALL_SIGN.get(),
                ModBlocks.PLOMBIR_WALL_SIGN.get()
        );

        tag(BlockTags.CEILING_HANGING_SIGNS).add(
                ModBlocks.CHEESE_HANGING_SIGN.get(),
                ModBlocks.PLOMBIR_HANGING_SIGN.get()
        );

        tag(BlockTags.WALL_HANGING_SIGNS).add(
                ModBlocks.CHEESE_WALL_HANGING_SIGN.get(),
                ModBlocks.PLOMBIR_WALL_HANGING_SIGN.get()
        );

        tag(BlockTags.SAPLINGS).add(
                ModBlocks.CHEESEWOOD_SAPLING.get(),
                ModBlocks.PLOMBIR_SAPLING.get()
        );

        tag(BlockTags.LEAVES).add(
                ModBlocks.CHEESEWOOD_LEAVES.get(),
                ModBlocks.PLOMBIR_LEAVES.get()
        );

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        ModBlocks.CHEESEWOOD_SAPLING.get(),
                        ModBlocks.PLOMBIR_SAPLING.get()
                )
                .addTag(ModBlockTags.CHEESE_FURNITURE)
                .addTag(ModBlockTags.CHEESE_SIGNS)
                .addTag(ModBlockTags.PLOMBIR_FURNITURE)
                .addTag(ModBlockTags.PLOMBIR_SIGNS);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.MILKSTONE.get(),
                ModBlocks.COBBLED_MILKSTONE.get(),
                ModBlocks.MOLDY_COBBLED_MILKSTONE.get()
        );

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(
                        ModBlocks.CHEESE_BLOCK.get(),
                        ModBlocks.CURD_BLOCK.get(),
                        ModBlocks.GORGONZOLA.get(),
                        ModBlocks.GORGONZOLA_TURF.get(),
                        ModBlocks.PLOMBIR_SNOW_BLOCK.get(),
                        ModBlocks.PLOMBIR_SNOW.get()
                )
                .addTag(ModBlockTags.PLOMBIR_LOGS)
                .addTag(ModBlockTags.PLOMBIR_FURNITURE)
                .addTag(ModBlockTags.PLOMBIR_SIGNS);

        tag(BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.CHEESEWOOD_LEAVES.get(),
                ModBlocks.PLOMBIR_LEAVES.get()
        );

        tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(
                ModBlocks.CHEESE_BLOCK.get(),
                ModBlocks.GORGONZOLA_TURF.get()
        );
    }
}
