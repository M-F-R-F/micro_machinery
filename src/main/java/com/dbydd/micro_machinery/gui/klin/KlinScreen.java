package com.dbydd.micro_machinery.gui.klin;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.gui.ScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class KlinScreen extends ScreenBase<KlinContainer> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(Micro_Machinery.NAME, "textures/gui/klin.png");

    public KlinScreen(KlinContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, TEXTURES, 176, 166);
    }

}
