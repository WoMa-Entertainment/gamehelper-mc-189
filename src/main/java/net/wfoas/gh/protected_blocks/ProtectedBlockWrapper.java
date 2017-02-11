package net.wfoas.gh.protected_blocks;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ProtectedBlockWrapper implements IProtectedBlock {

	NBTTagCompound nbt_prot_data;
	BlockPos posi;
	World w;

	public ProtectedBlockWrapper(NBTTagCompound nbt_prot_data, BlockPos posi, World w) {
		this.nbt_prot_data = nbt_prot_data;
		this.posi = posi;
		this.w = w;
	}

	@Override
	public List<String> getWhitelistedPlayers() {
		return null;
	}

	@Override
	public void addWhiteListedPlayer(UUID uid) {

	}

	@Override
	public UUID getOwner() {
		return null;
	}

	@Override
	public void setOwner(UUID u) {

	}

	@Override
	public boolean isPlayerCapableOfOpeningBlock(EntityPlayer ep) {
		return false;
	}

	@Override
	public boolean isOwner(EntityPlayer ep) {
		return false;
	}

	@Override
	public LockType getLockType() {
		return null;
	}

	@Override
	public void setLockType(LockType l) {

	}

}
