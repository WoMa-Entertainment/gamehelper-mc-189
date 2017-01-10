package tconstruct.client.tabs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.minersinventory.MinersInventoryGui;
import net.wfoas.gh.survivaltabs.GHTabMinersInv;

public class TabRegistry {
	private static ArrayList<AbstractTab> tabList = new ArrayList();
	// private static Field buttonListField;
	//
	// static {
	// try {
	// for (Field f : GuiScreen.class.getDeclaredFields()) {
	// if (List.class.isAssignableFrom(f.getType())) {
	// buttonListField = f;
	// break;
	// }
	// }
	// buttonListField.setAccessible(true);
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public static List<?> getButtonList(GuiScreen gui) {
	// try {
	// return (List) buttonListField.get(gui);
	// } catch (IllegalArgumentException e) {
	// return null;
	// } catch (IllegalAccessException e) {
	// }
	// return null;
	// }

	public static void registerTab(AbstractTab tab) {
		tabList.add(tab);
	}

	public static ArrayList<AbstractTab> getTabList() {
		return tabList;
	}

	/**
	 * There is no need to use Java-Reflection because the button list is
	 * visible which is passed by the GuiScreenEvent.
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void guiPostInit(GuiScreenEvent.InitGuiEvent event) {
		if ((event.gui instanceof GuiInventory)) {
			int xSize = 176;
			int ySize = 166;
			int guiLeft = (event.gui.width - xSize) / 2;
			int guiTop = (event.gui.height - ySize) / 2;
			guiLeft += getPotionOffset();
			updateTabValues(guiLeft, guiTop, InventoryTabVanilla.class);
			addTabsToList(event.buttonList);
		} else if ((event.gui instanceof MinersInventoryGui)) {
			MinersInventoryGui mig = (MinersInventoryGui) event.gui;
			int xSize = mig.xsize();
			int ySize = mig.ysize();
			int guiLeft = (event.gui.width - xSize) / 2;
			int guiTop = (event.gui.height - ySize) / 2;
			// guiLeft += getPotionOffset();
			updateTabValues(guiLeft, guiTop, GHTabMinersInv.class);
			addTabsToList(event.buttonList);
		}
	}

	//
	// @SideOnly(Side.CLIENT)
	// @SubscribeEvent
	// public void preGui(GuiScreenEvent.DrawScreenEvent.Pre event) {
	// if ((event.gui instanceof GuiInventory)) {
	// // GlStateManager.disableLighting();
	// // GlStateManager.disableDepth();
	// // GlStateManager.colorMask(true, true, true, false);
	// // GlStateManager.colorMask(true, true, true, true);
	// // GlStateManager.enableLighting();
	// // GlStateManager.enableDepth();
	// // RenderHelper.enableGUIStandardItemLighting();
	// // GlStateManager.disableLighting();
	// // GlStateManager.disableDepth();
	// // GlStateManager.colorMask(true, true, true, false);
	// // GlStateManager.colorMask(true, true, true, true);
	// // GlStateManager.enableLighting();
	// // GlStateManager.enableDepth();
	// // GlStateManager.enableBlend();
	// // GlStateManager.enableAlpha();
	// // GlStateManager.enableLighting();
	// // GlStateManager.enableDepth();
	// // RenderHelper.disableStandardItemLighting();
	// // RenderHelper.enableGUIStandardItemLighting();
	// }
	// }

	private static Minecraft mc = FMLClientHandler.instance().getClient();

	public static void openInventoryGui() {
		mc.thePlayer.sendQueue.addToSendQueue(new C0DPacketCloseWindow(mc.thePlayer.openContainer.windowId));
		GuiInventory inventory = new GuiInventory(mc.thePlayer);
		mc.displayGuiScreen(inventory);
	}

	public static void updateTabValues(int cornerX, int cornerY, Class<?> selectedButton) {
		int count = 2;
		for (int i = 0; i < tabList.size(); i++) {
			AbstractTab t = tabList.get(i);
			if (t.shouldAddToList()) {
				t.id = count;
				t.xPosition = (cornerX + (count - 2) * 28);
				t.yPosition = (cornerY - 28);
				t.enabled = (!t.getClass().equals(selectedButton));
				count++;
			}
		}
	}

	public static void addTabsToList(List buttonList) {
		for (AbstractTab tab : tabList) {
			if (tab.shouldAddToList()) {
				buttonList.add(tab);
			}
		}
	}

	public static void recalcAndAddTabsToList(GuiContainer gc, List buttonList) {
		for (AbstractTab tab : tabList) {
			if (tab.shouldAddToList()) {
				AbstractTab t = tab.clone();
				// t.
				buttonList.add(t);
			}
		}
	}

	public static int getPotionOffset() {
		if (!mc.thePlayer.getActivePotionEffects().isEmpty()) {
			if (Loader.isModLoaded("NotEnoughItems")) {
				try {
					Class<?> c = Class.forName("codechicken.nei.NEIClientConfig");
					Object hidden = c.getMethod("isHidden", new Class[0]).invoke(null, new Object[0]);
					Object enabled = c.getMethod("isEnabled", new Class[0]).invoke(null, new Object[0]);
					if ((hidden != null) && ((hidden instanceof Boolean)) && (enabled != null)
							&& ((enabled instanceof Boolean))) {
						if ((((Boolean) hidden).booleanValue()) || (!((Boolean) enabled).booleanValue())) {
							return 60;
						}
					}
				} catch (Exception localException) {
				}
			}
			return 60;
		}
		return 0;
	}
}
