package com.github.solventj.milkbased.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class MilkPortalParticle extends PortalParticle {
    protected MilkPortalParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        float f = this.random.nextFloat() * 0.2f + 0.8f;
        this.rCol = f;
        this.gCol = f;
        this.bCol = f;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider extends PortalParticle.Provider {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            super(sprite);
            this.sprite = sprite;
        }

        @Override
        public @NotNull Particle createParticle(
                @NotNull SimpleParticleType type, @NotNull ClientLevel level, double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed) {

            MilkPortalParticle particle = new MilkPortalParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }
}
