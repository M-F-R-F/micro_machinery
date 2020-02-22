package com.dbydd.micro_machinery.blocks.machine;

import com.dbydd.micro_machinery.Micro_Machinery;
import com.dbydd.micro_machinery.Reference;
import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import com.dbydd.micro_machinery.init.ModBlocks;
import com.dbydd.micro_machinery.init.ModItems;
import com.dbydd.micro_machinery.util.IHasModel;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class BlockForgingAnvil extends BlockContainer implements IHasModel {
    private final int level;

    public BlockForgingAnvil(String name, int level) {
        super(Material.ANVIL);
        this.level = level;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Micro_Machinery.Micro_Machinery);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Override
    public void registerModels() {
        Micro_Machinery.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(Micro_Machinery.instance, Reference.GUI_ForgingAnvil, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityForgingAnvil(this.level);
    }
}
