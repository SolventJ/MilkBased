package com.github.solventj.milkbased.mixin;

import com.github.solventj.milkbased.entity.MilkBoat;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Boat.class)
public abstract class BoatMixin {
    @Shadow
    @Nullable
    protected abstract Boat.Status isUnderwater();

    @Shadow
    private double waterLevel;

    @Shadow
    protected abstract boolean checkInWater();

    @Shadow
    public abstract float getGroundFriction();

    @Shadow
    private float landFriction;

    @Inject(method = "getStatus", at = @At("HEAD"), cancellable = true)
    private void injectGetStatus(CallbackInfoReturnable<Boat.Status> info) {
        var thisBoat = (Boat) (Object) this;
        if (!(thisBoat instanceof MilkBoat)) return;
        info.cancel();

        Boat.Status status = this.isUnderwater();
        if (status != null) {
            this.waterLevel = thisBoat.getBoundingBox().maxY;
            info.setReturnValue(status);
        } else if (this.checkInWater()) {
            info.setReturnValue(Boat.Status.IN_WATER);
        } else {
            float friction = this.getGroundFriction();
            if (friction > 0.0f) {
                this.landFriction = friction;
                info.setReturnValue(Boat.Status.ON_LAND);
            } else {
                info.setReturnValue(Boat.Status.IN_AIR);
            }
        }
    }
}
