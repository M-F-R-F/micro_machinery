package mfrf.dbydd.micro_machinery.utils;

import mfrf.dbydd.micro_machinery.blocks.machines.multi_block_old_system.lathe.TileLathe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class ActionContainer implements INBTSerializable<CompoundNBT> {
    private TileLathe.Action[] actionQueue = new TileLathe.Action[3];
    private int count = 0;


    public void addStep(TileLathe.Action action) {
        if (count < 3) {
            actionQueue[count++] = action;
        } else {
            TileLathe.Action[] actions = new TileLathe.Action[3];
            actions[0] = actionQueue[1];
            actions[1] = actionQueue[2];
            actions[2] = action;
            actionQueue = actions;
        }
    }

    public boolean test(TileLathe.Action action1, TileLathe.Action action2) {
        return getAction2() == action1 && getAction3() == action2;
    }

    public TileLathe.Action getAction1() {
        return actionQueue[0] == null ? TileLathe.Action.EMPTY : actionQueue[0];
    }

    public TileLathe.Action getAction2() {
        return actionQueue[1] == null ? TileLathe.Action.EMPTY : actionQueue[1];
    }

    public TileLathe.Action getAction3() {
        return actionQueue[2] == null ? TileLathe.Action.EMPTY : actionQueue[2];
    }

    public void reset() {
        count = 0;
        actionQueue = new TileLathe.Action[3];
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        for (int i = 0; i < 3; i++) {
            TileLathe.Action poll = actionQueue[i];
            if(poll != null) {
                compoundNBT.putString("action" + i,poll.name());
            }
        }
        compoundNBT.putInt("count", count);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.count = nbt.getInt("count");
        for (int i = 0; i < count; i++) {
            String s = "action" + i;
            if (nbt.contains(s)) {
              this.actionQueue[i] = TileLathe.Action.valueOf(nbt.getString(s));
            }
        }
    }
}