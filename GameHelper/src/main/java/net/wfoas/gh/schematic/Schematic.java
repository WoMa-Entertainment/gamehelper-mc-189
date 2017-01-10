package net.wfoas.gh.schematic;

import java.io.InputStream;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/*TODO Enhanced Schematic format*/
public class Schematic {

	private short width;
	private short height;
	private short length;
	private int size;
	private BlockObject[] blockObjects;
	// private boolean useStaticLocation = true;
	// private boolean usePlayerLocation = true;

	public Schematic(NBTTagCompound nbtdata) {
		try {
			width = nbtdata.getShort("Width");
			height = nbtdata.getShort("Height");
			length = nbtdata.getShort("Length");
			// useStaticLocation = nbtdata.getBoolean("StaticLocation");
			// usePlayerLocation = nbtdata.getBoolean("PlayerLocation");

			size = width * height * length;
			blockObjects = new BlockObject[size];

			byte[] blockIDs = nbtdata.getByteArray("Blocks");
			byte[] metadata = nbtdata.getByteArray("Data");

			int counter = 0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < length; j++) {
					for (int k = 0; k < width; k++) {
						BlockPos pos = new BlockPos(k, i, j);
						IBlockState state = Block.getBlockById(blockIDs[counter]).getStateFromMeta(metadata[counter]);
						blockObjects[counter] = new BlockObject(pos, state);
						counter++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NBTTagCompound serialize() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setShort("Width", width);
		nbt.setShort("Height", height);
		nbt.setShort("Length", length);
		byte[] blockIDs = new byte[width * height * length];
		byte[] metas = new byte[width * height * length];
		//
		int counter = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				for (int k = 0; k < width; k++) {
					// BlockPos pos = new BlockPos(k, i, j);
					// IBlockState state =
					// Block.getBlockById(blockIDs[counter]).getStateFromMeta(metadata[counter]);
					// blockObjects[counter].getState().getBlock().getid
					blockIDs[counter] = (byte) Block.getIdFromBlock(blockObjects[counter].getState().getBlock());
					metas[counter] = (byte) blockObjects[counter].getState().getBlock()
							.getMetaFromState(blockObjects[counter].getState());
					counter++;
				}
			}
		}
		nbt.setByteArray("Blocks", blockIDs);
		nbt.setByteArray("Data", metas);
		return nbt;
	}

	public void generate(World world, int x, int y, int z) {
		for (BlockObject obj : blockObjects) {
			world.setBlockState(obj.getPositionWithOffset(x, y, z), obj.getState());
		}
	}
}