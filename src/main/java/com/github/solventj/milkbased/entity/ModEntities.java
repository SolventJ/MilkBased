package com.github.solventj.milkbased.entity;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.entity.boat.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MilkBased.MOD_ID);

    public static final Supplier<EntityType<? extends Boat>> CHEESE_BOAT =
            registerBoat(CheeseBoat.type + "_boat", CheeseBoat::new);

    public static final Supplier<EntityType<? extends Boat>> CHEESE_CHEST_BOAT =
            registerBoat(CheeseBoat.type + "_chest_boat", CheeseChestBoat::new);

    public static final Supplier<EntityType<? extends Boat>> PLOMBIR_BOAT =
            registerBoat(PlombirBoat.type + "_boat", PlombirBoat::new);

    public static final Supplier<EntityType<? extends Boat>> PLOMBIR_CHEST_BOAT =
            registerBoat(PlombirBoat.type + "_chest_boat", PlombirChestBoat::new);

    private static Supplier<EntityType<? extends Boat>> registerBoat(
            String name, EntityType.EntityFactory<Boat> factory) {
        return ENTITIES.register(name, () -> EntityType.Builder
                .of(factory, MobCategory.MISC)
                .sized(1.375f, 0.5625f)
                .eyeHeight(0.5625f)
                .clientTrackingRange(10)
                .build(name));
    }

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
