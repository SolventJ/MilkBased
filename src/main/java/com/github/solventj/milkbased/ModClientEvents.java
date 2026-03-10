package com.github.solventj.milkbased;

import com.github.solventj.milkbased.block_entity.ModBlockEntities;
import com.github.solventj.milkbased.entity.ModEntities;
import com.github.solventj.milkbased.entity.boat.CheeseBoat;
import com.github.solventj.milkbased.entity.boat.MilkBoatRenderer;
import com.github.solventj.milkbased.entity.boat.PlombirBoat;
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
        ItemBlockRenderTypes.setRenderLayer(ModFluids.MILK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_MILK.get(), RenderType.translucent());

        Sheets.addWoodType(ModWoodTypes.CHEESEWOOD);
        Sheets.addWoodType(ModWoodTypes.PLOMBIR);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.HANGING_SIGN.get(), HangingSignRenderer::new);

        event.registerEntityRenderer(ModEntities.CHEESE_BOAT.get(),
                ctx -> new MilkBoatRenderer(ctx, false, CheeseBoat.type));
        event.registerEntityRenderer(ModEntities.CHEESE_CHEST_BOAT.get(),
                ctx -> new MilkBoatRenderer(ctx, true, CheeseBoat.type));

        event.registerEntityRenderer(ModEntities.PLOMBIR_BOAT.get(),
                ctx -> new MilkBoatRenderer(ctx, false, PlombirBoat.type));
        event.registerEntityRenderer(ModEntities.PLOMBIR_CHEST_BOAT.get(),
                ctx -> new MilkBoatRenderer(ctx, true, PlombirBoat.type));
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.MILK_SPLASH.get(), WaterDropParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.MILK_PORTAL.get(), MilkPortalParticle.Provider::new);
    }

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
        }, ModFluidTypes.MILK.get());
    }
}