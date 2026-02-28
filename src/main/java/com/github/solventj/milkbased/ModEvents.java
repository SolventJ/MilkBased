package com.github.solventj.milkbased;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.util.ModCauldronInteractions;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.Map;

@EventBusSubscriber(modid = MilkBased.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    private static void onCommonSetup(FMLCommonSetupEvent event) {
        ModCauldronInteractions.bootstrap();
    }

    @SubscribeEvent
    private static void onToolModification(BlockEvent.BlockToolModificationEvent event) {
        if (event.getItemAbility().name().equals("axe_strip") && event.getState().is(ModBlocks.CHEESEWOOD_LOG.get())) {
            BlockState newState = ModBlocks.STRIPPED_CHEESEWOOD_LOG.get().defaultBlockState()
                    .setValue(RotatedPillarBlock.AXIS, event.getState().getValue(RotatedPillarBlock.AXIS));

            event.setFinalState(newState);
        }
    }
}
