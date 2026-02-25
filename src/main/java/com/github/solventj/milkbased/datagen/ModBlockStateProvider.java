package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.GlowLichenBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MilkBased.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.CURD_BLOCK.get());
        simpleBlock(ModBlocks.CHEESE_BLOCK.get());

        logBlock(ModBlocks.CHEESE_LOG.get());
        simpleBlock(ModBlocks.CHEESE_LEAVES.get());
        simpleBlock(ModBlocks.CHEESE_PLANKS.get());

        ModelFile blueMoldModel = models().getBuilder("block/blue_mold")
                .parent(models().getExistingFile(mcLoc("block/glow_lichen")))
                .renderType("cutout")
                .texture("particle", modLoc("block/blue_mold"))
                .texture("glow_lichen", modLoc("block/blue_mold"));

        MultiPartBlockStateBuilder builder = getMultipartBuilder(ModBlocks.BLUE_MOLD.get());

        builder.part()
                .modelFile(blueMoldModel)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.NORTH), true);

        builder.part()
                .modelFile(blueMoldModel)
                .rotationY(180) // <-- Вот магия!
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.SOUTH), true);

        builder.part()
                .modelFile(blueMoldModel)
                .rotationY(270)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.WEST), true);

        builder.part()
                .modelFile(blueMoldModel)
                .rotationY(90)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.EAST), true);

        builder.part()
                .modelFile(blueMoldModel)
                .rotationX(270)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.UP), true);

        builder.part()
                .modelFile(blueMoldModel)
                .rotationX(90)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.DOWN), true);
    }
}
