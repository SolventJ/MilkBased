package com.github.solventj.milkbased;

import com.github.solventj.milkbased.fluid.ModFluidTypes;
import com.github.solventj.milkbased.fluid.ModFluids;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = MilkBased.MOD_ID, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_MILK_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_MILK_FLUID.get(), RenderType.translucent());
    }
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation STILL =
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "block/milk_still");
            private static final ResourceLocation FLOWING =
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "block/milk_flow");
            private static final ResourceLocation OVERLAY =
                    ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, "block/milk_overlay");

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
            public int getTintColor() {
                return 0xFFFFFFFF;
            }
        }, ModFluidTypes.MILK_FLUID_TYPE.get());
    }
}