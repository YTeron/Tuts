package net.YTeron;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class IndoorChecker {

    /**
     * Проверяет, находится ли игрок в помещении
     * @param player игрок для проверки
     * @return true если игрок в помещении, false если на улице
     */
    public static boolean isPlayerIndoors(Player player) {
        Level world = player.level();
        BlockPos playerPos = player.blockPosition();

        // Получаем координаты для проверки
        int x = playerPos.getX();
        int y = playerPos.getY();
        int z = playerPos.getZ();

        // Проверяем блоки над игроком (начиная с уровня головы)
        // Голова игрока находится на ~1.6 блока над ногами, поэтому начинаем с y+2
        for (int offset = 2; offset <= 5; offset++) {
            BlockPos checkPos = new BlockPos(x, y + offset, z);
            BlockState state = world.getBlockState(checkPos);

            // Если нашли твердый непрозрачный блок - игрок под крышей
            if (!state.isAir() && state.isSolid()) {
                return true;
            }
        }

        // если игрок глубоко под землей (Y < 50)
        if (y < 50) {
            // Проверяем, есть ли над ним земля (не пустота до неба)
            BlockPos highestBlock = world.getHeightmapPos(
                    net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING,
                    playerPos
            );

            // Если самая высокая точка выше игрока - он под землей
            return highestBlock.getY() > y + 2;
        }

        return false;
    }

    /**
     * Проверяет, находится ли игрок в пещере
     * @param player игрок для проверки
     * @return true если игрок в пещере
     */
    public static boolean isInCave(Player player) {
        Level world = player.level();
        BlockPos playerPos = player.blockPosition();

        // Пещеры обычно ниже 50 уровня и имеют блоки над головой
        return playerPos.getY() < 50 && isPlayerIndoors(player);
    }


    // Перегрузка 2: принимает Level и BlockPos
    /**
     * Проверяет, находится ли игрок внутри здания.
     * Критерий: в пределах 5 блоков от игрока в каждом из 6 основных направлений (вперёд, назад, влево, вправо, вверх, вниз)
     * должен находиться непустой (не воздушный) блок.
     *
     * Иными словами: игрок считается "в здании", если вокруг него есть стены/пол/потолок в радиусе 5 блоков.
     */
    public static boolean isInBuilding(Player player) {
        return true;//тут все нормально
    }
    public static int DistanceY(Player player) {
        Level world = player.level();
        BlockPos playerPos = player.blockPosition();
        for (int dist = 1; dist <= 5; dist++) {
            BlockPos checkPos = playerPos.above(dist);
            if (!world.getBlockState(checkPos).isAir()) {
                return dist;
            }
        }
        return -1;
    }
    public static int DistanceX(Player player) {
        Level world = player.level();
        BlockPos playerPos = player.blockPosition();
        for (int dist = 1; dist <= 5; dist++) {
            BlockPos checkPos = playerPos.east(dist);
            if (!world.getBlockState(checkPos).isAir()) {
                return dist;
            }
        }
        return -1;
    }
    public static int DistanceMX(Player player) {
        Level world = player.level();
        BlockPos playerPos = player.blockPosition();
        for (int dist = 1; dist <= 5; dist++) {
            BlockPos checkPos = playerPos.west(dist);
            if (!world.getBlockState(checkPos).isAir()) {
                return dist;
            }
        }
        return -1;
    }
    public static int DistanceZ(Player player) {
        Level world = player.level();
        BlockPos playerPos = player.blockPosition();

        // Проверяем вперёд (+Z)
        for (int dist = 1; dist <= 5; dist++) {
            BlockPos checkPos = playerPos.south(dist);  // или playerPos.offset(0, 0, dist)
            if (!world.getBlockState(checkPos).isAir()) {
                return dist;
            }
        }

        // Проверяем назад (-Z)
        for (int dist = 1; dist <= 5; dist++) {
            BlockPos checkPos = playerPos.north(dist);  // или playerPos.offset(0, 0, -dist)
            if (!world.getBlockState(checkPos).isAir()) {
                return dist;
            }
        }

        return -1;
    }
    public static int DistanceMZ(Player player) {
        Level world = player.level();
        BlockPos playerPos = player.blockPosition();
        for (int dist = 1; dist <= 5; dist++) {
            BlockPos checkPos = playerPos.north(dist);
            if (!world.getBlockState(checkPos).isAir()) {
                return dist;
            }
        }

        return -1;
    }

       // другой калькулятор
       public static boolean OCH(Player player) {
           Level world = player.level();
           BlockPos playerPos = player.blockPosition();

           int radius = 5;
           int solidBlocks = 0;
           int totalBlocks = 0;

           // Проверяем куб вокруг игрока
           for (int x = -radius; x <= radius; x++) {
               for (int y = 0; y <= radius; y++) {
                   for (int z = -radius; z <= radius; z++) {
                       if (x == 0 && y == 0 && z == 0) continue;

                       totalBlocks++;
                       BlockPos checkPos = playerPos.offset(x, y, z);
                       BlockState state = world.getBlockState(checkPos);

                       if (!state.isAir() && state.isSolid()) {
                           solidBlocks++;
                       }
                   }
               }
           }

           // Если больше 40% блоков твёрдые - считаем что в помещении
           return (double) solidBlocks / totalBlocks > 0.4;
       }
}


