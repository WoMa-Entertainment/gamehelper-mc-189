package net.wfoas.gh.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.Teleporter;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.wfoas.gh.GameHelper;
import net.wfoas.gh.multipleworlds.GHSimpleTeleporter;
import net.wfoas.gh.multipleworlds.GHWorld;
import net.wfoas.gh.multipleworlds.WorldUtils;
import net.wfoas.gh.multipleworlds.storage.GHWorldManager;
import net.wfoas.gh.world.utils.WorldPermission;
import net.wfoas.gh.world.utils.WorldPermissionsManager;

@Cancelable
public class PlayerChangeDimensionEvent extends GHEvent {

	final EntityPlayer entityPlayer;
	final int dimensionIDPlayerFrom;
	private GHWorld worldTo;
	public boolean forceChange = false;

	public PlayerChangeDimensionEvent(EntityPlayer player, int dimIDFrom, GHWorld worldTo) {
		this.entityPlayer = player;
		this.dimensionIDPlayerFrom = dimIDFrom;
		this.worldTo = worldTo;
		System.out.println("ghw: " + worldTo);
	}

	public GHWorld getWorldTo() {
		return worldTo;
	}

	@Override
	public void run() {
		if (!isCanceled())
			eventRun(this);
	}

	public static void eventRun(PlayerChangeDimensionEvent dimChange) {
		// System.out.println(Thread.currentThread().getName());
		if (dimChange.entityPlayer.worldObj.isRemote)
			return;
		GHWorld ghw = dimChange.worldTo;
		if ((!WorldPermissionsManager.getPermission(WorldPermission.VISIT, dimChange.entityPlayer, ghw)) && (!GameHelper.getUtils().isHacker((EntityPlayerMP) dimChange.entityPlayer))) {
			dimChange.entityPlayer.addChatMessage(new ChatComponentTranslation("gamehelper.error.world.nopermission.visit", ghw.getName()));
			return;
		}
		EntityPlayer playerIn = dimChange.entityPlayer;
		System.out.println("GHW: " + ghw);
		if (ghw.getName().startsWith("_DIMID:")) {
			try {
				int dimID = Integer.parseInt(ghw.getName().replaceAll("_DIMID:", ""));
				if (dimID == 0) {
					if (dimChange.dimensionIDPlayerFrom == dimID) {
						if (!dimChange.forceChange) {
							playerIn.addChatMessage(
									new ChatComponentTranslation("gamehelper.error.dimensionchange.samedimension"));
							return;
						}
					}
					//
					WorldUtils.transferPlayerToDimension((EntityPlayerMP) playerIn, dimID,
							new GHSimpleTeleporter(DimensionManager.getWorld(dimID)));
					UnloadDimensionEventCausedByPlayerWorldChange unload = new UnloadDimensionEventCausedByPlayerWorldChange(
							dimChange.dimensionIDPlayerFrom);
				}
			} catch (NumberFormatException nfe) {
				return;// fail silently
			}
		}
		if (!GHWorldManager.isWorldAlreadyCreated(ghw)) {
			// GHWorldManager.createWorld(ghw);
			// if (playerIn instanceof EntityPlayerMP) {
			// WorldUtils.transferPlayerToDimension((EntityPlayerMP) playerIn,
			// ghw.dimensionId,
			// new GHSimpleTeleporter(ghw.getWorldServer()));
			// }
			GameHelper.println("World not created: " + ghw);
			return;
		} else {
			if (!GHWorldManager.isWorldAlreadyLoaded(ghw)) {
				System.out.println("Loading World: " + ghw);
				// GHWorldManager.loadWorld(ghw);
				LoadDimensionEventCausedByPlayerRequest load = new LoadDimensionEventCausedByPlayerRequest(ghw);
				GHEventBus.fireEvent(load);
			} else {
				ghw = GHWorldManager.getLoadedGHWorld(ghw.getName());
				dimChange.worldTo = ghw;
				System.out.println("Already loaded!");
			}
			if (playerIn instanceof EntityPlayerMP) {
				// System.out.println("MP");
				// WorldUtils.transferPlayerToDimension((EntityPlayerMP)
				// playerIn, ghw.dimensionId,
				// new GHSimpleTeleporter(ghw.getWorldServer()));
				// WorldUtils.teleportPlayerToDimension((EntityPlayerMP)
				// playerIn, ghw.dimensionId);WorldManager
				if (dimChange.dimensionIDPlayerFrom == ghw.dimensionId) {
					if (!dimChange.forceChange) {
						playerIn.addChatMessage(
								new ChatComponentTranslation("gamehelper.error.dimensionchange.samedimension"));
						return;
					}
				} 
				
				WorldUtils.transferPlayerToDimension((EntityPlayerMP) playerIn, ghw.dimensionId,
						new GHSimpleTeleporter(DimensionManager.getWorld(ghw.dimensionId)));
				UnloadDimensionEventCausedByPlayerWorldChange unload = new UnloadDimensionEventCausedByPlayerWorldChange(
						dimChange.dimensionIDPlayerFrom);
//				((EntityPlayerMP) playerIn).worldObj
			}
		}
	}
}
