package com.eltariel.mc.flattery.worldgen;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Generator for "Void" chunks.
 */
public class FlatteryVoidChunkGenerator implements net.minecraft.world.gen.IChunkGenerator {
    private World world;

    /**
     * Create a new FlatteryVoidChunkGenerator.
     * @param world The world to generate chunks in.
     */
    public FlatteryVoidChunkGenerator(World world) {
        this.world = world;
    }

    /**
     * Generate a void chunk at a given location.
     * @param x The X coordinate of the chunk.
     * @param z The Z coordinate of the chunk.
     * @return The chunk.
     */
    @Override
    public Chunk generateChunk(int x, int z) {
        Chunk chunk = new Chunk(world, x, z);
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
