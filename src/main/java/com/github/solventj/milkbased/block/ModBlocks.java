package com.github.solventj.milkbased.block;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.block.custom.*;
import com.github.solventj.milkbased.block.custom.sign.CheeseHangingSignBlock;
import com.github.solventj.milkbased.block.custom.sign.CheeseSignBlock;
import com.github.solventj.milkbased.block.custom.sign.WallCheeseHangingSignBlock;
import com.github.solventj.milkbased.block.custom.sign.WallCheeseSignBlock;
import com.github.solventj.milkbased.fluid.ModFluids;
import com.github.solventj.milkbased.item.ModItems;
import com.github.solventj.milkbased.util.ModWoodTypes;
import com.github.solventj.milkbased.block.grower.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MilkBased.MOD_ID);

    public static final DeferredBlock<Block> CURD_BLOCK = registerSimpleBlock(
            "curd_block", BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOL)
                    .instrument(NoteBlockInstrument.FLUTE)
                    .strength(0.6f)
                    .sound(SoundType.GRAVEL));

    public static final DeferredBlock<Block> CURD_STONE = registerSimpleBlock(
            "curd_stone", BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 6.0f));

    public static final DeferredBlock<Block> COBBLED_CURD_STONE = registerSimpleBlock(
            "cobbled_curd_stone", BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F));

    public static final DeferredBlock<Block> CHEESE_BLOCK = registerSimpleBlock(
            "cheese_block", BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .instrument(NoteBlockInstrument.COW_BELL)
                    .strength(0.6f)
                    .sound(SoundType.FROGLIGHT));

    public static final DeferredBlock<LiquidBlock> MILK = BLOCKS.register(ModFluids.MILK_ID,
            () -> new LiquidBlock(ModFluids.MILK.get(), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW)
                    .replaceable()
                    .noCollission()
                    .strength(100.0F)
                    .pushReaction(PushReaction.DESTROY)
                    .noLootTable()
                    .liquid()
                    .sound(SoundType.EMPTY)));

    public static final DeferredBlock<MilkCauldronBlock> MILK_CAULDRON = BLOCKS.register("milk_cauldron",
            () -> new MilkCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)));

    public static final DeferredBlock<Block> BLUE_MOLD = registerBlock(
            "blue_mold", GlowLichenBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GLOW_LICHEN)
                    .replaceable()
                    .noCollission()
                    .strength(0.2f)
                    .sound(SoundType.GLOW_LICHEN)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY));

    public static final DeferredBlock<Block> MOLDY_COBBLED_CURD_STONE = registerSimpleBlock(
            "moldy_cobbled_curd_stone", BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F));

    public static final DeferredBlock<Block> CHEESEWOOD_SAPLING = registerBlock("cheesewood_sapling",
            () -> new CheesewoodSapling(ModTreeGrowers.CHEESEWOOD, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> CHEESEWOOD_LEAVES = registerBlock(
            "cheesewood_leaves", LeavesBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(0.2f)
                    .randomTicks()
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(ModBlocks::never)
                    .isViewBlocking(ModBlocks::never)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(ModBlocks::never));

    public static final DeferredBlock<RotatedPillarBlock> CHEESEWOOD_LOG = registerBlock(
            "cheesewood_log", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(bs -> bs.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                            ? MapColor.WOOD : MapColor.PODZOL)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava());

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHEESEWOOD_LOG = registerBlock(
            "stripped_cheesewood_log", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava());

    public static final DeferredBlock<RotatedPillarBlock> CHEESEWOOD = registerBlock(
            "cheesewood", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PODZOL)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava());

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHEESEWOOD = registerBlock(
            "stripped_cheesewood", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava());

    public static final DeferredBlock<Block> CHEESE_PLANKS = registerSimpleBlock(
            "cheese_planks", BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0f, 3.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava());

    public static final DeferredBlock<StairBlock> CHEESE_STAIRS = registerBlock("cheese_stairs",
            () -> new StairBlock(CHEESE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_STAIRS)));

    public static final DeferredBlock<SlabBlock> CHEESE_SLAB = registerBlock("cheese_slab",
            SlabBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_SLAB));

    public static final DeferredBlock<FenceBlock> CHEESE_FENCE = registerBlock("cheese_fence",
            FenceBlock::new, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_FENCE));

    public static final DeferredBlock<FenceGateBlock> CHEESE_FENCE_GATE = registerBlock("cheese_fence_gate",
            () -> new FenceGateBlock(ModWoodTypes.CHEESEWOOD, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_FENCE_GATE)));

    public static final DeferredBlock<DoorBlock> CHEESE_DOOR = registerBlock("cheese_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_DOOR)));

    public static final DeferredBlock<TrapDoorBlock> CHEESE_TRAPDOOR = registerBlock("cheese_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_TRAPDOOR)));

    public static final DeferredBlock<PressurePlateBlock> CHEESE_PRESSURE_PLATE = registerBlock(
            "cheese_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));

    public static final DeferredBlock<ButtonBlock> CHEESE_BUTTON = registerBlock("cheese_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_BUTTON)));

    public static final DeferredBlock<StandingSignBlock> CHEESE_SIGN = BLOCKS.register("cheese_sign",
            () -> new CheeseSignBlock(ModWoodTypes.CHEESEWOOD, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_SIGN)));

    public static final DeferredBlock<WallSignBlock> CHEESE_WALL_SIGN = BLOCKS.register("cheese_wall_sign",
            () -> new WallCheeseSignBlock(ModWoodTypes.CHEESEWOOD, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_WALL_SIGN)));

    public static final DeferredBlock<CeilingHangingSignBlock> CHEESE_HANGING_SIGN =
            BLOCKS.register("cheese_hanging_sign", () -> new CheeseHangingSignBlock(
                    ModWoodTypes.CHEESEWOOD, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_HANGING_SIGN)));

    public static final DeferredBlock<WallHangingSignBlock> CHEESE_WALL_HANGING_SIGN =
            BLOCKS.register("cheese_wall_hanging_sign", () -> new WallCheeseHangingSignBlock(
                    ModWoodTypes.CHEESEWOOD, BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final DeferredBlock<MilkPortalBlock> MILK_PORTAL =
            BLOCKS.registerBlock("milk_portal", MilkPortalBlock::new, BlockBehaviour.Properties.of()
                    .noCollission()
                    .randomTicks()
                    .strength(-1.0F)
                    .sound(SoundType.GLASS)
                    .lightLevel(state -> 11)
                    .pushReaction(PushReaction.BLOCK)
                    .noLootTable());

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

    public static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }
}
