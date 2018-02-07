package com.eltariel.mc.flattery;

import com.eltariel.mc.flattery.events.FlatteryEventHandler;
import com.eltariel.mc.flattery.worldgen.FlatteryWorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Flattery.MODID, version = Flattery.VERSION, useMetadata = true)
public class Flattery
{
    public static final String MODID = "flattery";
    public static final String VERSION = "0.1";

    public Flattery() {
        MinecraftForge.EVENT_BUS.register(new FlatteryEventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("Registering new world type");
        new FlatteryWorldType();
    }
}
