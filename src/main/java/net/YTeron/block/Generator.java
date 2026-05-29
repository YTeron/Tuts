package net.YTeron.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraft.util.RandomSource;

public class Generator extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE = Block.box(3.0, 0.0, 3.0, 14.0, 9.0, 14.0);

    private static final int RADIUS = 10;
    private static final int CHECK_INTERVAL = 20; // Проверка каждую секунду

    public Generator() {
        super(Properties.of()
                .copy(Blocks.CAMPFIRE)
                .mapColor(MapColor.COLOR_RED)
                .strength(3.0f, 10.0f)
                .sound(SoundType.LANTERN)
                .lightLevel(state -> state.getValue(CLICKED) ? 6 : 0)
                .noOcclusion()
        );
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(CLICKED, false)
        );
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

    // Метод синхронизации обогревателей с состоянием генератора
    private void syncObogrev2(ServerLevel level, BlockPos center) {
        boolean isGeneratorOn = level.getBlockState(center).getValue(CLICKED);

        for (int x = -RADIUS; x <= RADIUS; x++) {
            for (int y = -RADIUS; y <= RADIUS; y++) {
                for (int z = -RADIUS; z <= RADIUS; z++) {
                    BlockPos targetPos = center.offset(x, y, z);
                    BlockState targetState = level.getBlockState(targetPos);
                    Block targetBlock = targetState.getBlock();

                    if (targetState.getBlock() instanceof Obogrev2 && !targetPos.equals(center)) {
                        boolean currentState = targetState.getValue(Obogrev2.ACTIVE);
                        if (currentState != isGeneratorOn) {
                            level.setBlock(targetPos, targetState.setValue(Obogrev2.ACTIVE, isGeneratorOn), 3);
                        }
                    }
                    if (targetBlock == Blocks.REDSTONE_LAMP) {
                        boolean isLampOn = targetState.getValue(RedstoneLampBlock.LIT);
                        if (isLampOn != isGeneratorOn) {
                            level.setBlock(targetPos, targetState.setValue(RedstoneLampBlock.LIT, isGeneratorOn), 3);
                        }
                    }
                }
            }
        }
    }

    // Тиковый метод - вызывается КАЖДУЮ СЕКУНДУ пока генератор включён
    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(CLICKED)) {
            syncObogrev2(pLevel, pPos);
            pLevel.scheduleTick(pPos, this, CHECK_INTERVAL);
        }
    }

    @NotNull
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            boolean currentState = pState.getValue(CLICKED);
            ServerLevel serverLevel = (ServerLevel) pLevel;

            if (!currentState) {
                serverLevel.scheduleTick(pPos, this, CHECK_INTERVAL);
            }

            pLevel.setBlock(pPos, pState.setValue(CLICKED, !currentState), 3);
            syncObogrev2(serverLevel, pPos); // Сразу синхронизируем
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, CLICKED);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(CLICKED)) {
            double x = pPos.getX() + 0.5D;
            double y = pPos.getY() + 0.4D;
            double z = pPos.getZ() + 0.5D;
            pLevel.addParticle(ParticleTypes.SMOKE,
                    x + (pRandom.nextDouble() - 0.5D) * 0.6D,
                    y + pRandom.nextDouble() * 0.4D,
                    z + (pRandom.nextDouble() - 0.5D) * 0.6D,
                    0.0D, 0.05D, 0.0D);
        }
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
        if (!pLevel.isClientSide() && pState.getValue(CLICKED)) {
            pLevel.scheduleTick(pPos, this, CHECK_INTERVAL);
        }
    }
}