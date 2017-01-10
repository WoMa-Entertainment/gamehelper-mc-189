package net.wfoas.gh.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.dropsapi.pdr.ChatColor;
import net.wfoas.gh.gui.GuiHandler;

public class BackpackItem extends GameHelperModItem {
	public BackpackItem() {
		super("backpack");
		setMaxStackSize(1);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			// GameHelper.broadcastS("MSB=" +
			// player.getUniqueID().getMostSignificantBits() + ";LSB="
			// + player.getUniqueID().getLeastSignificantBits());
			if (!player.isSneaking()) {
				if (itemstack.hasTagCompound()) {
					if (itemstack.getTagCompound().getLong("OwnerMSB") == 0) {
						itemstack.getTagCompound().setLong("OwnerMSB", player.getUniqueID().getMostSignificantBits());
						itemstack.getTagCompound().setLong("OwnerLSB", player.getUniqueID().getLeastSignificantBits());
						itemstack.getTagCompound().setString("OwnerName", player.getName());
					}
					if (!itemstack.getTagCompound().getBoolean("private"))
						player.openGui(GameHelper.instance, GuiHandler.BACKPACK_GUI, world, 0, 0, 0);
					else {
						long msb = itemstack.getTagCompound().getLong("OwnerMSB");
						long lsb = itemstack.getTagCompound().getLong("OwnerLSB");
						UUID ownerUuid = new UUID(msb, lsb);
						if (player.getUniqueID().equals(ownerUuid)) {
							if (!itemstack.getTagCompound().getString("OwnerName").equals(player.getName())) {
								itemstack.getTagCompound().setString("OwnerName", player.getName());
							}
							player.openGui(GameHelper.instance, GuiHandler.BACKPACK_GUI, world, 0, 0, 0);
						} else {
							player.addChatMessage(
									new ChatComponentTranslation("message.gamehelper.nopermission.backpack"));
						}
					}
				} else {
					System.out.println("No Owner: Create One!");
					NBTTagCompound nbttc = new NBTTagCompound();
					nbttc.setLong("OwnerMSB", player.getUniqueID().getMostSignificantBits());
					nbttc.setLong("OwnerLSB", player.getUniqueID().getLeastSignificantBits());
					nbttc.setString("OwnerName", player.getName());
					player.getHeldItem().setTagCompound(nbttc);
					player.openGui(GameHelper.instance, GuiHandler.BACKPACK_GUI, world, 0, 0, 0);
				}
			}
		}

		return itemstack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		if (stack.hasTagCompound()) {
			boolean ownerprivate = stack.getTagCompound().getBoolean("private");
			String owner = stack.getTagCompound().getString("OwnerName");
			tooltip.add(ChatColor.GOLD + I18n.format("gamehelper.backpack.owner") + (owner.equals("") ? "-" : owner));
			tooltip.add(ownerprivate ? I18n.format("button.gamehelper.backpack.private")
					: I18n.format("gamehelper.backpack.public_long"));
			if (stack.getTagCompound().getBoolean("private")) {
				long msb = stack.getTagCompound().getLong("OwnerMSB");
				long lsb = stack.getTagCompound().getLong("OwnerLSB");
				UUID ownerUuid = new UUID(msb, lsb);
				if (playerIn.getUniqueID().equals(ownerUuid)) {
					if (!stack.getTagCompound().getString("OwnerName").equals(playerIn.getName())) {
						stack.getTagCompound().setString("OwnerName", playerIn.getName());
					}
					tooltip.add(I18n.format("message.gamehelper.canopen.backpack"));
				} else {
					tooltip.add(I18n.format("message.gamehelper.nopermission.backpack"));
				}
			} else {
				tooltip.add(I18n.format("message.gamehelper.canopen.backpack"));
			}
			if (advanced) {
				tooltip.add(ChatColor.AQUA + I18n.format("gamehelper.backpack.uuid")
						+ stack.getTagCompound().getString("BPuniqueID"));
			}
		} else {
			tooltip.add(ChatColor.GOLD + I18n.format("gamehelper.backpack.no_owner"));
		}
	}
}
