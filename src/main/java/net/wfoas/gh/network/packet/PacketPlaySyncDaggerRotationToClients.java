package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.dagger.staticdaggers.StaticDagger;
import net.wfoas.gh.dagger.throwable.ThrowableDagger;

public class PacketPlaySyncDaggerRotationToClients implements IMessage {

	World w;
	int worldID, entityID;
	float yaw, pitch;
	ItemStack thrownItem;

	public PacketPlaySyncDaggerRotationToClients() {
	}

	public PacketPlaySyncDaggerRotationToClients(World w, int eID, float yaw, float pitch, ItemStack thrownItem) {
		this.worldID = w.provider.getDimensionId();
		this.entityID = eID;
		this.yaw = yaw;
		this.pitch = pitch;
		this.thrownItem = thrownItem;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		World w = DimensionManager.getWorld(worldID = buf.readInt());
		entityID = buf.readInt();
		yaw = buf.readFloat();
		pitch = buf.readFloat();
		thrownItem = ItemStack.loadItemStackFromNBT(ByteBufUtils.readTag(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(worldID);
		buf.writeInt(entityID);
		buf.writeFloat(yaw);
		buf.writeFloat(pitch);
		if (thrownItem == null) {
			thrownItem = new ItemStack(GameHelperCoreModule.IRON_DAGGER);
		}
		ByteBufUtils.writeTag(buf, thrownItem.writeToNBT(new NBTTagCompound()));
	}

	public static class PacketPlaySyncDaggerRotationHandler
			implements IMessageHandler<PacketPlaySyncDaggerRotationToClients, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlaySyncDaggerRotationToClients message, MessageContext ctx) {
			if (Minecraft.getMinecraft().theWorld.provider.getDimensionId() != message.worldID)
				return null;
			GameHelper.getScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					Entity e = Minecraft.getMinecraft().theWorld.getEntityByID(message.entityID);
					if (!(e instanceof ThrowableDagger) && !(e instanceof StaticDagger)) {
						return;
					}
					if (e instanceof ThrowableDagger) {
						ThrowableDagger dagger = (ThrowableDagger) e;
						dagger.saveItemStackToEntityData(message.thrownItem);
						dagger.setPlayersYawPitch(message.yaw, message.pitch);
					} else if (e instanceof StaticDagger) {
						// static dagger code //TODO implements static dagger or remove static dagger
						StaticDagger dagger = (StaticDagger) e;
						dagger.saveItemStackToEntityData(message.thrownItem);
						dagger.setPlayersYawPitch(message.yaw, message.pitch);
					}
				}
			}, 0l);
			return null;
		}
	}
}