package mfrf.dbydd.micro_machinery.blocks.machines.energy_cable;

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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileEnergyCable extends MMTileBase implements ITickable {
    private final Map<Direction, EnumCableState> stateMap = new HashMap<>();
    private EnumCableMaterial material = EnumCableMaterial.NULL;
    private Integer sign = null;
    private BigInteger number = null;

    public TileEnergyCable() {
        super(Registered_Tileentitie_Types.TILE_ENERGY_CABLE.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("material", material.toString());
        stateMap.forEach((direction, enumCableState) -> compound.putString(direction.toString(), enumCableState.toString()));
        compound.putInt("sign", sign);
        compound.putByteArray("number", number.toByteArray());
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        material = EnumCableMaterial.fromName(compound.getString("material"));
        for (Direction direction : Direction.values()) {
            String string = compound.getString(direction.toString());
            if (!string.isEmpty()) {
                stateMap.put(direction, EnumCableState.valueOf(string));
            }
        }
        sign = compound.getInt("sign");
        number = new BigInteger(compound.getByteArray("number"));
        super.read(compound);
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {


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
            markDirty2();
        }

    }

    public void notifyUpdateNumber(BigInteger number, Direction fromDirection) {
        if (!this.number.equals(number)) {
            this.number = number;
            List<Direction> tempList = new ArrayList<>();

            stateMap.forEach((direction, enumCableState) -> {
                if (direction != fromDirection && enumCableState == EnumCableState.CABLE) tempList.add(direction);
            });

            for (Direction direction : tempList) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                if(tileEntity instanceof TileEnergyCable){
                    TileEnergyCable tileEnergyCable = (TileEnergyCable) tileEntity;
                    tileEnergyCable.notifyUpdateNumber(number.add(BigInteger.ONE), direction.getOpposite());
                }
            }
        }
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

        if (number == null) {
            if (tempDirectionList.size() == 0) {

                this.number = BigInteger.ZERO;

            } else {
                for (Direction direction : tempDirectionList) {
                    TileEntity tileEntity = world.getTileEntity(pos.offset(direction));
                    if (tileEntity instanceof TileEnergyCable) {
                        BigInteger cableNumber = ((TileEnergyCable) tileEntity).number;
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
                            tileEnergyCable.notifyUpdateNumber(number.add(BigInteger.ONE), direction.getOpposite());
                        }
                    }
                }

            }
            markDirty2();
        }

        if (material == EnumCableMaterial.NULL) {
            this.material = state.get(BlockEnergyCable.CABLE_MATERIAL_ENUM_PROPERTY);
        }

        if (this.sign == null) {
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

        markDirty2();
    }

    public void notifyBreak(BlockState state, World world) {
        List<Direction> tempDirectionList = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            EnumCableState enumCableState = state.get(BlockEnergyCable.DIRECTION_ENUM_PROPERTY_MAP.get(direction));
            if (enumCableState == EnumCableState.CABLE) {
                tempDirectionList.add(direction);
            }
        }

        EnergyCableSavedData data = EnergyCableSavedData.get(world);
        BigInteger removeCablePartRemainValue = data.removeCablePart(this.sign, new IntegerContainer(0, this.material.getTransfer(), this.material.getTransfer()));

        if (!removeCablePartRemainValue.equals(BigInteger.ZERO)) {



        }

    }
}
