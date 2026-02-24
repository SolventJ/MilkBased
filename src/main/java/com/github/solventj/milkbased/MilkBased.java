package com.github.solventj.milkbased;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MilkBased.MOD_ID)
public class MilkBased {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "milkbased";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public MilkBased(IEventBus eventBus) {
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
    }
}
