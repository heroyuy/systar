package com.soyomaker.data.model;

import com.soyomaker.data.model.enums.ConditionType;

public class Condition extends Model {

	private ConditionType conditionType;

	private String name;

	private int paramNum;

	private int[] params;

}