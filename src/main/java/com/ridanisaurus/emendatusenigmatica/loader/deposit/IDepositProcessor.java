package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;

import java.util.List;

public interface IDepositProcessor {
	void load();
	String getType();
	CommonDepositModelBase getCommonModel();

	List<CommonBlockDefinitionModel> getBlocks();

	int getChance();
	int getMaxY();
	int getMinY();
	boolean hasSurfaceSample();
}
