package com.github.solventj.milkbased.mixin;

import com.github.solventj.milkbased.entity.boat.MilkBoat;
import com.github.solventj.milkbased.entity.boat.MilkChestBoat;
import com.github.solventj.milkbased.fluid.ModFluidTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Boat.class)
public abstract class BoatMixin {
    @Shadow
    private boolean inputLeft;

    @Shadow
    private float deltaRotation;

    @Shadow
    private boolean inputRight;

    @Shadow
    private boolean inputUp;

    @Shadow
    private boolean inputDown;

    @Inject(method = "controlBoat", at = @At("HEAD"), cancellable = true)
    private void onControlBoat(CallbackInfo callbackInfo) {
        var thisBoat = (Boat) (Object) this;

        if (thisBoat instanceof MilkBoat || thisBoat instanceof MilkChestBoat) return;
        if (!thisBoat.isInFluidType(ModFluidTypes.MILK.get())) return;
        if (!thisBoat.isVehicle()) return;
        callbackInfo.cancel();

        float slowness = 0.5f;
        float f = 0.0f;

        if (inputLeft) deltaRotation--;
        if (inputRight) deltaRotation++;

        if (inputRight != inputLeft && !inputUp && !inputDown)
            f += 0.005f * slowness;

        thisBoat.setYRot(thisBoat.getYRot() + deltaRotation);
        if (inputUp) f += 0.04f * slowness;
        if (inputDown) f -= 0.005f * slowness;

        float angle = thisBoat.getYRot() * (float) (Math.PI / 180);
        thisBoat.setDeltaMovement(thisBoat.getDeltaMovement().add(
                Mth.sin(-angle) * f,
                0.0f,
                Mth.cos(angle) * f
        ));

        thisBoat.setPaddleState(
                inputRight && !inputLeft || inputUp,
                inputLeft && !inputRight || inputUp);
    }
}
