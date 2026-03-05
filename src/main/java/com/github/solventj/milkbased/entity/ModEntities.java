package com.github.solventj.milkbased.entity;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MilkBased.MOD_ID);

    public static final Supplier<EntityType<MilkBoat>> CHEESE_BOAT =
            ENTITIES.register("cheese_boat", () -> EntityType.Builder
                    .of(MilkBoat::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f)
                    .eyeHeight(0.5625f)
                    .clientTrackingRange(10)
                    .build("cheese_boat"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
