package com.soyomaker.model;

import java.util.HashMap;
import java.util.Map;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.application.BeanFactory;
import com.soyomaker.application.IBean;
import com.soyomaker.application.IService;
import com.soyomaker.data.ISMObject;
import com.soyomaker.model.dataSource.GUDataSource;
import com.soyomaker.model.dataSource.IGUDataSource;
import com.soyomaker.model.dataset.DirectDataset;
import com.soyomaker.model.dataset.IDataSet;
import com.soyomaker.model.proxy.IDataProxy;

public class Model extends AbstractBean implements IService {
	private BeanFactory beanFactory = new BeanFactory();
	private boolean runFlag = true;

	private Map<String, IDataSet> datasets = new HashMap<String, IDataSet>();
	private Map<String, IDataProxy> proxies = new HashMap<String, IDataProxy>();
	private Map<String, IGUDataSource> dataSources = new HashMap<String, IGUDataSource>();

	/**
	 * 刷新周期
	 */
	private int flushPeriod = 1800000;
	/**
	 * Model的配置文件
	 */
	private String configFile = null;

	@Override
	public void initialize() {
		flushPeriod = this.getIntParam("flushPeriod", flushPeriod);
		configFile = this.getParam("config");

		beanFactory.addBean("dataSource", GUDataSource.class);

		beanFactory.addBean("dataset:direct", DirectDataset.class);

		beanFactory.initBeansWithConfig(configFile);

		for (String key : beanFactory.getBeanNames()) {
			IBean bean = beanFactory.getBean(key);

			if (bean instanceof IGUDataSource) {
				dataSources.put(key, (IGUDataSource) bean);
			} else if (bean instanceof IDataProxy) {
				proxies.put(key, (IDataProxy) bean);
			} else if (bean instanceof IDataSet) {
				datasets.put(key, (IDataSet) bean);
			}
		}
	}

	@Override
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (runFlag) {
					flush();
					try {
						Thread.sleep(flushPeriod);
					} catch (InterruptedException e) {
					}
				}
			}

		});
		thread.start();
	}

	@Override
	public void stop() {
		runFlag = false;
		flush();
	}

	private void flush() {
		for (IDataSet ds : datasets.values()) {
			ds.flush();
		}
	}

}
