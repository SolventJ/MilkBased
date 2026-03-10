package com.github.solventj.milkbased.datagen.tags;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> CHEESEWOOD_LOGS = createTag("cheesewood_logs");
    public static final TagKey<Block> CHEESEWOOD_GROWABLE_ON = createTag("cheesewood_soil");
    public static final TagKey<Block> PLOMBIR_LOGS = createTag("plombir_logs");
    public static final TagKey<Block> CHEESE_SIGNS = createTag("cheese_signs");
    public static final TagKey<Block> PLOMBIR_SIGNS = createTag("plombir_signs");
    public static final TagKey<Block> CHEESE_FURNITURE = createTag("cheese_furniture");
    public static final TagKey<Block> PLOMBIR_FURNITURE = createTag("plombir_furniture");

    private static TagKey<Block> createTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}
