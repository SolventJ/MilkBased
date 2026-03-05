package com.github.solventj.milkbased.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;

public class MilkBoat extends Boat {
    public MilkBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }
}
