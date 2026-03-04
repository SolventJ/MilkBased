package com.github.solventj.milkbased.item;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.fluid.ModFluids;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MilkBased.MOD_ID);

    public static final DeferredItem<BucketItem> MILK_BUCKET = ITEMS.register("milk_bucket",
            () -> new BucketItem(ModFluids.MILK.get(), new Item.Properties().stacksTo(1)));

    public static final DeferredItem<SignItem> CHEESE_SIGN = ITEMS.register("cheese_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16),
                    ModBlocks.CHEESE_SIGN.get(), ModBlocks.CHEESE_WALL_SIGN.get()));

    public static final DeferredItem<HangingSignItem> CHEESE_HANGING_SIGN = ITEMS.register("cheese_hanging_sign",
            () -> new HangingSignItem(ModBlocks.CHEESE_HANGING_SIGN.get(), ModBlocks.CHEESE_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));

    public static final DeferredItem<SignItem> PLOMBIR_SIGN = ITEMS.register("plombir_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16),
                    ModBlocks.PLOMBIR_SIGN.get(), ModBlocks.PLOMBIR_WALL_SIGN.get()));

    public static final DeferredItem<HangingSignItem> PLOMBIR_HANGING_SIGN = ITEMS.register("plombir_hanging_sign",
            () -> new HangingSignItem(ModBlocks.PLOMBIR_HANGING_SIGN.get(), ModBlocks.PLOMBIR_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> CHECHIL = ITEMS.registerSimpleItem("chechil",
            new Item.Properties().food(Foods.COOKIE));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
