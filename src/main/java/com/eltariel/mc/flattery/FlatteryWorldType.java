package com.eltariel.mc.flattery;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IChunkGenerator;

public class FlatteryWorldType extends WorldType {
    public FlatteryWorldType() {
        super("FLATTERY");
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new FlatteryChunkGenWrapper(super.getChunkGenerator(world, generatorOptions), world);
    }
}