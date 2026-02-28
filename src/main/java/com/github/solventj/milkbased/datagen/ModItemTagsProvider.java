package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(
            PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, blockTags, MilkBased.MOD_ID, existingFileHelper);
    }

    public static final TagKey<Item> CHEESEWOOD_LOGS = createTag("cheesewood_logs");

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy(ModBlockTagsProvider.CHEESEWOOD_LOGS, CHEESEWOOD_LOGS);
    }

    private static TagKey<Item> createTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}
