package net.wfoas.gh.network.packet;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.enchaltar.EnchantmentAltarContainer;
import net.wfoas.gh.multipleworlds.GHSimpleTeleporter;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.WorldUtils;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.omapi.GameHelperAPI;

public class PacketPlayCreateNewWorld implements IMessage {

	static enum WorldType {
		SURFACE(0, "_overworld"), NETHER(-1, "_nether"), END(1, "_the_end"), MINERSDIM(2, "_minersdim"), FLAT(3,
				"_overworld");
		final int wID;
		final String suffix;

		WorldType(int id, String suffix) {
			wID = id;
			this.suffix = suffix;
		}

		public static WorldType getById(int id) {
			for (WorldType t : WorldType.values()) {
				if (t.wID == id)
					return t;
			}
			return null;
		}
	}

	public PacketPlayCreateNewWorld() {
	}

	String worldName;
	int worldType;

	public PacketPlayCreateNewWorld(String worldName, String type) {
		this.worldName = worldName;
		System.out.println("WorldType: " + type);
		if (type.equalsIgnoreCase("normal")) {
			worldType = WorldType.SURFACE.wID;
		} else if (type.equalsIgnoreCase("nether")) {
			worldType = WorldType.NETHER.wID;
		} else if (type.equalsIgnoreCase("end")) {
			worldType = WorldType.END.wID;
		} else if (type.equalsIgnoreCase("minersdim")) {
			worldType = WorldType.MINERSDIM.wID;
		} else if (type.equalsIgnoreCase("flat")) {
			worldType = WorldType.FLAT.wID;
		}
		System.out.println("WorldType: " + worldType);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		int len = buf.readInt();
		byte[] string = new byte[len];
		buf.readBytes(string);
		worldType = buf.readInt();
		worldName = new String(string, StandardCharsets.UTF_8);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		byte[] utf8_enc_name = worldName.getBytes(StandardCharsets.UTF_8);
		buf.writeInt(utf8_enc_name.length);
		buf.writeBytes(utf8_enc_name);
		buf.writeInt(worldType);
	}

	public static class PacketPlayCreateNewWorldHandler implements IMessageHandler<PacketPlayCreateNewWorld, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlayCreateNewWorld message, final MessageContext ctx) {
			final IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj; // or
																											// Minecraft.getMinecraft()
																											// on
																											// the
																											// client
			final Runnable r = new Runnable() {
				@Override
				public void run() {
					if (!GameHelper.getUtils().hasRightsToCreateWorld(ctx.getServerHandler().playerEntity)) {
						ctx.getServerHandler().playerEntity
								.addChatComponentMessage(new ChatComponentTranslation("gamehelper.createworld.noperm"));
						return;
					}
					GHWorld ghw;
					System.out.println("WorldType: " + message.worldType + ", WorldName: " + message.worldName);
					System.out.println("Provider: " + GHWorldManager.getWorldProviderStringById(message.worldType));
					String worldname;
					// if (message.worldName.endsWith("_nether") ||
					// message.worldName.endsWith("_the_end")
					// || message.worldName.endsWith("_minersdim")) {
					worldname = GHWorldManager.removeDimensionSpecificAppendices(message.worldName);
					// }
					worldname = worldname + WorldType.getById(message.worldType).suffix;
					if (message.worldType == 3) {
						ghw = new GHWorld(message.worldName, GHWorldManager.PROVIDER_NORMAL, "FLAT");
					} else {
						ghw = new GHWorld(message.worldName,
								GHWorldManager.getWorldProviderStringById(message.worldType),
								GHWorldManager.WORLD_TYPE_DEFAULT);
					}
					if (DimensionManager.getWorld(0).getWorldInfo().getWorldName()
							.equalsIgnoreCase(message.worldName)) {
						ctx.getServerHandler().playerEntity.addChatComponentMessage(
								new ChatComponentTranslation("gamehelper.createworld.error.exists"));
						return;
					}
					if (GHWorldManager.isWorldAlreadyCreated(ghw)) {
						if (GHWorldManager.isWorldAlreadyLoaded(ghw)) {
							return;
						} else {
							GHWorldManager.loadWorld(ghw);
							return;
						}
					} else {
						GHWorldManager.createWorld(ghw);
						// WorldUtils.teleportPlayerToDimension((EntityPlayerMP)
						// ctx.getServerHandler().playerEntity,
						// ghw.dimensionId);
						// WorldUtils.transferPlayerToDimension((EntityPlayerMP)
						// ctx.getServerHandler().playerEntity,
						// ghw.dimensionId, new
						// GHSimpleTeleporter(DimensionManager.getWorld(ghw.dimensionId)));
					}
				}
			};
			GameHelperAPI.ghAPI().ghScheduler().scheduleSyncDelayedTask(new Runnable() {
				public void run() {
					mainThread.addScheduledTask(r);
				}
			}, 1l);
			return null; // no response in this case
		}

	}
}
