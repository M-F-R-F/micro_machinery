package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.tileentities.MMFEMachineBase;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityFireGenerator;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class FireGenerator extends BlockContainer implements IHasModel {
    protected FireGenerator() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setUnlocalizedName("testcable");
        setRegistryName("testcable");
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        setHardness(3.0f);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Override
    public void registerModels() {
        Micro_Machinery.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFireGenerator(25600);
    }

}
