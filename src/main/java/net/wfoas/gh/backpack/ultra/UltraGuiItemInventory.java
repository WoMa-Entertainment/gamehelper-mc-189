package net.wfoas.gh.backpack.ultra;

import java.util.UUID;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentSelector;
import net.minecraft.util.ResourceLocation;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayBackpackInventoryPrivacy;

public class UltraGuiItemInventory extends GuiContainer {
	/**
	 * x and y size of the inventory window in pixels. Defined as float, passed
	 * as int These are used for drawing the player model.
	 */
	private float xSize_lo;
	private float ySize_lo;

	/**
	 * ResourceLocation takes 2 parameters: ModId, path to texture at the
	 * location: "src/minecraft/assets/modid/"
	 * 
	 * I have provided a sample texture file that works with this tutorial.
	 * Download it from Forge_Tutorials/textures/gui/
	 */
	private static final ResourceLocation iconLocation = new ResourceLocation("gamehelper",
			"gui/container/ultra_big_backpack.png");

	/** The inventory to render on screen */
	private final UltraInventoryItem inventory;
	private final UltraContainerItem containerItem;

	public UltraGuiItemInventory(UltraContainerItem containerItem) {
		super(containerItem);
		this.containerItem = containerItem;
		this.xSize = 252;
		this.ySize = 240;
		this.inventory = containerItem.inventory;
	}

	GuiButton b1;
	GuiButton b2;

	@Override
	public void initGui() {
		super.initGui();
		b1 = new GuiButton(1, this.guiLeft + 174 + 8 + 48, this.guiTop + 8 + 54, 16, 16,
				I18n.format("button.gamehelper.backpack.private"));
		b2 = new GuiButton(2, this.guiLeft + 174 + 8 + 48, this.guiTop + 8 + 16 + 54, 16, 16,
				I18n.format("button.gamehelper.backpack.public"));
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
			} else {
				containerItem.player.addChatMessage(new ChatComponentSelector(
						ChatColor.RED + I18n.format("message.gamehelper.nopermission.changebpsettings")));
			}
		} else if (b.id == 2) {
			if (isOwner(containerItem.player, inventory.itemStack.getTagCompound())) {
				NetworkHandler.sendToServer(new PacketPlayBackpackInventoryPrivacy(this.mc.thePlayer, false));
			} else {
				containerItem.player.addChatMessage(new ChatComponentSelector(
						ChatColor.RED + I18n.format("message.gamehelper.nopermission.changebpsettings")));
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

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		// Minecraft.getMinecraft().standardGalacticFontRenderer.drawString("GG
		// ANDERSON", par1, par2, 4210752);
		this.xSize_lo = par1;
		this.ySize_lo = par2;
		if (b1.isMouseOver()) {
			if (isOwner(containerItem.player, inventory.itemStack.getTagCompound())) {
				this.drawHoveringText(
						Lists.newArrayList(new String[] { I18n.format("gamehelper.backpack.setprivate") }), par1,
						par2);
			} else {
				this.drawHoveringText(
						Lists.newArrayList(new String[] {
								ChatColor.DARK_RED + I18n.format("message.gamehelper.nopermission.changebpsettings") }),
						par1, par2);
			}
		}
		if (b2.isMouseOver()) {
			if (isOwner(containerItem.player, inventory.itemStack.getTagCompound())) {
				this.drawHoveringText(
						Lists.newArrayList(new String[] {I18n.format("gamehelper.backpack.setpublic")}),
						par1, par2);
			} else {
				this.drawHoveringText(
						Lists.newArrayList(new String[] {
								ChatColor.DARK_RED + I18n.format("message.gamehelper.nopermission.changebpsettings") }),
						par1, par2);
			}
		}
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = this.inventory.getName();
		// this.fontRendererObj.drawString(s, this.xSize / 2 -
		// this.fontRendererObj.getStringWidth(s) / 2, 0, 4210752);
		// this.fontRendererObj.drawString(I18n.format("container.inventory"),
		// 26, this.ySize - 96 + 4, 4210752);
		this.fontRendererObj.drawString(s, 6, 6, 4210752);
		this.fontRendererObj.drawString(
				((containerItem.player.getHeldItem().getTagCompound().getBoolean("private")) ? ChatColor.RED
						: ChatColor.GREEN) + inventory.ownerName,
				super.xSize - fontRendererObj.getStringWidth(inventory.ownerName) - 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 16 + 20, this.ySize - 96 + 4, 4210752);

	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(iconLocation);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		// int i1;
		// drawPlayerModel(k + 51, l + 75, 30, (float)(k + 51) - this.xSize_lo,
		// (float)(l + 75 - 50) - this.ySize_lo, this.mc.thePlayer);
	}

	/**
	 * This renders the player model in standard inventory position (in later
	 * versions of Minecraft / Forge, you can simply call
	 * GuiInventory.drawEntityOnScreen directly instead of copying this code)
	 */
	public static void drawPlayerModel(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity) {
		// GuiInventory.drawEntityOnScreen(x, y, scale, yaw, pitch, entity);
		// GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		// GL11.glPushMatrix();
		// GL11.glTranslatef(x, y, 50.0F);
		// GL11.glScalef(-scale, scale, scale);
		// GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		// float f2 = entity.renderYawOffset;
		// float f3 = entity.rotationYaw;
		// float f4 = entity.rotationPitch;
		// float f5 = entity.prevRotationYawHead;
		// float f6 = entity.rotationYawHead;
		// GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		// RenderHelper.enableStandardItemLighting();
		// GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		// GL11.glRotatef(-((float) Math.atan(pitch / 40.0F)) * 20.0F, 1.0F,
		// 0.0F, 0.0F);
		// entity.renderYawOffset = (float) Math.atan(yaw / 40.0F) * 20.0F;
		// entity.rotationYaw = (float) Math.atan(yaw / 40.0F) * 40.0F;
		// entity.rotationPitch = -((float) Math.atan(pitch / 40.0F)) * 20.0F;
		// entity.rotationYawHead = entity.rotationYaw;
		// entity.prevRotationYawHead = entity.rotationYaw;
		// GL11.glTranslatef(0.0F, (float)entity.getYOffset(), 0.0F);
		// RenderManager.instance.playerViewY = 180.0F;
		// RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D,
		// 0.0D, 0.0F, 1.0F);
		// entity.renderYawOffset = f2;
		// entity.rotationYaw = f3;
		// entity.rotationPitch = f4;
		// entity.prevRotationYawHead = f5;
		// entity.rotationYawHead = f6;
		// GL11.glPopMatrix();
		// RenderHelper.disableStandardItemLighting();
		// GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		// OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		// GL11.glDisable(GL11.GL_TEXTURE_2D);
		// OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
}
