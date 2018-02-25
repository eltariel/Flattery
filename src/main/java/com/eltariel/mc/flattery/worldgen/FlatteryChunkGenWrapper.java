package com.eltariel.mc.flattery.worldgen;

import com.eltariel.mc.flattery.Utils;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrap an existing IChunkGenerator to allow restricting it to certain chunks only.
 */
public class FlatteryChunkGenWrapper implements IChunkGenerator {
    private final IChunkGenerator wrapped;
    private final IChunkGenerator voidGen;
    private final boolean singleBlock;

    private final Map<ChunkPos, Chunk> rawChunks = new HashMap<>();
    /**
     * Create a new FlatteryChunkGenWrapper.
     * @param wrapped The IChunkGenerator to wrap.
     * @param world The world that the wrapped chunk generator belongs to.
     */
    public FlatteryChunkGenWrapper(IChunkGenerator wrapped, World world, boolean singleBlock) {
        this.wrapped = wrapped;
        this.singleBlock = singleBlock;
        voidGen = new FlatteryVoidChunkGenerator(world);
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        Chunk chunk = getCachedChunk(x, z);
        return z == 0
                ? mangleChunk(chunk)
                : voidGen.generateChunk(x, z);
    }

    private Chunk getCachedChunk(int x, int z) {
        ChunkPos pos = new ChunkPos(x, z);
        Chunk chunk = rawChunks.getOrDefault(pos, null);
        if(chunk == null) {
            chunk = wrapped.generateChunk(x, z);
            rawChunks.put(pos, chunk);
        }
        return chunk;
    }

    private Chunk mangleChunk(Chunk chunk) {
        if(singleBlock) {
            for (int x = 0; x < 16; ++x) {
                for (int y = 0; y < 256; ++y) {
                    for (int z = 1; z < 16; ++z) {
                        chunk.setBlockState(new BlockPos(x,y,z), Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        return chunk;
    }

    @Override
    public void populate(int x, int z) {
        if (z == 0) {
            wrapped.populate(x, z);
        } else {
            voidGen.populate(x, z);
        }
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return z == 0
                ? wrapped.generateStructures(chunkIn, x, z)
                : voidGen.generateStructures(chunkIn, x, z);
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return Utils.ChunkZFromBlockPos(pos) == 0
                ? wrapped.getPossibleCreatures(creatureType, pos)
                : voidGen.getPossibleCreatures(creatureType, pos);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return Utils.ChunkZFromBlockPos(position) == 0
                ? wrapped.getNearestStructurePos(worldIn, structureName, position, findUnexplored)
                : voidGen.getNearestStructurePos(worldIn, structureName, position, findUnexplored);
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
        // Always recreate from wrapped generator
        Chunk c = getCachedChunk(x, z);
        wrapped.recreateStructures(c, x, z);
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return Utils.ChunkZFromBlockPos(pos) == 0
                ? wrapped.isInsideStructure(worldIn, structureName, pos)
                : voidGen.isInsideStructure(worldIn, structureName, pos);
    }

}
