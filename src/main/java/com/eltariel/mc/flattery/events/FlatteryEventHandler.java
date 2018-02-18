package com.eltariel.mc.flattery.events;

import com.eltariel.mc.flattery.Utils;
import com.eltariel.mc.flattery.worldgen.ExtraFlatteryWorldType;
import com.eltariel.mc.flattery.worldgen.FlatteryWorldType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldType;
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
        WorldType wt = event.getEntityPlayer().world.getWorldType();

        if(wt instanceof FlatteryWorldType) {
            if (Utils.ChunkZFromBlockPos(spawn) != 0) {
                spawn = new BlockPos(spawn.getX(), spawn.getY(), Math.abs(spawn.getZ() % 16));
                event.getEntityPlayer().setSpawnPoint(spawn, event.isForced());
            }
        }
        else if(wt instanceof ExtraFlatteryWorldType) {
            if (spawn.getZ() != 0) {
                System.out.println("Spawn: " + spawn.getX() +","+ spawn.getY() +","+ spawn.getZ());
                spawn = new BlockPos(spawn.getX(), spawn.getY(), 0);
                System.out.println("Fixing Spawn to: " + spawn.getX() +","+ spawn.getY() +","+ spawn.getZ());
                event.getEntityPlayer().setSpawnPoint(spawn, event.isForced());
            }
        }
    }
}
