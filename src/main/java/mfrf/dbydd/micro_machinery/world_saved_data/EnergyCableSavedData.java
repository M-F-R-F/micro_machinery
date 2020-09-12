package mfrf.dbydd.micro_machinery.world_saved_data;

import com.google.common.primitives.UnsignedLong;
import mfrf.dbydd.micro_machinery.utils.IntegerContainer;
import mfrf.dbydd.micro_machinery.utils.WorldFEContainer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

import java.util.*;
import java.util.stream.Collectors;

public class EnergyCableSavedData extends WorldSavedData {
    public static final String NAME = "MMCableWorldSavedData";

    private Map<Integer, WorldFEContainer> integerWorldFEContainerMap = new HashMap<>();

    public EnergyCableSavedData() {
        super(NAME);
    }

    public static EnergyCableSavedData get(World worldIn) {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }

        ServerWorld world = (ServerWorld) worldIn;
        DimensionSavedDataManager storage = world.getSavedData();
        return storage.getOrCreate(EnergyCableSavedData::new, NAME);
    }

    @Override
    public void read(CompoundNBT nbt) {
        INBT inbt = nbt.get("list");
        if (inbt instanceof ListNBT) {
            ListNBT list = (ListNBT) inbt;

            List<PackedContainer> packedContainerListFromNBTList = PackedContainer.getPackedContainerListFromNBTList(list);

            packedContainerListFromNBTList.forEach(packedContainer -> integerWorldFEContainerMap.put(packedContainer.sign, packedContainer.container));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT listNBT = new ListNBT();
        List<CompoundNBT> collect = PackedContainer.getPackedContainerListFromMap(integerWorldFEContainerMap).stream().map(PackedContainer::serializeNBT).filter(Objects::nonNull).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            collect.forEach(listNBT::add);
            compound.put("list", listNBT);
        }
        return compound;
    }

    public int receiveEnergy(int sign, int maxRecive, boolean simulate) {
        int i = integerWorldFEContainerMap.get(sign).receiveEnergy(maxRecive, simulate);
        markDirty();
        return i;
    }

    public int extractEnergy(int sign, int maxExtract, boolean simulate) {
        int i = integerWorldFEContainerMap.get(sign).extractEnergy(maxExtract, simulate);
        markDirty();
        return i;
    }

    public boolean hasContainer(int sign) {
        return !(integerWorldFEContainerMap.get(sign) == null);
    }

    public int createContainer(int maxFE, int currentFE) {
        WorldFEContainer container = new WorldFEContainer(UnsignedLong.ZERO, UnsignedLong.valueOf(maxFE), UnsignedLong.valueOf(currentFE));
        Random random = new Random();
        while (true) {
            int i = random.nextInt();
            if (integerWorldFEContainerMap.get(i) == null) {
                integerWorldFEContainerMap.put(i, container);
                markDirty();
                return i;
            }
        }
    }

    public WorldFEContainer getContainer(int sign) {
        return integerWorldFEContainerMap.get(sign);
    }

    public UnsignedLong removeCablePart(int sign, IntegerContainer container) {
        WorldFEContainer FEcontainer = integerWorldFEContainerMap.get(sign);
        if (FEcontainer != null) {
            UnsignedLong subtractedMax = FEcontainer.getMax().minus(UnsignedLong.valueOf(container.getMax()));
            if (subtractedMax.compareTo(UnsignedLong.ZERO) > 0) {
                if(!FEcontainer.getCurrent().equals(UnsignedLong.ZERO)) {
                    UnsignedLong subtractCurrent = FEcontainer.minus(UnsignedLong.valueOf(container.getCurrent()), false);
                    FEcontainer.setCurrent(subtractCurrent);
                }

                FEcontainer.setMax(subtractedMax);
                markDirty();
                return FEcontainer.getMax();
            } else {
                integerWorldFEContainerMap.remove(sign);
                markDirty();
                return UnsignedLong.ZERO;
            }
        }
        return UnsignedLong.ZERO;
    }

    public void addCablePart(int sign, IntegerContainer container) {
        WorldFEContainer targetContainer = integerWorldFEContainerMap.get(sign);

        targetContainer.setMax(targetContainer.getMax().plus(UnsignedLong.valueOf(container.getMax())));
        targetContainer.setCurrent(targetContainer.getCurrent().plus(UnsignedLong.valueOf(container.getCurrent())));

    }

    public void mergeOneInToAnother(int signMergeInto, int signBeMerge) {
        WorldFEContainer container1 = integerWorldFEContainerMap.get(signMergeInto);
        WorldFEContainer container2 = integerWorldFEContainerMap.get(signBeMerge);

        container1.setMax(container1.getMax().plus(container2.getMax()));
        container1.setCurrent(container1.getCurrent().plus(container2.getCurrent()));

        integerWorldFEContainerMap.remove(signBeMerge);

        markDirty();

    }

    public void splitCable(int sign, Integer sign1Splited, UnsignedLong sign1MaxEnergy, Integer sign2Splited, UnsignedLong sign2MaxEnergy) {
        WorldFEContainer container = integerWorldFEContainerMap.get(sign);
        WorldFEContainer containerSplited1 = new WorldFEContainer(UnsignedLong.ZERO, sign1MaxEnergy, container.getCurrent().times(sign1MaxEnergy.dividedBy(container.getMax())));
        WorldFEContainer containerSplited2 = new WorldFEContainer(UnsignedLong.ZERO, sign2MaxEnergy, container.getCurrent().times(sign2MaxEnergy.dividedBy(container.getMax())));
        Random random = new Random();
        integerWorldFEContainerMap.remove(sign);

        while (true) {
            int i = random.nextInt();
            if (integerWorldFEContainerMap.get(i) == null) {
                integerWorldFEContainerMap.put(i, containerSplited1);
                sign1Splited = i;
                break;
            }
        }

        while (true) {
            int i = random.nextInt();
            if (integerWorldFEContainerMap.get(i) == null) {
                integerWorldFEContainerMap.put(i, containerSplited2);
                sign2Splited = i;
                break;
            }
        }

        markDirty();

    }

    public int splitOutAPartFromMain(int signToBeSplit, UnsignedLong splitOutMaxEnergy) {
        WorldFEContainer container = integerWorldFEContainerMap.get(signToBeSplit);
        UnsignedLong subtractedMax = container.getMax().minus(splitOutMaxEnergy);
        if (subtractedMax.compareTo(UnsignedLong.ZERO) <= 0) {
            return signToBeSplit;
        } else {
            UnsignedLong currentSplited = container.getCurrent().times(splitOutMaxEnergy.dividedBy(container.getMax()));
            WorldFEContainer containerSplitOut = new WorldFEContainer(UnsignedLong.ZERO, splitOutMaxEnergy, currentSplited);
            container.setMax(container.getMax().minus(subtractedMax));
            container.setCurrent(container.getCurrent().minus(currentSplited));
            Random random = new Random();

            while (true) {
                int i = random.nextInt();
                if (integerWorldFEContainerMap.get(i) == null) {
                    integerWorldFEContainerMap.put(i, containerSplitOut);
                    markDirty();
                    return i;
                }
            }

        }

    }

    public int mergeCable(int sign1, int sign2) {
        WorldFEContainer container1 = integerWorldFEContainerMap.get(sign1);
        WorldFEContainer container2 = integerWorldFEContainerMap.get(sign2);

        WorldFEContainer newContainer = new WorldFEContainer(UnsignedLong.ZERO, container1.getMax().plus(container2.getMax()), container1.getCurrent().plus(container2.getCurrent()));

        integerWorldFEContainerMap.remove(sign1);
        integerWorldFEContainerMap.remove(sign2);

        Random random = new Random();

        while (true) {
            int i = random.nextInt();
            if (integerWorldFEContainerMap.get(i) == null) {
                integerWorldFEContainerMap.put(i, newContainer);
                markDirty();
                return i;
            }
        }

    }

    public static class PackedContainer {
        private int sign;
        private WorldFEContainer container;

        public PackedContainer(Integer sign, WorldFEContainer container) {
            this.container = container;
            this.sign = sign;
        }

        public static PackedContainer deserializeNBT(CompoundNBT nbt) {
            return new PackedContainer(nbt.getInt("sign"), WorldFEContainer.deserializeNBT(nbt.getCompound("container")));
        }

        public static List<PackedContainer> getPackedContainerListFromMap(Map<Integer, WorldFEContainer> map) {
            return map.entrySet().stream().map(integerWorldFEContainerEntry -> new PackedContainer(integerWorldFEContainerEntry.getKey(), integerWorldFEContainerEntry.getValue())).collect(Collectors.toList());
        }

        public static List<PackedContainer> getPackedContainerListFromNBTList(ListNBT listNBT) {
            return listNBT.stream().map(inbt -> (CompoundNBT) inbt).map(PackedContainer::deserializeNBT).collect(Collectors.toList());
        }

        public CompoundNBT serializeNBT() {
            CompoundNBT compoundNBT = new CompoundNBT();
            compoundNBT.putInt("sign", sign);
            compoundNBT.put("container", container.serializeNBT());
            return compoundNBT;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }
    }
}
