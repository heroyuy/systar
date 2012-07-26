package com.soyomaker.model.criteria;

import com.soyomaker.model.DataValue;

/**
 * 判断条件，检查制定的对象是否符合条件
 * 
 * @author zhangjun
 * 
 */
public interface ICriteria {
	public ICriteria and(ICriteria c);

	public boolean execute(DataValue data);

	public ICriteria not();

	public ICriteria or(ICriteria c);
}
