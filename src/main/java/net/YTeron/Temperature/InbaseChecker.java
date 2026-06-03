package net.YTeron.Temperature;

import net.YTeron.Temperature.Modif.BloockModif;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import static net.YTeron.Temperature.Modif.BloockModif.getBlockType;

public class InbaseChecker {
    //Проверяет, есть ли твёрдые блоки в определённом направлении от игрока на определённой высоте.
    private static int hasBlock(Level world, BlockPos start,int dx, int dy,int dz) {
        for (int i = 1; i <= 5; i++) {
            BlockPos checkPos = start.offset(dx*i, dy, dz*i);
            BlockState state = world.getBlockState(checkPos);

            if (state.isAir()) continue;


            String blockId = ForgeRegistries.BLOCKS.getKey(state.getBlock()).getPath();
            BloockModif.BlockM blockType = BloockModif.getBlockType(blockId);

            if (state.isSolid()) {
                return 1;
            }

        }
        return 0;
    }
    // Проверяет, окружён ли игрок блоками со всех 4 сторон
    public static boolean IPB(Player player) {
        int dist = getDistanceToBlockUp(player);
        int px =0;
        int pz =0;
        int ox =0;
        int oz =0;
        if (dist==-1) {
            return false;
        }
        Level world = player.level();
        BlockPos pos = player.blockPosition();
        for (int y = 1; y <= dist; y++) {
            if (hasBlock(world,pos,1,y-1,0) == 1)
                px++;
            if (hasBlock(world,pos,0,y-1,1) == 1)
                pz++;
            if (hasBlock(world,pos,-1,y-1,0) == 1)
                ox++;
            if (hasBlock(world,pos,0,y-1,-1) == 1)
                oz++;
        }
        if(px==dist&&pz==dist && ox==dist&&oz==dist)
            return true;
        return false;
    }
    // Ищет расстояние от головы игрока до ближайшего блока над ним
    public static int getDistanceToBlockUp(Player player) {
        Level world = player.level();
        BlockPos pos = player.blockPosition();

        for (int i = 1; i <= 5; i++) {
            BlockPos checkPos = pos.above(i);
            BlockState state = world.getBlockState(checkPos);

            if (!state.isAir()) {
                return i;
            }
        }
        return -1;
    }

    //Вычисляет суммарную температуру всех блоков вокруг игрока в заданном радиусе.
    public static int BlockR(int radius, Player player) {
        Level world = player.level();
        BlockPos playerPos = player.blockPosition();
        int totalTemperature = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -1; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = playerPos.offset(x, y, z);
                    BlockState blockState = world.getBlockState(checkPos);
                    Block block = blockState.getBlock();
                    String blockId = ForgeRegistries.BLOCKS.getKey(block).getPath();
                    BloockModif.BlockM blockType = getBlockType(blockId);

                    int blockTemp = blockType.getBaseTemperature(player);


                    if (y == -1 && blockType == BloockModif.BlockM.SNOW) {
                        totalTemperature -= blockTemp;
                    } else
                    {
                        totalTemperature += blockTemp;
                    }
                    if(hasBlock(world,playerPos,0,y-1,0) == 1) {
                        switch (blockType) {
                            case DEEPF -> totalTemperature += 15;
                            case SNOW -> totalTemperature += 5;
                            case ICE -> totalTemperature += 10;
                            default -> {}
                        }
                    }

                }
            }
        }

        return totalTemperature;
    }
}
