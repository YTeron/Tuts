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
            return highestBlock.getY() > y + 2;
        }

        return false;
    }

}


