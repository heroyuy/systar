package com.soyomaker.test;

import com.soyomaker.GameServer;
import com.soyomaker.model.dataset.DirectDataset;
import com.soyomaker.model.proxy.QueryParams;

public class TableProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameServer.main(args);
		DirectDataset dataset = (DirectDataset) GameServer.instance().getBeanFactory().getBean("playerDataSet");

		/**
		 * get
		 */
		/*
		 * QueryParams parms = new QueryParams(); parms.add(1); DataElement de =
		 * dataset.get(parms); System.out.println(de.toJson());
		 */

		/**
		 * load all
		 */
		/*
		 * List<DataElement> list= dataset.queryAll(); for(DataElement de :
		 * list){ System.out.println(de.toJson()); }
		 */

		/**
		 * write
		 */
		/*
		 * QueryParams parms = new QueryParams(); parms.add(1); DataElement de =
		 * dataset.get(parms); de.put("name",new
		 * GUDataWrapper(GUDataType.STRING, "2222")); dataset.put(de.getKey(),
		 * de);
		 */
		/**
		 * remove
		 */
		QueryParams parms = new QueryParams();
		parms.add(1);
		dataset.remove(parms);
	}

}
