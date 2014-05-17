package com.neuralnoise.map.service.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class XSSFParser {

	private static final Logger log = LoggerFactory.getLogger(XSSFParser.class);
	
	private XSSFParser() { }
	
	public static List<Map<String, String>> parse(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(0);

		Map<Integer, String> colNames = Maps.newHashMap();
		List<Map<String, String>> content = Lists.newLinkedList();

		boolean first = true;

		for (Row row : sheet) {
			Map<String, String> map = (!first ? new HashMap<String, String>() : null);

			for (Cell cell : row) {
				String str = "";

				switch (cell.getCellType()) {
				case 0: {
					str = Long.toString(Math.round(cell.getNumericCellValue()));
				}
					break;
				default: {
					str = cell.getStringCellValue();
				}
					break;
				}

				if (first) {
					colNames.put(cell.getColumnIndex(), str);
				} else {
					if (str.length() > 0) {
						final String colName = colNames.get(cell.getColumnIndex());
						map.put(colName, str);
					}
				}
			}

			first = false;

			if (map != null) {
				if (map.keySet().size() > 0) {
					content.add(map);
				}
			}
		}

		for (Map<String, String> map : content) {
			log.info("Content: " + map);
		}
		
		return content;
	}

}
