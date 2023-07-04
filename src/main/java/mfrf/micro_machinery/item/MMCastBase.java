package mfrf.micro_machinery.item;

public class MMCastBase extends MMItemBase {

    public final String type;

    public MMCastBase(String type) {
        super(new Properties().stacksTo(1));
        this.type = type;
    }
}
