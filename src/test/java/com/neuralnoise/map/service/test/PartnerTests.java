package com.neuralnoise.map.service.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PartnerTests {

	private static final Logger log = LoggerFactory.getLogger(PartnerTests.class);
	
	private static final String PARTNER_RES = "/partner01/PARTNER.xlsx";
	
	@Test
	public void test() throws IOException {
		String path = ClassLoader.class.getResource(PARTNER_RES).getPath();
		FileInputStream file = new FileInputStream(new File(path));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		XSSFSheet sheet = workbook.getSheetAt(0);

		Map<Integer, String> colNames = Maps.newHashMap();		
		List<Map<String, String>> content = Lists.newLinkedList();
		
		boolean first = true;
		
		for (Row row : sheet) {
			Map<String, String> map = (!first ? new HashMap<String, String>() : null);
			
			for (Cell cell : row) {
				String str = null;
				
				switch (cell.getCellType()) {
				case 0: {
					str = Long.toString(Math.round(cell.getNumericCellValue()));
				} break;
				default: {
					str = cell.getStringCellValue();
				} break;
				}

				if (first) {
					colNames.put(cell.getColumnIndex(), str);
				} else {
					final String colName = colNames.get(cell.getColumnIndex());
					map.put(colName, str);
				}
			}
			
			first = false;
			
			if (map != null) {
				content.add(map);
			}
		}
		
		for (Map<String, String> map : content) {
			log.info("Content: " + map);
		}
	}
}
