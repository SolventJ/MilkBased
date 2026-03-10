package com.github.solventj.milkbased.entity.boat;

import com.github.solventj.milkbased.entity.ModEntities;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PlombirChestBoat extends MilkChestBoat {

    public PlombirChestBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public PlombirChestBoat(Level level, double x, double y, double z) {
        super(ModEntities.PLOMBIR_CHEST_BOAT.get(), level, x, y, z);
    }

    @Override
    public String getBoatType() {
        return PlombirBoat.type;
    }

    @Override
    public @NotNull Item getDropItem() {
        return ModItems.PLOMBIR_CHEST_BOAT.get();
    }
}
