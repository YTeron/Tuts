package net.YTeron;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static net.YTeron.IndoorChecker.*;

public class ModifBlocks {
    public static int Material(Player player) {
        Level world = player.level();
        BlockPos playerPos = player.blockPosition();
        int totalValue = 0;

        // Проверяем блоки вокруг игрока на наличие "строительных" материалов
        for (int dx = -4; dx <= 4; dx++) {
            for (int dy = -1; dy <= 4; dy++) {
                for (int dz = -4; dz <= 4; dz++) {
                    BlockPos checkPos = playerPos.offset(dx, dy, dz);
                    BlockState state = world.getBlockState(checkPos);


                    if (dx == 0 && dy == 0 && dz == 0) {
                        continue;
                    }

                    if (Planks(state)) {
                        totalValue += 35;
                    } else if (isBuildingBlock(state)) {
                        totalValue += 15;
                    } else if (WOOL(state))//шерсть
                    {
                        totalValue += 50;
                    } else if (Ice(state)) {
                        totalValue -= 25;
                    } else if (TempObject(state)) {
                        totalValue += 500;
                    }
                    else if (FireB(state)) {
                        totalValue += 1000;
                    }
                    else if (Air(state)) {
                        totalValue -= 20;
                    } else if (Snow(state)) {
                        totalValue -= 10;
                    } else if (SnowB(state)) {
                        if (dy <= -1) {
                            totalValue -= 20;
                        } if(dy>=0) {
                            totalValue += 30;
                        }
                    }
                    else {
                        // Все остальные блоки
                        totalValue -= 5;
                    }
                }
            }
        }
        return totalValue;
    }
    public static boolean Planks(BlockState state) {
        net.minecraft.world.level.block.Block block = state.getBlock();
        if (block == net.minecraft.world.level.block.Blocks.OAK_PLANKS) return true;
        if (block == net.minecraft.world.level.block.Blocks.SPRUCE_PLANKS) return true;
        if (block == net.minecraft.world.level.block.Blocks.BIRCH_PLANKS) return true;
        if (block == net.minecraft.world.level.block.Blocks.JUNGLE_PLANKS) return true;
        if (block == net.minecraft.world.level.block.Blocks.ACACIA_PLANKS) return true;
        if (block == net.minecraft.world.level.block.Blocks.CHERRY_PLANKS) return true;
        if (block == net.minecraft.world.level.block.Blocks.DARK_OAK_PLANKS) return true;
        if (block == net.minecraft.world.level.block.Blocks.MANGROVE_PLANKS) return true;
        if (block == net.minecraft.world.level.block.Blocks.BAMBOO_PLANKS) return true;

        return false;
    }
    public static boolean SnowB(BlockState state) {
        net.minecraft.world.level.block.Block block = state.getBlock();
        if (block == net.minecraft.world.level.block.Blocks.SNOW_BLOCK) return true;
        return false;
    } public static boolean Snow(BlockState state) {
        net.minecraft.world.level.block.Block block = state.getBlock();
        if (block == net.minecraft.world.level.block.Blocks.SNOW) return true;
        return false;
    }
    public static boolean Ice(BlockState state) {
        net.minecraft.world.level.block.Block block = state.getBlock();
        if (block == net.minecraft.world.level.block.Blocks.BLUE_ICE) return true;
        if (block == net.minecraft.world.level.block.Blocks.ICE) return true;
        if (block == net.minecraft.world.level.block.Blocks.FROSTED_ICE) return true;
        if (block == net.minecraft.world.level.block.Blocks.PACKED_ICE) return true;
        return false;
    }
    public static boolean WOOL(BlockState state) {
        net.minecraft.world.level.block.Block block = state.getBlock();
        if (block == net.minecraft.world.level.block.Blocks.WHITE_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.ORANGE_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.MAGENTA_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.LIGHT_GRAY_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.GREEN_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.RED_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.YELLOW_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.BLACK_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.BLUE_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.BROWN_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.PURPLE_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.GRAY_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.LIGHT_BLUE_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.CYAN_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.PINK_WOOL) return true;
        if (block == net.minecraft.world.level.block.Blocks.LIME_WOOL) return true;

        return false;
    }
    public static boolean isBuildingBlock(BlockState state) {
        net.minecraft.world.level.block.Block block = state.getBlock();

        if (block == net.minecraft.world.level.block.Blocks.STONE) return true;
        if (block == net.minecraft.world.level.block.Blocks.COBBLESTONE) return true;
        if (block == net.minecraft.world.level.block.Blocks.STONE_BRICKS) return true;

        if (block == net.minecraft.world.level.block.Blocks.BRICKS) return true;
        if (block == net.minecraft.world.level.block.Blocks.NETHER_BRICKS) return true;

        return false;
    }
    public static boolean Ignore(BlockState state) {
        net.minecraft.world.level.block.Block block = state.getBlock();
        return state.is(Blocks.GLASS) ||
                state.is(Blocks.IRON_BARS) ||
                state.is(Blocks.OAK_FENCE) ||
                state.is(Blocks.AIR);

    }

    public static boolean FireB(BlockState state) {
        // Постоянные источники тепла
        if (state.is(Blocks.FIRE) ||
                state.is(Blocks.CAMPFIRE)) {
            return true;
        }

        return false;
    }


    public static boolean TempObject(BlockState state) {
        // Постоянные источники тепла
        if (
                state.is(Blocks.SOUL_FIRE) ||
                state.is(Blocks.SOUL_CAMPFIRE) ||
                state.is(Blocks.LAVA) ||
                state.is(Blocks.MAGMA_BLOCK) ||
                state.is(Blocks.TORCH) ||
                state.is(Blocks.SOUL_TORCH) ||
                state.is(Blocks.LANTERN) ||
                state.is(Blocks.SOUL_LANTERN)) {
            return true;
        }

        // Активные печи
        if (state.getBlock() instanceof AbstractFurnaceBlock &&
                state.getValue(AbstractFurnaceBlock.LIT)) {
            return true;
        }


        return false;
    }
    public static boolean Air(BlockState state) {
        net.minecraft.world.level.block.Block block = state.getBlock();
        if (block == net.minecraft.world.level.block.Blocks.AIR) return true;
        if (block == net.minecraft.world.level.block.Blocks.CAVE_AIR) return true;
        if (block == net.minecraft.world.level.block.Blocks.VOID_AIR) return true;


        return false;
    }
}
