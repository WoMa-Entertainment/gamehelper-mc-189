package net.wfoas.gh.worldgenerator;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.wfoas.gh.GameHelperCoreModule;
import net.wfoas.gh.titanmodule.TitanModule;

public class GHWorldGenerator implements IWorldGenerator {
	WorldGenMinable gen_saphirre, gen_ruby, gen_titan, gen_citrin, gen_amethyst;

	public GHWorldGenerator() {
		this.gen_saphirre = new WorldGenMinable(GameHelperCoreModule.SAPPHIRE_ORE.getDefaultState(), 4);
		this.gen_ruby = new WorldGenMinable(GameHelperCoreModule.RUBY_ORE.getDefaultState(), 5);
		this.gen_titan = new WorldGenMinable(TitanModule.TITAN_ORE.getDefaultState(), 4);
		this.gen_citrin = new WorldGenMinable(TitanModule.CITRIN_ORE.getDefaultState(), 5);
		this.gen_amethyst = new WorldGenMinable(GameHelperCoreModule.AMETHYST_ORE.getDefaultState(), 7);
	}

	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z,
			int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimensionId()) {
		case 0: // Overworld
			runGenerator(gen_saphirre, world, random, chunkX, chunkZ, 4, 10, 25);
			runGenerator(gen_ruby, world, random, chunkX, chunkZ, 4, 3, 17);
			runGenerator(gen_titan, world, random, chunkX, chunkZ, 4, 4, 17);
			runGenerator(gen_citrin, world, random, chunkX, chunkZ, 4, 5, 40);
			runGenerator(gen_amethyst, world, random, chunkX, chunkZ, 4, 3, 20);
			break;
		case -1: // Nether
			break;
		case 1: // End
			break;
		}
	}
}