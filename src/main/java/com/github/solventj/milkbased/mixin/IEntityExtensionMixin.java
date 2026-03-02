package com.github.solventj.milkbased.mixin;

import com.github.solventj.milkbased.fluid.ModFluidTypes;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IEntityExtension.class)
public interface IEntityExtensionMixin {

    @Inject(method = "isInFluidType(Lnet/neoforged/neoforge/fluids/FluidType;)Z", at = @At("HEAD"), cancellable = true)
    private void onIsInFluidType(FluidType type, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (type != NeoForgeMod.WATER_TYPE.value()) return;
        var obj = (IEntityExtension) this;
        var h1 = obj.getFluidTypeHeight(type) > 0;
        var h2 = obj.getFluidTypeHeight(ModFluidTypes.MILK_FLUID_TYPE.get()) > 0;
        callbackInfo.setReturnValue(h1 || h2);
    }

    @Inject(method = "isEyeInFluidType", at = @At("HEAD"), cancellable = true)
    private void onIsEyeInFluidType(FluidType type, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (type != NeoForgeMod.WATER_TYPE.value()) return;
        var obj = (IEntityExtension) this;
        var inType = obj.getEyeInFluidType();
        callbackInfo.setReturnValue(inType == type || inType == ModFluidTypes.MILK_FLUID_TYPE.get());
    }
}
