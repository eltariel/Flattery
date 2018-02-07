package com.eltariel.mc.flattery.events;

import com.eltariel.mc.flattery.Utils;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Handler for Forge events.
 */
public class FlatteryEventHandler {
    /**
     * Fix player spawn location so it's always on the world strip.
     * @param event The player spawn event.
     */
    @SubscribeEvent
    public void onPlayerSetSpawn(PlayerSetSpawnEvent event) {
        BlockPos spawn = event.getNewSpawn();
        if(Utils.ChunkZFromBlockPos(spawn) != 0)
        {
            spawn = new BlockPos(spawn.getX(), spawn.getY(), Math.abs(spawn.getZ()%16));
            event.getEntityPlayer().setSpawnPoint(spawn, event.isForced());
        }
    }
}
