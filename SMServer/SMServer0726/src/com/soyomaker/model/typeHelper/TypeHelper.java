package com.soyomaker.model.typeHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;

import com.soyomaker.data.GUDataType;
import com.soyomaker.data.GUDataWrapper;
import com.soyomaker.model.DataValue;

public class TypeHelper {
	public static TypeHelper fromElement(Element e) {
		TypeHelper m = new TypeHelper();
		m.setName(e.attributeValue("name"));
		m.setKeyColumn(e.attributeValue("keyColumn"));
		m.setKeyType(GUDataType.fromTypeName(e.attributeValue("keyType")));

		@SuppressWarnings("unchecked")
		Iterator<Element> props = e.elementIterator();
		while (props.hasNext()) {
			PropertyHelper prop = PropertyHelper.fromElement(props.next());
			m.add(prop);
		}
		return m;
	}

	private String name;
	private String keyColumn;
	private GUDataType keyType;

	private List<PropertyHelper> props = new ArrayList<PropertyHelper>();

	public boolean add(PropertyHelper arg0) {
		return props.add(arg0);
	}

	public void clear() {
		props.clear();
	}

	public DataValue createDataElement() {
		DataValue e = new DataValue();
		e.setType(name);
		for (PropertyHelper ph : props) {
			GUDataType dataType = ph.getType();
			e.put(ph.getFieldName(), new GUDataWrapper(dataType, dataType.getDefault()));
		}
		return e;
	}

	public PropertyHelper get(int arg0) {
		return props.get(arg0);
	}

	public String getKeyColumn() {
		return keyColumn;
	}

	public GUDataType getKeyType() {
		return keyType;
	}

	public String getName() {
		return name;
	}

	public PropertyHelper getPropertyHelperByColumnName(String colName) {
		for (PropertyHelper p : props) {
			if (p.getColumnName().equalsIgnoreCase(colName)) {
				return p;
			}
		}
		return null;
	}

	public int indexOf(Object arg0) {
		return props.indexOf(arg0);
	}

	public Iterator<PropertyHelper> iterator() {
		return props.iterator();
	}

	public PropertyHelper remove(int arg0) {
		return props.remove(arg0);
	}

	public boolean remove(Object arg0) {
		return props.remove(arg0);
	}

	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
	}

	public void setKeyType(GUDataType keyType) {
		this.keyType = keyType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int size() {
		return props.size();
	}
}
