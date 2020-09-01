package mfrf.dbydd.micro_machinery.world_saved_data;

import mfrf.dbydd.micro_machinery.utils.WorldFEContainer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EnergyCableSavedData extends WorldSavedData {
    public static final String NAME = "CableWorldSavedData";

    private Map<Integer, WorldFEContainer> integerWorldFEContainerMap = new HashMap<>();

    public EnergyCableSavedData() {
        super(NAME);
    }

    public static EnergyCableSavedData get(World worldIn) {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }

        ServerWorld world = (ServerWorld)worldIn;
        DimensionSavedDataManager storage = world.getSavedData();
        return storage.getOrCreate(EnergyCableSavedData::new, NAME);
    }

    @Override
    public void read(CompoundNBT nbt) {
        INBT inbt = nbt.get("list");
        if (inbt instanceof ListNBT) {
            ListNBT list = (ListNBT) inbt;
            for (INBT inbt1 : list) {
                CompoundNBT inbt11 = (CompoundNBT) inbt1;
                int sign = inbt11.getInt("sign");
                WorldFEContainer.deserializeNBT((CompoundNBT) inbt11.get(Integer.toString(sign)));
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT inbts = new ListNBT();
        integerWorldFEContainerMap.forEach((integer, container) -> {
            CompoundNBT compoundNBT = new CompoundNBT();
            compound.putInt("sign", integer);
            compound.put(integer.toString(), container.serializeNBT());
            inbts.add(compound);
        });
        compound.put("list", inbts);
        return compound;
    }

    public int reciveEnergy(int sign, int maxRecive, boolean simulate){
        int i = integerWorldFEContainerMap.get(sign).receiveEnergy(maxRecive, simulate);
        markDirty();
        return i;
    }

    public int extractEnergy(int sign, int maxExtract, boolean simulate){
        int i = integerWorldFEContainerMap.get(sign).extractEnergy(maxExtract, simulate);
        markDirty();
        return i;
    }

    public boolean hasContainer(int sign){
        return !(integerWorldFEContainerMap.get(sign) == null);
    }

    public int createContainer(int maxFE, int currentFE, int minFE){
        WorldFEContainer container = new WorldFEContainer(BigInteger.valueOf(maxFE), BigInteger.valueOf(currentFE), BigInteger.valueOf(minFE));
        Random random = new Random();
        while (true){
            int i = random.nextInt();
            if(integerWorldFEContainerMap.get(i) == null){
                integerWorldFEContainerMap.put(i, container);
                markDirty();
                return i;
            }
        }
    }

    public WorldFEContainer getContainer(int sign){
        return integerWorldFEContainerMap.get(sign);
    }
}
