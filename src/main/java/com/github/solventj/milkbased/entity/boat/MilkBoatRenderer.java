package com.github.solventj.milkbased.entity.boat;

import com.github.solventj.milkbased.MilkBased;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;

public class MilkBoatRenderer extends BoatRenderer {

    private final Pair<ResourceLocation, ListModel<Boat>> modelWithLocation;

    public MilkBoatRenderer(EntityRendererProvider.Context context, boolean isChestBoat, String name) {
        super(context, isChestBoat);

        modelWithLocation = Pair.of(
                getTextureLocation(name, isChestBoat),
                createBoatModel(context, isChestBoat)
        );
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, boolean chestBoat) {
        ModelLayerLocation layerLocation = chestBoat
                ? ModelLayers.createChestBoatModelName(Boat.Type.OAK)
                : ModelLayers.createBoatModelName(Boat.Type.OAK);

        ModelPart modelpart = context.bakeLayer(layerLocation);
        return chestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    private static ResourceLocation getTextureLocation(String name, boolean chestBoat) {
        var location = ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name);
        return chestBoat
                ? location.withPrefix("textures/entity/chest_boat/").withSuffix(".png")
                : location.withPrefix("textures/entity/boat/").withSuffix(".png");
    }

    @Override
    public @NotNull Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@NotNull Boat boat) {
        return modelWithLocation;
    }
}
