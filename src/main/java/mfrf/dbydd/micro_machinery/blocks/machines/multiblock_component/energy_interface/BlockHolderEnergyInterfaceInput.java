package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component.energy_interface;

import mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component.BlockUtilPlaceHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockHolderEnergyInterfaceInput extends BlockUtilPlaceHolder {

    public BlockHolderEnergyInterfaceInput() {
        super(Block.Properties.create(Material.IRON), "energy_interface_input", true, true, false);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        TileEnergyInterface tileEnergyInterface = new TileEnergyInterface();
        tileEnergyInterface.canExtract = false;
        tileEnergyInterface.canReceive = true;
        tileEnergyInterface.markDirty();
        return tileEnergyInterface;
    }

    @Override
    public void LinkToMainPart(BlockPos pos, World world, int arg1, int arg2, String arg3) {

    }
}
