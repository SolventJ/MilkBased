package com.github.solventj.milkbased;

import com.github.solventj.milkbased.block_entity.ModBlockEntities;
import com.github.solventj.milkbased.fluid.ModFluidTypes;
import com.github.solventj.milkbased.fluid.ModFluids;
import com.github.solventj.milkbased.particle.MilkPortalParticle;
import com.github.solventj.milkbased.particle.ModParticleTypes;
import com.github.solventj.milkbased.util.ModWoodTypes;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.WaterDropParticle;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

@EventBusSubscriber(modid = MilkBased.MOD_ID, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    private static void onClientSetup(FMLClientSetupEvent event) {
        Sheets.addWoodType(ModWoodTypes.CHEESEWOOD);
        ItemBlockRenderTypes.setRenderLayer(ModFluids.MILK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_MILK.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.CHEESE_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CHEESE_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.MILK_RAIN.get(), WaterDropParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.MILK_PORTAL.get(), MilkPortalParticle.Provider::new);
    }

//    private static boolean _inMilk;
//    private static boolean _isUnderMilk;
//
//    @SubscribeEvent
//    private static void milkFluidSounds(ClientTickEvent.Post event) {
//        Minecraft mc = Minecraft.getInstance();
//        if (mc.player == null || mc.level == null) return;
//
//        LocalPlayer player = mc.player;
//
//        boolean inMilk = player.isInFluidType(ModFluidTypes.MILK_FLUID_TYPE.get());
//        boolean isUnderMilk = player.getEyeInFluidType().equals(ModFluidTypes.MILK_FLUID_TYPE.get());
//
//        if (!_inMilk && inMilk) {
//            Vec3 vec3 = player.getDeltaMovement();
//            float f1 = Math.min(1.0F, (float) Math.sqrt(vec3.x * vec3.x * 0.2F + vec3.y * vec3.y + vec3.z * vec3.z * 0.2f) * 0.2f);
//            player.playSound(SoundEvents.PLAYER_SPLASH, f1, 1.0F +
//                    (RandomSource.create().nextFloat() - RandomSource.create().nextFloat()) * 0.4f);
//        }
//
//        if (player.isSpectator()) return;
//
//        if (!_isUnderMilk && isUnderMilk) {
//            player.level().playLocalSound(player.getX(), player.getY(), player.getZ(),
//                    SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundSource.AMBIENT, 1.0F, 1.0F, false);
//            mc.getSoundManager()
//                    .play(new UnderwaterAmbientSoundInstances.UnderwaterAmbientSoundInstance(player));
//        }
//
//        if (_isUnderMilk && !isUnderMilk) {
//            player.level().playLocalSound(player.getX(), player.getY(), player.getZ(),
//                    SoundEvents.AMBIENT_UNDERWATER_EXIT, SoundSource.AMBIENT,
//                    1.0F, 1.0F, false);
//        }
//
//        _inMilk = inMilk;
//        _isUnderMilk = isUnderMilk;
//    }

    @SubscribeEvent
    private static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL =
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "block/milk_still");
            private static final ResourceLocation FLOWING =
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "block/milk_flow");
            private static final ResourceLocation OVERLAY =
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "block/milk_overlay");
            private static final ResourceLocation MISC =
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "textures/misc/undermilk.png");

            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return STILL;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return FLOWING;
            }

            @Override
            public @NotNull ResourceLocation getOverlayTexture() {
                return OVERLAY;
            }

            @Override
            public @NotNull ResourceLocation getRenderOverlayTexture(@NotNull Minecraft mc) {
                return MISC;
            }

            @Override
            public int getTintColor() {
                return 0xFFFFFFFF;
            }

            @Override
            public @NotNull Vector3f modifyFogColor(
                    @NotNull Camera camera, float partialTick, @NotNull ClientLevel level, int renderDistance,
                    float darkenWorldAmount, @NotNull Vector3f fluidFogColor) {
                return new Vector3f(1, 1, 1);
            }

            @Override
            public void modifyFogRender(
                    @NotNull Camera camera, FogRenderer.@NotNull FogMode mode, float renderDistance,
                    float partialTick, float nearDistance, float farDistance, @NotNull FogShape shape) {
                RenderSystem.setShaderFogStart(-8.0f);
                RenderSystem.setShaderFogEnd(20.0f);
                RenderSystem.setShaderFogShape(FogShape.CYLINDER);
            }
        }, ModFluidTypes.MILK_TYPE.get());
    }
}