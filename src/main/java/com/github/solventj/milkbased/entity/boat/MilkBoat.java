package com.github.solventj.milkbased.entity.boat;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MilkBoat extends Boat {
    public MilkBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public MilkBoat(EntityType<? extends Boat> entityType, Level level, double x, double y, double z) {
        this(entityType, level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public String getBoatType() {
        return null;
    }

    @Override
    public @NotNull Item getDropItem() {
        return Items.MILK_BUCKET;
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Type", getBoatType());
    }

    public interface BoatFactory {
        Boat create(Level level, double x, double y, double z);
    }
}
