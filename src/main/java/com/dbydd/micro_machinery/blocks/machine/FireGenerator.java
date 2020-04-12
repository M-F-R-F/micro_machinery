package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityFireGenerator;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class FireGenerator extends BlockContainerBase {

    public FireGenerator() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setUnlocalizedName("firegenerator");
        setRegistryName("firegenerator");
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        setHardness(3.0f);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFireGenerator(25600);
    }

}
