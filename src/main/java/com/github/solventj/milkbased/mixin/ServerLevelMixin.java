package com.github.solventj.milkbased.mixin;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Inject(method = "tickPrecipitation", at = @At("HEAD"), cancellable = true)
    public void injectTickPrecipitation(BlockPos blockPos, CallbackInfo info) {
        ServerLevel thisLevel = (ServerLevel) (Object) this;
        if (thisLevel.dimension() != ModDimensions.MILK_LEVEL_KEY) return;
        info.cancel();

        BlockPos heightmapPos = thisLevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, blockPos);
        BlockPos belowHighPos = heightmapPos.below();
        Biome biome = thisLevel.getBiome(heightmapPos).value();

        if (thisLevel.isAreaLoaded(belowHighPos, 1))
            if (biome.shouldFreeze(thisLevel, belowHighPos)) {
                thisLevel.setBlockAndUpdate(belowHighPos, Blocks.ICE.defaultBlockState());
            }

        if (!thisLevel.isRaining()) return;

        int height = thisLevel.getGameRules().getInt(GameRules.RULE_SNOW_ACCUMULATION_HEIGHT);
        if (height > 0 && biome.shouldSnow(thisLevel, heightmapPos)) {
            BlockState state = thisLevel.getBlockState(heightmapPos);
            if (state.is(ModBlocks.PLOMBIR_SNOW)) {
                int layers = state.getValue(SnowLayerBlock.LAYERS);
                if (layers < Math.min(height, 8)) {
                    BlockState newState = state.setValue(SnowLayerBlock.LAYERS, layers + 1);
                    Block.pushEntitiesUp(state, newState, thisLevel, heightmapPos);
                    thisLevel.setBlockAndUpdate(heightmapPos, newState);
                }
            } else {
                thisLevel.setBlockAndUpdate(heightmapPos, ModBlocks.PLOMBIR_SNOW.get().defaultBlockState());
            }
        }

        Biome.Precipitation precipitation = biome.getPrecipitationAt(belowHighPos);
        if (precipitation != Biome.Precipitation.NONE) {
            BlockState state = thisLevel.getBlockState(belowHighPos);
            state.getBlock().handlePrecipitation(state, thisLevel, belowHighPos, precipitation);
        }
    }
}
