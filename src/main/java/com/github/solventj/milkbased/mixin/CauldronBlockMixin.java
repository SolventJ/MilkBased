package com.github.solventj.milkbased.mixin;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CauldronBlock.class)
public class CauldronBlockMixin {

    @Invoker("shouldHandlePrecipitation")
    static boolean invokeShouldHandlePrecipitation(Level level, Biome.Precipitation precipitation) {
        throw new AssertionError();
    }

    @Inject(method = "handlePrecipitation", at = @At(value = "HEAD"), cancellable = true)
    private void onHandlePrecipitation(BlockState state, Level level, BlockPos pos, Biome.Precipitation precipitation,
                                       CallbackInfo callbackInfo) {
        if (level.dimension() != ModDimensions.MILK_LEVEL_KEY) return;
        callbackInfo.cancel();

        if (!invokeShouldHandlePrecipitation(level, precipitation)
                && precipitation != Biome.Precipitation.RAIN) return;

        level.setBlockAndUpdate(pos, ModBlocks.MILK_CAULDRON.get().defaultBlockState());
        level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
    }
}
