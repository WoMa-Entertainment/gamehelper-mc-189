package net.wfoas.gh.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
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
}
