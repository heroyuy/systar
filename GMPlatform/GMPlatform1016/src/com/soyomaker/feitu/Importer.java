package com.soyomaker.feitu;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Importer {

	private Logger logger = Logger.getInstance();

	private ImportListener listener = null;

	private String getCellContent(Sheet sheet, int row, int col) {
		String value = sheet.getCell(col, row).getContents();
		if (value == null) {
			value = "";
		}
		return value;
	}

	public ImportListener getListener() {
		return listener;
	}

	/**
	 * 从excel导入
	 * 
	 * @param excelFilePath
	 */
	public void importFromExcel(final String excelFilePath) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				logger.addLog(new ILog() {

					@Override
					public void info(Object msg) {
						System.out.println(msg);

					}
				});
				File excelFile = new File(excelFilePath);
				try {
					Workbook book = Workbook.getWorkbook(excelFile);
					String[] sheetNames = book.getSheetNames();
					logger.info("共有工作表" + sheetNames.length + "张");
					for (String sheetName : sheetNames) {
						Sheet sheet = book.getSheet(sheetName);
						logger.info("开始导入工作表:" + sheet.getName());
						if (sheet.getColumns() == 0 || sheet.getRows() == 0
								|| getCellContent(sheet, 0, 0).equals("")) {
							logger.info("工作表为空，跳过");
							return;
						}
						logger.info("开始读取表头");// 第0行是列说明，第1行是表头
						List<String> keyList = new ArrayList<String>();
						int index = 0;
						while (index < sheet.getColumns()) {
							String key = getCellContent(sheet, 1, index);
							if (!key.equals("")) {
								logger.info("key:" + key);
								keyList.add(key);
								index++;
							} else {
								break;
							}
						}
						logger.info("开始读取表内容");// 从第二行开始读数据
						List<Map<String, String>> values = new ArrayList<Map<String, String>>();
						for (int i = 2; i < sheet.getRows(); i++) {
							if (getCellContent(sheet, i, 0).equals("")) {
								logger.info("遇到空行，结束");
								break;
							}
							Map<String, String> valueMap = new HashMap<String, String>();
							for (int j = 0; j < keyList.size(); j++) {
								String value = getCellContent(sheet, i, j);
								valueMap.put(keyList.get(j), value);
							}
							values.add(valueMap);
						}
						logger.info("表内容读取结束，一共" + values.size() + "行有效数据");
						importToDB(sheet.getName(), values);
					}
				} catch (BiffException e) {
					e.printStackTrace();
					logger.info(e);
				} catch (IOException e) {
					e.printStackTrace();
					logger.info(e);
				}
				if (listener != null) {
					logger.info("导出完成");
					listener.onEnd();
				}
			}
		}).start();

	}

	private void importToDB(String tableName,
			List<Map<String, String>> columnList) {
		logger.info("开始导入数据到数据库");
		try {
			Connection conn = DataSource.getConnection();
			Table table = new Table(tableName, conn);
			// (1)清空表
			table.clear();
			// (2)插入数据
			for (Map<String, String> valueMap : columnList) {
				table.insert(valueMap);
			}
			conn.close();
		} catch (SQLException e) {
			logger.info("导入出错");
			e.printStackTrace();
		}
	}

	public void setListener(ImportListener listener) {
		this.listener = listener;
	}

}
