package com.soyomaker.data.model;

import com.soyomaker.data.model.enums.MoveType;
import com.soyomaker.data.model.enums.ScriptType;
import com.soyomaker.data.model.enums.SpeedType;

public class NPCState extends Model {

	private ScriptType scriptType;

	private String charPath;

	private String headImg;

	private byte face;

	private MoveType moveType;

	private SpeedType speedType;

	private boolean penetrable;

	private int conditionNum;

	private Condition[] conditions;

	private int scriptId;

}
