package net.wfoas.gh.enchaltar;

import org.lwjgl.util.glu.Project;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.gui.button.OverlayTexToggleButton;
import net.wfoas.gh.items.TradeItems;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayEnchantmentAltarApplyEnchantment;
import net.wfoas.gh.omapi.GameHelperAPI;

public class EnchantmentAltarGui extends GuiContainer {
	private static final ResourceLocation ENCHANTMENT_TABLE_GUI_TEXTURE = new ResourceLocation("gamehelper",
			"gui/container/enchantment_altar.png");

	public static final int GUI_ID = GuiHandler.ENCHANTMENT_ALTAR_GUI;
	EnchantmentAltarContainer container;

	public EnchantmentAltarGui(InventoryPlayer inventory) {
		super(new EnchantmentAltarContainer(inventory));
		container = (EnchantmentAltarContainer) this.inventorySlots;
		this.xSize = 256;
		this.ySize = 194;
	}

	OverlayTexToggleButton[] ENCHANTMENT_SELECTOR = new OverlayTexToggleButton[20];
	OverlayTexToggleButton[] LEVEL = new OverlayTexToggleButton[10];
	OverlayTexToggleButton CONFIRM;

	String ench = "-";
	Enchantment sel_ench;
	int level = 0;
	Enchantment[] itemBoundEnchantments = new Enchantment[20];

	public void resetItemBoundEnchantments() {
		for (int i = 0; i < itemBoundEnchantments.length; i++) {
			itemBoundEnchantments[i] = null;
		}
		for (int i = 0; i < ENCHANTMENT_SELECTOR.length; i++) {
			ENCHANTMENT_SELECTOR[i].visible = true;
			ENCHANTMENT_SELECTOR[i].enabled = true;
		}
	}

	public void setItemBoundEnchantments(Enchantment[] ench) {
		resetItemBoundEnchantments();
		for (int i = 0; i < Math.min(itemBoundEnchantments.length, ench.length); i++) {
			itemBoundEnchantments[i] = ench[i];
			if (ench[i] == null) {
				ENCHANTMENT_SELECTOR[i + 1].enabled = false;
				ENCHANTMENT_SELECTOR[i + 1].visible = false;
			} else {
				ENCHANTMENT_SELECTOR[i + 1].enabled = true;
				ENCHANTMENT_SELECTOR[i + 1].visible = true;
			}
		}
		if (ench.length < itemBoundEnchantments.length) {
			int left = itemBoundEnchantments.length - ench.length;
			for (int i = (itemBoundEnchantments.length - left); i < itemBoundEnchantments.length; i++) {
				ENCHANTMENT_SELECTOR[i].enabled = false;
				ENCHANTMENT_SELECTOR[i].visible = false;
			}
		}
		for (int lid = 1; lid <= 10; lid++) {
			if (container.entityPlayer.experienceLevel >= lid * 2) {
				for (OverlayTexToggleButton lb : LEVEL) {
					if (lb.id == (lid + 20)) {
						lb.enabled = true;
					}
				}
			} else {
				for (OverlayTexToggleButton lb : LEVEL) {
					if (lb.id == (lid + 20)) {
						lb.enabled = false;
					}
				}
			}
		}
	}

	public String getNameOfItemBoundEnchantment(int index) {
		Enchantment ench = itemBoundEnchantments[index];
		if (ench == null) {
			return ChatColor.RED + "-";
		}
		return ChatColor.LIGHT_PURPLE
				// + I18n.format("text.gamehelper.enchantment")
				+ StatCollector.translateToLocal(ench.getName());
	}

	@Override
	public void actionPerformed(GuiButton gb) {
		if (gb.id == CONFIRM.id) {
			if (sel_ench != null && level != 0) {
				NetworkHandler.sendToServer(
						new PacketPlayEnchantmentAltarApplyEnchantment(sel_ench, level, container.entityPlayer));
				for (OverlayTexToggleButton tounsel : ENCHANTMENT_SELECTOR) {
					tounsel.setSelected(false);
				}
				for (OverlayTexToggleButton tounsel : LEVEL) {
					tounsel.setSelected(false);
				}
				sel_ench = null;
				level = 0;
			}
			return;
		}
		for (OverlayTexToggleButton enchb : ENCHANTMENT_SELECTOR) {
			if (enchb.id == gb.id) {
				enchb.setSelected(true);
				sel_ench = itemBoundEnchantments[gb.id - 1];// index + 1 =
															// guibutton
				for (OverlayTexToggleButton tounsel : ENCHANTMENT_SELECTOR) {
					if (tounsel.id != enchb.id) {
						tounsel.setSelected(false);
					}
				}
				return;
			}
		}
		for (OverlayTexToggleButton lb : LEVEL) {
			if (lb.id == gb.id) {
				lb.setSelected(true);
				level = lb.id - 20;
				for (OverlayTexToggleButton tounsel : LEVEL) {
					if (tounsel.id != lb.id) {
						tounsel.setSelected(false);
					}
				}
				if (container.entityPlayer.experienceLevel == (level * 2)) {

				}
				return;
			}
		}
	}

