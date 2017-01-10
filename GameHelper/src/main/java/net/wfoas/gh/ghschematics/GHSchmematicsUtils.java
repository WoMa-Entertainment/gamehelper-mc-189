package net.wfoas.gh.ghschematics;

import java.io.IOException;
import java.io.OutputStream;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameData;

public class GHSchmematicsUtils {
	public static void writeToStream(OutputStream os, EntityPlayerMP playerMp, int sx, int lx, int sy, int ly, int sz,
			int lz) throws IOException {
		NBTTagCompound nbttc = new NBTTagCompound();
		nbttc.setInteger("x", 1 + (lx - sx));
		nbttc.setInteger("y", 1 + (ly - sy));
		nbttc.setInteger("z", 1 + (lz - sz));
		int[][][] bytearray = new int[1 + (lx - sx)][1 + (ly - sy)][1 + (lz - sz)];
		int[][][] metaarray = new int[1 + (lx - sx)][1 + (ly - sy)][1 + (lz - sz)];
		int[][][] nonsolid_array = new int[1 + (lx - sx)][1 + (ly - sy)][1 + (lz - sz)];
		int[][][] nonsolid_metaarray = new int[1 + (lx - sx)][1 + (ly - sy)][1 + (lz - sz)];
		NBTTagCompound id_name_map = new NBTTagCompound();
		NBTTagList tileEntities = new NBTTagList();
		NBTTagCompound entities = new NBTTagCompound();
		for (int x = sx; x <= lx; x++) {
			for (int y = sy; y <= ly; y++) {
				for (int z = sz; z <= lz; z++) {
					if (playerMp.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock().isBlockNormalCube()) {
						int block = Block
								.getIdFromBlock(playerMp.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock());
						if (id_name_map.getString("id_" + block).equalsIgnoreCase("")) {
							ResourceLocation rl = GameData.getBlockRegistry().getNameForObject(
									playerMp.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock());
							id_name_map.setString("id_" + block, rl.getResourceDomain() + ":" + rl.getResourcePath());
						}
						bytearray[x - sx][y - sy][z - sz] = block;
						metaarray[x - sx][y - sy][z - sz] = playerMp.worldObj.getBlockState(new BlockPos(x, y, z))
								.getBlock().getMetaFromState(playerMp.worldObj.getBlockState(new BlockPos(x, y, z)));
						// if (playerMp.worldObj.getTileEntity(new
						// BlockPos(x, y, z)) != null) {
						//
						// }
						if (playerMp.worldObj.getTileEntity(new BlockPos(x, y, z)) != null) {
							// Kill the old TileEntity
							// playerMp.worldObj.removeTileEntity(new
							// BlockPos(x, y, z));
							NBTTagCompound nativeTag = new NBTTagCompound();
							playerMp.worldObj.getTileEntity(new BlockPos(x, y, z)).writeToNBT(nativeTag);
							nativeTag.removeTag("x");
							nativeTag.removeTag("y");
							nativeTag.removeTag("z");
							nativeTag.setInteger("_relative_x", x - sx);
							nativeTag.setInteger("_relative_y", y - sy);
							nativeTag.setInteger("_relative_z", z - sz);
							// playerMp.worldObj.getTileEntity(new
							// BlockPos(x, y, z)).createAndLoadEntity(nbt)
							// nativeTag.setString("id", block.getNbtId());
							// TileEntityUtils.setTileEntity(world,
							// position, nativeTag);
						}
					} else {
						bytearray[x - sx][y - sy][z - sz] = 0;
						metaarray[x - sx][y - sy][z - sz] = 0;
					}
				}
			}
		}
		// unsolid blocks
		boolean unsolid = false;
		for (int x = sx; x <= lx; x++) {
			for (int y = sy; y <= ly; y++) {
				for (int z = sz; z <= lz; z++) {
					if (!playerMp.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock().isBlockNormalCube()) {
						unsolid = true;
						int block = Block
								.getIdFromBlock(playerMp.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock());
						if (id_name_map.getString("id_" + block).equalsIgnoreCase("")) {
							ResourceLocation rl = GameData.getBlockRegistry().getNameForObject(
									playerMp.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock());
							id_name_map.setString("id_" + block, rl.getResourceDomain() + ":" + rl.getResourcePath());
						}
						nonsolid_array[x - sx][y - sy][z - sz] = block;
						nonsolid_metaarray[x - sx][y - sy][z - sz] = playerMp.worldObj
								.getBlockState(new BlockPos(x, y, z)).getBlock()
								.getMetaFromState(playerMp.worldObj.getBlockState(new BlockPos(x, y, z)));
						// if (playerMp.worldObj.getTileEntity(new
						// BlockPos(x, y, z)) != null) {
						//
						// }
						if (playerMp.worldObj.getTileEntity(new BlockPos(x, y, z)) != null) {
							// Kill the old TileEntity
							// playerMp.worldObj.removeTileEntity(new
							// BlockPos(x, y, z));
							NBTTagCompound nativeTag = new NBTTagCompound();
							playerMp.worldObj.getTileEntity(new BlockPos(x, y, z)).writeToNBT(nativeTag);
							nativeTag.removeTag("x");
							nativeTag.removeTag("y");
							nativeTag.removeTag("z");
							nativeTag.setInteger("_relative_x", x - sx);
							nativeTag.setInteger("_relative_y", y - sy);
							nativeTag.setInteger("_relative_z", z - sz);
							tileEntities.appendTag(nativeTag);
							// playerMp.worldObj.getTileEntity(new
							// BlockPos(x, y, z)).createAndLoadEntity(nbt)
							// nativeTag.setString("id", block.getNbtId());
							// TileEntityUtils.setTileEntity(world,
							// position, nativeTag);
						}
					} else {
						nonsolid_array[x - sx][y - sy][z - sz] = 0;
						nonsolid_metaarray[x - sx][y - sy][z - sz] = 0;
					}
				}
			}
		}
		NBTTagCompound data = new NBTTagCompound();
		NBTTagCompound meta = new NBTTagCompound();
		NBTTagCompound nsdata = new NBTTagCompound();
		NBTTagCompound nsmeta = new NBTTagCompound();
		for (int x = 0; x < bytearray.length; x++) {
			int[][] _arr = bytearray[x];
			int[][] _meta = metaarray[x];
			int[][] _nsdata = null, _nsmeta = null;
			if (unsolid) {
				_nsdata = nonsolid_array[x];
				_nsmeta = nonsolid_metaarray[x];
			}
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			NBTTagCompound nbttagmeta = new NBTTagCompound();
			NBTTagCompound nbttagnsdata = null, nbttagnsmeta = null;
			if (unsolid) {
				nbttagnsdata = new NBTTagCompound();
				nbttagnsmeta = new NBTTagCompound();
			}
			for (int y = 0; y < _arr.length; y++) {
				System.out.println(_arr.length + " | " + bytearray.length);
				int[] _zarr = _arr[y];
				NBTTagIntArray iar = new NBTTagIntArray(_zarr);
				nbttagcompound.setTag("_" + y, iar);

				int[] _zmeta = _meta[y];
				NBTTagIntArray iar2 = new NBTTagIntArray(_zmeta);
				nbttagmeta.setTag("_" + y, iar2);
				if (unsolid) {
					int[] _znsdata = _nsdata[y];
					NBTTagIntArray iar3 = new NBTTagIntArray(_znsdata);
					nbttagnsdata.setTag("_" + y, iar3);

					int[] _znsmeta = _nsmeta[y];
					NBTTagIntArray iar4 = new NBTTagIntArray(_znsmeta);
					nbttagnsmeta.setTag("_" + y, iar4);
				}
			}
			data.setTag("_" + x, nbttagcompound);
			meta.setTag("_" + x, nbttagmeta);
			if (unsolid) {
				nsdata.setTag("_" + x, nbttagnsdata);
				nsmeta.setTag("_" + x, nbttagnsmeta);
			}
		}
		nbttc.setTag("id_name_map", id_name_map);
		nbttc.setTag("tile_entites", tileEntities);
		nbttc.setTag("entites", entities);
		nbttc.setTag("data", data);
		nbttc.setTag("meta", meta);
		if (unsolid) {
			nbttc.setTag("nsdata", nsdata);
			nbttc.setTag("nsmeta", nsmeta);
		}
		// TODO write data, meta, nsdata, nsmeta
		CompressedStreamTools.writeCompressed(nbttc, os);
	}
}
