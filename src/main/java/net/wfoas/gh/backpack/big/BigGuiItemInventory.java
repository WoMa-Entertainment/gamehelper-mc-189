package net.wfoas.gh.backpack.big;

import java.util.UUID;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentSelector;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayBackpackInventoryPrivacy;

public class BigGuiItemInventory extends GuiContainer {
	private float xSize_lo;
	private float ySize_lo;

	private static final ResourceLocation iconLocation = new ResourceLocation("gamehelper",
			"gui/container/big_backpack.png");
	private final BigInventoryItem inventory;
	private final BigContainerItem containerItem;

	public BigGuiItemInventory(BigContainerItem containerItem) {
		super(containerItem);
		this.containerItem = containerItem;
		this.xSize = 198;
		this.ySize = 222;
		this.inventory = containerItem.inventory;
	}

	GuiButton b1;
	GuiButton b2;

	@Override
	public void initGui() {
		super.initGui();
		b1 = new GuiButton(1, this.guiLeft + 174, this.guiTop + 54, 16, 16, I18n.format("button.gamehelper.backpack.private"));
		b2 = new GuiButton(2, this.guiLeft + 174, this.guiTop + 16 + 54, 16, 16, I18n.format("button.gamehelper.backpack.public"));
		b1.enabled = isOwner(containerItem.player, inventory.itemStack.getTagCompound());
		b2.enabled = isOwner(containerItem.player, inventory.itemStack.getTagCompound());
		this.buttonList.add(b1);
		this.buttonList.add(b2);
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		if (b.id == 1) {
			if (isOwner(containerItem.player, inventory.itemStack.getTagCompound())) {
				NetworkHandler.sendToServer(new PacketPlayBackpackInventoryPrivacy(this.mc.thePlayer, true));
				inventory.ownerPrivate = containerItem.player.getHeldItem().getTagCompound().getBoolean("private");
			} else {
				containerItem.player.addChatMessage(new ChatComponentSelector(ChatColor.RED + I18n.format("message.gamehelper.nopermission.changebpsettings")));
			}
		} else if (b.id == 2) {
			if (isOwner(containerItem.player, inventory.itemStack.getTagCompound())) {
				NetworkHandler.sendToServer(new PacketPlayBackpackInventoryPrivacy(this.mc.thePlayer, false));
				inventory.ownerPrivate = containerItem.player.getHeldItem().getTagCompound().getBoolean("private");
			} else {
				containerItem.player.addChatMessage(new ChatComponentSelector(ChatColor.RED + I18n.format("message.gamehelper.nopermission.changebpsettings")));
			}
		}
	}

	public static boolean isOwner(EntityPlayer ep, NBTTagCompound nbttc) {
		if (nbttc == null)
			return false;
		if (ep == null)
			return false;
		UUID uid = new UUID(nbttc.getLong("OwnerMSB"), nbttc.getLong("OwnerLSB"));
		return ep.getUniqueID().equals(uid);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = par1;
		this.ySize_lo = par2;
		if (b1.isMouseOver()) {
			if (isOwner(containerItem.player, inventory.itemStack.getTagCompound())) {
				this.drawHoveringText(
						Lists.newArrayList(new String[] { I18n.format("gamehelper.backpack.setprivate") }), par1,
						par2);
			} else {
				this.drawHoveringText(Lists.newArrayList(new String[] {
						ChatColor.DARK_RED + I18n.format("message.gamehelper.nopermission.changebpsettings") }), par1,
						par2);
			}
		}
		if (b2.isMouseOver()) {
			if (isOwner(containerItem.player, inventory.itemStack.getTagCompound())) {
				this.drawHoveringText(
						Lists.newArrayList(new String[] { I18n.format("gamehelper.backpack.setpublic") }),
						par1, par2);
			} else {
				this.drawHoveringText(Lists.newArrayList(new String[] {
						ChatColor.DARK_RED + I18n.format("message.gamehelper.nopermission.changebpsettings") }), par1,
						par2);
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = this.inventory.getName();
		this.fontRendererObj.drawString(s, 6, 6, 4210752);
		this.fontRendererObj.drawString((containerItem.player.getHeldItem().getTagCompound().getBoolean("private") ? ChatColor.RED : ChatColor.GREEN) + inventory.ownerName, super.xSize - fontRendererObj.getStringWidth(inventory.ownerName) - 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 16, this.ySize - 96 + 4, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(iconLocation);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
