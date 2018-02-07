package com.eltariel.mc.flattery;

import net.minecraft.util.math.BlockPos;

/**
 * Simple utilities that I don't have a better home for yet.
 */
public class Utils {
    /**
     * Calculate the chunk Z coordinate for a BlockPos.
     * @param position The BlockPos to locate.
     * @return The Chunk's Z coordinate.
     */
    public static int ChunkZFromBlockPos(BlockPos position) {
        return position.getZ()/16;
    }
}
