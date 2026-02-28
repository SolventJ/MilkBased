package com.github.solventj.milkbased.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlockProperties {
    public static BlockBehaviour.Properties curdBlock() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.CLAY)
                .instrument(NoteBlockInstrument.FLUTE)
                .strength(0.6f)
                .sound(SoundType.GRAVEL);
    }

    public static BlockBehaviour.Properties cheeseBlock() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_YELLOW)
                .instrument(NoteBlockInstrument.COW_BELL)
                .strength(0.6f)
                .sound(SoundType.FROGLIGHT);
    }

    public static BlockBehaviour.Properties cheesewoodSapling() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS)
                .pushReaction(PushReaction.DESTROY);
    }

    public static BlockBehaviour.Properties cheesewoodLeaves() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(0.2f)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(ModBlockProperties::never)
                .isViewBlocking(ModBlockProperties::never)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor(ModBlockProperties::never);
    }

    public static BlockBehaviour.Properties cheesewoodLog() {
        return BlockBehaviour.Properties.of()
                .mapColor(bs -> bs.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                        ? MapColor.WOOD : MapColor.PODZOL)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0f)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    public static BlockBehaviour.Properties cheesewood() {
        return cheesewoodLog()
                .mapColor(MapColor.PODZOL);
    }

    public static BlockBehaviour.Properties strippedCheesewood() {
        return cheesewood()
                .mapColor(MapColor.WOOD);
    }

    public static BlockBehaviour.Properties cheesePlanks() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0f, 3.0f)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    public static BlockBehaviour.Properties blueMold() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.GLOW_LICHEN)
                .replaceable()
                .noCollission()
                .strength(0.2f)
                .sound(SoundType.GLOW_LICHEN)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY);
    }


    public static Boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }
}
