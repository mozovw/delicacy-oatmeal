package com.delicacy.oatmeal.poi.excel.tmp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.delicacy.oatmeal.poi.excel.tmp.entity.ExcelData;


/**
 * @ClassName: ExcelTmpUtil 
 * @Description:  Excel模板文件内容util类
 * @author Frank
 */
public class ExcelTmpUtil {
	/**
	 * 替换Excel模板文件内容
	 * 
	 * @param datas
	 *            文档数据
	 * @param sourceFilePath
	 *            Excel模板文件路径
	 * @param targetFilePath
	 *            Excel生成文件路径
	 */
	public static boolean replaceModel(List<ExcelData> datas,
			String sourceFilePath, String targetFilePath) {
		boolean bool = true;
		FileOutputStream fileOut = null;
		try {
			Workbook wb = getWorkBook(sourceFilePath);
			Sheet sheet = wb.getSheetAt(0);
			for (ExcelData data : datas) {
				if (data.getRow() != null && data.getColumn() != null) {
					replaceCell(sheet, data);
				} else if (data.getKey() != null) {
					replaceCell2(sheet, data);
				}
			}
			fileOut = new FileOutputStream(targetFilePath);
			wb.write(fileOut);
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	/**
	 * @Title: getWorkBook 
	 * @Description: get 2k3 or 2k7 excel
	 * @param filePath
	 * @return Workbook
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static Workbook getWorkBook(String filePath) throws IOException{
		Workbook wb;
		if (filePath.indexOf(".xlsx") != -1) {
			wb = new XSSFWorkbook(new FileInputStream(filePath));
		} else {
			wb = new HSSFWorkbook(new FileInputStream(filePath));
		}
		return wb;
	}

	private static void replaceCell(Sheet sheet, ExcelData data) {
		// 获取单元格内容
		Row row = sheet.getRow(data.getRow());
		Cell cell = row.getCell(data.getColumn());
		Object value = data.getValue();
		if (value instanceof InputStream) {
			cell.setCellValue("");
		}
		// 写入单元格内容
		cell.setCellType(cell.getCellType());
		if (value == null) {
			cell.setCellValue("");
		} else {
			/*System.out.println(data.getRow() + " " + data.getColumn() + "  "
					+ data.getValue().toString());*/
			cell.setCellValue(String.valueOf(value));
		}
	}

	private static void replaceCell2(Sheet sheet, ExcelData data) {
		data = CelltoExcelHandleDataVO(sheet, data);
		if (data.getRow() != null && data.getColumn() != null) {
			replaceCell(sheet, data);
		}
	}

	private static ExcelData CelltoExcelHandleDataVO(Sheet sheet,
			ExcelData excelData) {
		String string = excelData.getKey().toString().trim();
		int rowCount = sheet.getLastRowNum();

		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j <= row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (cell == null)
					continue;
				int cellType = cell.getCellType();
				String key = null;
				// System.out.println(cellType);
				switch (cellType) {
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
						Date dataDate = cell.getDateCellValue();
						key = formater.format(dataDate);
						break;
					}
					key = String.valueOf(cell.getNumericCellValue());
					key = key.substring(0, key.indexOf("."));
					break;
				case Cell.CELL_TYPE_STRING:
					key = cell.getStringCellValue().trim();
					break;
				case Cell.CELL_TYPE_FORMULA:
					key = cell.getCellFormula();
					break;
				default:
					continue;
				}
				if (key.equals(string)) {
					excelData.setRow(i);
					excelData.setColumn(j);
				}
			}
		}
		return excelData;
	}
}