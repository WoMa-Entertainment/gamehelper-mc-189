package net.wfoas.gh.omapi;

import net.wfoas.gh.GameHelper;

public class GHConstructAPIResolverCl extends GHConstructAPIResolver {
	@Override
	public GameHelperAPI construct(GHAPIModContainer gh) {
		return new GameHelperAPIClientSide(gh);
	}
}
