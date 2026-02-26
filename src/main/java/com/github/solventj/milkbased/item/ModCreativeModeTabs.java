package com.github.solventj.milkbased.item;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MilkBased.MOD_ID);

    public static final Supplier<CreativeModeTab> MILK_TAB =
            CREATIVE_TABS.register("milk_tab", () -> CreativeModeTab.builder()
                    .title(Component.literal("Milk Based"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        for (var item : ModItems.ITEMS.getEntries()){
                            output.accept(item.get());
                        }
//                        output.accept(ModBlocks.CURD_BLOCK);
//                        output.accept(ModBlocks.CHEESE_BLOCK);
//                        output.accept(ModBlocks.CHEESE_LOG);
//                        output.accept(ModBlocks.STRIPPED_CHEESE_LOG);
//                        output.accept(ModBlocks.CHEESE_LEAVES);
//                        output.accept(ModBlocks.CHEESE_SAPLING);
//                        output.accept(ModBlocks.CHEESE_PLANKS);
//                        output.accept(ModBlocks.BLUE_MOLD);
                    })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
