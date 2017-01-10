package net.wfoas.gh.tablist;

import java.lang.reflect.Field;

import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.util.IChatComponent;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;

public class TabList {
	public static final String LINE_SEPERATOR_UNIX = "\n";

	public static final String HEADER = ChatColor.GREEN + "WFOAS-Netzwerk | " + ChatColor.AQUA + "Survival/Freebuild"
			+ LINE_SEPERATOR_UNIX + "Survivalmodus", FOOTER = GameHelper.PREFIX + ChatColor.AQUA + "SURVIVALSERVER1";

	private static Field footerField;

	static {
		footerField = S47PacketPlayerListHeaderFooter.class.getDeclaredFields()[1];
		footerField.setAccessible(true);
	}

	public static void sendList(EntityPlayerMP player, IChatComponent header, IChatComponent footer) {
		try {
			sendList0(player, header, footer);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private static void setFooterHackaryWay(S47PacketPlayerListHeaderFooter packet, IChatComponent footer) {
		try {
			footerField.set(packet, footer);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void sendList0(EntityPlayerMP player, IChatComponent header, IChatComponent footer)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// IChatBaseComponent tabtitle = ChatSerializer.a("{\"text\": \"" +
		// header + "\"}");
		// IChatBaseComponent footertab = ChatSerializer.a("{\"text\": \"" +
		// footer + "\"}");
		// PacketPlayOutPlayerListHeaderFooter tabpack = new
		// PacketPlayOutPlayerListHeaderFooter(tabtitle);
		S47PacketPlayerListHeaderFooter packet = new S47PacketPlayerListHeaderFooter(header);
		setFooterHackaryWay(packet, footer);
		player.playerNetServerHandler.sendPacket(packet);
		// if (f == null) {GuiIngameForge
		// f = tabpack.getClass().getDeclaredField("b");
		// f.setAccessible(true);
		// }
		// f.set(tabpack, footertab);
		// ((CraftPlayer)
		// player).getHandle().playerConnection.sendPacket(tabpack);
	}
}
