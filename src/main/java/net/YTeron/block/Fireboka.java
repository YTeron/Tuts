package net.YTeron.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;

public class Fireboka extends HorizontalDirectionalBlock {
    private static int   ticks = 0;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 18.0, 14.0);

    public Fireboka() {
        super(Properties.of()
                .copy(Blocks.CAMPFIRE)
                .mapColor(MapColor.COLOR_RED)
                .strength(3.0f, 10.0f)
                .sound(SoundType.METAL)
                .lightLevel(state -> 15)
                .noOcclusion()
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return SHAPE;
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public void animateTick(BlockState state, net.minecraft.world.level.Level level, BlockPos pos, RandomSource random) {
        ticks++;
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);
        if (aboveState.isAir() || !aboveState.isCollisionShapeFullBlock(level, abovePos)) {
            level.addParticle(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    pos.getX() + 0.5,
                    pos.getY() + 1.5,
                    pos.getZ() + 0.5,
                    0.0,
                    0.05 + random.nextDouble() * 0.1,
                    0.0
            );
            level.addParticle(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    pos.getX() + 0.5,
                    pos.getY() + 1.5,
                    pos.getZ() + 0.5,
                    0.0,
                    0.05 + random.nextDouble() * 0.1,
                    0.0
            );
        }
        if (ticks==5) {
            ticks=0;
            Direction facing = state.getValue(FACING);
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 1.5;
            double z = pos.getZ() + 0.5;

            x += (random.nextDouble() - 0.5) * 0.6;
            y += (random.nextDouble() - 0.5) * 0.2;
            z += (random.nextDouble() - 0.5) * 0.6;

            level.addParticle(
                    ParticleTypes.FLAME,
                    x,
                    y,
                    z,
                    0.0,
                    0.0,
                    0.0
            );
        }
        if (random.nextInt(15) == 0) {
            Direction facing = state.getValue(FACING);

            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.8;
            double y = pos.getY() + 0.8 + random.nextDouble() * 0.4;
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.8;

            level.addParticle(
                    ParticleTypes.LAVA,
                    x,
                    y,
                    z,
                    0.0,
                    0.02 + random.nextDouble() * 0.05,
                    0.0
            );
        }
    }
}
