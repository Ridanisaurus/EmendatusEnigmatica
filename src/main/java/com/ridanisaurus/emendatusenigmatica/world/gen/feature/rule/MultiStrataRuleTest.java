package com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiStrataRuleTest extends RuleTest {
	public static final Codec<MultiStrataRuleTest> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(Codec.STRING).fieldOf("fillerList").forGetter(it -> it.fillerList)
	).apply(x, MultiStrataRuleTest::new));

	public static final IRuleTestType<MultiStrataRuleTest> TYPE = IRuleTestType.func_237129_a_("multi_block_test", CODEC);
	private final List<Block> blockFillerList = new ArrayList<>();
	private List<String> fillerList;

	public MultiStrataRuleTest(List<String> fillerList) {
		this.fillerList = fillerList;
		setup();

	}

	private void setup() {
		for (StrataModel stratum : EELoader.STRATA) {
			if (this.fillerList.contains(stratum.getId())) {
				this.blockFillerList.add(ForgeRegistries.BLOCKS.getValue(stratum.getFillerType()));
			}
		}
	}

	@Override
	public boolean test(BlockState state, Random rand) {
		for (Block block : blockFillerList) {
			if (state.getBlock().getRegistryName().toString().equals(block.getRegistryName().toString())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected IRuleTestType<?> getType() {
		return TYPE;
	}
}
