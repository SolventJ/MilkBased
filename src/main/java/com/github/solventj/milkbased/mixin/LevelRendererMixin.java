package com.github.solventj.milkbased.mixin;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.world.dimension.ModDimensions;
import com.github.solventj.milkbased.particle.ModParticleTypes;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Final
    @Shadow
    private static ResourceLocation RAIN_LOCATION;

    @Unique
    private static final ResourceLocation MILK_RAIN_LOCATION =
            ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "textures/environment/milk_rain.png");

    @Redirect(method = "renderSnowAndRain", at = @At(value = "INVOKE", target =
            "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V"))
    private void redirectSetShaderTexture(int texture, ResourceLocation originalTexture) {
        Level level = Minecraft.getInstance().level;

        if (level != null && originalTexture == RAIN_LOCATION
                && level.dimension() == ModDimensions.MILK_LEVEL_KEY
        ) {
            RenderSystem.setShaderTexture(texture, MILK_RAIN_LOCATION);
        } else RenderSystem.setShaderTexture(texture, originalTexture);
    }

    @Redirect(method = "tickRain", at = @At(value = "FIELD", target =
            "Lnet/minecraft/core/particles/ParticleTypes;RAIN:Lnet/minecraft/core/particles/SimpleParticleType;", opcode = Opcodes.GETSTATIC))
    private SimpleParticleType redirectRainField() {
        Level level = Minecraft.getInstance().level;

        if (level != null && level.dimension() == ModDimensions.MILK_LEVEL_KEY) {
            return ModParticleTypes.MILK_SPLASH.get();
        }

        return ParticleTypes.RAIN;
    }
}
