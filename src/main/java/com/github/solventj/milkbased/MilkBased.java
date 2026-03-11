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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        ModEntities.register(eventBus);
        ModBlockEntities.register(eventBus);

        ModParticleTypes.register(eventBus);
        ModPoiTypes.register(eventBus);
    }

    // TODO: Fix portal overlay
    // TODO: Fix milk texture

    // TODO: Fix cheesewood trees dirt
}