	public void drawToolTipOfButton(int mouseX, int mouseY) {
		if (CONFIRM.isMouseOver()) {
			if (container.entityPlayer.experienceLevel < 20) {
				this.drawHoveringText(
						Lists.newArrayList(new String[] {
								ChatColor.RED + "Du brauchst mindestens 20 Level um Verzaubern zu können" }),
						mouseX, mouseY);
				return;
			}
			if (sel_ench == null) {
				this.drawHoveringText(
						Lists.newArrayList(new String[] { ChatColor.RED + "Wähle erst eine Verzauberung" }), mouseX,
						mouseY);
				return;
			}
			if (level == 0) {
				this.drawHoveringText(
						Lists.newArrayList(new String[] { ChatColor.RED + "Wähle erst die Anzahl an Level" }), mouseX,
						mouseY);
				return;
			}
			if (getStackSizeOfItem(container.tableInventory.getStackInSlot(1)) >= 4) {
				this.drawHoveringText(Lists.newArrayList(new String[] { ChatColor.GREEN + "Verzaubern" }), mouseX,
						mouseY);
				return;
			} else {
				this.drawHoveringText(Lists.newArrayList(new String[] {
						ChatColor.RED + "Es ist nicht genug LapisLazuli", ChatColor.RED + "im Verzauberungsaltar",
						ChatColor.YELLOW + "Es werden mindestens 4 LapisLazuli benötigt" }), mouseX, mouseY);
				return;
			}
		}
		for (OverlayTexToggleButton enchb : ENCHANTMENT_SELECTOR) {
			if (enchb.isMouseOver()) {
				this.drawHoveringText(Lists.asList(getNameOfItemBoundEnchantment(enchb.id - 1), new String[] {}),
						mouseX, mouseY);
				return;
			}
		}
		for (OverlayTexToggleButton lb : LEVEL) {
			if (lb.isMouseOver()) {
				if (container.entityPlayer.experienceLevel >= ((lb.id - 20) * 2)) {
					this.drawHoveringText(Lists.asList(
							ChatColor.GREEN + "Level "
									+ I18n.format("button.gamehelper.enchantmentaltar.level." + (lb.id - 20)),
							new String[] {}), mouseX, mouseY);
				} else {
					this.drawHoveringText(Lists.asList(
							ChatColor.RED + "Level "
									+ I18n.format("button.gamehelper.enchantmentaltar.level." + (lb.id - 20)),
							new String[] {}), mouseX, mouseY);
				}
				return;
			}
			continue;
		}
	}

	public static final ItemStack ENCH_BOOK = new ItemStack(Items.enchanted_book);
	public static final ItemStack ENCH_BOTTLE = new ItemStack(Items.experience_bottle);
	public static final ItemStack CONFIRM_TEX = new ItemStack(TradeItems.OK_ITEM);

	public boolean firstCreate = true;

