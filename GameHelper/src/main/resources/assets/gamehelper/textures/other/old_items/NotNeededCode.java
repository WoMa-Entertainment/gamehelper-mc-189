
/**
 * This renders the player model in standard inventory position (in later
 * versions of Minecraft / Forge, you can simply call
 * GuiInventory.drawEntityOnScreen directly instead of copying this code)
 */
// public static void drawPlayerModel(int x, int y, int scale, float yaw, float
// pitch, EntityLivingBase entity) {
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
// }

// int i1;
// drawPlayerModel(k + 51, l + 75, 30, (float)(k + 51) - this.xSize_lo,
// (float)(l + 75 - 50) - this.ySize_lo, this.mc.thePlayer);

/**
 * Draw the background layer for the GuiContainer (everything behind the items)
 */
// this.fontRendererObj.drawString(s, this.xSize / 2 -
// this.fontRendererObj.getStringWidth(s) / 2, 0, 4210752);
// this.fontRendererObj.drawString(I18n.format("container.inventory"),
// 26, this.ySize - 96 + 4, 4210752);

/**
 * Draw the foreground layer for the GuiContainer (everything in front of the
 * items)
 */
// this.fontRendererObj.drawString(s, this.xSize / 2 -
// this.fontRendererObj.getStringWidth(s) / 2, 0, 4210752);
// this.fontRendererObj.drawString(I18n.format("container.inventory"),
// 26, this.ySize - 96 + 4, 4210752);

/**
 * Draw the foreground layer for the GuiContainer (everything in front of the
 * items)
 */

// public Object getServerGuiElement(int ID, EntityPlayer player, World
// world, int x, int y, int z) {
// Object obj = getServerGuiElement0(ID, player, world, x, y, z);
// System.out.println(obj);
// return obj;
// }
// IThreadListener mainThread = (WorldServer)
// ctx.getServerHandler().playerEntity.worldObj;

// backpack.getTagCompound().removeTag("items");
// for (int index = 0; index < 5; index++) {
// if (minersbelt[index] != null)
// ((NBTTagList) backpack.getTagCompound().getTag("items"))
// .appendTag(minersbelt[index].writeToNBT(new NBTTagCompound()));
// }

// @SideOnly(Side.CLIENT)
// public int getLapisAmount() {
// ItemStack itemstack = this.tableInventory.getStackInSlot(1);
// return itemstack == null ? 0 : itemstack.stackSize;
// }

/**
 * Draws the screen and all the components in it.
 */

/** The inventory to render on screen */
// if (tool.equalsIgnoreCase("pickaxe")) {
// if (hde.harvester.getHeldItem().getItem() instanceof
// ItemPickaxe)
// ;
// else
// return;
// } else if (tool.equalsIgnoreCase("axe")) {
// if (hde.harvester.getHeldItem().getItem() instanceof
// ItemAxe)
// ;
// else
// return;
// } else if (tool.equalsIgnoreCase("shovel")) {
// if (hde.harvester.getHeldItem().getItem() instanceof
// ItemSpade)
// ;
// else
// return;
// }
/** MainClass: GameHelper */
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("ADMIN"),
// PlayerRank.ADMIN);
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("OWNER"),
// PlayerRank.OWNER);
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("PREMIUM"),
// PlayerRank.PREMIUM);
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("YT"),
// PlayerRank.YT);
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("MODERATOR"),
// PlayerRank.MODERATOR);
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("ADMIN_DEV"),
// PlayerRank.ADMIN_DEV);
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("OWNER_DEV"),
// PlayerRank.OWNER_DEV);
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("DEV"),
// PlayerRank.DEV);
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("SUPPORTER"),
// PlayerRank.SUPPORTER);
// registerTeam(event.getServer().getEntityWorld().getScoreboard().createTeam("NORMAL"),
// PlayerRank.NORMAL);