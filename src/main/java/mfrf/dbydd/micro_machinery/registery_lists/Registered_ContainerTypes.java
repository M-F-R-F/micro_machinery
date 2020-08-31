package mfrf.dbydd.micro_machinery.registery_lists;

import mfrf.dbydd.micro_machinery.Micro_Machinery;
import mfrf.dbydd.micro_machinery.blocks.machines.generator.TileGenerator;
import mfrf.dbydd.micro_machinery.blocks.machines.klin.TileKlin;
import mfrf.dbydd.micro_machinery.gui.generator.GeneratorContainer;
import mfrf.dbydd.micro_machinery.gui.klin.KlinContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registered_ContainerTypes {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, Micro_Machinery.NAME);

    public static RegistryObject<ContainerType<KlinContainer>> KLINCONTAINER = CONTAINER_TYPE_REGISTER.register("klincontainer", () -> IForgeContainerType.create((windowId, inv, data) -> new KlinContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld(), new TileKlin.KlinProgressBarNumArray())));
    public static final RegistryObject<ContainerType<GeneratorContainer>> GENERATOR = CONTAINER_TYPE_REGISTER.register("generatorcontainer", ()->IForgeContainerType.create((windowId, inv, data) -> new GeneratorContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld(), new TileGenerator.GeneratorEnergyAndFuelIntArray())));

}