	@Override
	public void initGui() {
		super.initGui();
		for (int i = 0; i < 10; i++) {
			ENCHANTMENT_SELECTOR[i] = new OverlayTexToggleButton(i + 1,
					6 + 1 + this.guiLeft + 32 + i * 18 + (i != 0 ? i * 1 : 0), this.guiTop + 20,
					I18n.format("button.gamehelper.enchantmentaltar.ench." + i), ENCH_BOOK);
			ENCHANTMENT_SELECTOR[i].setWidth(18);
			ENCHANTMENT_SELECTOR[i].height = 18;
			this.buttonList.add(ENCHANTMENT_SELECTOR[i]);
		}
		for (int i = 0; i < 10; i++) {
			ENCHANTMENT_SELECTOR[i + 10] = new OverlayTexToggleButton(i + 1 + 10,
					6 + 1 + this.guiLeft + 32 + i * 18 + (i != 0 ? i * 1 : 0), 3 + this.guiTop + 20 + 18,
					I18n.format("button.gamehelper.enchantmentaltar.ench." + (i + 10)), ENCH_BOOK);
			ENCHANTMENT_SELECTOR[i + 10].setWidth(18);
			ENCHANTMENT_SELECTOR[i + 10].height = 18;
			this.buttonList.add(ENCHANTMENT_SELECTOR[i + 10]);
		}
		for (int i = 0; i < 10; i++) {
			LEVEL[i] = new OverlayTexToggleButton(i + 1 + 20, 6 + 1 + this.guiLeft + 32 + i * 18 + (i != 0 ? i * 1 : 0),
					6 + 3 + this.guiTop + 20 + 18 + 24,
					I18n.format("button.gamehelper.enchantmentaltar.level." + (i + 1)), ENCH_BOTTLE);
			LEVEL[i].setWidth(18);
			LEVEL[i].height = 18;
			this.buttonList.add(LEVEL[i]);
		}
		// b1 = new GuiButton(1, this.guiLeft + 174, this.guiTop + 54, 16, 16,
		// I18n.format("button.gamehelper.backpack.private"));
		// b2 = new GuiButton(2, this.guiLeft + 174, this.guiTop + 16 + 54, 16,
		// 16,
		// I18n.format("buttom.gamehelper.backpack.public"));
		CONFIRM = new OverlayTexToggleButton(0, 4 + 4 + this.guiLeft + 224, this.guiTop + 50,
				I18n.format("button.gamehelper.enchantmentaltar.confirm"), CONFIRM_TEX);
		CONFIRM.setWidth(18);
		CONFIRM.height = 18;
		CONFIRM.enabled = false;
		this.buttonList.add(CONFIRM);
		setEnabled(false);
		if (!firstCreate) {
			// iscopy = container.tableInventory.getStackInSlot(0).copy();
			if (container.tableInventory.getStackInSlot(0) == null)
				return;
			if (container.tableInventory.getStackInSlot(0).getIsItemStackEqual(iscopy)) {
				;
			} else {
				iscopy = container.tableInventory.getStackInSlot(0);
			}
			setItemBoundEnchantments(GameHelperAPI.ghEnchantAPI().getItemBoundEnchantments(iscopy));
			if (level != 0)
				LEVEL[level - 1].setSelected(true);
			if (sel_ench != null) {
				for (int i = 0; i < itemBoundEnchantments.length; i++) {
					if (sel_ench.equals(itemBoundEnchantments[i])) {
						if (ENCHANTMENT_SELECTOR[i] != null) {
							ENCHANTMENT_SELECTOR[i].setSelected(true);
						}
					}
				}
			}

		}
		firstCreate = false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(ENCHANTMENT_TABLE_GUI_TEXTURE);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		GlStateManager.pushMatrix();
		GlStateManager.matrixMode(5889);
		GlStateManager.pushMatrix();
		GlStateManager.loadIdentity();
		ScaledResolution scaledresolution = new ScaledResolution(this.mc);
		GlStateManager.viewport((scaledresolution.getScaledWidth() - 320) / 2 * scaledresolution.getScaleFactor(),
				(scaledresolution.getScaledHeight() - 240) / 2 * scaledresolution.getScaleFactor(),
				320 * scaledresolution.getScaleFactor(), 240 * scaledresolution.getScaleFactor());
		GlStateManager.translate(-0.34F, 0.23F, 0.0F);
		Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
		float f1 = 1.0F;
		GlStateManager.matrixMode(5888);
		GlStateManager.loadIdentity();
		RenderHelper.enableStandardItemLighting();
		GlStateManager.translate(0.0F, 3.3F, -16.0F);
		GlStateManager.scale(f1, f1, f1);
		float f2 = 5.0F;
		GlStateManager.scale(f2, f2, f2);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		// this.mc.getTextureManager().bindTexture(ENCHANTMENT_TABLE_BOOK_TEXTURE);
		// GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
		// float f3 = this.field_147076_A + (this.field_147080_z -
		// this.field_147076_A) * partialTicks;
		// GlStateManager.translate((1.0F - f3) * 0.2F, (1.0F - f3) * 0.1F,
		// (1.0F - f3) * 0.25F);
		// GlStateManager.rotate(-(1.0F - f3) * 90.0F - 90.0F, 0.0F, 1.0F,
		// 0.0F);
		// GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
		// float f4 = this.field_147069_w + (this.field_147071_v -
		// this.field_147069_w) * partialTicks + 0.25F;
		// float f5 = this.field_147069_w + (this.field_147071_v -
		// this.field_147069_w) * partialTicks + 0.75F;
		// f4 = (f4 - (float)MathHelper.truncateDoubleToInt((double)f4)) * 1.6F
		// - 0.3F;
		// f5 = (f5 - (float)MathHelper.truncateDoubleToInt((double)f5)) * 1.6F
		// - 0.3F;
		//
		// if (f4 < 0.0F)
		// {
		// f4 = 0.0F;
		// }
		//
		// if (f5 < 0.0F)
		// {
		// f5 = 0.0F;
		// }
		//
		// if (f4 > 1.0F)
		// {
		// f4 = 1.0F;
		// }
		//
		// if (f5 > 1.0F)
		// {
		// f5 = 1.0F;
		// }
		//
		// GlStateManager.enableRescaleNormal();
		// MODEL_BOOK.render((Entity)null, 0.0F, f4, f5, f3, 0.0F, 0.0625F);
		// GlStateManager.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.matrixMode(5889);
		GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
		GlStateManager.popMatrix();
		GlStateManager.matrixMode(5888);
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		// GuiCrafting
		// FontRenderer fontRenderer = this.mc.fontRendererObj;
		// fontRenderer.
		// EnchantmentNameParts.getInstance().reseedRandomGenerator((long)this.container.xpSeed);
		// int i1 = this.container.getLapisAmount();
		//
		// for (int j1 = 0; j1 < 3; ++j1)
		// {
		// int k1 = k + 60;
		// int l1 = k1 + 20;
		// byte b0 = 86;
		// String s =
		// EnchantmentNameParts.getInstance().generateNewRandomName();
		// this.zLevel = 0.0F;
		// this.mc.getTextureManager().bindTexture(ENCHANTMENT_TABLE_GUI_TEXTURE);
		// int i2 = this.container.enchantLevels[j1];
		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		//
		// if (i2 == 0)
		// {
		// this.drawTexturedModalRect(k1, l + 14 + 19 * j1, 0, 185, 108, 19);
		// }
		// else
		// {
		// String s1 = "" + i2;
		// FontRenderer fontrenderer = this.mc.standardGalacticFontRenderer;
		// int j2 = 6839882;
		//
		// if ((i1 < j1 + 1 || this.mc.thePlayer.experienceLevel < i2) &&
		// !this.mc.thePlayer.capabilities.isCreativeMode)
		// {
		// this.drawTexturedModalRect(k1, l + 14 + 19 * j1, 0, 185, 108, 19);
		// this.drawTexturedModalRect(k1 + 1, l + 15 + 19 * j1, 16 * j1, 239,
		// 16, 16);
		// fontrenderer.drawSplitString(s, l1, l + 16 + 19 * j1, b0, (j2 &
		// 16711422) >> 1);
		// j2 = 4226832;
		// }
		// else
		// {
		// int k2 = mouseX - (k + 60);
		// int l2 = mouseY - (l + 14 + 19 * j1);
		//
		// if (k2 >= 0 && l2 >= 0 && k2 < 108 && l2 < 19)
		// {
		// this.drawTexturedModalRect(k1, l + 14 + 19 * j1, 0, 204, 108, 19);
		// j2 = 16777088;
		// }
		// else
		// {
		// this.drawTexturedModalRect(k1, l + 14 + 19 * j1, 0, 166, 108, 19);
		// }
		//
		// this.drawTexturedModalRect(k1 + 1, l + 15 + 19 * j1, 16 * j1, 223,
		// 16, 16);
		// fontrenderer.drawSplitString(s, l1, l + 16 + 19 * j1, b0, j2);
		// j2 = 8453920;
		// }
		//
		// fontrenderer = this.mc.fontRendererObj;
		// fontrenderer.drawStringWithShadow(s1, (float)(l1 + 86 -
		// fontrenderer.getStringWidth(s1)), (float)(l + 16 + 19 * j1 + 7), j2);
		// }
		// }
	}

