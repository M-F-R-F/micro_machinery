package mfrf.micro_machinery.item;

import mfrf.micro_machinery.events.RegistryThingsEvent;
import mfrf.micro_machinery.registry_lists.MMTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class MMBlockItemBase extends BlockItem {

    public MMBlockItemBase(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
        RegistryThingsEvent.addItemToRegisterTab(() -> this);
    }

    public MMBlockItemBase(Block pBlock) {
        this(pBlock, MMItemBase.DEFAULT_PROPERTIES);
        RegistryThingsEvent.addItemToRegisterTab(() -> this);
    }
}
