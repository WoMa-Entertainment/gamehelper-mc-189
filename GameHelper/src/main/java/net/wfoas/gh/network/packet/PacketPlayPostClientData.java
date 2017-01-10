package net.wfoas.gh.network.packet;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.WorldUtils;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.world.owner.WorldOwners;

public class PacketPlayPostClientData implements IMessage {
	public List<String> ownedWorlds, onlinePlayers, allWorlds;

	public PacketPlayPostClientData() {

	}

	public PacketPlayPostClientData(EntityPlayerMP playerServerSide) {
		ownedWorlds = WorldOwners.getAllForPlayer(playerServerSide);
		if (ownedWorlds == null)
			ownedWorlds = new ArrayList<String>();
		onlinePlayers = new ArrayList<String>();
		for (EntityPlayerMP entityPlayer : GameHelper.getUtils().getOnlinePlayers()) {
			onlinePlayers.add(entityPlayer.getName());
		}
		allWorlds = GHWorldManager.getWorlds();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class PacketPlayPostClientDataHandler implements IMessageHandler<PacketPlayPostClientData, IMessage> {

		@Override
		public IMessage onMessage(PacketPlayPostClientData message, MessageContext ctx) {
			return null;
		}
	}
}