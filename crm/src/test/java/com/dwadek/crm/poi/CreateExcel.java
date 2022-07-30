package com.dwadek.crm.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;

public class CreateExcel {

    public static void main(String[] args) throws Exception{
        HSSFWorkbook workbook  = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("学号");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("年龄");

        for(int i=1;i<=10;i++){
            row = sheet.createRow(i);

            cell = row.createCell(0);
            cell.setCellValue(100+i);
            cell = row.createCell(1);
            cell.setCellValue("name"+i);
            cell = row.createCell(2);
            cell.setCellValue(20+i);

        }

        FileOutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\交易策略\\student.xlsx ");
        workbook.write(os);



    }
}
