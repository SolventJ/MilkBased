package com.github.solventj.milkbased.particle;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, MilkBased.MOD_ID);

    public static final Supplier<SimpleParticleType> MILK_RAIN = PARTICLES.register("milk_rain",
            () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> MILK_PORTAL = PARTICLES.register("milk_portal",
            () -> new SimpleParticleType(false));

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
