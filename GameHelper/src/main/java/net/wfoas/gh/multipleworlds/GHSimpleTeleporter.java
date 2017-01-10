package net.wfoas.gh.multipleworlds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class GHSimpleTeleporter extends Teleporter {

	public GHSimpleTeleporter(WorldServer world) {
		super(world);
	}

	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float rotationYaw) {
		entity.setLocationAndAngles(x, y, z, rotationYaw, entity.rotationPitch);
		return true;
	}

	@Override
	public boolean makePortal(Entity p_85188_1_) {
		return true;
	}

	@Override
	public void removeStalePortalLocations(long totalWorldTime) {
		/* do nothing */
	}

	public void placeInPortal(Entity entity, double x, double y, double z, float rotationYaw) {
		placeInExistingPortal(entity, x, y, z, rotationYaw);
	}

	public static class TeleportInfo {

		private EntityPlayerMP player;

		private long start;

		private int timeout;

		private TeleportPoint point;

		private TeleportPoint playerPos;

		public TeleportInfo(EntityPlayerMP player, TeleportPoint point, int timeout) {
			this.point = point;
			this.timeout = timeout;
			this.start = System.currentTimeMillis();
			this.player = player;
			this.playerPos = new TeleportPoint(player);
		}

		public boolean check() {
			// if (playerPos.distance(new TeleportPoint(player)) > 0.2) {
			// ChatOutputHandler.chatWarning(player, "Teleport cancelled.");
			// return true;
			// }
			// if (System.currentTimeMillis() - start < timeout) {
			// return false;
			// }
			WorldUtils.checkedTeleport(player, point);
			// ChatOutputHandler.chatConfirmation(player, "Teleported.");
			System.out.println("Teleported.");
			return true;
		}

	}

}