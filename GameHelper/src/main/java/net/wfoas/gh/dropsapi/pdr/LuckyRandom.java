package net.wfoas.gh.dropsapi.pdr;

import net.wfoas.gh.dropsapi.Drop;

public class LuckyRandom extends java.util.Random {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7740283846620761594L;
	
	public LuckyRandom(){
		super();
	}
	
	public LuckyRandom(long seed){
		super(seed);
	}
	
	@Deprecated
	public Drop nextDrop(){
		return null;
	}
	
	@Deprecated
	public Drop nextLuckyDrop(){
		return null;
	}
	
	@Deprecated
	public Drop nextUnluckyDrop(){
		return null;
	}
	
	@Deprecated
	public Drop nextNeutralDrop(){
		return null;
	}
	public Luck nextLuckValue(){
		Luck l = null;
		int i = nextInt(3);
		switch(i){
			case 1:
				l = Luck.LUCKY;
				break;
			case 2:
				l = Luck.UNLUCKY;
				break;
			default:
				l = Luck.NEUTRAL;
				break;
		}
		
		return l;
	}
	
}
