package com.github.solventj.milkbased.fluid;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, MilkBased.MOD_ID);

    public static final Supplier<FluidType> MILK_TYPE = registerFluidType(
            ModFluids.MILK_ID, FluidType.Properties.create()
                    .descriptionId("fluid_type.milkbased.milk")
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .density(1200).viscosity(2000).temperature(600).canConvertToSource(true)
                    .canHydrate(false).canExtinguish(true).canPushEntity(true).canSwim(true)
                    .canDrown(true).fallDistanceModifier(0).lightLevel(0));

    public static Supplier<FluidType> registerFluidType(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new FluidType(properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
