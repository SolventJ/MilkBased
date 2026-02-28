package com.github.solventj.milkbased.item;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.fluid.ModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MilkBased.MOD_ID);

    public static final DeferredItem<BucketItem> MILK_BUCKET = ITEMS.register("milk_bucket",
            () -> new BucketItem(ModFluids.SOURCE_MILK_FLUID.get(), new Item.Properties().stacksTo(1)));

    public static final DeferredItem<SignItem> CHEESE_SIGN = ITEMS.register("cheese_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16),
                    ModBlocks.CHEESE_SIGN.get(), ModBlocks.WALL_CHEESE_SIGN.get()));

    public static final DeferredItem<HangingSignItem> CHEESE_HANGING_SIGN = ITEMS.register("cheese_hanging_sign",
            () -> new HangingSignItem(ModBlocks.CHEESE_HANGING_SIGN.get(), ModBlocks.WALL_CHEESE_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
