package cn.com.xl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/*import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
*/
//import au.com.bytecode.opencsv.CSVWriter;  

/**
 * <p>
 * 通过poi将csv导成excel,所需的包: opencsv-1.8.jar poi-2.5.1-final-20040804.jar
 * poi-contrib-2.5.1-final-20040804.jar poi-scratchpad-2.5.1-final-20040804.jar
 * 下载地址:http://www.apache.org/dyn/closer.cgi/jakarta/poi/
 * 
 * 此类待完善
 * </p>
 * 
 * @author tw
 * 
 */
public class PoiCsvUtils {

	/**
	 * <p>
	 * 通过poi将csv导成excel
	 * </p>
	 * 
	 * @author tw 2009-07-16
	 * 
	 */
	public static void CSVexcel() {
		/*try {
			String xlsPath = "e:/workbook.xls";
			String csvFilePath = "e:/workbook.csv";

			// CSVWriter writer = null;
			File tempFile = null;
			FileWriter fwriter = null; // 写数据
			try {
				tempFile = new File(csvFilePath);
				fwriter = new FileWriter(tempFile);
				// writer = new CSVWriter(fwriter);
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
			
			 * 读取Excel文件时，首先生成一个POIFSFileSystem对象，
			 * 由POIFSFileSystem对象构造一个HSSFWorkbook， 该HSSFWorkbook对象就代表了Excel文档
			 
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					xlsPath));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			HSSFRow row = null;
			HSSFCell cell = null;
			String cellStr = "";

			// 循环读取行与列的值,并将值写入CSV文件
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				String[] cellArray = new String[row.getLastCellNum()];
				for (int j = 0; j < row.getLastCellNum(); j++) {
					cell = row.getCell((short) j);
					// 判断储存格的格式
					if (cell == null) {
						cellStr = "";
					} else {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:// 数字格式
							cellStr = cell.getNumericCellValue() + "";
							// getNumericCellValue()会回传double值，若不希望出现小数点，请自行转型为int
							break;
						case HSSFCell.CELL_TYPE_STRING:// 字符格式
							cellStr = cell.getStringCellValue();
							break;
						// case HSSFCell.CELL_TYPE_FORMULA:
						// System.out.print(cell.getNumericCellValue());
						// //读出公式储存格计算後的值
						// //若要读出公式内容，可用cell.getCellFormula()
						// break;
						default:// 不明的格式
							break;
						}
					}
					System.out.println("-----row-" + i + "-----cell-" + j
							+ "---:" + cellStr);
					cellArray[j] = cellStr;
				}
				// writer.writeNext(cellArray);// 把Excel一行记录写入CSV文件
			}

			fwriter.flush();
			fwriter.close();
			// writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}