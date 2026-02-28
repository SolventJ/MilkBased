package com.github.solventj.milkbased.datagen;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MilkBased.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.CURD_BLOCK.get());
        simpleBlock(ModBlocks.CHEESE_BLOCK.get());

        crossBlock(ModBlocks.CHEESEWOOD_SAPLING.get());
        simpleBlock(ModBlocks.CHEESEWOOD_LEAVES.get());
        logBlock(ModBlocks.CHEESEWOOD_LOG.get());
        logBlock(ModBlocks.STRIPPED_CHEESEWOOD_LOG.get());
        woodBlock(ModBlocks.CHEESEWOOD.get(), ModBlocks.CHEESEWOOD_LOG.get());
        woodBlock(ModBlocks.STRIPPED_CHEESEWOOD.get(), ModBlocks.STRIPPED_CHEESEWOOD_LOG.get());

        simpleBlock(ModBlocks.CHEESE_PLANKS.get());
        var cheesePlanks = blockTexture(ModBlocks.CHEESE_PLANKS.get());

        stairsBlock(ModBlocks.CHEESE_STAIRS.get(), cheesePlanks);
        slabBlock(ModBlocks.CHEESE_SLAB.get(), cheesePlanks, cheesePlanks);
        fenceBlock(ModBlocks.CHEESE_FENCE.get(), cheesePlanks);
        fenceGateBlock(ModBlocks.CHEESE_FENCE_GATE.get(), cheesePlanks);
        basicDoor(ModBlocks.CHEESE_DOOR.get());
        basicTrapDoor(ModBlocks.CHEESE_TRAPDOOR.get());
        pressurePlateBlock(ModBlocks.CHEESE_PRESSURE_PLATE.get(), cheesePlanks);
        buttonBlock(ModBlocks.CHEESE_BUTTON.get(), cheesePlanks);

        signBlock(ModBlocks.CHEESE_SIGN.get(), ModBlocks.WALL_CHEESE_SIGN.get(), cheesePlanks);
        hangingSignBlock(ModBlocks.CHEESE_HANGING_SIGN.get(), ModBlocks.WALL_CHEESE_HANGING_SIGN.get(),
                blockTexture(ModBlocks.STRIPPED_CHEESEWOOD_LOG.get()));

        fluidBlock(ModBlocks.MILK.get());
        fullCauldron(ModBlocks.MILK_CAULDRON.get());

        glowLichenBlock(ModBlocks.BLUE_MOLD.get());
    }

    public void crossBlock(Block block) {
        var texture = blockTexture(block);
        simpleBlock(block, models()
                .getBuilder(texture.getPath())
                .parent(models().getExistingFile(mcLoc("block/cross")))
                .renderType("cutout")
                .texture("cross", texture));
    }

    public void fluidBlock(Block block) {
        var texture = blockTexture(block);
        simpleBlock(block, models().getBuilder(texture.getPath())
                .texture("particle", texture.withSuffix("_still")));
    }

    public void fullCauldron(Block block) {
        simpleBlock(block, models().withExistingParent(blockTexture(block).getPath(), mcLoc("block/template_cauldron_full"))
                .texture("content", modLoc("block/milk_still")));
    }

    public void woodBlock(RotatedPillarBlock block, Block parent) {
        var tex = blockTexture(parent);
        axisBlock(block, tex, tex);
    }

    private void basicDoor(DoorBlock block) {
        var location = blockTexture(block);
        doorBlock(block, location.withSuffix("_bottom"), location.withSuffix("_top"));

        models().getBuilder(location + "_bottom_left").renderType("cutout");
        models().getBuilder(location + "_bottom_left_open").renderType("cutout");
        models().getBuilder(location + "_bottom_right").renderType("cutout");
        models().getBuilder(location + "_bottom_right_open").renderType("cutout");
        models().getBuilder(location + "_top_left").renderType("cutout");
        models().getBuilder(location + "_top_left_open").renderType("cutout");
        models().getBuilder(location + "_top_right").renderType("cutout");
        models().getBuilder(location + "_top_right_open").renderType("cutout");
    }

    private void basicTrapDoor(TrapDoorBlock block) {
        var texture = blockTexture(block);
        trapdoorBlock(block, texture, true);

        models().getBuilder(texture + "_bottom").renderType("cutout");
        models().getBuilder(texture + "_top").renderType("cutout");
        models().getBuilder(texture + "_open").renderType("cutout");
    }

    private void glowLichenBlock(Block block) {
        var texture = blockTexture(block);

        ModelFile model = models().withExistingParent(texture.getPath(), mcLoc("block/glow_lichen"))
                .renderType("cutout")
                .texture("particle", texture)
                .texture("glow_lichen", texture);

        var builder = getMultipartBuilder(block);

        builder.part()
                .modelFile(model)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.NORTH), true);

        builder.part()
                .modelFile(model)
                .rotationY(180)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.SOUTH), true);

        builder.part()
                .modelFile(model)
                .rotationY(270)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.WEST), true);

        builder.part()
                .modelFile(model)
                .rotationY(90)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.EAST), true);

        builder.part()
                .modelFile(model)
                .rotationX(270)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.UP), true);

        builder.part()
                .modelFile(model)
                .rotationX(90)
                .addModel()
                .condition(GlowLichenBlock.getFaceProperty(Direction.DOWN), true);
    }
}
