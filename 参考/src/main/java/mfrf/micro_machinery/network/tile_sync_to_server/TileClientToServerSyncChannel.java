package mfrf.micro_machinery.network.tile_sync_to_server;

import mfrf.micro_machinery.MicroMachinery;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class TileClientToServerSyncChannel {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(MicroMachinery.MODID + ":tile_client_to_server_sync"),
                () -> VERSION,
                VERSION::equals,
                VERSION::equals
        );
        INSTANCE.registerMessage(
                nextID(),
                TileClientToServerSyncPackage.class,
                TileClientToServerSyncPackage::toBytes,
                TileClientToServerSyncPackage::new,
                TileClientToServerSyncPackage::handler
        );
    }
}
