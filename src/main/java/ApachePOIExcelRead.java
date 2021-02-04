import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApachePOIExcelRead {

    private static final String FILE_NAME = "./src/main/resources/SampleExcel.xlsx";
    private static final String OUTPUT_FILE_NAME = "./src/main/resources/SampleExcelResult.xlsx";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    private static final List<String> allowedHeaders = Arrays.asList("Venue", "Instrument", "Instrument Group", "Price");
    private static Map<String, String> filters = new HashMap<String,String>() {
        {
            put("Price", "3");
            //put("Header 4", "13/04/2008");
        }
    };
    
    public static void main(String[] args) {

        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet currentSheet = workbook.getSheetAt(0);

            final Map<Integer, String> headers = new HashMap();
            
            // get the first row (Headers)
            Row headersRow = currentSheet.getRow(0);
            // get the number of headers
            int countHeaders = headersRow.getLastCellNum();
            System.out.println("Number of headers: " + countHeaders);

            // populate headers map [index, header]
            for (int i=0; i < countHeaders; i++) {
                Cell currentCell = headersRow.getCell(i);
                headers.put(currentCell.getColumnIndex(), currentCell.getStringCellValue());
            }

            System.out.println ("Headers: " + headers);
            System.out.println ("Allowed Headers: " + allowedHeaders);
            System.out.println ("Filters: " + filters);

            //System.out.println (getCellData(currentSheet, headers, "Header 3", 2));

            // Create the new excel (output)
            XSSFWorkbook outputWorkbook = new XSSFWorkbook();
            XSSFSheet outputSheet = outputWorkbook.createSheet("Data");
    
            // Go through each row in the excel
            Iterator<Row> iterator = currentSheet.iterator();
            int rowNum = 0;
            String rowResult = null;
            int outputRowNum = 0;

            CellStyle cellStyle = outputWorkbook.createCellStyle();
            CreationHelper createHelper = outputWorkbook.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(DATE_FORMAT));

            while (iterator.hasNext()) {
                
                Row currentRow = iterator.next();

                if (rowResult != null) {outputRowNum++;}
                Row outputRow = outputSheet.createRow(outputRowNum);

                rowResult = "Row " + rowNum + ": ";

                // For each row, check every column values (as per the number of columns)
                for (int outputRowColumn=0,i=0; i < countHeaders; i++) {
                    
                    // Get only the cells for the columns allowed
                    if (allowedHeaders.contains(headers.get(i))) {

                        Cell currentCell = currentRow.getCell(i);
                        Cell outputCell = outputRow.createCell(outputRowColumn);

                        // Continue only if:
                        // - row is 0 -> it´s the headers row OR
                        // - there is no filter for the specific header OR
                        // - there is a filter and matches the cell´s value

                        System.out.println ("Checking cell content " + getCellDataAsString(currentCell, evaluator) + " vs filter " + filters.get(headers.get(i)));

                        if ( (rowNum == 0) || 
                             (!filters.containsKey(headers.get(i))) || 
                             (filters.containsKey(headers.get(i))) && getCellDataAsString(currentCell, evaluator).equals(filters.get(headers.get(i)))
                            ) {
                            // Check the cell´s type and get the value (just for printing)

                            CellType cellType = evaluator.evaluateInCell (currentCell).getCellType();

                            if ( (currentCell == null || cellType == CellType.BLANK)) {
                                rowResult += "BLANK;";
                                outputCell.setCellValue("");
                            } else if (cellType == CellType.STRING) {
                                rowResult += currentCell.getStringCellValue() + ";";
                                outputCell.setCellValue(currentCell.getStringCellValue());
                            // Special case for Dates, as it´s a Numeric cell formatted for a Date
                            } else if (cellType == CellType.NUMERIC) {
                                if (DateUtil.isCellDateFormatted(currentCell)) {
                                    rowResult += dateFormat.format(currentCell.getDateCellValue()) + ";";
                                    outputCell.setCellValue(currentCell.getDateCellValue());
                                    outputCell.setCellStyle(cellStyle);
                                } else {
                                    rowResult += currentCell.getNumericCellValue() + ";";
                                    outputCell.setCellValue(currentCell.getNumericCellValue());
                                }
                            }
                            outputRowColumn++;
                        } else {
                            //rowResult = "Row " + rowNum + ": Not allowed";
                            // Remove OutputRow
                            outputSheet.removeRow(outputRow);
                            rowResult = null;
                            break;
                        }
                    }
                }
                if (rowResult != null) System.out.println(rowResult);

                rowNum ++;
            }

            // Write the new Excel
            try {
                FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE_NAME);
                outputWorkbook.write(outputStream);
                outputWorkbook.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public static String getCellData(Sheet currentSheet, Map<String, Integer> columns, String column, int row){
        Row dataRow = currentSheet.getRow(row);
        return getCellDataAsString(dataRow.getCell(columns.get(column)));
    }
    */
    
    public static String getCellDataAsString(Cell cell, FormulaEvaluator evaluator){
        String result = "";
        
        CellType cellType = evaluator.evaluateInCell (cell).getCellType();

        if (cellType == CellType.STRING) {
            result = cell.getStringCellValue();
        } else if (cellType == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                result = dateFormat.format(cell.getDateCellValue());
            } else {
                //result = String.valueOf(cell.getNumericCellValue());
                result = NumberToTextConverter.toText(cell.getNumericCellValue());
            }
        } else if (cellType == CellType.BLANK) {}
        return result;
    }
}