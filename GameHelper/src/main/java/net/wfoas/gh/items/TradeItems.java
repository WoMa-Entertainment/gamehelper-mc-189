package net.wfoas.gh.items;

import net.minecraft.creativetab.CreativeTabs;

public class TradeItems {
	public static OKItem OK_ITEM;
	public static NOTOKItem NOT_OK_ITEM;
	public static CANCELItem CANCEL_ITEM;
	
	public static void instanciateAll(){
		OK_ITEM = new OKItem();
		NOT_OK_ITEM = new NOTOKItem();
		CANCEL_ITEM = new CANCELItem();
	}
	
	public static void updateInitFireCallEventsAll(CreativeTabs tab){
		OK_ITEM.updateInitEvent(tab);
		NOT_OK_ITEM.updateInitEvent(tab);
		CANCEL_ITEM.updateInitEvent(tab);
	}
	
	public static class OKItem extends GameHelperModItem {

		public OKItem() {
			super("ok_item");
		}

	}

	public static class NOTOKItem extends GameHelperModItem {

		public NOTOKItem() {
			super("not_ok_item");
		}
	}

	public static class CANCELItem extends GameHelperModItem {

		public CANCELItem() {
			super("cancel_item");
		}
	}
}
