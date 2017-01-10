package net.wfoas.gh.dropsapi.pdr;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;

public class PotionEffectHelper {
	public static void addAttributeModToItemPotion(PotionEffect pe, ItemStack is) {
		// SharedMonsterAttributes
	}

//	public static void pnbttag() {
//		String json = "{ CustomPotionEffects: [ { Id:2, Amplifier:2, Duration:800 }, { Id:9, Amplifier:0, Duration:600 } ] }";
//		try {
//			NBTTagCompound nbttc = JsonToNBT.func_180713_a(json);
//			System.out.println("---JSONNBT---");
//			func_sdfhuio(nbttc);
//			System.out.println("---JSONNBT---");
//		} catch (NBTException e) {
//			e.printStackTrace();
//		}
//	}

//	public static void func_45a6df46s5df() {
//		NBTTagCompound nbttc = new NBTTagCompound();
//		NBTTagList nbttl = new NBTTagList();
//		NBTTagCompound potion1 = new NBTTagCompound();
//		NBTTagCompound potion2 = new NBTTagCompound();
//		potion1.setInteger("Id", 2);
//		potion1.setInteger("Amplifier", 2);
//		potion1.setInteger("Duration", 800);
//		potion2.setInteger("Id", 9);
//		potion2.setInteger("Amplifier", 0);
//		potion2.setInteger("Duration", 600);
//		nbttl.appendTag(potion1);
//		nbttl.appendTag(potion2);
//		nbttc.setTag("CustomPotionEffects", nbttl);
//		System.out.println("---CustomNBT---");
//		func_sdfhuio(nbttc);
//		System.out.println("---CustomNBT---");
//	}

//	public static void func_sdfhuio(NBTTagCompound nbttc) {
//		NBTTagList nbttl = (NBTTagList) nbttc.getTag("CustomPotionEffects");
//		NBTTagCompound potion1 = (NBTTagCompound) nbttl.get(0);
//		NBTTagCompound potion2 = (NBTTagCompound) nbttl.get(1);
//		System.out.println("p1" + potion1.getInteger("Id"));
//		System.out.println("p1" + potion1.getInteger("Amplifier"));
//		System.out.println("p1" + potion1.getInteger("Duration"));
//
//		System.out.println("p2" + potion2.getInteger("Id"));
//		System.out.println("p2" + potion2.getInteger("Amplifier"));
//		System.out.println("p2" + potion2.getInteger("Duration"));
//	}

	public static void writePotionContainerToNBT(List<PotionContainer> pc, ItemStack is) {
		writePotionEffectToNBT(convertPotionContainerToPotionEffectList(pc), is);
		// NBTTagCompound nbttc = new NBTTagCompound();
		// // if (!(is.getTagCompound().hasKey("CustomPotionEffects"))) {
		// nbttc.setTag("CustomPotionEffects",
		// createCustomPotionEffectList(convertPotionContainerListToNBTListCompound(pc)));
		// is.setTagCompound(nbttc);
		return;
		// }

		// NBTTagList nbttl =
		// is.getTagCompound().getTagList("CustomPotionEffects", 10);
		// NBTTagList nbttl = (NBTTagList)
		// is.getTagCompound().getTag("CustomPotionEffects");
		// NBTTagList nbttl2 =
		// addCustomPotionEffectsToList(convertPotionContainerListToNBTListCompound(pc),
		// nbttl);
		// is.getTagCompound().setTag("CustomPotionEffects", nbttl2);
	}

	public static List<PotionEffect> convertPotionContainerToPotionEffectList(List<PotionContainer> pc){
		List<PotionEffect> pc3 = new ArrayList<PotionEffect>();
		for(PotionContainer pc2 : pc){
			pc3.add(new PotionEffect(pc2.poe));
		}
		return pc3;
	}

	public static void writePotionEffectToNBT(List<PotionEffect> pc, ItemStack is) {
		 if (!(is.hasTagCompound())) {
		 	is.setTagCompound(new NBTTagCompound());
		 }
//		NBTTagCompound nbttc = new NBTTagCompound();
//		// if (!(is.getTagCompound().hasKey("CustomPotionEffects"))) {
//		nbttc.setTag("CustomPotionEffects", createCustomPotionEffectList(
//				convertPotionContainerListToNBTListCompound(convertPotionEffectListToPotionContainerList(pc))));
//		is.setTagCompound(nbttc);
		applyToItem(is.getTagCompound(), pc);
		return;
		// }
		//
	}

	static final String AMPLIFIER = "Amplifier";
	static final String AMBIENT = "Ambient";
	static final String DURATION = "Duration";
	static final String SHOW_PARTICLES = "ShowParticles";
	static final String POTION_EFFECTS = "CustomPotionEffects";
	static final String ID = "Id";

	public static void applyToItem(NBTTagCompound tag, List<PotionEffect> customEffects) {
		// super.applyToItem(tag);
		if (customEffects != null) {
			NBTTagList effectList = new NBTTagList();
			tag.setTag(POTION_EFFECTS, effectList);

			for (PotionEffect effect : customEffects) {
				NBTTagCompound effectData = new NBTTagCompound();
				effectData.setByte(ID, (byte) effect.getPotionID());
				effectData.setByte(AMPLIFIER, (byte) effect.getAmplifier());
				effectData.setInteger(DURATION, effect.getDuration());
				// effectData.setBoolean(AMBIENT, effect.isAmbient());
				// effectData.setBoolean(SHOW_PARTICLES, effect.hasParticles());
				effectList.appendTag(effectData);
			}
		}
	}

	// public static void createTagCompound(ItemStack is) {
	// is.setTagCompound(new NBTTagCompound());
	// }

	// public static NBTTagList
	// createCustomPotionEffectList(List<NBTTagCompound> effectList) {
	// NBTTagList nbttl = new NBTTagList();
	// for (NBTTagCompound nbttc : effectList) {
	// nbttl.appendTag(nbttc);
	// }
	//
	// return nbttl;
	// }
	//
	// public static NBTTagList
	// addCustomPotionEffectsToList(List<NBTTagCompound> effectList, NBTTagList
	// nbttl) {
	// for (NBTTagCompound nbttc : effectList) {
	// nbttl.appendTag(nbttc);
	// }
	// return nbttl;
	// }

	// public static NBTTagCompound
	// convertPotionEffectToNBTCompound(PotionContainer pc) {
	// NBTTagCompound nbttc = new NBTTagCompound();
	// nbttc.setInteger("Id", pc.poe.getPotionID());
	// nbttc.setInteger("Amplifier", pc.poe.getAmplifier());
	// nbttc.setInteger("Duration", pc.poe.getDuration());
	// return nbttc;
	// }
	//
	// public static PotionContainer convertPotionEffectToContainer(PotionEffect
	// pe) {
	// return new PotionContainer(pe);
	// }
	//
	// public static List<PotionContainer>
	// convertPotionEffectListToPotionContainerList(List<PotionEffect> pel) {
	// List<PotionContainer> lpc = new ArrayList<PotionContainer>();
	// for (PotionEffect pe : pel) {
	// lpc.add(convertPotionEffectToContainer(pe));
	// }
	// return lpc;
	// }
	//
	// public static List<NBTTagCompound>
	// convertPotionContainerListToNBTListCompound(List<PotionContainer>
	// potionList) {
	// List<NBTTagCompound> lnbttc = new ArrayList<NBTTagCompound>();
	//
	// for (PotionContainer pc : potionList) {
	// lnbttc.add(convertPotionEffectToNBTCompound(pc));
	// }
	//
	// return lnbttc;
	// }
}
