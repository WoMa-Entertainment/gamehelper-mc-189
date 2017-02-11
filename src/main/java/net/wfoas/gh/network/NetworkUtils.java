package net.wfoas.gh.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.EncoderException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.wfoas.gh.GameHelper;

public class NetworkUtils {
	public static byte[] writeNBTTagCompoundToBytes(NBTTagCompound nbttc) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			CompressedStreamTools.writeCompressed(nbttc, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	public static NBTTagCompound readNBTTagCompoundFromBytes(byte[] bytes) {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		try {
			return CompressedStreamTools.readCompressed(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] serializeMapIntStringToBytes(Map<Integer, String> map) {
		NBTTagCompound nbttc = new NBTTagCompound();
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			nbttc.setString(entry.getKey().toString(), entry.getValue());
		}
		return writeNBTTagCompoundToBytes(nbttc);
	}

	public static Map<Integer, String> deserializeMapIntStringFromBytes(byte[] bytes) {
		NBTTagCompound nbttc = readNBTTagCompoundFromBytes(bytes);
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (String key : nbttc.getKeySet()) {
			map.put(Integer.parseInt(key), nbttc.getString(key));
		}
		return map;
	}

	public static void writeUTF8String(String s, ByteBuf buffer) {
		byte[] bytes = s.getBytes(GameHelper.UTF_8);
		buffer.writeInt(bytes.length);
		buffer.writeBytes(bytes);
	}

	public static String readUTF8String(ByteBuf buffer) {
		byte[] bytes = new byte[buffer.readInt()];
		buffer.readBytes(bytes);
		return new String(bytes, GameHelper.UTF_8);
	}

	public static void writeNBTTagCompoundToBuffer(NBTTagCompound nbt, ByteBuf buf) {
		if (nbt == null) {
			buf.writeByte(0);
		} else {
			try {
				CompressedStreamTools.write(nbt, new ByteBufOutputStream(buf));
			} catch (IOException ioexception) {
				throw new EncoderException(ioexception);
			}
		}
	}

	public static NBTTagCompound readNBTTagCompoundFromBuffer0(ByteBuf buf) throws IOException {
		int i = buf.readerIndex();
		byte b0 = buf.readByte();

		if (b0 == 0) {
			return null;
		} else {
			buf.readerIndex(i);
			return CompressedStreamTools.read(new ByteBufInputStream(buf), new NBTSizeTracker(2097152L));
		}
	}

	public static NBTTagCompound readNBTTagCompoundFromBuffer(ByteBuf buf) {
		try {
			return readNBTTagCompoundFromBuffer0(buf);
		} catch (IOException ioe) {
			return null;// glsl: discard;
		}
	}

	public static void writeBlockPos(BlockPos pos, ByteBuf buf) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}

	public static BlockPos readBlockPos(ByteBuf buf) {
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		return new BlockPos(x, y, z);
	}
}
