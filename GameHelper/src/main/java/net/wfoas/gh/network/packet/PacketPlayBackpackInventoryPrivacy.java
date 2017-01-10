package net.wfoas.gh.network.packet;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPlayBackpackInventoryPrivacy implements IMessage {
	
	public PacketPlayBackpackInventoryPrivacy() { 
	}
	EntityPlayer ep;
	World w;
	boolean Private;
	public PacketPlayBackpackInventoryPrivacy(EntityPlayer ep, boolean priv){
		this.ep = ep;
		this.Private = priv;
	}

//	@Override
//	public void processPacket(INetHandler handler) {
//		NBTTagCompound nbttc = ep.getHeldItem().getTagCompound();
//		nbttc.setBoolean("private", Private);
//	}

	@Override
	public void fromBytes(ByteBuf buf) {
		w = DimensionManager.getWorld(buf.readInt());
		ep = w.getPlayerEntityByUUID(new UUID(buf.readLong(), buf.readLong()));
		Private = buf.readBoolean();		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(ep.worldObj.provider.getDimensionId());
		buf.writeLong(ep.getUniqueID().getMostSignificantBits());
		buf.writeLong(ep.getUniqueID().getLeastSignificantBits());
		buf.writeBoolean(Private);		
	}
	
	public static class PacketBackpackHandler implements IMessageHandler<PacketPlayBackpackInventoryPrivacy, IMessage>{

		@Override
		public IMessage onMessage(final PacketPlayBackpackInventoryPrivacy message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj; // or Minecraft.getMinecraft() on the client
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
            		NBTTagCompound nbttc = message.ep.getHeldItem().getTagCompound();
            		nbttc.setBoolean("private", message.Private);
            		System.out.println("Backpack of " + message.ep.getName() + " is now " + (message.Private ? "private" : "public"));
            		message.ep.addChatMessage(new ChatComponentTranslation(message.Private ? "message.gamehelper.backpack.private" : "message.gamehelper.backpack.public"));
                }
            });
            return null; // no response in this case
		}
		
	}

}
