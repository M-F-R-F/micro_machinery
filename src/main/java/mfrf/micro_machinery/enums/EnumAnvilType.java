package mfrf.micro_machinery.enums;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringRepresentable;

public enum EnumAnvilType implements StringRepresentable {
    STONE(0, "stone", SoundEvents.STONE_BREAK),
    BRONZE(1, "bronze", SoundEvents.ANVIL_LAND),
    PIGIRON(2, "pig_iron", SoundEvents.ANVIL_LAND);

    private final int rank;
    private final String name;
    private final SoundEvent soundType;

    EnumAnvilType(int rank, String name, SoundEvent soundType) {
        this.rank = rank;
        this.name = name;
        this.soundType = soundType;
    }

    public int getRank() {
        return rank;
    }

    public SoundEvent getSound() {
        return this.soundType;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
