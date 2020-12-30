package mfrf.dbydd.micro_machinery.blocks.machines.multiblock_component.energy_interface;

import mfrf.dbydd.micro_machinery.blocks.machines.MMMultiBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockEnergyInterface extends MMMultiBlockBase {
    public static BooleanProperty CAN_INPUT = BooleanProperty.create("can_input");
    public static BooleanProperty CAN_OUTPUT = BooleanProperty.create("can_output");

    public BlockEnergyInterface() {
        super(Block.Properties.create(Material.IRON), "energy_interface", true, false, false);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(CAN_INPUT);
        builder.add(CAN_OUTPUT);
        super.fillStateContainer(builder);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        TileEnergyInterface tileEnergyInterface = new TileEnergyInterface();
        tileEnergyInterface.canExtract = state.get(CAN_OUTPUT);
        tileEnergyInterface.canReceive = state.get(CAN_INPUT);
        tileEnergyInterface.markDirty();
        return tileEnergyInterface;
    }
}
