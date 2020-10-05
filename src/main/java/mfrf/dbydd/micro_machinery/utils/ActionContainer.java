package mfrf.dbydd.micro_machinery.utils;

import com.google.common.collect.EvictingQueue;
import mfrf.dbydd.micro_machinery.blocks.machines.lathe.TileLathe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class ActionContainer implements INBTSerializable<CompoundNBT> {
    private EvictingQueue<TileLathe.Action> actionQueue;

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

    public boolean test(TileLathe.Action action1, TileLathe.Action action2) {
        TileLathe.Action[] actions = actionQueue.toArray(new TileLathe.Action[3]);
        return true;
    }

    public void reset() {
        actionQueue.clear();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        for (int i = 0; i < 3; i++) {
            TileLathe.Action poll = actionQueue.poll();
            compoundNBT.putString("action" + i, poll != null ? poll.name() : TileLathe.Action.EMPTY.name());
        }
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        reset();
        for (int i = 0; i < 3; i++) {
            actionQueue.add(TileLathe.Action.valueOf(nbt.getString("action" + i)));
        }
    }
}