	public static int colorToOneInt(float r, float g, float b, float a) {
		int red = (int) (r * 255);
		red = red << 16;
		// (color >> 16 & 255);
		int blue = (int) (b * 255);
		blue = blue << 8;
		// int blue = (color >> 8 & 255) * 255.0F;
		int green = (int) (g * 255);
		// green = green << 24;
		// int green = (color & 255) * 255.0F;
		int alpha = (int) (a * 255);
		alpha = alpha << 24;
		// int alpha = (color >> 24 & 255) * 255.0F;
		int color = red & green & blue & alpha;
		return color;
	}

	public static String getName(Enchantment ench) {
		if (ench == null) {
			return ChatColor.GREEN + "-";
		}
		return
		// + I18n.format("text.gamehelper.enchantment")
		StatCollector.translateToLocal(ench.getName());
	}

	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(I18n.format("container.enchantmentaltar", new Object[0]), 16, 7, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 16, this.ySize - 96 + 2,
				4210752);

		// this.fontRendererObj.drawString(
		// ChatColor.GREEN + "Enchantment: " + getName(sel_ench) + ", Level: " +
		// (level == 0 ? "-" : level),
		// ((this.xSize / 2 - 32)-16), 96-2, 0);
		String text = ChatColor.GREEN + "Enchantment: " + getName(sel_ench) + ", Level: "
				+ (level == 0 ? "-" : GameHelper.getUtils().toRomainNumber(level));
		// this.drawCenteredString(this.fontRendererObj,
		// text, this.width/2-, 96-2, 0);

