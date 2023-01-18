package mfrf.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.energy_interface;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.multiblock_component.BlockAccessoryPlaceHolder;
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockHolderEnergyInterfaceInput extends BlockAccessoryPlaceHolder {

    public BlockHolderEnergyInterfaceInput() {
        super(Block.Properties.create(Material.IRON), "energy_interface_input", true, true, false);
    }

    @Override
    public BlockEntity createBlockEntity(BlockState state, IBlockReader world) {
        TileEnergyInterface tileEnergyInterface = new TileEnergyInterface();
        tileEnergyInterface.canExtract = false;
        tileEnergyInterface.canReceive = true;
        tileEnergyInterface.setChanged();
        return tileEnergyInterface;
    }

    @Override
    public void LinkToMainPart(BlockPos pos, World world, String arg1, String arg2, String arg3) {

    }
}
