package net.wfoas.gh.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dagger.throwable.ThrowableDagger;
import net.wfoas.gh.omapi.GameHelperAPI;

public class PacketPlaySyncDaggerRotationToServer implements IMessage {

	World w;
	int worldID, entityID;
	float yaw, pitch;

	public PacketPlaySyncDaggerRotationToServer() {
	}

	public PacketPlaySyncDaggerRotationToServer(World w, int eID, float yaw, float pitch) {
		this.worldID = w.provider.getDimensionId();
		this.entityID = eID;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		World w = DimensionManager.getWorld(worldID = buf.readInt());
		entityID = buf.readInt();
		yaw = buf.readFloat();
		pitch = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(worldID);
		buf.writeInt(entityID);
		buf.writeFloat(yaw);
		buf.writeFloat(pitch);
	}

	public static class PacketPlaySyncDaggerRotationHandler
			implements IMessageHandler<PacketPlaySyncDaggerRotationToServer, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlaySyncDaggerRotationToServer message, final MessageContext ctx) {
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				@Override
				public void run() {
					World w = DimensionManager.getWorld(message.worldID);
					Entity e = w.getEntityByID(message.entityID);
					if (!(e instanceof ThrowableDagger)) {
						return;
					}
					ThrowableDagger dagger = (ThrowableDagger) e;
					dagger.setPlayersYawPitch(message.yaw, message.pitch);
					dagger.syncToClients();
				}
			}, 0l);
			return null;// do sth
		}

	}
}
