package com.github.solventj.milkbased.entity.boat;

import com.github.solventj.milkbased.entity.ModEntities;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PlombirBoat extends MilkBoat {

    public PlombirBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public PlombirBoat(Level level, double x, double y, double z) {
        super(ModEntities.PLOMBIR_BOAT.get(), level, x, y, z);
    }

    public static final String type = "plombir";

    @Override
    public String getBoatType() {
        return type;
    }

    @Override
    public @NotNull Item getDropItem() {
        return ModItems.PLOMBIR_BOAT.get();
    }
}
