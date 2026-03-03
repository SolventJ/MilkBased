package com.github.solventj.milkbased.mixin;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.datagen.tags.ModFluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.LavaFluid;
import net.neoforged.neoforge.event.EventHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin {

    @Shadow
    protected abstract void fizz(LevelAccessor level, BlockPos pos);

    @Shadow
    public abstract boolean isSame(Fluid fluid);

    @Inject(method = "spreadTo", at = @At("HEAD"), cancellable = true)
    private void onSpreadTo(LevelAccessor level, BlockPos pos, BlockState blockState,
                            Direction direction, FluidState fluidState, CallbackInfo callbackInfo) {
        if (direction != Direction.DOWN) return;

        boolean isLava = this.isSame(Fluids.LAVA) || this.isSame(Fluids.FLOWING_LAVA);
        boolean isMilk = level.getFluidState(pos).is(ModFluidTags.MILK);

        if (!isLava || !isMilk) return;

        if (blockState.getBlock() instanceof LiquidBlock) {
            BlockState newState = ModBlocks.MILKSTONE.get().defaultBlockState();
            level.setBlock(pos, EventHooks.fireFluidPlaceBlockEvent(level, pos, pos, newState), 3);
        }

        this.fizz(level, pos);
        callbackInfo.cancel();
    }
}