		// this.fontRendererObj.drawString(text, 0, 96-2, 0);
		this.fontRendererObj.drawString(text, this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 2,
				96 - 2 - 2, 0);
		// this.fontRendererObj.drawString(
		// ChatColor.GREEN + "Enchantment: " + getName(sel_ench) + ", Level: " +
		// (level == 0 ? "-" : level),
		// (/* (this.guiLeft/2) + */(this.xSize / 2
		// - 32))/* - this.xSize + 16 */,
		// 96, 0);
		// drawToolTipOfButton(mouseX, mouseY);
		// this.fontRendererObj.drawString(I18n, x, y, color)
	}

	ItemStack iscopy = null;
	boolean alldisabled = true;

	public void setEnabled(boolean v) {
		// alldisabled = !v;
		for (OverlayTexToggleButton gb : ENCHANTMENT_SELECTOR) {
			gb.enabled = v;
			gb.setSelected(false);
		}
		for (OverlayTexToggleButton gb : LEVEL) {
			gb.enabled = v;
			gb.setSelected(false);
		}
		CONFIRM.enabled = v;
	}

	public static int getStackSizeOfItem(ItemStack is) {
		if (is == null)
			return 0;
		return is.stackSize;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawToolTipOfButton(mouseX, mouseY);
		if (container.entityPlayer.experienceLevel < 20) {
			CONFIRM.enabled = false;
		} else if (sel_ench == null) {
			CONFIRM.enabled = false;
		} else if (level == 0) {
			CONFIRM.enabled = false;
		} else if (getStackSizeOfItem(container.tableInventory.getStackInSlot(1)) >= 4) {
			CONFIRM.enabled = true;
		} else {
			CONFIRM.enabled = false;
		}
		if (container.tableInventory.getStackInSlot(0) != null) {
			if (iscopy == null) {
				// item is set
				iscopy = container.tableInventory.getStackInSlot(0).copy();
				// setItemBoundEnchantments(ench);
				if ((alldisabled)) {
					setEnabled(true);
					alldisabled = false;
					sel_ench = null;
					level = 0;
					setItemBoundEnchantments(GameHelperAPI.ghEnchantAPI().getItemBoundEnchantments(iscopy));
				}
				return;
			}
			if (container.tableInventory.getStackInSlot(0).getIsItemStackEqual(iscopy)) {
				return;
			} else {
				iscopy = container.tableInventory.getStackInSlot(0).copy();
				setItemBoundEnchantments(GameHelperAPI.ghEnchantAPI().getItemBoundEnchantments(iscopy));
				sel_ench = null;
				level = 0;
				return;
			}
		} else {
			if (!(alldisabled)) {
				resetItemBoundEnchantments();
				setEnabled(false);
				alldisabled = true;
				level = 0;
				ench = "-";
				sel_ench = null;
				iscopy = null;
			}
		}
	}
}
