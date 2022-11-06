package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;

public interface IDepositProcessor {
	void load();
	String getType();
	CommonDepositModelBase getCommonModel();
}
