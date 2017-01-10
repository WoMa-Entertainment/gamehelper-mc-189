package net.wfoas.gh.gui.login;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.config.GuiCheckBox;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.gui.field.GuiPasswordField;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.securedlogin.PacketPlayPasswordFromPlayer;

@SideOnly(Side.CLIENT)
public class GuiScreenLogin extends GuiScreen {
	private final String label = I18n.format("gamehelper.gui.text.login");
	private GuiTextField passField;
	private final int posYConfirm = 85;

	public GuiScreenLogin() {
	}

	public void updateScreen() {
		if (passField != null)
			passField.updateCursorCounter();
	}

	String login;

	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 12, I18n.format(label)));
		((GuiButton) buttonList.get(0)).enabled = false;
		buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 12, I18n.format("gui.cancel")));
		String savedPass = "";
		passField = new GuiPasswordField(3, fontRendererObj, width / 2 - 100, 60, 200, 20);
		passField.setFocused(true);
		passField.setText("");
		login = I18n.format("gamehelper.msg.login.passreq", GameHelper.getUtils().getCurrentServerIP());
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.id == 0) {
			PacketPlayPasswordFromPlayer pppfp = new PacketPlayPasswordFromPlayer(passField.getText());
			NetworkHandler.sendToServer(pppfp);
			mc.displayGuiScreen(null);
			mc.setIngameFocus();
		} else if (par1GuiButton.id == 1) {
			GameHelper.getUtils().leaveServer(Minecraft.getMinecraft().thePlayer, "gamehelper.msg.canceled_login");
			if (GameHelper.getUtils().isSinglePlayer()) {
				this.mc.loadWorld((WorldClient) null);
				this.mc.displayGuiScreen(new GuiMainMenu());
			}
		}
	}

	protected void keyTyped(char par1, int par2) {
		if (GuiScreen.isShiftKeyDown() && par2 == 1) {
			// Minecraft.getMinecraft().setIngameFocus();
			GameHelper.getUtils().leaveServer(Minecraft.getMinecraft().thePlayer, "gamehelper.msg.canceled_login");
			if (GameHelper.getUtils().isSinglePlayer()) {
				this.mc.loadWorld((WorldClient) null);
				this.mc.displayGuiScreen(new GuiMainMenu());
			}
		}
		// if (Keyboard.KEY_ESCAPE == par2 ||){
		// return;
		// }
		passField.textboxKeyTyped(par1, par2);
		((GuiButton) buttonList.get(0)).enabled = (passField.getText().trim().length() > 0);

		if ((par2 == 28) || (par2 == 156)) {
			actionPerformed((GuiButton) buttonList.get(0));
		}
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		try {
			super.mouseClicked(par1, par2, par3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		passField.mouseClicked(par1, par2, par3);
	}

	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, label, width / 2, 20, 16777215);
		drawString(fontRendererObj, login, width / 2 - fontRendererObj.getStringWidth(login) / 2, 47, 10526880);
		passField.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}
}