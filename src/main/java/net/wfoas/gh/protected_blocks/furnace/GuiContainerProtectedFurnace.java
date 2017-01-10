package net.wfoas.gh.protected_blocks.furnace;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.gui.GuiHandler;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabChangePermission;
import net.wfoas.gh.protected_blocks.tabs.ProtectedBlockTabManipulateBlock;
import tconstruct.client.tabs.AbstractTab;

@SideOnly(Side.CLIENT)
public class GuiContainerProtectedFurnace extends GuiContainer {
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(
			"textures/gui/container/furnace.png");
	private final InventoryPlayer playerInventory;
	private IInventory tileFurnace;
	private int __phys_posx, __phys_posy, __phys_posz;

	public void initGui() {
		super.initGui();
		AbstractTab ab = new ProtectedBlockTabManipulateBlock(0, 0, 0, __phys_posx, __phys_posy, __phys_posz,
				Item.getItemFromBlock((Block) GameHelperCoreModule.SEC_FURNACE), GuiHandler.PROTECTED_FURNACE).main();
		ab.xPosition = (this.guiLeft + (4 - 2) * 28);
		ab.yPosition = (this.guiTop - 28);
		this.buttonList.add(ab);
		// ownership
		AbstractTab at = new ProtectedBlockTabChangePermission(1, 20, 0, __phys_posx, __phys_posy, __phys_posz);
		at.xPosition = (this.guiLeft + (5 - 2) * 28);
		at.yPosition = (this.guiTop - 28);
		this.buttonList.add(at);
	}

	public GuiContainerProtectedFurnace(InventoryPlayer playerInv, IInventory furnaceInv) {
		super(new ContainerProtectedFurnace(playerInv, furnaceInv));
		if (furnaceInv instanceof ProtectedFurnaceTileEntity) {
			ProtectedFurnaceTileEntity p = (ProtectedFurnaceTileEntity) furnaceInv;
			// System.out.println(p.getTileData().getString(""));
			this.__phys_posx = p.getPos().getX();
			this.__phys_posy = p.getPos().getY();
			this.__phys_posz = p.getPos().getZ();
		}
		this.playerInventory = playerInv;
		this.tileFurnace = furnaceInv;
	}

	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.tileFurnace.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8,
				this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// this.zLevel = 0.0F;
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		if (ProtectedFurnaceTileEntity.isBurning(this.tileFurnace)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.tileFurnace.getField(2);
		int j = this.tileFurnace.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}

	private int getBurnLeftScaled(int pixels) {
		int i = this.tileFurnace.getField(1);
		if (i == 0) {
			i = 200;
		}
		return this.tileFurnace.getField(0) * pixels / i;
	}
}