package mfrf.dbydd.micro_machinery.utils;

import com.google.common.collect.EvictingQueue;
import mfrf.dbydd.micro_machinery.blocks.machines.lathe.TileLathe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class ActionContainer implements INBTSerializable<CompoundNBT> {
    EvictingQueue<TileLathe.Action> actionQueue;

    public ActionContainer() {
        this.actionQueue = EvictingQueue.create(3);
        for (int i = 0; i < 3; i++) {
            actionQueue.add(TileLathe.Action.EMPTY);
        }
    }

    public void addStep(TileLathe.Action action) {
        actionQueue.add(action);
    }

    public EvictingQueue<TileLathe.Action> getActionQueue() {
        return actionQueue;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        for (int i = 0; i < 3; i++) {
            compoundNBT.putString("action" + i, actionQueue.poll().name());
        }
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        for (int i = 0; i < 3; i++) {
            actionQueue.add(TileLathe.Action.valueOf(nbt.getString("action" + i)));
        }
    }
}