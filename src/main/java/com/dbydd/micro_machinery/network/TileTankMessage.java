//package com.dbydd.micro_machinery.network;
//
//import io.netty.buffer.ByteBuf;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraftforge.fluids.Fluid;
//import net.minecraftforge.fluids.FluidTank;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//
//import java.nio.ByteBuffer;
//
//public class TileTankMessage implements IMessage {
//
//    private NBTTagCompound toSend;
//
//    public TileTankMessage() {
//    }
//
//    public TileTankMessage(NBTTagCompound toSend) {
//        this.toSend = toSend;
//    }
//
//    /**
//     * Convert from the supplied buffer into your specific message type
//     *
//     * @param buf
//     */
//    @Override
//    public void fromBytes(ByteBuf buf) {
//        buf.writeBytes(ByteBuffer.allocateDirect(toSend.getByte("tank")));
//    }
//
//    /**
//     * Deconstruct your message into the supplied byte buffer
//     *
//     * @param buf
//     */
//    @Override
//    public void toBytes(ByteBuf buf) {
//        toSend.setByte()
//    }
//}
//wip
//todo