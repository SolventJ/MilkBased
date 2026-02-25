package com.github.solventj.milkbased.fluid;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, MilkBased.MOD_ID);

    public static final String MILK_ID = "milk";

    public static final Supplier<FlowingFluid> SOURCE_MILK_FLUID = FLUIDS.register(MILK_ID,
            () -> new BaseFlowingFluid.Source(ModFluids.MILK_FLUID_PROPERTIES));

    public static final Supplier<FlowingFluid> FLOWING_MILK_FLUID = FLUIDS.register("flowing_" + MILK_ID,
            () -> new BaseFlowingFluid.Flowing(ModFluids.MILK_FLUID_PROPERTIES));

    public static final BaseFlowingFluid.Properties MILK_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.MILK_FLUID_TYPE, SOURCE_MILK_FLUID, FLOWING_MILK_FLUID)
            .block(ModBlocks.MILK);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
