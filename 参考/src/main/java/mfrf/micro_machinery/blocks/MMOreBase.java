package mfrf.micro_machinery.blocks;

import java.util.ArrayList;
import java.util.List;

public class MMOreBase extends MMBlockBase {
    public static List<MMOreBase> oreList = new ArrayList<>();

    public MMOreBase(Properties properties, String name) {
        super(properties, name);
        oreList.add(this);
    }
}
