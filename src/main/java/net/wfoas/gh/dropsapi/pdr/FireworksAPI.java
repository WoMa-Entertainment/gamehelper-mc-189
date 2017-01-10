package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class FireworksAPI {
	public static EntityFireworkRocket createRandomFireworks(World w, Vec3d pos){
		NBTTagCompound tag0 = new NBTTagCompound();
		NBTTagList explosions = new NBTTagList();
		NBTTagCompound tag1 = new NBTTagCompound();
		NBTTagCompound tag2 = new NBTTagCompound();
		
		int[] array = new int[1];
		array[0] = 65536*230;
		
		tag2.setIntArray("Colors", array);
		tag2.setBoolean("Flicker", true);
		
		explosions.appendTag(tag2);
		
		tag1.setTag("Explosions", explosions);
		tag1.setByte("Flight", (byte) 0);
		
		tag0.setTag("Fireworks", tag1);
		
		
		ItemStack modifier = new ItemStack(Items.fireworks);
		
		modifier.setTagCompound(tag0);
		
		EntityFireworkRocket effect = new EntityFireworkRocket(w, pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D, modifier);
		
		w.spawnEntityInWorld(effect);
		return effect;
	}
}
