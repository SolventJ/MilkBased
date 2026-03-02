package com.github.solventj.milkbased.world.dimension;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

public class ModDimensionTypes {
    public static final ResourceKey<DimensionType> MILK_DIMENSION_TYPE_KEY =
            registerKey("milk_dimension");

    public static void bootstrap(BootstrapContext<DimensionType> context) {
        context.register(MILK_DIMENSION_TYPE_KEY, new DimensionType(
                OptionalLong.empty(),
                true, false,
                false, true, 1,
                true, false,
                -64, 384, 384,
                BlockTags.INFINIBURN_OVERWORLD,
                ResourceLocation.parse("minecraft:overworld"),
                0.1f,
                new DimensionType.MonsterSettings(false,
                        false, ConstantInt.of(0),
                        0
                )
        ));
    }

    public static ResourceKey<DimensionType> registerKey(String name) {
        return ResourceKey.create(Registries.DIMENSION_TYPE,
                ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, name));
    }
}
