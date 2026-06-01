package net.YTeron.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
public class Obogrev2 extends HorizontalDirectionalBlock{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    private static final VoxelShape SHAPE_NORTH = Block.box(0.0, 0.0, 0.0, 4.0, 16.0, 16.0);
    private static final VoxelShape SHAPE_SOUTH = Block.box(12.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape SHAPE_WEST  = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 4.0);
    private static final VoxelShape SHAPE_EAST  = Block.box(0.0, 0.0, 12.0, 16.0, 16.0, 16.0);
    public Obogrev2() {
        super(Properties.of()
                .copy(Blocks.ANVIL)
                .mapColor(MapColor.COLOR_RED)
                .strength(3.0f, 10.0f)
                .sound(SoundType.ANVIL)
                .lightLevel(state -> state.getValue(ACTIVE) ? 12 : 4)
                .noOcclusion()
        );
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(ACTIVE, false)
        );
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
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(ACTIVE, false);  // ← добавлено
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVE);
    }
    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(ACTIVE)) {
            Direction facing = pState.getValue(FACING);
            double x = pPos.getX() + 0.5D;
            double y = pPos.getY() + 0.8D;
            double z = pPos.getZ() + 0.5D;

            switch (facing) {
                case NORTH -> z = pPos.getZ() + 1D;
                case SOUTH -> z = pPos.getZ() + 0D;
                case WEST -> x = pPos.getX() + 1D;
                case EAST -> x = pPos.getX() + 0D;
                default -> z = pPos.getX() + 1D;
            }

            if (pRandom.nextInt(2) == 0) {
                pLevel.addParticle(ParticleTypes.FLAME,
                        x + (pRandom.nextDouble() - 0.5D) * 0.3D,
                        y + pRandom.nextDouble() * 0.3D,
                        z + (pRandom.nextDouble() - 0.5D) * 0.3D,
                        0.0D, 0.02D, 0.0D);
            }
        }
    }
}

