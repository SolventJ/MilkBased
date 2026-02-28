package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
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

    public static final TagKey<Block> CHEESEWOOD_LOGS = createTag("cheesewood_logs");

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(CHEESEWOOD_LOGS)
                .add(ModBlocks.CHEESEWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_CHEESEWOOD_LOG.get())
                .add(ModBlocks.CHEESEWOOD.get())
                .add(ModBlocks.STRIPPED_CHEESEWOOD.get());

        tag(BlockTags.LOGS).addTag(CHEESEWOOD_LOGS);
    }

    private static TagKey<Block> createTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}
