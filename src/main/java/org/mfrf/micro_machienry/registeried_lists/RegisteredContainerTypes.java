package org.mfrf.micro_machienry.registeried_lists;

import mfrf.dbydd.micro_machinery.MicroMachinery;
import mfrf.dbydd.micro_machinery.blocks.machines.single_block_machines.klin.TileKlin;
import mfrf.dbydd.micro_machinery.gui.atomization.AtomizationContainer;
import mfrf.dbydd.micro_machinery.gui.blast_furnace.BlastFurnaceContainer;
import mfrf.dbydd.micro_machinery.gui.centrifuge.CentrifugeContainer;
import mfrf.dbydd.micro_machinery.gui.cutter.CutterContainer;
import mfrf.dbydd.micro_machinery.gui.electrolysis.ElectrolysisContainer;
import mfrf.dbydd.micro_machinery.gui.generator.GeneratorContainer;
import mfrf.dbydd.micro_machinery.gui.klin.KlinContainer;
import mfrf.dbydd.micro_machinery.gui.lathe.LatheContainer;
import mfrf.dbydd.micro_machinery.gui.weld.WeldContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisteredContainerTypes {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, MicroMachinery.NAME);

    public static RegistryObject<ContainerType<KlinContainer>> KLINCONTAINER = CONTAINER_TYPE_REGISTER.register("klincontainer", () -> IForgeContainerType.create((windowId, inv, data) -> new KlinContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld(), new TileKlin.KlinProgressBarNumArray())));
    public static final RegistryObject<ContainerType<GeneratorContainer>> GENERATOR = CONTAINER_TYPE_REGISTER.register("generatorcontainer", () -> IForgeContainerType.create((windowId, inv, data) -> new GeneratorContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld())));
    public static final RegistryObject<ContainerType<LatheContainer>> LATHE_CONTAINER = CONTAINER_TYPE_REGISTER.register("lathe", () -> IForgeContainerType.create((windowId, inv, data) -> new LatheContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld())));
    public static final RegistryObject<ContainerType<BlastFurnaceContainer>> BLAST_FURNACE_CONTAINER = CONTAINER_TYPE_REGISTER.register("blast_furnace_container", () -> IForgeContainerType.create((windowId, inv, data) -> new BlastFurnaceContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld())));
    public static final RegistryObject<ContainerType<ElectrolysisContainer>> ELECTROLYSIS_CONTAINER = CONTAINER_TYPE_REGISTER.register("electrolysis_container", () -> IForgeContainerType.create(((windowId, inv, data) -> new ElectrolysisContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld()))));
    public static final RegistryObject<ContainerType<CutterContainer>> CUTTER_CONTAINER = CONTAINER_TYPE_REGISTER.register("cutter_container", () -> IForgeContainerType.create(((windowId, inv, data) -> new CutterContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld()))));
    public static final RegistryObject<ContainerType<CentrifugeContainer>> CENTRIFUGE_CONTAINER = CONTAINER_TYPE_REGISTER.register("centrifuge_container", () -> IForgeContainerType.create(((windowId, inv, data) -> new CentrifugeContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld()))));
    public static final RegistryObject<ContainerType<AtomizationContainer>> ATOMIZATION_CONTAINER = CONTAINER_TYPE_REGISTER.register("atomization_container", () -> IForgeContainerType.create(((windowId, inv, data) -> new AtomizationContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld()))));
    public static final RegistryObject<ContainerType<WeldContainer>> WELD_CONTAINER = CONTAINER_TYPE_REGISTER.register("weld_container", () -> IForgeContainerType.create(((windowId, inv, data) -> new WeldContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld()))));
}
