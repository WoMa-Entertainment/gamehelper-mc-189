package net.wfoas.gh.minersinventory.helmetlight;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.wfoas.gh.minersinventory.layer.MinersInventoryHelper;

public class ClientSideLightHandler {
	public static int LIGHT_LEVEL_MINERS_HELMET = 13;

	@SubscribeEvent
	public void handleLight(TickEvent.PlayerTickEvent event) {
		if (!event.player.worldObj.isRemote) {
			// System.out.println("kill serverside!");
			return;
		}
		if (event.phase == TickEvent.Phase.START) {
			EntityPlayer playerMp = event.player;
			World world = playerMp.worldObj;
			if ((MinersInventoryHelper.getMinersInventory(playerMp).hasMinersHelmet())) {
				// System.out.println("Miners-light equ");
				// System.out.println("X:" +
				// LightProperties.getItem("LastLightX", playerMp) + "Y:"
				// + LightProperties.getItem("LastLightY", playerMp) + "Z:"
				// + LightProperties.getItem("LastLightZ", playerMp));
				BlockPos lpgpppos = LightProperties.getPlayerPosition(playerMp);
				if ((lpgpppos.getX() != LightProperties.getItem("LastLightX", playerMp))
						|| (lpgpppos.getY() != LightProperties.getItem("LastLightY", playerMp))
						|| (lpgpppos.getZ() != LightProperties.getItem("LastLightZ", playerMp))) {
					world.setLightFor(EnumSkyBlock.BLOCK, new BlockPos(lpgpppos), LIGHT_LEVEL_MINERS_HELMET);
					world.checkLightFor(EnumSkyBlock.BLOCK,
							new BlockPos(LightProperties.getItem("LastLightX", playerMp),
									LightProperties.getItem("LastLightY", playerMp),
									LightProperties.getItem("LastLightZ", playerMp)));
					world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lpgpppos).east());
					world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lpgpppos).west());
					world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lpgpppos).up());
					world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lpgpppos).down());
					world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lpgpppos).south());
					world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(lpgpppos).north());
					LightProperties.setItem("LastLightX", lpgpppos.getX(), playerMp);
					LightProperties.setItem("LastLightY", lpgpppos.getY(), playerMp);
					LightProperties.setItem("LastLightZ", lpgpppos.getZ(), playerMp);
					// props.setLastLight(props.getPlayerPosX(),
					// props.getPlayerPosY(), props.getPlayerPosZ());
				}
			} else {
				// System.out.println("Miners-light n equ");
				// System.out.println("X:" +
				// LightProperties.getItem("LastLightX", playerMp) + "Y:"
				// + LightProperties.getItem("LastLightY", playerMp) + "Z:"
				// + LightProperties.getItem("LastLightZ", playerMp));
				if (!(LightProperties.getItem("LastLightX", playerMp) == 0
						&& LightProperties.getItem("LastLightY", playerMp) == 0
						&& LightProperties.getItem("LastLightZ", playerMp) == 0)) {
					System.out.println("Miners-light n 0 equ");
					world.checkLightFor(EnumSkyBlock.BLOCK,
							new BlockPos(LightProperties.getItem("LastLightX", playerMp),
									LightProperties.getItem("LastLightY", playerMp),
									LightProperties.getItem("LastLightZ", playerMp)));
					world.checkLightFor(EnumSkyBlock.BLOCK,
							new BlockPos(LightProperties.getPlayerPosition(playerMp)).east());
					world.checkLightFor(EnumSkyBlock.BLOCK,
							new BlockPos(LightProperties.getPlayerPosition(playerMp)).west());
					world.checkLightFor(EnumSkyBlock.BLOCK,
							new BlockPos(LightProperties.getPlayerPosition(playerMp)).up());
					world.checkLightFor(EnumSkyBlock.BLOCK,
							new BlockPos(LightProperties.getPlayerPosition(playerMp)).down());
					world.checkLightFor(EnumSkyBlock.BLOCK,
							new BlockPos(LightProperties.getPlayerPosition(playerMp)).south());
					world.checkLightFor(EnumSkyBlock.BLOCK,
							new BlockPos(LightProperties.getPlayerPosition(playerMp)).north());
					LightProperties.setItem("LastLightX", 0, playerMp);
					LightProperties.setItem("LastLightY", 0, playerMp);
					LightProperties.setItem("LastLightZ", 0, playerMp);
				}
			}
		}
	}
}