package net.wfoas.gh.protected_blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.network.NetworkHandler;
import net.wfoas.gh.network.packet.PacketPlayInformServerAboutNewPermissions;

public final class ProtectedBlockWrapper implements IProtectedBlock {

	public NBTTagCompound nbt_prot_data;
	public BlockPos posi;
	public World w;

	public ProtectedBlockWrapper(NBTTagCompound nbt_prot_data, BlockPos posi, World w) {
		this.nbt_prot_data = nbt_prot_data;
		this.posi = posi;
		this.w = w;
	}

	@Override
	public List<UUID> getWhitelistedPlayers() {
		List<UUID> stringList = new ArrayList<UUID>();
		if (!nbt_prot_data.hasKey("ProtectedBlockWhitelistedPlayers")) {
			nbt_prot_data.setTag("ProtectedBlockWhitelistedPlayers", new NBTTagList());
			return stringList;
		}
		NBTTagList list = (NBTTagList) nbt_prot_data.getTag("ProtectedBlockWhitelistedPlayers");
		for (int i = 0; i < list.tagCount(); i++) {
			stringList.add(UUID.fromString(list.getStringTagAt(i)));
		}
		return stringList;
	}

	@Override
	public void addWhiteListedPlayer(UUID uid) {
		if (!nbt_prot_data.hasKey("ProtectedBlockWhitelistedPlayers")) {
			nbt_prot_data.setTag("ProtectedBlockWhitelistedPlayers", new NBTTagList());
		}
		if (uid == null)
			return;
		NBTTagList list = (NBTTagList) nbt_prot_data.getTag("ProtectedBlockWhitelistedPlayers");
		list.appendTag(new NBTTagString(uid.toString()));
	}

	@Override
	public UUID getOwner() {
		return UUID.fromString(this.nbt_prot_data.getString("ProtectedBlockOwner"));
	}

	@Override
	public void setOwner(UUID u) {
		// client not implemented
	}

	@Override
	public boolean isPlayerCapableOfOpeningBlock(EntityPlayer ep) {
		if (ep == null)
			return false;
		if (isOwner(ep))
			return true;
		if (getLockType() == LockType.ALL_PLAYERS)
			return true;
		else if (getLockType() == LockType.WHITELISTED_PLAYERS)
			return getWhitelistedPlayers().contains(ep.getUniqueID().toString());
		else if (getLockType() == LockType.ONLY_OWNER)
			return isOwner(ep);
		return false;
	}

	@Override
	public boolean isOwner(EntityPlayer ep) {
		if (ep == null)
			return false;
		return getOwner().equals(ep.getUniqueID());
	}

	@Override
	public LockType getLockType() {
		return LockType.getFromId(nbt_prot_data.getByte("ProtectedBlockLockType"));
	}

	@Override
	public void setLockType(LockType l) {
		if (l == null)
			return;
		nbt_prot_data.setByte("ProtectedBlockLockType", l.getId());
	}

	@Override
	public void removeWhiteListedPlayer(UUID uid) {
		if (!nbt_prot_data.hasKey("ProtectedBlockWhitelistedPlayers")) {
			nbt_prot_data.setTag("ProtectedBlockWhitelistedPlayers", new NBTTagList());
		}
		if (uid == null)
			return;
		NBTTagList list = (NBTTagList) nbt_prot_data.getTag("ProtectedBlockWhitelistedPlayers");
		int remove = -1;
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagString s = (NBTTagString) list.get(i);
			if (s.getString().equalsIgnoreCase(uid.toString())) {
				remove = i;
				break;
			}
		}
		if (remove != -1)
			list.removeTag(remove);
		nbt_prot_data.setTag("ProtectedBlockWhitelistedPlayers", list);
	}

	@Override
	public Packet getCUDescrPacket() {
		return null;
	}

	public void writeBackToServer() {
		System.out.println("Saved: " + nbt_prot_data);
		NetworkHandler.sendToServer(new PacketPlayInformServerAboutNewPermissions(this));
	}
}