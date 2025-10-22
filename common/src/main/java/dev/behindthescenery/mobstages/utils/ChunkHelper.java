package dev.behindthescenery.mobstages.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

import java.util.List;

public class ChunkHelper {

    public static Player getNearestPlayer(LevelAccessor level, BlockPos pos) {
        List<? extends Player> players = level.players();
        Player nearestPlayer = null;
        double minDistance = Double.MAX_VALUE;
        for (Player player : players) {
            final double distance = player.distanceToSqr(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPlayer = player;
            }
        }
        return nearestPlayer;
    }
}
