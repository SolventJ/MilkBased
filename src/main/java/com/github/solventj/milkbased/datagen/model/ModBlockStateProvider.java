package com.github.solventj.milkbased.datagen.model;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
        simpleBlock(ModBlocks.MILKSTONE.get());
        simpleBlock(ModBlocks.COBBLED_MILKSTONE.get());
        simpleBlock(ModBlocks.CHEESE_BLOCK.get());
        simpleTranslucentBlock(ModBlocks.WHEY_BLOCK.get());

        fluidBlock(ModBlocks.MILK.get());
        fullCauldron(ModBlocks.MILK_CAULDRON.get());

        glowLichenBlock(ModBlocks.BLUE_MOLD.get());
        simpleBlock(ModBlocks.MOLDY_COBBLED_MILKSTONE.get());

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

        signBlock(ModBlocks.CHEESE_SIGN.get(), ModBlocks.CHEESE_WALL_SIGN.get(), cheesePlanks);
        hangingSignBlock(ModBlocks.CHEESE_HANGING_SIGN.get(), ModBlocks.CHEESE_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.STRIPPED_CHEESEWOOD_LOG.get()));

        netherPortalBlock(ModBlocks.MILK_PORTAL.get());

        simpleBlock(ModBlocks.GORGONZOLA.get());
        grassBlock(ModBlocks.GORGONZOLA_TURF.get(), ModBlocks.GORGONZOLA.get());

        simpleBlock(ModBlocks.PLOMBIR_SNOW.get());

        crossBlock(ModBlocks.PLOMBIR_SAPLING.get());
        simpleBlock(ModBlocks.PLOMBIR_LEAVES.get());
        logBlock(ModBlocks.PLOMBIR_LOG.get());
        logBlock(ModBlocks.STRIPPED_PLOMBIR_LOG.get());
        woodBlock(ModBlocks.PLOMBIR_WOOD.get(), ModBlocks.PLOMBIR_LOG.get());
        woodBlock(ModBlocks.STRIPPED_PLOMBIR_WOOD.get(), ModBlocks.STRIPPED_PLOMBIR_LOG.get());

        simpleBlock(ModBlocks.PLOMBIR_PLANKS.get());
        var plombirPlanks = blockTexture(ModBlocks.PLOMBIR_PLANKS.get());

        stairsBlock(ModBlocks.PLOMBIR_STAIRS.get(), plombirPlanks);
        slabBlock(ModBlocks.PLOMBIR_SLAB.get(), plombirPlanks, plombirPlanks);
        fenceBlock(ModBlocks.PLOMBIR_FENCE.get(), plombirPlanks);
        fenceGateBlock(ModBlocks.PLOMBIR_FENCE_GATE.get(), plombirPlanks);
        basicDoor(ModBlocks.PLOMBIR_DOOR.get());
        basicTrapDoor(ModBlocks.PLOMBIR_TRAPDOOR.get());
        pressurePlateBlock(ModBlocks.PLOMBIR_PRESSURE_PLATE.get(), plombirPlanks);
        buttonBlock(ModBlocks.PLOMBIR_BUTTON.get(), plombirPlanks);

        signBlock(ModBlocks.PLOMBIR_SIGN.get(), ModBlocks.PLOMBIR_WALL_SIGN.get(), plombirPlanks);
        hangingSignBlock(ModBlocks.PLOMBIR_HANGING_SIGN.get(), ModBlocks.PLOMBIR_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.STRIPPED_PLOMBIR_LOG.get()));
    }

    public void crossBlock(Block block) {
        var texture = blockTexture(block);
        simpleBlock(block, models().cross(texture.getPath(), texture)
                .renderType("cutout"));
    }

    private void simpleTranslucentBlock(Block block) {
        this.simpleBlock(block, models()
                .cubeAll(blockTexture(block).getPath(), this.blockTexture(block))
                .renderType("translucent"));
    }

    private void grassBlock(Block block, Block bottomBlock) {
        var loc = blockTexture(block);
        var side = loc.withSuffix("_side");
        var top = loc.withSuffix("_top");
        var bottom = blockTexture(bottomBlock);
        simpleBlock(block, models().cubeBottomTop(loc.getPath(), side, bottom, top));
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
        var bottom = location.withSuffix("_bottom");
        var top = location.withSuffix("_top");
        doorBlockWithRenderType(block, bottom, top, "cutout");
    }

    private void basicTrapDoor(TrapDoorBlock block) {
        var texture = blockTexture(block);
        trapdoorBlockWithRenderType(block, texture, true, "cutout");
    }

    private void netherPortalBlock(Block block) {
        ResourceLocation portalLoc = blockTexture(block);
        ModelFile portalModel_ns = models()
                .withExistingParent(portalLoc.getPath() + "_ns", mcLoc("block/nether_portal_ns"))
                .texture("particle", portalLoc).texture("portal", portalLoc).renderType("translucent");
        ModelFile portalModel_ew = models()
                .withExistingParent(portalLoc.getPath() + "_ew", mcLoc("block/nether_portal_ew"))
                .texture("particle", portalLoc).texture("portal", portalLoc).renderType("translucent");

        var portalVariantBuilder = getVariantBuilder(block);
        portalVariantBuilder.partialState().with(NetherPortalBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(portalModel_ns).addModel();
        portalVariantBuilder.partialState().with(NetherPortalBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(portalModel_ew).addModel();
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
