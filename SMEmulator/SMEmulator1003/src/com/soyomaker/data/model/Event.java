package com.soyomaker.data.model;

import com.soyomaker.data.model.enums.EventType;

public class Event extends Model {

	private int conditionNum;

	private Condition[] conditions;

	private EventType eventType;

	private int scriptId;

}
