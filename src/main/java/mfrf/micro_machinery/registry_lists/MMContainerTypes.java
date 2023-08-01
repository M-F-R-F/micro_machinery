package mfrf.micro_machinery.registry_lists;

import mfrf.micro_machinery.MicroMachinery;
import mfrf.micro_machinery.gui.atomization.AtomizationContainer;
import mfrf.micro_machinery.gui.centrifuge.CentrifugeContainer;
import mfrf.micro_machinery.gui.cutter.CutterContainer;
import mfrf.micro_machinery.gui.electrolysis.ElectrolysisContainer;
import mfrf.micro_machinery.gui.generator.GeneratorContainer;
import mfrf.micro_machinery.gui.klin.KlinContainer;
import mfrf.micro_machinery.gui.weld.WeldContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMContainerTypes {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPE_REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MicroMachinery.MODID);

    public static RegistryObject<MenuType<KlinContainer>> KLINCONTAINER = CONTAINER_TYPE_REGISTER.register("klincontainer", () -> IForgeMenuType.create((windowId, inv, data) -> new KlinContainer(windowId, inv, data.readBlockPos(), inv.player.level())));
    public static final RegistryObject<MenuType<GeneratorContainer>> GENERATOR = CONTAINER_TYPE_REGISTER.register("generatorcontainer", () -> IForgeMenuType.create((windowId, inv, data) -> new GeneratorContainer(windowId, inv, data.readBlockPos(), inv.player.level())));
    //        public static final RegistryObject<MenuType<LatheContainer>> LATHE_CONTAINER = CONTAINER_TYPE_REGISTER.register("lathe", () -> IForgeMenuType.create((windowId, inv, data) -> new LatheContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().level)));
//    public static final RegistryObject<MenuType<BlastFurnaceContainer>> BLAST_FURNACE_CONTAINER = CONTAINER_TYPE_REGISTER.register("blast_furnace_container", () -> IForgeMenuType.create((windowId, inv, data) -> new BlastFurnaceContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().level)));
    public static final RegistryObject<MenuType<ElectrolysisContainer>> ELECTROLYSIS_CONTAINER = CONTAINER_TYPE_REGISTER.register("electrolysis_container", () -> IForgeMenuType.create(((windowId, inv, data) -> new ElectrolysisContainer(windowId, inv, data.readBlockPos(), inv.player.level()))));
    public static final RegistryObject<MenuType<CutterContainer>> CUTTER_CONTAINER = CONTAINER_TYPE_REGISTER.register("cutter_container", () -> IForgeMenuType.create(((windowId, inv, data) -> new CutterContainer(windowId, inv, data.readBlockPos(), inv.player.level()))));
    public static final RegistryObject<MenuType<CentrifugeContainer>> CENTRIFUGE_CONTAINER = CONTAINER_TYPE_REGISTER.register("centrifuge_container", () -> IForgeMenuType.create(((windowId, inv, data) -> new CentrifugeContainer(windowId, inv, data.readBlockPos(), inv.player.level()))));
    public static final RegistryObject<MenuType<AtomizationContainer>> ATOMIZATION_CONTAINER = CONTAINER_TYPE_REGISTER.register("atomization_container", () -> IForgeMenuType.create(((windowId, inv, data) -> new AtomizationContainer(windowId, inv, data.readBlockPos(), inv.player.level()))));
    public static final RegistryObject<MenuType<WeldContainer>> WELD_CONTAINER = CONTAINER_TYPE_REGISTER.register("weld_container", () -> IForgeMenuType.create(((windowId, inv, data) -> new WeldContainer(windowId, inv, data.readBlockPos(), inv.player.level()))));
}
