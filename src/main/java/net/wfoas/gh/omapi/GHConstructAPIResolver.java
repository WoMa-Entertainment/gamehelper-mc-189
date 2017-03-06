package net.wfoas.gh.omapi;

import net.wfoas.gh.GameHelper;

public class GHConstructAPIResolver {
	public GameHelperAPI construct(GameHelper gh) {
		return new GameHelperAPI(gh);
	}
}
