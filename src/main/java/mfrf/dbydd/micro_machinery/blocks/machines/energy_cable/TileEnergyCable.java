package mfrf.dbydd.micro_machinery.blocks.machines.energy_cable;

import com.google.common.primitives.UnsignedLong;
import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.enums.EnumCableMaterial;
import mfrf.dbydd.micro_machinery.enums.EnumCableState;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import mfrf.dbydd.micro_machinery.world_saved_data.EnergyCableSavedData;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileEnergyCable extends MMTileBase implements ITickable, IEnergyStorage {
    private final Map<Direction, EnumCableState> stateMap = new HashMap<>();
    private EnumCableMaterial material = EnumCableMaterial.NULL;
    private Integer sign = 0;
    private UnsignedLong number = UnsignedLong.ZERO;

    public TileEnergyCable() {
        super(Registered_Tileentitie_Types.TILE_ENERGY_CABLE.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("material", material.toString());
        stateMap.forEach((direction, enumCableState) -> compound.putString(direction.toString().toLowerCase() + "_state", enumCableState.toString()));
        compound.putInt("sign", sign);
        compound.putLong("number", number.longValue());
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        material = EnumCableMaterial.fromName(compound.getString("material"));
        for (Direction direction : Direction.values()) {
            String string = compound.getString(direction.toString().toLowerCase() + "_state");
            if (!string.isEmpty()) {
                stateMap.put(direction, EnumCableState.valueOf(string));
            }
        }
        sign = compound.getInt("sign");
        number = UnsignedLong.fromLongBits(compound.getLong("number"));
        super.read(compound);
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            List<Direction> connectedDirectionList = getConnectedDirectionList();
            for (Direction direction : connectedDirectionList) {
                pushEnergyToDirection(direction, EnergyCableSavedData.get(world).getContainer(sign), material.getTransfer());
            }
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityEnergy.ENERGY) return LazyOptional.of(() -> this).cast();
        return super.getCapability(cap, side);
    }

    public Integer getSign() {
        return sign;
    }

    public boolean existLoop(Direction fromDirection, World world) {
        List<Direction> tempList = new ArrayList<>();

        stateMap.forEach((direction, enumCableState) -> {
            if (direction != fromDirection && enumCableState == EnumCableState.CABLE) tempList.add(direction);
        });

        if (this.number.equals(UnsignedLong.ZERO)) {
            return true;
        } else {
            if (tempList.size() == 0) return false;

            for (Direction direction : tempList) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if (tileEntity instanceof TileEnergyCable) {
                    TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;

                    boolean existLoop = tileEnergyCable.existLoop(direction.getOpposite(), world);

                    if (existLoop) return true;

                }
            }
            return false;

        }

    }

    public void notifyMergeByNearbyCable(Integer signToChange, Direction fromDirection, World world) {
        if (signToChange.compareTo(this.sign) != 0) {
            Integer tempSign = this.sign;
            this.sign = signToChange;

            for (Direction direction : Direction.values()) {
                if (stateMap.get(direction) == EnumCableState.CABLE && direction != fromDirection) {
                    TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                    if (tileEntity instanceof TileEnergyCable) {
                        TileEnergyCable tileCable = (TileEnergyCable) tileEntity;
                        if (!tileCable.sign.equals(tempSign)) {
                            EnergyCableSavedData data = EnergyCableSavedData.get(world);
                            data.mergeOneInToAnother(signToChange, tileCable.sign);
                        }
                        tileCable.notifyMergeByNearbyCable(signToChange, direction.getOpposite(), world);
                    }
                }
            }
            markDirty();
        }

    }

    public void notifyUpdateNumber(UnsignedLong number, Direction fromDirection) {
        if (!this.number.equals(number)) {
            this.number = number;
            List<Direction> tempList = new ArrayList<>();

            stateMap.forEach((direction, enumCableState) -> {
                if (direction != fromDirection && enumCableState == EnumCableState.CABLE) tempList.add(direction);
            });

            for (Direction direction : tempList) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if (tileEntity instanceof TileEnergyCable) {
                    TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                    tileEnergyCable.notifyUpdateNumber(number.plus(UnsignedLong.ONE), direction.getOpposite());
                }
            }
            markDirty();
        }
    }

    public UnsignedLong askForConnectedCapacity(UnsignedLong integer, Direction fromDirection, World world) {

        UnsignedLong add = integer.plus(UnsignedLong.valueOf(this.material.getTransfer()));

        List<Direction> cableDirectionList = getCableDirectionList(fromDirection);

        if (cableDirectionList.size() == 0) return add;
        else {
            for (Direction direction : cableDirectionList) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));

                if (tileEntity instanceof TileEnergyCable) {
                    TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;

                    add = tileEnergyCable.askForConnectedCapacity(add, direction.getOpposite(), world);

                }
            }
            return add;
        }

    }

    public void notifyUpdateSign(int signTochange, Direction fromDirection, World world) {
        this.sign = signTochange;

        List<Direction> cableDirectionList = getCableDirectionList(fromDirection);

        for (Direction direction : cableDirectionList) {
            TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
            if (tileEntity instanceof TileEnergyCable) {
                TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;

                tileEnergyCable.notifyMergeByNearbyCable(signTochange, fromDirection, world);

            }
        }


        markDirty();
    }

    public void notifyStateUpdate(BlockState state, World world) {
        stateMap.clear();
        List<Direction> tempDirectionList = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            EnumCableState enumCableState = state.get(BlockEnergyCable.DIRECTION_ENUM_PROPERTY_MAP.get(direction));
            stateMap.put(direction, enumCableState);
            if (enumCableState == EnumCableState.CABLE) {
                tempDirectionList.add(direction);
            }
        }

        if (number.equals(UnsignedLong.ZERO)) {
            if (tempDirectionList.size() != 0) {
                for (Direction direction : tempDirectionList) {
                    TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                    if (tileEntity instanceof TileEnergyCable) {
                        UnsignedLong cableNumber = ((TileEnergyCable) tileEntity).number;
                        if (this.number == null || this.number.compareTo(cableNumber) > 0) {
                            this.number = cableNumber;
                        }
                    }
                }

                for (Direction direction : tempDirectionList) {
                    TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                    if (tileEntity instanceof TileEnergyCable) {
                        TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                        if (this.number.compareTo(tileEnergyCable.number) < 0) {
                            tileEnergyCable.notifyUpdateNumber(number.plus(UnsignedLong.ONE), direction.getOpposite());
                        }
                    }
                }
            }
        }

        if (material == EnumCableMaterial.NULL) {
            this.material = state.get(BlockEnergyCable.CABLE_MATERIAL_ENUM_PROPERTY);
        }

        if (this.sign == 0) {
            if (tempDirectionList.isEmpty()) {
                EnergyCableSavedData data = EnergyCableSavedData.get(world);
                this.sign = data.createContainer(this.material.getTransfer(), 0, 0);
            } else {
                for (Direction direction : tempDirectionList) {
                    TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                    if (tileEntity instanceof TileEnergyCable) {
                        TileEnergyCable tileCable = (TileEnergyCable) tileEntity;
                        EnergyCableSavedData data = EnergyCableSavedData.get(world);
                        data.addCablePart(tileCable.sign, new IntegerContainer(0, this.material.getTransfer()));
                        this.sign = tileCable.sign;
                        tempDirectionList.remove(direction);
                        break;
                    }
                }
            }
        }

        if (this.sign != null) {
            for (Direction direction : tempDirectionList) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if (tileEntity instanceof TileEnergyCable) {
                    TileEnergyCable tileCable = (TileEnergyCable) tileEntity;
                    if (tileCable.sign.compareTo(this.sign) != 0) {
                        EnergyCableSavedData data = EnergyCableSavedData.get(world);
                        data.mergeOneInToAnother(this.sign, tileCable.sign);
                        tileCable.notifyMergeByNearbyCable(this.sign, direction.getOpposite(), world);
                    }
                }
            }
        }

        markDirty();
    }

    public void notifyBreak(BlockState state, World world) {
        List<Direction> tempDirectionList = new ArrayList<>();
        List<Direction> directionConnectToOroginPoint = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            EnumCableState enumCableState = state.get(BlockEnergyCable.DIRECTION_ENUM_PROPERTY_MAP.get(direction));
            if (enumCableState == EnumCableState.CABLE) {
                tempDirectionList.add(direction);
            }
        }

        EnergyCableSavedData data = EnergyCableSavedData.get(world);
        UnsignedLong removeCablePartRemainValue = data.removeCablePart(this.sign, new IntegerContainer(0, this.material.getTransfer(), this.material.getTransfer()));

        if (!removeCablePartRemainValue.equals(UnsignedLong.ZERO)) {

            if (this.number.compareTo(UnsignedLong.ZERO) == 0) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(tempDirectionList.get(0)));
                if (tileEntity instanceof TileEnergyCable) {
                    TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                    tileEnergyCable.notifyUpdateNumber(UnsignedLong.ZERO, tempDirectionList.get(0).getOpposite());
                }
            }

            for (Direction direction : tempDirectionList) {

                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if (tileEntity instanceof TileEnergyCable) {
                    TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                    boolean b = tileEnergyCable.existLoop(direction.getOpposite(), world);
                    if (!b) {
                        int sign = data.splitOutAPartFromMain(this.sign, tileEnergyCable.askForConnectedCapacity(UnsignedLong.ZERO, direction.getOpposite(), world));
                        tileEnergyCable.notifyUpdateNumber(UnsignedLong.ZERO, direction.getOpposite());
                        tileEnergyCable.notifyUpdateSign(sign, direction.getOpposite(), world);
                    }
                }
            }

        }

    }

    private List<Direction> getCableDirectionList(Direction fromDirection) {
        List<Direction> tempList = new ArrayList<>();

        stateMap.forEach((direction, enumCableState) -> {
            if (direction != fromDirection && enumCableState == EnumCableState.CABLE) tempList.add(direction);
        });

        return tempList;
    }

    private List<Direction> getConnectedDirectionList() {
        List<Direction> tempList = new ArrayList<>();

        stateMap.forEach((direction, enumCableState) -> {
            if (enumCableState == EnumCableState.CONNECT) tempList.add(direction);
        });

        return tempList;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        EnergyCableSavedData data = EnergyCableSavedData.get(world);
        if(data.hasContainer(sign)) {
            if (maxReceive <= material.getTransfer()) {
                return data.receiveEnergy(sign, maxReceive, simulate);
            } else {
                return EnergyCableSavedData.get(world).receiveEnergy(sign, material.getTransfer(), simulate);
            }
        }else {
            return 0;
        }
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        EnergyCableSavedData data = EnergyCableSavedData.get(world);
        if(data.hasContainer(sign)) {
            if (maxExtract <= material.getTransfer()) {
                return data.extractEnergy(sign, maxExtract, simulate);
            } else {
                return data.extractEnergy(sign, material.getTransfer(), simulate);
            }
        }else {
            return 0;
        }
    }

    @Override
    public int getEnergyStored() {
        return EnergyCableSavedData.get(world).getContainer(sign).getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return EnergyCableSavedData.get(world).getContainer(sign).getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
