package net.wfoas.gh.gui.world;

import java.io.IOException;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.gui.button.OverlayTexToggleButton;
import net.wfoas.gh.items.TradeItems;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayCreateNewWorld;

public class CreateImportWorld extends GuiScreen {

	private static final ResourceLocation BACKGROUND = new ResourceLocation("gamehelper", "gui/gui_background.png");
	OverlayTexToggleButton createworld;
	OverlayTexToggleButton normalDim, netherDim, endDim, minersDim;
	OverlayTexToggleButton flat;
	GuiTextField worldName;

	public static final ItemStack SURFACE = new ItemStack(Blocks.grass), NETHER = new ItemStack(Blocks.netherrack),
			END = new ItemStack(Blocks.end_stone), MINERSDIM = new ItemStack(Blocks.cobblestone),
			OK = new ItemStack(TradeItems.OK_ITEM), NOT_OK = new ItemStack(TradeItems.NOT_OK_ITEM),
			FLATMAP = new ItemStack(Blocks.carpet, 1, 5);
	protected int xSize, ySize;

	public CreateImportWorld() {
		this.xSize = 248;
		this.ySize = 184;
	}

	public void drawBackground() {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(BACKGROUND);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	public void keyTyped(char c, int i) {
		try {
			super.keyTyped(c, i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		worldName.textboxKeyTyped(c, i);
	}

	public void mouseClicked(int i, int j, int k) {
		try {
			super.mouseClicked(i, j, k);
		} catch (IOException e) {
			e.printStackTrace();
		}
		worldName.mouseClicked(i, j, k);
	}

	String nameOfWorld;

	public void updateScreen() {
		nameOfWorld = worldName.getText();
	}

	List<String> _normalDim, _netherDim, _endDim, _flatDim, _minersDim;

	@Override
	public void initGui() {
		super.initGui();
		_normalDim = Lists.newArrayList(new String[] { I18n.format("gamehelper.createworlddialog.surface") });
		_netherDim = Lists.newArrayList(new String[] { I18n.format("gamehelper.createworlddialog.nether") });
		_endDim = Lists.newArrayList(new String[] { I18n.format("gamehelper.createworlddialog.end") });
		_flatDim = Lists.newArrayList(new String[] { I18n.format("gamehelper.createworlddialog.flat") });
		_minersDim = Lists.newArrayList(new String[] { I18n.format("gamehelper.createworlddialog.minersdim") });
		createworld = new OverlayTexToggleButton(0, this.width / 2 + 5, this.height / 2 + 60, null, OK);
		createworld.setDisplayStringExplicitely(I18n.format("gamehelper.createworlddialog.proceed"));
		createworld.width = 86 + 18;
		this.buttonList.add(createworld);
		this.worldName = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 50, this.height / 2 - 55 - 2, 160,
				12);
		worldName.setFocused(true);
		worldName.setMaxStringLength(20);
		normalDim = new OverlayTexToggleButton(1, this.width / 2 - 60, this.height / 2 + 10, "S", SURFACE);
		normalDim.width = 18;
		netherDim = new OverlayTexToggleButton(2, this.width / 2 - 30, this.height / 2 + 10, "N", NETHER);
		netherDim.width = 18;
		endDim = new OverlayTexToggleButton(3, this.width / 2, this.height / 2 + 10, "E", END);
		endDim.width = 18;
		minersDim = new OverlayTexToggleButton(4, this.width / 2 + 30, this.height / 2 + 10, "M", MINERSDIM);
		minersDim.width = 18;
		flat = new OverlayTexToggleButton(5, this.width / 2 + 60, this.height / 2 + 10, "F", FLATMAP);
		flat.width = 18;
		this.buttonList.add(normalDim);
		this.buttonList.add(netherDim);
		this.buttonList.add(endDim);
		this.buttonList.add(flat);
		this.buttonList.add(minersDim);
	}

	@Override
	public void actionPerformed(GuiButton gb) {
		if (gb.id == createworld.id) {
			// newpackettoserverforcreating the new world
			String type = null;
			if (normalDim.isSelected())
				type = "normal";
			if (netherDim.isSelected())
				type = "nether";
			if (endDim.isSelected())
				type = "end";
			if (minersDim.isSelected())
				type = "minersdim";
			if (flat.isSelected())
				type = "flat";
			if (type == null) {
				System.err.println("type has no value! Now has 'normal'");
				type = "normal";
			} else {
				System.err.println("type has value: " + type);
			}
			System.out.println(nameOfWorld);
			NetworkHandler.sendToServer(new PacketPlayCreateNewWorld(nameOfWorld, type));
			// Minecraft.getMinecraft().currentScreen = null;
			Minecraft.getMinecraft().setIngameFocus();
		}
		if (gb.id == normalDim.id) {
			netherDim.setSelected(false);
			endDim.setSelected(false);
			normalDim.setSelected(true);
			minersDim.setSelected(false);
			flat.setSelected(false);
		} else if (gb.id == netherDim.id) {
			netherDim.setSelected(true);
			endDim.setSelected(false);
			normalDim.setSelected(false);
			minersDim.setSelected(false);
			flat.setSelected(false);
		} else if (gb.id == endDim.id) {
			netherDim.setSelected(false);
			endDim.setSelected(true);
			normalDim.setSelected(false);
			minersDim.setSelected(false);
			flat.setSelected(false);
		} else if (gb.id == minersDim.id) {
			netherDim.setSelected(false);
			endDim.setSelected(false);
			normalDim.setSelected(false);
			minersDim.setSelected(true);
			flat.setSelected(false);
		} else if (gb.id == flat.id) {
			netherDim.setSelected(false);
			endDim.setSelected(false);
			normalDim.setSelected(false);
			minersDim.setSelected(false);
			flat.setSelected(true);
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawBackground();
		super.drawScreen(x, y, par3);
		// this.drawDefaultBackground();
		worldName.drawTextBox();
		this.fontRendererObj.drawString(I18n.format("gamehelper.createworlddialog.title"), this.width / 2 - 30,
				this.height / 2 - 80, 0);
		this.fontRendererObj.drawString(I18n.format("gamehelper.createworlddialog.name"), this.width / 2 - 95,
				this.height / 2 - 55, 0);
		this.fontRendererObj.drawString(I18n.format("gamehelper.createworlddialog.worldtype"), this.width / 2 - 95,
				this.height / 2 - 15, 0);
		if (normalDim.isMouseOver()) {
			this.drawHoveringText(_normalDim, x, y);
		}
		if (netherDim.isMouseOver()) {
			this.drawHoveringText(_netherDim, x, y);
		}
		if (endDim.isMouseOver()) {
			this.drawHoveringText(_endDim, x, y);
		}
		if (flat.isMouseOver()) {
			this.drawHoveringText(_flatDim, x, y);
		}
		if (minersDim.isMouseOver()) {
			this.drawHoveringText(_minersDim, x, y);
		}
	}
}
