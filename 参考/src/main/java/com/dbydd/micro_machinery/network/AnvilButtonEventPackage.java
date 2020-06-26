package com.dbydd.micro_machinery.network;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityForgingAnvil;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class AnvilButtonEventPackage implements IMessage {
    public NBTTagCompound compound;
    public int x, y, z, dimesion;

    public AnvilButtonEventPackage() {
    }

    public AnvilButtonEventPackage(NBTTagCompound compound, BlockPos pos, int dimesion) {
        this.compound = compound;
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.dimesion = dimesion;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        compound = ByteBufUtils.readTag(buf);
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        dimesion = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, compound);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(dimesion);
    }

    public static class MessageHandler implements IMessageHandler<AnvilButtonEventPackage, IMessage> {
        @Override
        public IMessage onMessage(AnvilButtonEventPackage message, MessageContext ctx) {
            if (ctx.side == Side.SERVER) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    TileEntity te = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(message.dimesion).getTileEntity(new BlockPos(message.x, message.y, message.z));
                    assert te != null;
                    if (te instanceof TileEntityForgingAnvil) {
                        te.readFromNBT(message.compound);
                    }
                });
            }
            return null;
        }
    }
}
