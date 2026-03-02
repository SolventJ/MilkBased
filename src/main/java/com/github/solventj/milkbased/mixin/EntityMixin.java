package com.github.solventj.milkbased.mixin;

import com.github.solventj.milkbased.fluid.ModFluidTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

//    @Inject(method = "updateFluidHeightAndDoFluidPushing*", at = @At(value = "HEAD"), cancellable = true)
//    private boolean onUpdateFluidHeightAndDoFluidPushing(TagKey<Fluid> fluidTag, double motionScale,
//                                                         CallbackInfo callbackInfo) {
//        callbackInfo.cancel();
//
//        Entity entity = (Entity) (Object) this;
//        entity.updateFluidHeightAndDoFluidPushing();
//        if (fluidTag == FluidTags.WATER) return entity.isInFluidType(NeoForgeMod.WATER_TYPE.value())
//                || entity.isInFluidType(ModFluidTypes.MILK_FLUID_TYPE.get());
//        else if (fluidTag == FluidTags.LAVA) return entity.isInFluidType(NeoForgeMod.LAVA_TYPE.value());
//        else return false;
//    }
}
