package mfrf.micro_machinery.blocks.machines.ter_test;

import mfrf.dbydd.micro_machinery.registeried_lists.RegisteredBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TerTestTile extends BlockEntity {
    public TerTestTile() {
        super(RegisteredBlockEntityTypes.TEST_TILE_TYPE.get());
    }
}
