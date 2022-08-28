package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component.energy_interface;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component.BlockAccessoryPlaceHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockHolderEnergyInterfaceOutput extends BlockAccessoryPlaceHolder {

    public BlockHolderEnergyInterfaceOutput() {
        super(Properties.create(Material.IRON), "energy_interface_output", true, true, false);
    }


    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        TileEnergyInterface tileEnergyInterface = new TileEnergyInterface();
        tileEnergyInterface.canExtract = true;
        tileEnergyInterface.canReceive = false;
        tileEnergyInterface.markDirty();
        return tileEnergyInterface;
    }

    @Override
    public void LinkToMainPart(BlockPos pos, World world, String arg1, String arg2, String arg3) {

    }
}
