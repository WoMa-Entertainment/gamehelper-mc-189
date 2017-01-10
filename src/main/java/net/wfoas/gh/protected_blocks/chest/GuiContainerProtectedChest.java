package net.wfoas.gh.protected_blocks.chest;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.protected_blocks.furnace.ProtectedFurnaceTileEntity;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabChangePermission;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabManipulateBlock;
import tconstruct.client.tabs.AbstractTab;

@SideOnly(Side.CLIENT)
public class GuiContainerProtectedChest extends GuiContainer {
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(
			"textures/gui/container/generic_54.png");
	private IInventory upperChestInventory;
	private IInventory lowerChestInventory;
	private int inventoryRows;

	public GuiContainerProtectedChest(IInventory upperInv, IInventory lowerInv) {
		super(new ContainerProtectedChest(upperInv, lowerInv, Minecraft.getMinecraft().thePlayer));
		this.upperChestInventory = upperInv;
		this.lowerChestInventory = lowerInv;
		if (lowerInv instanceof ProtectedChestTileEntity) {
			ProtectedChestTileEntity p = (ProtectedChestTileEntity) lowerInv;
			this.__phys_posx = p.getPos().getX();
			this.__phys_posy = p.getPos().getY();
			this.__phys_posz = p.getPos().getZ();
		}
		this.allowUserInput = false;
		int i = 222;
		int j = i - 108;
		this.inventoryRows = lowerInv.getSizeInventory() / 9;
		this.ySize = j + this.inventoryRows * 18;
	}

	private int __phys_posx, __phys_posy, __phys_posz;

	@Override
	public void initGui() {
		super.initGui();
		AbstractTab ab = new ProtectedBlockTabManipulateBlock(0, 0, 0, __phys_posx, __phys_posy, __phys_posz,
				Item.getItemFromBlock((Block) GameHelperCoreModule.SEC_CHEST), GuiHandler.PROTECTED_CHEST).main();
		ab.xPosition = (this.guiLeft + (4 - 2) * 28);
		ab.yPosition = (this.guiTop - 28);
		this.buttonList.add(ab);
		// ownership
		AbstractTab at = new ProtectedBlockTabChangePermission(1, 20, 0, __phys_posx, __phys_posy, __phys_posz);
		at.xPosition = (this.guiLeft + (5 - 2) * 28);
		at.yPosition = (this.guiTop - 28);
		this.buttonList.add(at);
	}

	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8,
				this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
		// GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
}