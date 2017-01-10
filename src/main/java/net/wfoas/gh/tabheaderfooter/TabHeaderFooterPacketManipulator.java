package net.wfoas.gh.tabheaderfooter;

import java.lang.reflect.Field;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.config.GHConfigKey;

public class TabHeaderFooterPacketManipulator {
	protected static Field HEADER_FIELD, FOOTER_FIELD;

	public static void init() {
		HEADER_FIELD = S47PacketPlayerListHeaderFooter.class.getDeclaredFields()[0];
		FOOTER_FIELD = S47PacketPlayerListHeaderFooter.class.getDeclaredFields()[1];
		HEADER_FIELD.setAccessible(true);
		FOOTER_FIELD.setAccessible(true);
	}

	public static void sendIfActive(EntityPlayerMP player) {
		if (GameHelper.instance.CONFIG.getBoolean(GHConfigKey.USE_TABLIST_HEADER_FOOTER)) {
			manipulateAndSendToPlayer(player);
		}
	}

	public static void manipulateAndSendToPlayer(EntityPlayerMP playerMP) {
		S47PacketPlayerListHeaderFooter packet = createPacket();
		sendToPlayer(playerMP, packet);
	}

	public static void sendToPlayer(EntityPlayerMP playerMP, S47PacketPlayerListHeaderFooter tabpacket) {
		playerMP.playerNetServerHandler.sendPacket(tabpacket);
	}

	public static S47PacketPlayerListHeaderFooter createPacket() {
		return createPacket(
				new ChatComponentTranslation(GameHelper.instance.CONFIG.getProperty(GHConfigKey.TABLIST_HEADER)),
				new ChatComponentTranslation(GameHelper.instance.CONFIG.getProperty(GHConfigKey.TABLIST_FOOTER)));
	}

	public static S47PacketPlayerListHeaderFooter createPacket(IChatComponent header, IChatComponent footer) {
		S47PacketPlayerListHeaderFooter packet = new S47PacketPlayerListHeaderFooter(header);
		try {
			FOOTER_FIELD.set(packet, footer);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return packet;
	}
}
