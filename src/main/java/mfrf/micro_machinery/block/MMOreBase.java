package mfrf.micro_machinery.block;

import mfrf.micro_machinery.block.MMBlockBase;

import java.util.ArrayList;
import java.util.List;

public class MMOreBase extends MMBlockBase {
    public static List<MMOreBase> oreList = new ArrayList<>();

    public MMOreBase(Properties properties) {
        super(properties);
        oreList.add(this);
    }
}
