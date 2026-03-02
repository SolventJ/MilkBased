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
                ModBlocks.STRIPPED_CHEESEWOOD.get());

        tag(BlockTags.LOGS).addTag(ModBlockTags.CHEESEWOOD_LOGS);

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.MILKSTONE.get(),
                ModBlocks.COBBLED_MILKSTONE.get(),
                ModBlocks.MOLDY_COBBLED_MILKSTONE.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.CURD_BLOCK.get());

        tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(ModBlocks.CHEESE_BLOCK.get());
    }
}
