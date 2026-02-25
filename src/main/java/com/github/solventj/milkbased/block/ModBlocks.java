package com.github.solventj.milkbased.block;

import com.github.solventj.milkbased.MilkBased;
import com.github.solventj.milkbased.fluid.ModFluids;
import com.github.solventj.milkbased.item.ModItems;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MilkBased.MOD_ID);

    public static final DeferredBlock<Block> CURD_BLOCK = registerBlock("curd_block",
            () -> new Block(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> CHEESE_BLOCK = registerBlock("cheese_block",
            () -> new Block(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<LiquidBlock> MILK = BLOCKS.register(ModFluids.MILK_ID, () -> new LiquidBlock(
                    ModFluids.SOURCE_MILK_FLUID.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));

    public static final DeferredBlock<RotatedPillarBlock> CHEESE_LOG = registerBlock("cheese_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final DeferredBlock<Block> CHEESE_LEAVES = registerBlock("cheese_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<Block> CHEESE_PLANKS = registerBlock("cheese_planks",
            () -> new Block(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> BLUE_MOLD = registerBlock("blue_mold",
            () -> new GlowLichenBlock(BlockBehaviour.Properties.of().noCollission().noOcclusion()
                    .pushReaction(PushReaction.DESTROY)));

    public static <B extends Block> DeferredBlock<B> registerBlock(String name, Supplier<B> bSupplier) {
        DeferredBlock<B> block = BLOCKS.register(name, bSupplier);
        registerBlockItems(name, block);
        return block;
    }

    public static <B extends Block> void registerBlockItems(String name, DeferredBlock<B> block) {
        ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
