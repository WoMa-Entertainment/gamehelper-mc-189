package net.wfoas.gh.network.packet;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.EncoderException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.network.NetworkUtils;

public class PacketPlayProtectedBlockManipulateNBT implements IMessage {

	NBTTagCompound head = null;
	BlockPos pos = null;

	public PacketPlayProtectedBlockManipulateNBT() {// receiver constructor
	}

	public PacketPlayProtectedBlockManipulateNBT(BlockPos pos, NBTTagCompound head) {
		this.pos = pos;
		this.head = head;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = NetworkUtils.readBlockPos(buf);
		this.head = NetworkUtils.readNBTTagCompoundFromBuffer(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NetworkUtils.writeBlockPos(pos, buf);
		NetworkUtils.writeNBTTagCompoundToBuffer(head, buf);
	}

	public static class PacketPlayProtectedBlockManipulateNBTHandler
			implements IMessageHandler<PacketPlayProtectedBlockManipulateNBT, IMessage> {

		@Override
		public IMessage onMessage(PacketPlayProtectedBlockManipulateNBT message, MessageContext ctx) {
			
			return null;
		}

	}

}
