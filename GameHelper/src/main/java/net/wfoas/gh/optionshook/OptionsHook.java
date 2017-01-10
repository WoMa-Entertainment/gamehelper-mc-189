package net.wfoas.gh.optionshook;

import java.io.IOException;
import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ReportedException;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OptionsHook {
	protected static final int GH_OPTIONS_ID = -485;

	@SubscribeEvent
	public void guiCreate(InitGuiEvent initgui) {
		if (initgui.gui instanceof GuiOptions) {
			GuiButton gb = new GuiButton(GH_OPTIONS_ID, initgui.gui.width / 2 - 155 + 2 % 2 * 160,
					initgui.gui.height / 6 - 12 + 24 * (2 >> 1), 150, 20, I18n.format("gamehelper.gui.menu.options"));
			initgui.buttonList.add(gb);
		}
	}

	@SubscribeEvent
	public void guiCreate(GuiScreenEvent.ActionPerformedEvent initgui) {
		if (initgui.gui instanceof GuiOptions && initgui.button.id == GH_OPTIONS_ID) {
			Minecraft.getMinecraft().displayGuiScreen(
					new GuiScreenGHOptions(reflectionGetParentOfGuiOptions((GuiOptions) initgui.gui)));
		}
	}

	static Field parentOfGuiOptions = null;

	private GuiScreen reflectionGetParentOfGuiOptions(GuiOptions go) {
		try {
			return reflectionGetParentOfGuiOptions0(go);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}

	private GuiScreen reflectionGetParentOfGuiOptions0(GuiOptions go)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		if (parentOfGuiOptions == null) {
			Field[] fields = GuiOptions.class.getDeclaredFields();
			for (Field f : fields) {
				if (f.getType().equals(GuiScreen.class)) {
					parentOfGuiOptions = f;
					parentOfGuiOptions.setAccessible(true);
					break;
				}
			}
		}
		if (parentOfGuiOptions == null)
			throw new NoSuchFieldException(
					"There was no field with type GuiScreen found! Please report this error to the mod author with the entire crash report!");
		return (GuiScreen) parentOfGuiOptions.get(go);
	}
}
