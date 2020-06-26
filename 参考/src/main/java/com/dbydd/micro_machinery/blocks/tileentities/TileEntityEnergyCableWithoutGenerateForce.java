package com.dbydd.micro_machinery.blocks.tileentities;

import com.dbydd.micro_machinery.EnumType.EnumMMFETileEntityStatus;
import com.dbydd.micro_machinery.energynetwork.EnergyNetWorkSpecialPackge;
import com.dbydd.micro_machinery.energynetwork.EnergyNetworkSign;
import com.dbydd.micro_machinery.energynetwork.SurrondingsState;
import com.dbydd.micro_machinery.exceptions.ErrorNumberException;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.interfaces.IMMFETransfer;
import com.dbydd.micro_machinery.util.EnergyNetWorkUtils;
import com.dbydd.micro_machinery.worldsaveddatas.EnergyNetSavedData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TileEntityEnergyCableWithoutGenerateForce extends MMFEMachineBaseV2 implements IMMFETransfer {

    protected int sign;
    protected int sequence;

    public TileEntityEnergyCableWithoutGenerateForce(int maxEnergyCapacity) {
        super(maxEnergyCapacity, new SurrondingsState());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.sequence = compound.getInteger("sequence");
        this.sign = compound.getInteger("EnergyNetSign");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("sequence", sequence);
        compound.setInteger("EnergyNetSign", sign);
        return super.writeToNBT(compound);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) return (T) this;
        return null;
    }

    @Override
    public EnumMMFETileEntityStatus updateStatue() {
        return EnumMMFETileEntityStatus.CABLE;
    }


    @Override
    public EnergyNetWorkSpecialPackge generatePackage() {
        return null;
    }

    @Override
    public void notifyNearbyCablesUpdateSign(int sign, int sequence, EnumFacing fromFacing) {
//        for (EnumFacing face : EnergyNetWorkUtils.getFacings()) {
//            TileEntity te = world.getTileEntity(pos.offset(face));
//            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
//                if (((TileEntityEnergyCableWithoutGenerateForce) te).getSign() != Sign) {
//                    ((TileEntityEnergyCableWithoutGenerateForce) te).setSign(Sign);
//                }
//            }
//        }
        List<EnumFacing> list = getNearbyCablesWithoutFacing(pos, world).getFacings(EnumMMFETileEntityStatus.CABLE);
        list.remove(fromFacing);
        for (EnumFacing facing : list) {
            ((TileEntityEnergyCableWithoutGenerateForce) world.getTileEntity(pos.offset(facing))).notifyByNearbyCablesUpdateSign(sign, sequence, facing.getOpposite());
        }

    }

    @Override
    public void notifyNearbyCableUpdateSign(int sign, int sequence, EnumFacing toFacing) {
        ((TileEntityEnergyCableWithoutGenerateForce) world.getTileEntity(pos.offset(toFacing))).notifyByNearbyCablesUpdateSign(sign, sequence, toFacing.getOpposite());
    }

    @Override
    public void notifyByNearbyCablesUpdateSign(int sign, int sequence, EnumFacing fromFacing) {

        this.sign = sign;
        this.sequence = sequence + 1;
        markDirty();
        notifyNearbyCablesUpdateSign(sign, this.sequence, fromFacing);
    }

    public void notifyNearbyCableMergeSign(int sign, int sequence, EnumFacing fromFacing) {
        List<EnumFacing> list = getNearbyCablesWithoutFacing(pos, world).getFacings(EnumMMFETileEntityStatus.CABLE);
        for (EnumFacing facing : list) {
            TileEntity tile = world.getTileEntity(pos.offset(facing));
            if (tile instanceof TileEntityEnergyCableWithoutGenerateForce) {
                int tileSign = ((TileEntityEnergyCableWithoutGenerateForce) tile).getSign();
                if (tileSign != this.sign) {
                    EnergyNetSavedData.mergeEnergyNet(this.sign, tileSign, world);
                    notifyNearbyCableUpdateSign(sign, sequence, facing);
                }
            }
        }
    }

    public int getSign() {
        return sign;
    }

    public void onBlockPlacedBy() {

        UpdateSequence();
        updateState();
        boolean hasupdated = false;

        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te != null) {
                if (!(te instanceof TileEntityEnergyCableWithoutGenerateForce)) {
                    if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite()) && te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).canReceive()) {
                        transferToTickable();
                        hasupdated = true;
                        break;
                    }
                }
            }
        }

        int i = 0;
        boolean hasUpdated = false;
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                i++;
                int sign1 = ((TileEntityEnergyCableWithoutGenerateForce) te).getSign();
                //unsafe todo 合并电网
                if (!hasUpdated && sign1 != this.sign) {
                    this.sign = sign1;
                    EnergyNetSavedData.updateEnergyNetCapacity(world, maxEnergyCapacity, this.sign);
                    EnergyNetSavedData.updateEnergyNetEnergy(world, energyStored, this.sign);
                    hasUpdated = true;
                    markDirty();
                } else {
                    notifyNearbyCableMergeSign(this.sign, sequence, facing);
                }
            }
        }

        if (i == 0 && !hasupdated) {
            EnergyNetworkSign sign = new EnergyNetworkSign();
            this.sign = sign.getSIGN();
            markDirty();
            sign.addEnergyStoragedOfNetwork(energyStored);
            sign.addMaxEnergyCapacityOfNetwork(maxEnergyCapacity);
            EnergyNetSavedData.addSign(world, sign);
        }

    }

    @Override
    public void updateState() {
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                if (states.getStatusInFacing(facing) != EnumMMFETileEntityStatus.CABLE) {
                    states.setStatusInFacing(facing, EnumMMFETileEntityStatus.CABLE);
                    markDirty();
                }
            } else if (te != null && te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                if (te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).canExtract()) {
                    if (states.getStatusInFacing(facing) != EnumMMFETileEntityStatus.CABLE_HEAD) {
                        states.setStatusInFacing(facing, EnumMMFETileEntityStatus.CABLE_HEAD);
                        markDirty();
                    }
                }
            }
        }
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public void onNeighborChanged(BlockPos neighbor) {
        TileEntity te = world.getTileEntity(neighbor);
        if (te != null) {
            if (!(te instanceof TileEntityEnergyCableWithoutGenerateForce) && te.hasCapability(CapabilityEnergy.ENERGY, EnergyNetWorkUtils.getFacing(pos, neighbor))) {
                IEnergyStorage storage = te.getCapability(CapabilityEnergy.ENERGY, EnergyNetWorkUtils.getFacing(pos, neighbor));
                if (storage.canReceive()) transferToTickable();
            }
        }


    }

    public boolean askForZeroSequence() {
        if (this.sequence == 0) return true;
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                if (((TileEntityEnergyCableWithoutGenerateForce) te).getSequence() < this.sequence) {
                    if (((TileEntityEnergyCableWithoutGenerateForce) te).askForZeroSequence()) return true;
                }
            }
        }
        return false;
    }

    public int askForFinalSequence(EnumFacing facingFrom, int useless) {
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            if (facing != facingFrom) {
                TileEntity te = world.getTileEntity(pos.offset(facing));
                if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                    if (((TileEntityEnergyCableWithoutGenerateForce) te).getSequence() > this.sequence) {
                        return ((TileEntityEnergyCableWithoutGenerateForce) te).askForFinalSequence(facing.getOpposite(), useless);
                    }
                }
            }
        }
        return this.sequence;
    }

    public int askForFinalSequence(EnumFacing facingFrom) {
        return ((TileEntityEnergyCableWithoutGenerateForce) world.getTileEntity(pos.offset(facingFrom))).askForFinalSequence(facingFrom.getOpposite(), 1);
    }

    public boolean askForZeroSequence(EnumFacing facing) {
        if (this.sequence == 0) return true;
        TileEntity te = world.getTileEntity(pos.offset(facing));
        if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
            if (((TileEntityEnergyCableWithoutGenerateForce) te).getSequence() < this.sequence) {
                if (((TileEntityEnergyCableWithoutGenerateForce) te).askForZeroSequence()) return true;
            }
        }
        return false;
    }

    public int[] askForCapacity(EnumFacing fromFacing) {
        List<EnumFacing> facings = IMMFETransfer.getNearbyCables(pos.offset(fromFacing.getOpposite()), world).getFacings(EnumMMFETileEntityStatus.CABLE);
        facings.remove(fromFacing);
        if (facings.size() == 0) {
            return new int[]{1, this.maxEnergyCapacity};
        } else {
            int[] amountandCapacity = new int[]{1, this.maxEnergyCapacity};
            for (EnumFacing facing : facings) {
                int[] tempArray = ((TileEntityEnergyCableWithoutGenerateForce) world.getTileEntity(pos.offset(facing))).askForCapacity(facing.getOpposite());
                amountandCapacity[0] += tempArray[0];
                amountandCapacity[1] += tempArray[1];
            }
            return amountandCapacity;
        }
    }

    public boolean OnNeighborDestoryed(EnumFacing facing) {
        return askForZeroSequence(facing);
    }

    public void OnBlockDestroyed() throws ErrorNumberException {
        EnergyNetSavedData.updateEnergyNetCapacity(world, -maxEnergyCapacity, sign);
        splitEnergyNet();
    }

    public void splitEnergyNet() throws ErrorNumberException {
        //todo 用自己的state
        SurrondingsState state = IMMFETransfer.getNearbyCables(pos, world);
        List<EnumFacing> list = state.getFacings(EnumMMFETileEntityStatus.CABLE);

        int size = list.size();

        if (size != 1) {
            //todo 发包
            switch (size) {
                case 2: {
                    EnergyNetworkSign sign1 = new EnergyNetworkSign();
                    EnergyNetworkSign sign2 = new EnergyNetworkSign();

                    int[] sign1AmountAndCapacity = askForCapacity(list.get(0));
                    int[] sign2AmountAndCapacity = askForCapacity(list.get(1));

                    EnergyNetSavedData.splitTwoEnergyNet(sign1, sign1AmountAndCapacity[0], sign1AmountAndCapacity[1], sign2, sign2AmountAndCapacity[0], sign2AmountAndCapacity[1], this.sign, this.world);

                    List<EnergyNetworkSign> listSigns = new ArrayList<>();
                    listSigns.add(sign1);
                    listSigns.add(sign2);
                    for (int b = 0; b < size; b++) {
                        notifyNearbyCableUpdateSign(listSigns.get(b).getSIGN(), -1, list.get(b).getOpposite());
                    }
                    break;
                }
                case 3: {
                    EnergyNetworkSign sign1 = new EnergyNetworkSign();
                    EnergyNetworkSign sign2 = new EnergyNetworkSign();
                    EnergyNetworkSign sign3 = new EnergyNetworkSign();
                    EnergyNetworkSign tempSign = null;

                    int[] sign1AmountAndCapacity = askForCapacity(list.get(0));
                    int[] sign2AmountAndCapacity = askForCapacity(list.get(1));
                    int[] sign3AmountAndCapacity = askForCapacity(list.get(2));

                    EnergyNetSavedData.splitTwoEnergyNet(sign1, sign1AmountAndCapacity[0] + sign3AmountAndCapacity[0], sign1AmountAndCapacity[1] + sign3AmountAndCapacity[1], sign2, sign2AmountAndCapacity[0], sign2AmountAndCapacity[1], this.sign, this.world);

                    //1 -> 1,3
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign3, sign3AmountAndCapacity[0], sign3AmountAndCapacity[1], tempSign, sign1AmountAndCapacity[0], sign1AmountAndCapacity[1], sign1.getSIGN(), world);
                    sign1 = tempSign;

                    List<EnergyNetworkSign> listSigns = new ArrayList<>();
                    listSigns.add(sign1);
                    listSigns.add(sign2);
                    listSigns.add(sign3);
                    for (int b = 0; b < size; b++) {
                        notifyNearbyCableUpdateSign(listSigns.get(b).getSIGN(), -1, list.get(b).getOpposite());
                    }
                    break;
                }
                case 4: {
                    EnergyNetworkSign sign1 = new EnergyNetworkSign();
                    EnergyNetworkSign sign2 = new EnergyNetworkSign();
                    EnergyNetworkSign sign3 = new EnergyNetworkSign();
                    EnergyNetworkSign sign4 = new EnergyNetworkSign();
                    EnergyNetworkSign tempSign = null;

                    int[] sign1AmountAndCapacity = askForCapacity(list.get(0));
                    int[] sign2AmountAndCapacity = askForCapacity(list.get(1));
                    int[] sign3AmountAndCapacity = askForCapacity(list.get(2));
                    int[] sign4AmountAndCapacity = askForCapacity(list.get(3));


                    EnergyNetSavedData.splitTwoEnergyNet(sign1, sign1AmountAndCapacity[0] + sign3AmountAndCapacity[0], sign1AmountAndCapacity[1] + sign3AmountAndCapacity[1], sign2, sign2AmountAndCapacity[0] + sign4AmountAndCapacity[0], sign2AmountAndCapacity[1] + sign4AmountAndCapacity[1], this.sign, this.world);

                    //1 -> 1,3
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign3, sign3AmountAndCapacity[0], sign3AmountAndCapacity[1], tempSign, sign1AmountAndCapacity[0], sign1AmountAndCapacity[1], sign1.getSIGN(), world);
                    sign1 = tempSign;

                    //2 -> 2,4
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign4, sign4AmountAndCapacity[0], sign4AmountAndCapacity[1], tempSign, sign2AmountAndCapacity[0], sign2AmountAndCapacity[1], sign2.getSIGN(), world);
                    sign2 = tempSign;

                    List<EnergyNetworkSign> listSigns = new ArrayList<>();
                    listSigns.add(sign1);
                    listSigns.add(sign2);
                    listSigns.add(sign3);
                    listSigns.add(sign4);
                    for (int b = 0; b < size; b++) {
                        notifyNearbyCableUpdateSign(listSigns.get(b).getSIGN(), -1, list.get(b).getOpposite());
                    }
                    break;
                }
                case 5: {
                    EnergyNetworkSign sign1 = new EnergyNetworkSign();
                    EnergyNetworkSign sign2 = new EnergyNetworkSign();
                    EnergyNetworkSign sign3 = new EnergyNetworkSign();
                    EnergyNetworkSign sign4 = new EnergyNetworkSign();
                    EnergyNetworkSign sign5 = new EnergyNetworkSign();
                    EnergyNetworkSign tempSign = null;

                    int[] sign1AmountAndCapacity = askForCapacity(list.get(0));
                    int[] sign2AmountAndCapacity = askForCapacity(list.get(1));
                    int[] sign3AmountAndCapacity = askForCapacity(list.get(2));
                    int[] sign4AmountAndCapacity = askForCapacity(list.get(3));
                    int[] sign5AmountAndCapacity = askForCapacity(list.get(4));


                    EnergyNetSavedData.splitTwoEnergyNet(sign1, sign1AmountAndCapacity[0] + sign3AmountAndCapacity[0] + sign5AmountAndCapacity[0], sign1AmountAndCapacity[1] + sign3AmountAndCapacity[1] + sign5AmountAndCapacity[1], sign2, sign2AmountAndCapacity[0] + sign4AmountAndCapacity[0], sign2AmountAndCapacity[1] + sign4AmountAndCapacity[1], this.sign, this.world);

                    //1->1,3,5
                    //1 -> 1,3
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign3, sign3AmountAndCapacity[0], sign3AmountAndCapacity[1], tempSign, sign1AmountAndCapacity[0] + sign5AmountAndCapacity[0], sign1AmountAndCapacity[1] + sign5AmountAndCapacity[1], sign1.getSIGN(), world);
                    sign1 = tempSign;

                    //1 -> 1,5
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign5, sign5AmountAndCapacity[0], sign5AmountAndCapacity[1], tempSign, sign1AmountAndCapacity[0], sign1AmountAndCapacity[1], sign1.getSIGN(), world);
                    sign1 = tempSign;

                    //2->2,4
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign4, sign4AmountAndCapacity[0], sign4AmountAndCapacity[1], tempSign, sign2AmountAndCapacity[0], sign2AmountAndCapacity[1], sign2.getSIGN(), world);
                    sign2 = tempSign;

                    List<EnergyNetworkSign> listSigns = new ArrayList<>();
                    listSigns.add(sign1);
                    listSigns.add(sign2);
                    listSigns.add(sign3);
                    listSigns.add(sign4);
                    listSigns.add(sign5);
                    for (int b = 0; b < size; b++) {
                        notifyNearbyCableUpdateSign(listSigns.get(b).getSIGN(), -1, list.get(b).getOpposite());
                    }
                    break;
                }
                case 6: {
                    EnergyNetworkSign sign1 = new EnergyNetworkSign();
                    EnergyNetworkSign sign2 = new EnergyNetworkSign();
                    EnergyNetworkSign sign3 = new EnergyNetworkSign();
                    EnergyNetworkSign sign4 = new EnergyNetworkSign();
                    EnergyNetworkSign sign5 = new EnergyNetworkSign();
                    EnergyNetworkSign sign6 = new EnergyNetworkSign();
                    EnergyNetworkSign tempSign = null;

                    int[] sign1AmountAndCapacity = askForCapacity(list.get(0));
                    int[] sign2AmountAndCapacity = askForCapacity(list.get(1));
                    int[] sign3AmountAndCapacity = askForCapacity(list.get(2));
                    int[] sign4AmountAndCapacity = askForCapacity(list.get(3));
                    int[] sign5AmountAndCapacity = askForCapacity(list.get(4));
                    int[] sign6AmountAndCapacity = askForCapacity(list.get(5));

                    EnergyNetSavedData.splitTwoEnergyNet(sign1, sign1AmountAndCapacity[0], sign1AmountAndCapacity[1], sign2, sign2AmountAndCapacity[0], sign2AmountAndCapacity[1], this.sign, this.world);

                    //1->1,3,5
                    //1 -> 1,3
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign3, sign3AmountAndCapacity[0], sign3AmountAndCapacity[1], tempSign, sign1AmountAndCapacity[0] + sign5AmountAndCapacity[0], sign1AmountAndCapacity[1] + sign5AmountAndCapacity[1], sign1.getSIGN(), world);
                    sign1 = tempSign;

                    //1 -> 1,5
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign5, sign5AmountAndCapacity[0], sign5AmountAndCapacity[1], tempSign, sign1AmountAndCapacity[0], sign1AmountAndCapacity[1], sign1.getSIGN(), world);
                    sign1 = tempSign;

                    //2->2,4,6
                    //2->2,4
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign4, sign4AmountAndCapacity[0], sign4AmountAndCapacity[1], tempSign, sign2AmountAndCapacity[0] + sign6AmountAndCapacity[0], sign2AmountAndCapacity[1] + sign6AmountAndCapacity[1], sign2.getSIGN(), world);
                    sign2 = tempSign;

                    //2->2,6
                    tempSign = new EnergyNetworkSign();
                    EnergyNetSavedData.splitTwoEnergyNet(sign6, sign6AmountAndCapacity[0], sign6AmountAndCapacity[1], tempSign, sign2AmountAndCapacity[0], sign2AmountAndCapacity[1], sign2.getSIGN(), world);
                    sign2 = tempSign;

                    List<EnergyNetworkSign> listSigns = new ArrayList<>();
                    listSigns.add(sign1);
                    listSigns.add(sign2);
                    listSigns.add(sign3);
                    listSigns.add(sign4);
                    listSigns.add(sign5);
                    listSigns.add(sign6);
                    for (int b = 0; b < size; b++) {
                        notifyNearbyCableUpdateSign(listSigns.get(b).getSIGN(), -1, list.get(b).getOpposite());
                    }
                    break;
                }
            }
        }

    }

    protected void UpdateSequence() {
        int tempsequence = 0;
        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te instanceof TileEntityEnergyCableWithoutGenerateForce) {
                if (((TileEntityEnergyCableWithoutGenerateForce) te).getSequence() == 0) {
                    this.sequence = 1;
                    markDirty();
                } else {
                    if (tempsequence == 0 || tempsequence > ((TileEntityEnergyCableWithoutGenerateForce) te).getSequence()) {
                        tempsequence = ((TileEntityEnergyCableWithoutGenerateForce) te).getSequence();
                    }
                }
            }
        }
        if (tempsequence != 0) {
            this.sequence = tempsequence + 1;
            markDirty();
        }
    }

    @Override
    public void setStatusInFacing(EnumFacing facing, EnumMMFETileEntityStatus status) {
        states.setStatusInFacing(facing, status);
        markDirty();
    }

    @Override
    public EnumMMFETileEntityStatus getStatusInFacing(EnumFacing facing) {
        return states.getStatusInFacing(facing);
    }

    private void transferToTickable() {
        EnergyNetSavedData.updateEnergyNetCapacity(world, -maxEnergyCapacity, sign);
        world.setBlockState(pos, ModBlocks.TICKABLEENERGYCABLEWITHOUTGENERATEFORCE.getDefaultState());
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return EnergyNetSavedData.ReciveEnergy(this.sign, maxReceive, simulate, world);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        transferToTickable();
        return 0;
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        // getUpdateTag() is called whenever the chunkdata is sent to the
        // client. In contrast getUpdatePacket() is called when the tile entity
        // itself wants to sync to the client. In many cases you want to send
        // over the same information in getUpdateTag() as in getUpdatePacket().
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        // Prepare a packet for syncing our TE to the client. Since we only have to sync the stack
        // and that's all we have we just write our entire NBT here. If you have a complex
        // tile entity that doesn't need to have all information on the client you can write
        // a more optimal NBT here.

        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        // Here we get the packet from the server and read it into our client side tile entity
        this.readFromNBT(packet.getNbtCompound());
    }

    public List<EnumFacing> getCableHeads(EnumMMFETileEntityStatus cableHead) {

        List<EnumFacing> list = new ArrayList<>();

        for (EnumFacing facing : EnergyNetWorkUtils.getFacings()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te != null && !(te instanceof TileEntityEnergyCableWithoutGenerateForce)) {
                if (te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                    list.add(facing);
                }
            }
        }
        return list;
    }
}
