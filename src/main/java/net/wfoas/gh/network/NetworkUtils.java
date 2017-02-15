package net.wfoas.gh.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.EncoderException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
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

	@Deprecated
	public static byte[] _depre_serializeListStringToBytes(List<String> list) {
		try {
			return serializeListStringToBytes0(list);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static byte[] serializeListStringToBytes0(List<String> list) throws IOException {
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(o);
		if (list.isEmpty()) {
			System.out.println("empty");
			dos.writeByte(-1);
			return o.toByteArray();
		}
		dos.writeInt(list.size());
		System.out.println("SList: " + list.size());
		for (String s : list) {
			System.out.println("SEntry: " + s);
			byte[] ser = s.getBytes(GameHelper.UTF_8);
			dos.writeInt(ser.length);
			dos.write(ser);
		}
		dos.close();
		return o.toByteArray();
	}

	public static NBTBase writeStringList(List<String> s) {
		NBTTagList list = new NBTTagList();
		for (String sl : s) {
			list.appendTag(new NBTTagString(sl));
		}
		return list;
	}

	public static List<String> readStringList(NBTBase b) {
		if (b instanceof NBTTagList) {
			NBTTagList list = (NBTTagList) b;
			List<String> s = new ArrayList<String>();
			for (int i = 0; i < list.tagCount(); i++) {
				s.add(list.getStringTagAt(i));
			}
			return s;
		} else {
			return null;
		}
	}

	@Deprecated
	public static List<String> _depre_deserializeBytesToStringList(byte[] a) {
		try {
			return deserializeBytesToString0(a);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private synchronized static List<String> deserializeBytesToString0(byte[] a) throws IOException {
		ByteArrayInputStream i = new ByteArrayInputStream(a);
		DataInputStream in = new DataInputStream(i);
		int size = in.readInt();
		if (size == -1)
			return new ArrayList<String>();
		List<String> sl = new ArrayList<String>();
		for (int ai = 0; ai < size; ai++) {
			byte[] ar = new byte[in.readInt()];
			in.readFully(ar, 0, ar.length);
			sl.add(new String(ar, GameHelper.UTF_8));
		}
		System.out.println(sl);
		return sl;
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

	private static NBTTagCompound readNBTTagCompoundFromBuffer0(ByteBuf buf) throws IOException {
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
