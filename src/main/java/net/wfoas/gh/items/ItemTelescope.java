package net.wfoas.gh.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.wfoas.gh.luckyblocksmodule.dropsapi.structure.Structure;
import net.wfoas.gh.luckyblocksmodule.dropsapi.structure.StructureParserFromWFShematics;
import net.wfoas.gh.multipleworlds.GHSimpleTeleporter;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.WorldUtils;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;

public class ItemTelescope extends GameHelperModItem {

	Structure struct;

	public ItemTelescope() {
		super("telescope");
		struct = StructureParserFromWFShematics
				.readShematics(new String[] { "#name=Tempel", "#version=GH 1.0", "//Set-DirtBlock On Diamond",
						"#useplayerpos=false", "#relativepos=true", "0;0;0#DIAMOND_BLOCK#0", "0;1;0#DIAMOND_BLOCK#0" });
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos,
			EntityLivingBase playerIn) {
		if (!worldIn.isRemote)
			struct.executeLuckyBlockDrop((EntityPlayer) playerIn, pos);
		System.out.println(struct.getName());
		return true;
	}

	public static double defaultSpeed = 0.699999988079071D;
	public static final AttributeModifier SCREEN_ZOOM = new AttributeModifier("movementSpeed", -defaultSpeed * 8, 1)
			.setSaved(false);

	public boolean alreadyInteracted = false;

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			long seed = 1l;
			String worldName = "bekiffteWelt";
			GHWorld ghw = new GHWorld(worldName, GHWorldManager.PROVIDER_NORMAL, GHWorldManager.WORLD_TYPE_DEFAULT,
					seed);
			if (!alreadyInteracted) {
				if (!GHWorldManager.isWorldAlreadyCreated(ghw)) {
					GHWorldManager.createWorld(ghw);
					if (playerIn instanceof EntityPlayerMP) {
						WorldUtils.transferPlayerToDimension((EntityPlayerMP) playerIn, ghw.dimensionId,
								new GHSimpleTeleporter(ghw.getWorldServer()));
					}
				} else {
					if (!GHWorldManager.isWorldAlreadyLoaded(ghw)) {
						GHWorldManager.loadWorld(ghw);
					} else
						ghw = GHWorldManager.getLoadedGHWorld(worldName);
					if (playerIn instanceof EntityPlayerMP) {
						// WorldUtils.transferPlayerToDimension((EntityPlayerMP)
						// playerIn, ghw.dimensionId,
						// new GHSimpleTeleporter(ghw.getWorldServer()));
						WorldUtils.teleportPlayerToDimension((EntityPlayerMP) playerIn, ghw.dimensionId);
					}
				}
				alreadyInteracted = true;
			} else {
				WorldUtils.transferPlayerToDimension((EntityPlayerMP) playerIn, 0,
						new GHSimpleTeleporter(DimensionManager.getWorld(0)));
				alreadyInteracted = false;
			}
			// int dimId = DimensionManager.getNextFreeDimId();
			// DimensionManager.registerProviderType(dimId,
			// WorldProviderSurface.class, true);
			// DimensionManager.registerDimension(dimId, 0);
			// WorldProvider wp = DimensionManager.createProviderFor(dimId);
			// // DimensionManager.setWorld(dimId, new
			// // WorldServerMulti(MinecraftServer.getServer(), wp.gets, dimId,
			// // delegate, profilerIn));
			// DimensionManager
			// .setWorld(dimId,
			// new WorldServer(MinecraftServer.getServer(),
			// new
			// GHWorldSaveHandler(DimensionManager.getWorld(0).getSaveHandler(),
			// worldName),
			// new WorldInfo(
			// new WorldSettings(seed, GameType.SURVIVAL, true, false,
			// WorldType.DEFAULT),
			// worldName),
			// dimId, MinecraftServer.getServer().theProfiler));
			// WorldServer ws = DimensionManager.getWorld(dimId);
			// playerIn.setWorld(ws);
		}
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote) {
			if (isSelected) {
				if (entityIn instanceof EntityPlayer) {
					// defaultSpeed =
					// ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
					// ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25d);
					EntityPlayer ep = (EntityPlayer) entityIn;
					if (!ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).func_111122_c()
							.contains(SCREEN_ZOOM)) {
						ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(SCREEN_ZOOM);
					}
					return;
				}
			} else {
				if (entityIn instanceof EntityPlayer) {
					// defaultSpeed =
					// ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
					// ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25d);
					EntityPlayer ep = (EntityPlayer) entityIn;
					if (ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).func_111122_c()
							.contains(SCREEN_ZOOM))
						ep.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(SCREEN_ZOOM);
				}
			}
		}
	}

}
