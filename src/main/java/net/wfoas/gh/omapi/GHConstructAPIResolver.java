package net.wfoas.gh.omapi;

import net.wfoas.gh.GameHelper;

public class GHConstructAPIResolver {
	public GameHelperAPI construct(GHAPIModContainer gh) {
		return new GameHelperAPI(gh);
	}
}
