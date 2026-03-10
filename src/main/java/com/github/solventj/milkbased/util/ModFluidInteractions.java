package com.github.solventj.milkbased.util;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.fluid.ModFluidTypes;
import com.github.solventj.milkbased.particle.ModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.Function;

public class ModFluidInteractions {

    public static void bootstrap() {
        add(NeoForgeMod.LAVA_TYPE.value(), ModFluidTypes.MILK.get(), state
                -> state.isSource()
                ? Blocks.OBSIDIAN.defaultBlockState()
                : ModBlocks.COBBLED_MILKSTONE.get().defaultBlockState(), 0);

        add(ModFluidTypes.MILK.get(), NeoForgeMod.WATER_TYPE.value(),
                ModBlocks.WHEY_BLOCK.get().defaultBlockState(), 1);
    }

    private static void add(FluidType source, FluidType type, BlockState state, int event) {
        add(source, type, (fluidState) -> state, event);
    }

    private static void add(FluidType source, FluidInteractionRegistry.HasFluidInteraction predicate,
                            BlockState state, int event) {
        add(source, predicate, (fluidState) -> state, event);
    }

    private static void add(FluidType source, FluidType type, Function<FluidState, BlockState> getState, int event) {
        add(source, (level, currentPos, relativePos, currentState)
                -> level.getFluidState(relativePos).getFluidType() == type, getState, event);
    }

    private static void add(FluidType source, FluidInteractionRegistry.HasFluidInteraction predicate,
                            Function<FluidState, BlockState> getState, int event) {
        FluidInteractionRegistry.FluidInteraction interaction = (
                level, currentPos, relativePos, currentState
        ) -> {
            level.setBlockAndUpdate(currentPos, EventHooks.fireFluidPlaceBlockEvent(
                    level, currentPos, currentPos, getState.apply(currentState)));

            switch (event) {
                case 0:
                    level.levelEvent(1501, currentPos, 0);
                    break;
                case 1:
                    ModFluidInteractions.wheyBlock(currentPos);
                    break;
            }
        };

        add(source, predicate, interaction);
    }

    private static void add(FluidType source, FluidInteractionRegistry.HasFluidInteraction predicate,
                            FluidInteractionRegistry.FluidInteraction interaction) {
        FluidInteractionRegistry.addInteraction(source,
                new FluidInteractionRegistry.InteractionInformation(predicate, interaction));
    }

    public static void wheyBlock(BlockPos pos) {
        Minecraft mc = Minecraft.getInstance();

        Level level = mc.level;
        if (level == null || !level.isClientSide()) return;

        mc.execute(() ->{
            RandomSource randomsource = RandomSource.createNewThreadLocalInstance();
            level.playLocalSound(pos, SoundEvents.HONEY_BLOCK_SLIDE, SoundSource.BLOCKS, 0.5f,
                    2.6f + (randomsource.nextFloat() - randomsource.nextFloat()) * 0.8f, false);

            for (int i = 0; i < 8; ++i) {
                level.addParticle(ModParticleTypes.MILK_SPLASH.get(),
                        (double) pos.getX() + randomsource.nextDouble(),
                        (double) pos.getY() + 1.2,
                        (double) pos.getZ() + randomsource.nextDouble(),
                        0.0f, 0.0f, 0.0f);
            }
        });
    }
}
