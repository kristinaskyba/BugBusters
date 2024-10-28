package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;


public class ExcelPrinter {

	private XSSFWorkbook workbook;
	private String excelName;

	public static final String[] DECA_HEADERS = new String[]{
		"Name",
			"100m", "400m", "1500m", "110m Hurdles",
			"Long Jump", "High Jump", "Pole Vault",
			"Discus Throw", "Javelin Throw", "Shot Put",
			"Total Score"
	};

	public static final String[] HEPA_HEADERS = new String[]{
			"Name",
			"100m Hurdles", "200m", "800m",
			"High Jump", "Javelin Throw",
			"Long Jump", "Shot Put",
			"Total Score"
	};

	public static final String DECA_SHEET_NAME = "Decathlon";

	public static final String HEPA_SHEET_NAME = "Heptathlon";

	public ExcelPrinter(String name) {
		workbook = new XSSFWorkbook();
		excelName = name;
	}

	public void add(Object[][] data, String sheetName) {

		String[] headerRowValues;
		if(DECA_SHEET_NAME.equals(sheetName)){
			headerRowValues = DECA_HEADERS;
		}else if(HEPA_SHEET_NAME.equals(sheetName)){
			headerRowValues = HEPA_HEADERS;
		}else{
			throw new RuntimeException(String.format("Can't use this sheet name: %s, use one of %s, %s",
					sheetName, HEPA_SHEET_NAME, DECA_SHEET_NAME));
		}

		XSSFSheet sheet = workbook.createSheet(sheetName);

		int rowCount = 0;
		Row headerRow = sheet.createRow(rowCount);
		CellStyle boldCellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		boldCellStyle.setFont(font);

		for(int columnIndex = 0; columnIndex < headerRowValues.length; columnIndex++){
			Cell cell = headerRow.createCell(columnIndex);
			cell.setCellStyle(boldCellStyle);
			cell.setCellValue(headerRowValues[columnIndex]);
		}
		rowCount++;


		for (Object[] aBook : data) {
			Row row = sheet.createRow(rowCount);
			rowCount++;
			int columnCount = 0;

			for (Object field : aBook) {
				Cell cell = row.createCell(columnCount);
				columnCount++;

				if (field instanceof String) {
					cell.setCellValue((String) field);

				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);

				} else if (field instanceof Double) {
					cell.setCellValue((Double) field);

				}
			}
		}
	}

	public void write(String pathPrefix) throws IOException {
		String outputPath = "C:/Eclipse/resultat_" + excelName + ".xlsx";
		if(pathPrefix!=null){
			outputPath = pathPrefix + excelName  + ".xlsx";
		}
		FileOutputStream out = new FileOutputStream(outputPath);
		workbook.write(out);
		workbook.close();
	}

}