package net.wfoas.gh.ranks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import de.winston.network.playerranks.PlayerRank;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.DimensionManager;

public class RankRetrieverHDD implements IRankRetriever {

	Map<UUID, PlayerRank> playerRankMap = new HashMap<UUID, PlayerRank>();

	public RankRetrieverHDD() {
		load();
	}

	private void load() {
		playerRankMap = new HashMap<UUID, PlayerRank>();
		try {
			load0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void load0() throws IOException {
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "player_ranks.gh");
		if (!f.exists()) {
			f.createNewFile();
			return;
		}
		FileInputStream fis = new FileInputStream(f);
		NBTTagCompound nbttc = CompressedStreamTools.readCompressed(fis);
		NBTTagList nbttl = (NBTTagList) nbttc.getTag("player_ranks");
		for (int i = 0; i < nbttl.tagCount(); i++) {
			NBTTagCompound wnbt = nbttl.getCompoundTagAt(i);
			PlayerRank pr = PlayerRank.resolveFromInt(wnbt.getInteger("rank"));
			UUID pu = new UUID(wnbt.getLong("player_msb"), wnbt.getLong("player_lsb"));
			playerRankMap.put(pu, pr);
		}
	}

	@Override
	public PlayerRank getRank(EntityPlayerMP player) {
		if (playerRankMap.containsKey(player.getUniqueID()))
			return playerRankMap.get(player.getUniqueID());
		else {
			playerRankMap.put(player.getUniqueID(), PlayerRank.NORMAL);
			return PlayerRank.NORMAL;
		}
	}

	@Override
	public void setRank(EntityPlayerMP player, PlayerRank rank) {
		if (playerRankMap.containsKey(player.getUniqueID())) {
			playerRankMap.remove(player.getUniqueID());
		}
		playerRankMap.put(player.getUniqueID(), rank);
		player.refreshDisplayName();
	}

	@Override
	public void save() {
		try {
			save0();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void save0() throws IOException {
		NBTTagCompound data = new NBTTagCompound();
		NBTTagList nbttl = new NBTTagList();
		for (Entry<UUID, PlayerRank> entry : playerRankMap.entrySet()) {
			NBTTagCompound wnbt = new NBTTagCompound();
			wnbt.setInteger("rank", entry.getValue().getSQLValue());
			wnbt.setLong("player_msb", entry.getKey().getMostSignificantBits());
			wnbt.setLong("player_lsb", entry.getKey().getLeastSignificantBits());
			nbttl.appendTag(wnbt);
		}
		File f = new File(DimensionManager.getWorld(0).getSaveHandler().getWorldDirectory(), "player_ranks.gh");
		data.setTag("player_ranks", nbttl);
		FileOutputStream fos = new FileOutputStream(f);
		CompressedStreamTools.writeCompressed(data, fos);
		fos.close();
	}

}
