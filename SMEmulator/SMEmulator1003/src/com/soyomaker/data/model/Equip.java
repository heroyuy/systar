package com.soyomaker.data.model;

import com.soyomaker.data.model.enums.EquipType;
import com.soyomaker.data.model.enums.LimitType;
import com.soyomaker.data.model.enums.TargetType;

public class Equip extends Model {

	private String name;

	private String intro;

	private String icon;

	private EquipType equipType;

	private int lev;

	private int userAniId;

	private int targetAniId;

	private int price;

	private int effectNum;

	private Effect[] effects;

	private int attributeNum;

	private Attribute[] attributes;

	private int statusNum;

	private Status[] statuses;
}
