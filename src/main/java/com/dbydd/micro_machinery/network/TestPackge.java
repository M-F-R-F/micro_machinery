package com.dbydd.micro_machinery.network;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class TestPackge implements IMessage {
    public NBTTagCompound compound;
    public int x, y, z;

    public TestPackge() {
    }

    public TestPackge(NBTTagCompound compound, BlockPos pos) {
        this.compound = compound;
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        compound = ByteBufUtils.readTag(buf);
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, compound);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    public static class TestHandler implements IMessageHandler<TestPackge, IMessage> {
        @Override
        public IMessage onMessage(TestPackge message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
//                FluidTank tank = null;
//                tank.readFromNBT(message.compound);
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    TileEntityKlin te = (TileEntityKlin) Minecraft.getMinecraft().world.getTileEntity(new BlockPos(message.x, message.y, message.z));
                    assert te != null;
                    te.readFromNBT(message.compound);
                });
            }
            return null;
        }
    }
}
