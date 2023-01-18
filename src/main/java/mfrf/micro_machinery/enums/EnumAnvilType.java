package mfrf.micro_machinery.enums;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum EnumAnvilType implements IStringSerializable {
    STONE(0, "stone", SoundEvents.BLOCK_STONE_BREAK),
    BRONZE(1, "bronze", SoundEvents.BLOCK_ANVIL_LAND),
    PIGIRON(2, "pig_iron", SoundEvents.BLOCK_ANVIL_LAND);

    private final int rank;
    private final String name;
    private final SoundEvent soundType;

    private EnumAnvilType(int rank, String name, SoundEvent soundType) {
        this.rank = rank;
        this.name = name;
        this.soundType = soundType;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public SoundEvent getSound(){
        return this.soundType;
    }
}
