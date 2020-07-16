package com.dbydd.micro_machinery.registery_lists;

import com.dbydd.micro_machinery.blocks.mathines.TileEntityRegistryBase;
import com.dbydd.micro_machinery.blocks.mathines.klin.TileKlin;

public class Registereyed_Tileentities {
    public static final TileEntityRegistryBase KLIN_TYPE = new TileEntityRegistryBase("klin", TileKlin::new, RegisteryedBlocks.KLIN);


    public static void init(){

    }
}
