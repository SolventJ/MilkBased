package com.github.solventj.milkbased.datagen.tags;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> CHEESEWOOD_LOGS = createTag("cheesewood_logs");
    public static final TagKey<Item> PLOMBIR_LOGS = createTag("plombir_logs");

    private static TagKey<Item> createTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}
