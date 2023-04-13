package grechen.parser.service;

import grechen.parser.model.ProductAndSearch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelCreate {
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;

    public ExcelCreate(){
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Parser_Rozetka");
    }

    public HSSFWorkbook getWorkbook(){
        return workbook;
    }

    public HSSFSheet getSheet(){
        return sheet;
    }

    private void addColumnCaptions(Row row, int numberCell, String captions){
        row.createCell(numberCell).setCellValue(captions);
    }

    public void createColumnCaptions(List<String> captions){
        int numberCell = 0;
        Row row = getSheet().createRow(0);
        for(String capt : captions){
            addColumnCaptions(row, numberCell, capt);
            numberCell += 1;
        }
    }

    private void fillOutSheet(List<ProductAndSearch> adList){
        int rowNum = 0;
        for (ProductAndSearch dataModel : adList) {
            fillExcel(sheet, ++rowNum, dataModel);
        }
    }

    private static void fillExcel(HSSFSheet sheet, int rowNum, ProductAndSearch dataModel) {
        Row row = sheet.createRow(rowNum);
        sheet.autoSizeColumn(rowNum);
        row.createCell(0).setCellValue(dataModel.getPageNumber());
        sheet.autoSizeColumn(0);
        row.createCell(1).setCellValue(dataModel.getSearch());
        sheet.autoSizeColumn(1);
        row.createCell(2).setCellValue(dataModel.getInternalNumber());
        sheet.autoSizeColumn(2);
        row.createCell(3).setCellValue(dataModel.getShortDescription());
        sheet.autoSizeColumn(3);
        row.createCell(4).setCellValue(dataModel.getPrice());
        sheet.autoSizeColumn(4);
        row.createCell(5).setCellValue(dataModel.getAvailability());
        sheet.autoSizeColumn(5);
        row.createCell(6).setCellValue(dataModel.getProductLink());
        sheet.autoSizeColumn(6);
    }

    public void createExcel(String fileName, List<ProductAndSearch> adList){
        File directory_PATH = new File("./Results");
        if(directory_PATH.mkdirs())
        {
            System.out.println("Directory created successfully");
        }

        System.out.println(directory_PATH);
        fillOutSheet(adList);
        try (FileOutputStream out = new FileOutputStream(directory_PATH + "/" + fileName + ".xls")) {
            getWorkbook().write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");
    }
}
