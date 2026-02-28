package com.github.solventj.milkbased.block_entity;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.block_entity.custom.ModHangingSignBlockEntity;
import com.github.solventj.milkbased.block_entity.custom.ModSignBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MilkBased.MOD_ID);

    public static final Supplier<BlockEntityType<ModSignBlockEntity>> CHEESE_SIGN =
            BLOCK_ENTITIES.register("cheese_sign",
                    () -> BlockEntityType.Builder.of(
                            ModSignBlockEntity::new,
                            ModBlocks.CHEESE_SIGN.get(),
                            ModBlocks.WALL_CHEESE_SIGN.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<ModHangingSignBlockEntity>> CHEESE_HANGING_SIGN =
            BLOCK_ENTITIES.register("cheese_hanging_sign",
                    () -> BlockEntityType.Builder.of(
                            ModHangingSignBlockEntity::new,
                            ModBlocks.CHEESE_HANGING_SIGN.get(),
                            ModBlocks.WALL_CHEESE_HANGING_SIGN.get()
                    ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
