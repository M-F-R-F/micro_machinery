package mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.blast_furnace;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_main_parts.MMMultiBlockTileMainPartBase;
import mfrf.dbydd.micro_machinery.interfaces.IMultiBlockMainPart;
import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlocks;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class TileBlastFurnace extends MMMultiBlockTileMainPartBase implements INamedContainerProvider, ITickableTileEntity{
    private static HashMap<BlockPos, ArrayList<Consumer<World>>> DelegateMap = new HashMap<>();

    public TileBlastFurnace() {
        super(Registered_Tileentitie_Types.TILE_BLAST_FURNACE.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {

        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
    }

    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }

    @Override
    public ITextComponent getDisplayName() {
        return RegisteredBlocks.BLAST_FURNACE.getNameTextComponent();
    }

    @Override
    public void tick() {

    }

    @Override
    public void addDelegate(Consumer<World> function) {
        BlockPos pos = this.getPos();
        if (DelegateMap.containsKey(pos)) {
            DelegateMap.get(pos).add(function);
        } else {
            ArrayList<Consumer<World>> list = new ArrayList<>();
            list.add(function);
            DelegateMap.put(pos, list);
        }
    }

    @Override
    public void onBreak(World worldIn, BlockPos pos, PlayerEntity player, BlockState state, ItemStack stack) {
        BlockPos blockPos = getPos();
        ArrayList<Consumer<World>> breakBlockDelegates = DelegateMap.get(blockPos);
        for (Consumer<World> breakBlockDelegate : breakBlockDelegates) {
            breakBlockDelegate.accept(worldIn);
        }

        DelegateMap.remove(blockPos);
    }
}
