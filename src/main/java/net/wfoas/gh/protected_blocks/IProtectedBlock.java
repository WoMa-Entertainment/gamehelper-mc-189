package net.wfoas.gh.protected_blocks;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;

public interface IProtectedBlock {
	List<UUID> getWhitelistedPlayers();

	void addWhiteListedPlayer(UUID uid);

	void removeWhiteListedPlayer(UUID uid);

	UUID getOwner();

	void setOwner(UUID u);

	boolean isPlayerCapableOfOpeningBlock(EntityPlayer ep);

	boolean isOwner(EntityPlayer ep);

	LockType getLockType();

	void setLockType(LockType l);

	Packet getCUDescrPacket();
}
