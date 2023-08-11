package mfrf.micro_machinery.block.machines.multiblock_new_system.components.interfaces;

import net.minecraft.core.Vec3i;

public record ComponentEvent(MMTileMultiBlockComponentInterface sender, int changed, Vec3i key) {

}
