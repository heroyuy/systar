package com.soyomaker.data.model;

import com.soyomaker.data.model.enums.LimitType;
import com.soyomaker.data.model.enums.TargetType;

public class Skill extends Model {

	private String name;

	private String intro;

	private String icon;

	private int lev;

	private TargetType target;

	private LimitType limit;

	private int userAniId;

	private int targetAniId;

	private String sound;

	private int eventId;

	private int costNum;

	private Cost[] costs;

	private int effectNum;

	private Effect[] effects;

	private int attributeNum;

	private Attribute[] attributes;

	private int statusNum;

	private Status[] statuses;
}
