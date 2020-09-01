package mfrf.dbydd.micro_machinery.blocks.machines.energy_cable;

import mfrf.dbydd.micro_machinery.blocks.machines.MMTileBase;
import mfrf.dbydd.micro_machinery.enums.EnumCableMaterial;
import mfrf.dbydd.micro_machinery.enums.EnumCableState;
import mfrf.dbydd.micro_machinery.registeried_lists.Registered_Tileentitie_Types;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.lwjgl.system.CallbackI;

import java.util.HashMap;
import java.util.Map;

public class TileEnergyCable extends MMTileBase implements ITickable {
    private EnumCableMaterial material = EnumCableMaterial.NULL;
    private Map<Direction, EnumCableState> stateMap = new HashMap<>();
    private Integer Sign = null;
    public TileEnergyCable() {
        super(Registered_Tileentitie_Types.TILE_ENERGY_CABLE.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("material", material.toString());
        stateMap.forEach((direction, enumCableState) -> compound.putString(direction.toString(), enumCableState.toString()));
        compound.putInt("sign", Sign);
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        material = EnumCableMaterial.fromName(compound.getString("material"));
        for (Direction direction : Direction.values()) {
            String string = compound.getString(direction.toString());
            if(!string.isEmpty()){
                stateMap.put(direction, EnumCableState.valueOf(string));
            }
        }
        Sign = compound.getInt("sign");
        super.read(compound);
    }

    @Override
    public void tick() {
        if(!world.isRemote()){
            if(material == EnumCableMaterial.NULL){
                this.material = world.getBlockState(pos).get(BlockEnergyCable.CABLE_MATERIAL_ENUM_PROPERTY);
                markDirty2();
            }



        }
    }

    public void notifyStateUpdate(BlockState state) {
        stateMap.clear();
        for(Direction direction : Direction.values()){
            EnumCableState enumCableState = state.get(BlockEnergyCable.DIRECTION_ENUM_PROPERTY_MAP.get(direction));
            if(enumCableState == EnumCableState.CONNECT){
                stateMap.put(direction,enumCableState);
            }
        }
        markDirty2();
    }
}
