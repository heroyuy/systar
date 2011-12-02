package com.soyomaker.data.model;

import com.soyomaker.data.model.enums.ActionType;

public class Action extends Model {

	private int conditionNum;

	private Condition[] conditions;

	private ActionType actionType;

	private int paramNum;

	private int[] params;

	private int rate;

}
