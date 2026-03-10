package com.github.solventj.milkbased.entity.boat;

import com.github.solventj.milkbased.entity.ModEntities;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CheeseChestBoat extends MilkChestBoat {

    public CheeseChestBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public CheeseChestBoat(Level level, double x, double y, double z) {
        super(ModEntities.CHEESE_CHEST_BOAT.get(), level, x, y, z);
    }

    @Override
    public String getBoatType() {
        return CheeseBoat.type;
    }

    @Override
    public @NotNull Item getDropItem() {
        return ModItems.CHEESE_CHEST_BOAT.get();
    }
}
