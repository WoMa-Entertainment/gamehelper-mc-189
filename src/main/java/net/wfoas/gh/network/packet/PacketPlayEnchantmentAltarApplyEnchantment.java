package net.wfoas.gh.network.packet;

import java.util.Map;
import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.enchaltar.EnchantmentAltarContainer;

public class PacketPlayEnchantmentAltarApplyEnchantment implements IMessage {
	public PacketPlayEnchantmentAltarApplyEnchantment() {
	}

	Enchantment ench;
	int lvl;
	EntityPlayer ep;
	World w;

	public PacketPlayEnchantmentAltarApplyEnchantment(Enchantment ench, int lvl, EntityPlayer ep) {
		this.ench = ench;
		this.lvl = lvl;
		this.ep = ep;
		this.w = ep.worldObj;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		w = DimensionManager.getWorld(buf.readInt());
		ep = w.getPlayerEntityByUUID(new UUID(buf.readLong(), buf.readLong()));
		ench = Enchantment.getEnchantmentById(buf.readInt());
		lvl = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(w.provider.getDimensionId());
		buf.writeLong(ep.getUniqueID().getMostSignificantBits());
		buf.writeLong(ep.getUniqueID().getLeastSignificantBits());
		buf.writeInt(ench.effectId);
		buf.writeInt(lvl);
	}

	public static class PacketEnchAltarHandler
			implements IMessageHandler<PacketPlayEnchantmentAltarApplyEnchantment, IMessage> {

		@Override
		public IMessage onMessage(final PacketPlayEnchantmentAltarApplyEnchantment message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj; // or
																										// Minecraft.getMinecraft()
																										// on
																										// the
																										// client
			mainThread.addScheduledTask(new Runnable() {
				@Override
				public void run() {
					EnchantmentAltarContainer enchalcon = (message.ep.openContainer instanceof EnchantmentAltarContainer)
							? (EnchantmentAltarContainer) message.ep.openContainer : null;
					if (enchalcon == null) {
						throw new IllegalStateException("There is no EnchantmentAltar opened at the client");
					}
					if (GameHelper.getUtils().hasEnchantment(enchalcon.tableInventory.getStackInSlot(0),
							message.ench)) {
					}
					Map<Integer, Integer> enchmap = EnchantmentHelper
							.getEnchantments(enchalcon.tableInventory.getStackInSlot(0));
					GameHelper.getUtils().addEnchantment(enchmap, message.ench, message.lvl);
					// enchalcon.tableInventory.getStackInSlot(0).addEnchantment(message.ench,
					// message.lvl);
					EnchantmentHelper.setEnchantments(enchmap, enchalcon.tableInventory.getStackInSlot(0));
					message.ep.removeExperienceLevel(message.lvl * 2);
					if (enchalcon.tableInventory.getStackInSlot(1).stackSize == 4) {
						enchalcon.tableInventory.setInventorySlotContents(1, null);
					} else {
						enchalcon.tableInventory
								.getStackInSlot(1).stackSize = enchalcon.tableInventory.getStackInSlot(1).stackSize - 4;
					}
					message.ep.worldObj.playSoundAtEntity(message.ep, "portal.travel", 1, 1);
					// NBTTagCompound nbttc =
					// message.ep.getHeldItem().getTagCompound();
					// nbttc.setBoolean("private", message.Private);
					// System.out.println("Backpack of " + message.ep.getName()
					// + " is now " + (message.Private ? "private" : "public"));
					// message.ep.addChatMessage(new
					// ChatComponentTranslation(message.Private ?
					// "message.gamehelper.backpack.private" :
					// "message.gamehelper.backpack.public"));
				}
			});
			return null; // no response in this case
		}

	}
}
