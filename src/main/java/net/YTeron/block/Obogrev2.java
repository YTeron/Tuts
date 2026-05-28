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
public class Obogrev2 extends HorizontalDirectionalBlock{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE_NORTH = Block.box(0.0, 0.0, 0.0, 4.0, 16.0, 16.0);
    private static final VoxelShape SHAPE_SOUTH = Block.box(12.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape SHAPE_WEST  = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 4.0);
    private static final VoxelShape SHAPE_EAST  = Block.box(0.0, 0.0, 12.0, 16.0, 16.0, 16.0);
    public Obogrev2() {
        super(Properties.of()
                .copy(Blocks.ANVIL)
                .mapColor(MapColor.COLOR_RED)
                .lightLevel(state -> 6)
                .strength(3.0f, 10.0f)
                .sound(SoundType.ANVIL)
                .lightLevel(state -> 4)
                .noOcclusion()
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        Direction facing = state.getValue(FACING);
        switch (facing) {
            case EAST: return SHAPE_NORTH;
            case WEST: return SHAPE_SOUTH;
            case SOUTH:  return SHAPE_WEST;
            case NORTH:  return SHAPE_EAST;
            default:    return SHAPE_NORTH;
        }
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
}

