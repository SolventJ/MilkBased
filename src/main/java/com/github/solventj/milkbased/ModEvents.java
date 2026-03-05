package com.github.solventj.milkbased;

import com.github.solventj.milkbased.block.ModBlocks;
import com.github.solventj.milkbased.entity.MilkBoat;
import com.github.solventj.milkbased.util.ModFluidInteractions;
import com.github.solventj.milkbased.world.dimension.portal.ModPortalShape;
import com.github.solventj.milkbased.item.ModItems;
import com.github.solventj.milkbased.util.ModCauldronInteractions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.Optional;

@EventBusSubscriber(modid = MilkBased.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    private static void onCommonSetup(FMLCommonSetupEvent event) {
        ModCauldronInteractions.bootstrap();
        ModFluidInteractions.bootstrap();
    }

    @SubscribeEvent
    private static void onMilkBucketUsedOnBlock(UseItemOnBlockEvent event) {
        if (!event.getUsePhase().equals(UseItemOnBlockEvent.UsePhase.BLOCK)) return;
        if (!event.getItemStack().is(Items.MILK_BUCKET)) return;

        Player player = event.getPlayer();
        if (player == null) return;

        Level level = event.getLevel();
        BlockPos pos = event.getPos();

        for (var dir : Direction.values()) {
            Optional<ModPortalShape> optional = ModPortalShape.findEmptyPortalShape(
                    level, pos.relative(dir), Direction.Axis.X);

            if (optional.isEmpty()) continue;

            optional.get().createPortalBlocks();
            level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!player.getAbilities().instabuild)
                player.setItemInHand(event.getHand(), new ItemStack(Items.BUCKET));

            break;
        }
    }

    @SubscribeEvent
    private static void onBucketUsedOnBlock(UseItemOnBlockEvent event) {
        if (!event.getUsePhase().equals(UseItemOnBlockEvent.UsePhase.BLOCK)) return;
        if (!event.getItemStack().is(Items.BUCKET)) return;

        Level level = event.getLevel();
        BlockPos pos = event.getPos();

        if (!level.getBlockState(pos).is(ModBlocks.MILK_PORTAL)) return;

        Player player = event.getPlayer();
        if (player == null) return;

        if (level.isClientSide)
            level.playSound(player, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        else {
            player.getItemInHand(event.getHand()).consume(1, player);
            player.addItem(new ItemStack(Items.MILK_BUCKET));
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
    }

    @SubscribeEvent
    private static void cheesewoodStrip(BlockEvent.BlockToolModificationEvent event) {
        if (!event.getItemAbility().name().equals("axe_strip")) return;
        BlockState state = event.getState();

        boolean isWood = state.is(ModBlocks.CHEESEWOOD);
        if (!isWood && !state.is(ModBlocks.CHEESEWOOD_LOG)) return;

        Block newBlock = isWood
                ? ModBlocks.STRIPPED_CHEESEWOOD.get()
                : ModBlocks.STRIPPED_CHEESEWOOD_LOG.get();

        event.setFinalState(newBlock.defaultBlockState().setValue(
                RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)));

        LevelAccessor levelAccessor = event.getLevel();
        if (levelAccessor.isClientSide()) return;

        Level level = (Level) levelAccessor;
        BlockPos pos = event.getPos();
        ItemStack drop = ModItems.CHECHIL.toStack();

        ItemEntity itemEntity = new ItemEntity(
                level,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                drop);

        levelAccessor.addFreshEntity(itemEntity);
    }

    @SubscribeEvent
    private static void plombirStrip(BlockEvent.BlockToolModificationEvent event) {
        if (!event.getItemAbility().name().equals("axe_strip")) return;
        BlockState state = event.getState();

        boolean isWood = state.is(ModBlocks.PLOMBIR_WOOD);
        if (!isWood && !state.is(ModBlocks.PLOMBIR_LOG)) return;

        Block newBlock = isWood
                ? ModBlocks.STRIPPED_PLOMBIR_WOOD.get()
                : ModBlocks.STRIPPED_PLOMBIR_LOG.get();

        event.setFinalState(newBlock.defaultBlockState().setValue(
                RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)));
    }
}
