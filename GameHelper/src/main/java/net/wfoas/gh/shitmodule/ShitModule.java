package net.wfoas.gh.shitmodule;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.wfoas.gh.blocks.GameHelperModBlock;
import net.wfoas.gh.items.GameHelperModItem;
import net.wfoas.gh.shitmodule.items.BongItem;
import net.wfoas.gh.shitmodule.items.CokeItem;
import net.wfoas.gh.shitmodule.items.JointItem;
import net.wfoas.gh.shitmodule.items.LsdPills;
import net.wfoas.gh.shitmodule.items.WeedItem;
import net.wfoas.gh.shitmodule.tileentity.ShitChestTileEntity;
import net.wfoas.gh.shitmodule.tileentity.ShitChestTileEntityBlock;

public class ShitModule {
	public static GameHelperModItem BONG, LSD_PILLS, COKE, JOINT, WEED;
	public static ShitTab TAB;
	public static GameHelperModBlock SCTE_BLOCK;
	public void preInitServer(FMLPreInitializationEvent event){
		BONG = new BongItem();
		LSD_PILLS = new LsdPills();
		COKE = new CokeItem();
		JOINT = new JointItem();
		WEED = new WeedItem();
		SCTE_BLOCK = new ShitChestTileEntityBlock();
	}
	
	public void preInitClient(FMLPreInitializationEvent event){
	}
	
	public void initServer(FMLInitializationEvent event){
		GameRegistry.registerTileEntity(ShitChestTileEntity.class, "shitChest");
//		GameRegistry.registerBlock(SCTE_BLOCK, "");
	}
	
	public void initClient(FMLInitializationEvent event){
		if(event.getSide() == Side.CLIENT){
			TAB = new ShitTab();
			BONG.updateInitEvent(TAB);
			LSD_PILLS.updateInitEvent(TAB);
			COKE.updateInitEvent(TAB);
			JOINT.updateInitEvent(TAB);
			WEED.updateInitEvent(TAB);
			SCTE_BLOCK.updateInitEvent(TAB);
		}
	}
	
	public void postInitServer(FMLPostInitializationEvent event){
		
	}
	
	public void postInitClient(FMLPostInitializationEvent event){
		
	}
}
