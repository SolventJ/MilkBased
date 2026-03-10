package com.github.solventj.milkbased.particle;

import com.github.solventj.milkbased.MilkBased;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;

import java.util.ArrayList;
import java.util.function.Function;

public class ModParticleProvider extends ParticleDescriptionProvider {
    public ModParticleProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(ModParticleTypes.MILK_SPLASH.get(), modList("milk_splash", 4));
        spriteSet(ModParticleTypes.MILK_PORTAL.get(), mcList("generic", 8));
    }

    private ArrayList<ResourceLocation> modList(String name, int count) {
        return forList(name, count, s -> ResourceLocation.fromNamespaceAndPath(MilkBased.MOD_ID, s));
    }

    private ArrayList<ResourceLocation> mcList(String name, int count) {
        return forList(name, count, ResourceLocation::withDefaultNamespace);
    }

    private ArrayList<ResourceLocation> forList(String name, int count, Function<String, ResourceLocation> func) {
        ArrayList<ResourceLocation> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            list.add(func.apply(name + "_" + i));
        }

        return list;
    }
}
