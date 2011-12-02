package com.soyomaker.data.model;

import com.soyomaker.data.model.enums.LastType;
import com.soyomaker.data.model.enums.StatusType;

public class Status extends Model {

	private StatusType statusType;

	private String name;

	private String desc;

	private String icon;

	private int lev;

	private int aniId;

	private LastType lastType;

	private int lastValue;

	private int paramNum;

	private Param[] params;

	private int removeConditionNum;

	private Condition[] conditions;

	private int attributeNum;

	private Attribute[] attributes;

	private int statusNum;

	private Status[] statuses;

	private int value;// 影响值

}
