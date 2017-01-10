package net.wfoas.gh.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(value = Side.CLIENT)
public class ToggleButton extends GuiButton implements TwoStepDrawable{

//	private String location, modid;

	public ToggleButton(int buttonId, int x, int y, String text) {
		super(buttonId, x, y, text);
		this.width = 200;
		this.height = 20;
		this.enabled = true;
		this.visible = true;
		this.id = buttonId;
		this.xPosition = x;
		this.yPosition = y;
//		this.width = widthIn;
//		this.height = heightIn;
		this.displayString = "";
//		this.location = location;
//		this.modid = modid;
//		BUTTON_TEX = new ResourceLocation(modid, location);
	}
	
	public boolean selected = false;
	
	public void setSelected(boolean t){
		selected = t;
	}
	
	public boolean isSelected(){
		return selected;
	}

//	public final ResourceLocation BUTTON_TEX;
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.hovered);
            if(isSelected()){
            	k = 2;
            }
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int l = 14737632;

            if (packedFGColour != 0)
            {
                l = packedFGColour;
            }
            else if (!this.enabled)
            {
                l = 10526880;
            }
            else if (this.hovered)
            {
                l = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
            drawStepTwo(mc, mouseX, mouseY);
        }
    }

	@Override
	public void drawStepTwo(Minecraft mc, int mouseX, int mouseY) {
		
	}
}