package com.github.solventj.milkbased.util;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Map;

public class ModCauldronInteractions {
    public static final CauldronInteraction.InteractionMap MILK = CauldronInteraction.newInteractionMap("milk");
    public static final CauldronInteraction FILL_MILK = (
            state, level, pos, player, hand, itemStack)
            -> CauldronInteraction.emptyBucket(level, pos, player, hand, itemStack,
            ModBlocks.MILK_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY
    );

    public static void bootstrap() {
        Map<Item, CauldronInteraction> milk_map = MILK.map();
        milk_map.put(Items.BUCKET, (
                state, level, blockPos, player, hand,
                itemStack) -> CauldronInteraction.fillBucket(
                state, level, blockPos, player, hand, itemStack,
                new ItemStack(ModItems.MILK_BUCKET.get()), (blockState1) -> true, SoundEvents.BUCKET_FILL));
        CauldronInteraction.addDefaultInteractions(milk_map);
        addDefaultInteractions(milk_map);

        addDefaultInteractions(CauldronInteraction.EMPTY.map());
        addDefaultInteractions(CauldronInteraction.WATER.map());
        addDefaultInteractions(CauldronInteraction.LAVA.map());
        addDefaultInteractions(CauldronInteraction.POWDER_SNOW.map());
    }

    private static void addDefaultInteractions(Map<Item, CauldronInteraction> interactionsMap) {
        interactionsMap.put(ModItems.MILK_BUCKET.get(), FILL_MILK);
    }
}
