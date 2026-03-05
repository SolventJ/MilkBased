package com.github.solventj.milkbased;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.block_entity.ModBlockEntities;
import com.github.solventj.milkbased.entity.ModEntities;
import com.github.solventj.milkbased.fluid.ModFluidTypes;
import com.github.solventj.milkbased.fluid.ModFluids;
import com.github.solventj.milkbased.item.ModCreativeModeTabs;
import com.github.solventj.milkbased.item.ModItems;
import com.github.solventj.milkbased.particle.ModParticleTypes;
import com.github.solventj.milkbased.util.ModPoiTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MilkBased.MOD_ID)
public class MilkBased {
    public static final String MOD_ID = "milkbased";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public MilkBased(IEventBus eventBus) {
        ModFluids.register(eventBus);
        ModFluidTypes.register(eventBus);

        ModBlocks.register(eventBus);
        ModItems.register(eventBus);

        ModCreativeModeTabs.register(eventBus);

        ModBlockEntities.register(eventBus);
        ModEntities.register(eventBus);

        ModParticleTypes.register(eventBus);
        ModPoiTypes.register(eventBus);
    }
}
