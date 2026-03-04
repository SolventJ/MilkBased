package com.github.solventj.milkbased.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class PlombirButtonBlock extends ButtonBlock {
    private final BlockSetType type;
    private final int ticksToStayPressed;

    public PlombirButtonBlock(Properties properties) {
        this(BlockSetType.SPRUCE, 20, properties);
    }

    public PlombirButtonBlock(BlockSetType setType, int ticks, Properties properties) {
        super(setType, ticks, properties);
        type = setType;
        ticksToStayPressed = ticks;
    }

    @Override
    protected void checkPressed(BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        AbstractArrow abstractarrow = type.canButtonBeActivatedByArrows()
                ? level.getEntitiesOfClass(AbstractArrow.class, state.getShape(level, pos).bounds().move(pos))
                .stream().findFirst().orElse(null) : null;
        boolean arrow = abstractarrow != null;
        boolean isPowered = state.getValue(POWERED);
        if (arrow != isPowered) {
            level.destroyBlock(pos, false);
            this.updateNeighbours(state, level, pos);
            this.playSound(null, level, pos, arrow);
            level.gameEvent(abstractarrow, arrow ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
        }

        if (arrow) {
            level.scheduleTick(new BlockPos(pos), this, this.ticksToStayPressed);
        }
    }

    private void updateNeighbours(BlockState state, Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
    }
}
