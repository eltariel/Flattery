package com.eltariel.mc.flattery.worldgen;

import com.eltariel.mc.flattery.worldgen.FlatteryChunkGenWrapper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IChunkGenerator;

/**
 * A Minecraft world type that only generates blocks where Z == 0.
 */
public class ExtraFlatteryWorldType extends WorldType {
    public static final String WORLD_TYPE_NAME = "EXTRA_FLATTERY";

    /**
     * Create and register the world type.
     */
    public ExtraFlatteryWorldType() {
        super(WORLD_TYPE_NAME);
    }

    /**
     * Gets the chunk generator.
     * @param world The world to generate in.
     * @param generatorOptions generator options.
     * @return The default chunk generator, wrapped in a Flattery one.
     */
    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new FlatteryChunkGenWrapper(super.getChunkGenerator(world, generatorOptions), world, true);
    }
}