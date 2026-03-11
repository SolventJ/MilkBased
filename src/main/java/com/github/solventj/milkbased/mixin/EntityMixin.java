package com.github.solventj.milkbased.mixin;

import com.github.solventj.milkbased.fluid.ModFluidTypes;
import com.github.solventj.milkbased.particle.ModParticleTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Objects;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    @Nullable
    public abstract LivingEntity getControllingPassenger();

    @Shadow
    @Final
    protected RandomSource random;

    @Shadow
    protected abstract SoundEvent getSwimSplashSound();

    @Shadow
    protected abstract SoundEvent getSwimHighSpeedSplashSound();

    @Shadow
    public abstract double getY();

    @Shadow
    private EntityDimensions dimensions;

    @Shadow
    public abstract double getX();

    @Shadow
    public abstract Level level();

    @Shadow
    public abstract void gameEvent(Holder<GameEvent> gameEvent);

    @Shadow
    public abstract double getZ();

    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Inject(method = "doWaterSplashEffect", at = @At("HEAD"), cancellable = true)
    private void onDoWaterSplashEffect(CallbackInfo callbackInfo) {
        Entity thisEntity = (Entity) (Object) this;

        if (!thisEntity.isInFluidType(ModFluidTypes.MILK.get())) return;
        callbackInfo.cancel();

        Entity entity = (Entity) Objects.requireNonNullElse(this.getControllingPassenger(), this);
        float f = entity == thisEntity ? 0.2F : 0.9F;
        Vec3 vec3 = entity.getDeltaMovement();
        float f1 = Math.min(1.0F, (float)Math.sqrt(vec3.x * vec3.x * 0.2d + vec3.y * vec3.y + vec3.z * vec3.z * 0.2d) * f);
        if (f1 < 0.25F) {
            this.playSound(this.getSwimSplashSound(), f1,
                    1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
        } else {
            this.playSound(this.getSwimHighSpeedSplashSound(), f1,
                    1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
        }

        float f2 = (float) Mth.floor(this.getY());

        for(int i = 0; (float)i < 1.0F + this.dimensions.width() * 20.0F; ++i) {
            double d0 = (this.random.nextDouble() * 2.0d - 1.0d) * (double)this.dimensions.width();
            double d1 = (this.random.nextDouble() * 2.0d - 1.0d) * (double)this.dimensions.width();
            this.level().addParticle(ParticleTypes.BUBBLE,
                    this.getX() + d0,
                    f2 + 1.0f,
                    this.getZ() + d1,
                    vec3.x,
                    vec3.y - this.random.nextDouble() * 0.2d,
                    vec3.z);
        }

        for(int j = 0; (float)j < 1.0F + this.dimensions.width() * 20.0F; ++j) {
            double dx = (this.random.nextDouble() * 2.0d - 1.0d) * (double)this.dimensions.width();
            double dz = (this.random.nextDouble() * 2.0d - 1.0d) * (double)this.dimensions.width();
            this.level().addParticle(ModParticleTypes.MILK_SPLASH.get(),
                    this.getX() + dx,
                    f2 + 1.0F,
                    this.getZ() + dz,
                    vec3.x, vec3.y, vec3.z);
        }

        this.gameEvent(GameEvent.SPLASH);
    }
}
