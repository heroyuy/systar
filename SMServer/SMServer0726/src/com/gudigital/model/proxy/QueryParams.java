package com.gudigital.model.proxy;

import java.util.ArrayList;
import java.util.List;

public class QueryParams {
	private List params = new ArrayList();

	/**
	 * 添加参数，目前只支持数字和字符串。应该参照GUDataType重新整理参数的类型。
	 * 
	 * @param v
	 */
	public void add(Object v) {
		if (v instanceof Number) {
			params.add(v);
		} else if (v instanceof String) {
			params.add(v);
		} else {
			throw new IllegalArgumentException("Not supported parameter type " + v.getClass().getName());
		}
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof QueryParams) {
			QueryParams p = (QueryParams) arg0;
			if (p.size() == size()) {
				for (int i = 0; i < size(); i++) {
					Object o1 = p.get(i);
					Object o2 = get(i);
					if (o1 == null || !o1.equals(o2)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public Object get(int idx) {
		if (idx >= 0 && idx < params.size()) {
			return params.get(idx);
		} else {
			return null;
		}
	}

	@Override
	public int hashCode() {
		int c = 0;
		for (Object o : params) {
			c ^= o.hashCode();
		}
		return c;
	}

	public void remove(int idx) {
		if (idx >= 0 && idx < params.size()) {
			params.remove(idx);
		}
	}

	public int size() {
		return params.size();
	}

	public Object[] toArray() {
		Object[] objs = new Object[params.size()];
		for (int i = 0; i < params.size(); i++) {
			objs[i] = params.get(i);
		}
		return objs;
	}

	@Override
	public String toString() {
		String s = "QueryParam[";
		for (Object o : params) {
			s += o.toString() + ",";
		}
		if (s.endsWith(",")) {
			s = s.substring(0, s.length() - 1) + "]";
		} else {
			s += "]";
		}
		return s;
	}

}
