package mfrf.dbydd.micro_machinery.gui.elements;

import net.minecraft.client.gui.widget.button.Button;

public class ButtonBase extends Button {
    public ButtonBase(int widthIn, int heightIn, int width, int height, String text, IPressable onPress) {
        super(widthIn, heightIn, width, height, text, onPress);
    }
}
