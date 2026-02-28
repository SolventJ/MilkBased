package com.github.solventj.milkbased.block;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.custom.*;
import com.github.solventj.milkbased.fluid.ModFluids;
import com.github.solventj.milkbased.item.ModItems;
import com.github.solventj.milkbased.util.ModWoodTypes;
import com.github.solventj.milkbased.worldgen.tree.ModTreeGrowers;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MilkBased.MOD_ID);

    public static final DeferredBlock<Block> CURD_BLOCK = registerSimpleBlock(
            "curd_block", ModBlockProperties.curdBlock());

    public static final DeferredBlock<Block> CHEESE_BLOCK = registerSimpleBlock(
            "cheese_block", ModBlockProperties.cheeseBlock());

    public static final DeferredBlock<LiquidBlock> MILK = BLOCKS.register(ModFluids.MILK_ID,
            () -> new LiquidBlock(ModFluids.SOURCE_MILK_FLUID.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));

    public static final DeferredBlock<MilkCauldronBlock> MILK_CAULDRON = BLOCKS.register("milk_cauldron",
            () -> new MilkCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)));

    public static final DeferredBlock<Block> CHEESEWOOD_SAPLING = registerBlock("cheesewood_sapling",
            () -> new CheesewoodSapling(ModTreeGrowers.CHEESEWOOD, ModBlockProperties.cheesewoodSapling()));

    public static final DeferredBlock<Block> CHEESEWOOD_LEAVES = registerBlock(
            "cheesewood_leaves", LeavesBlock::new, ModBlockProperties.cheesewoodLeaves());

    public static final DeferredBlock<RotatedPillarBlock> CHEESEWOOD_LOG = registerBlock(
            "cheesewood_log", RotatedPillarBlock::new, ModBlockProperties.cheesewoodLog());

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHEESEWOOD_LOG = registerBlock(
            "stripped_cheesewood_log", RotatedPillarBlock::new, ModBlockProperties.strippedCheesewood());

    public static final DeferredBlock<RotatedPillarBlock> CHEESEWOOD = registerBlock(
            "cheesewood", RotatedPillarBlock::new, ModBlockProperties.cheesewood());

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHEESEWOOD = registerBlock(
            "stripped_cheesewood", RotatedPillarBlock::new, ModBlockProperties.strippedCheesewood());

    public static final DeferredBlock<Block> CHEESE_PLANKS = registerSimpleBlock(
            "cheese_planks", ModBlockProperties.cheesePlanks());

    public static final DeferredBlock<StairBlock> CHEESE_STAIRS = registerBlock("cheese_stairs",
            () -> new StairBlock(CHEESE_PLANKS.get().defaultBlockState(), ModBlockProperties.cheesePlanks()));

    public static final DeferredBlock<SlabBlock> CHEESE_SLAB = registerBlock(
            "cheese_slab", SlabBlock::new, ModBlockProperties.cheesePlanks());

    public static final DeferredBlock<FenceBlock> CHEESE_FENCE = registerBlock(
            "cheese_fence", FenceBlock::new, ModBlockProperties.cheesePlanks());

    public static final DeferredBlock<FenceGateBlock> CHEESE_FENCE_GATE = registerBlock("cheese_fence_gate",
            () -> new FenceGateBlock(ModWoodTypes.CHEESEWOOD, ModBlockProperties.cheesePlanks()));

    public static final DeferredBlock<DoorBlock> CHEESE_DOOR = registerBlock("cheese_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));

    public static final DeferredBlock<TrapDoorBlock> CHEESE_TRAPDOOR = registerBlock("cheese_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));

    public static final DeferredBlock<PressurePlateBlock> CHEESE_PRESSURE_PLATE = registerBlock("cheese_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, ModBlockProperties.cheesePlanks()));

    public static final DeferredBlock<ButtonBlock> CHEESE_BUTTON = registerBlock("cheese_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10, ModBlockProperties.cheesePlanks()));

    public static final DeferredBlock<StandingSignBlock> CHEESE_SIGN = BLOCKS.register("cheese_sign",
            () -> new CheeseSignBlock(ModWoodTypes.CHEESEWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));

    public static final DeferredBlock<WallSignBlock> WALL_CHEESE_SIGN = BLOCKS.register("cheese_wall_sign",
            () -> new WallCheeseSignBlock(ModWoodTypes.CHEESEWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));

    public static final DeferredBlock<CeilingHangingSignBlock> CHEESE_HANGING_SIGN =
            BLOCKS.register("cheese_hanging_sign", () -> new CheeseHangingSignBlock(
                    ModWoodTypes.CHEESEWOOD, ModBlockProperties.cheesePlanks()));

    public static final DeferredBlock<WallHangingSignBlock> WALL_CHEESE_HANGING_SIGN =
            BLOCKS.register("cheese_wall_hanging_sign", () -> new WallCheeseHangingSignBlock(
                    ModWoodTypes.CHEESEWOOD, ModBlockProperties.cheesePlanks()));

    public static final DeferredBlock<Block> BLUE_MOLD = registerBlock(
            "blue_mold", GlowLichenBlock::new, ModBlockProperties.blueMold());

    public static <B extends Block> DeferredBlock<B> registerBlock(String name, Supplier<B> bSupplier) {
        DeferredBlock<B> block = BLOCKS.register(name, bSupplier);
        registerBlockItem(name, block);
        return block;
    }

    public static <B extends Block> DeferredBlock<B> registerBlock(
            String name, Function<BlockBehaviour.Properties, ? extends B> func, BlockBehaviour.Properties properties) {
        DeferredBlock<B> block = BLOCKS.register(name, () -> func.apply(properties));
        registerBlockItem(name, block);
        return block;
    }

    public static DeferredBlock<Block> registerSimpleBlock(String name, BlockBehaviour.Properties properties) {
        DeferredBlock<Block> block = BLOCKS.registerSimpleBlock(name, properties);
        registerBlockItem(name, block);
        return block;
    }

    public static <B extends Block> void registerBlockItem(String name, DeferredBlock<B> block) {
        ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
