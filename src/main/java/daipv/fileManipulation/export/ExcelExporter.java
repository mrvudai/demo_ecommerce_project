package daipv.fileManipulation.export;

import daipv.model.Users;
import daipv.repository.IUserRepo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


@Component
public class ExcelExporter {
    @Autowired
    private IUserRepo userRepo;
    public void reportExcel(HttpServletResponse response){
        try {
            List<Users> usersList = userRepo.findAll();

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("Sheet1");
            XSSFRow rowhead = sheet.createRow(0);
            rowhead.createCell(0).setCellValue("id");
            rowhead.createCell(1).setCellValue("username");
            rowhead.createCell(2).setCellValue("pass");
            rowhead.createCell(3).setCellValue("email");

            int index = 1;
            for (Users user : usersList) {
                XSSFRow rowData = sheet.createRow(index);
                rowData.createCell(0).setCellValue(user.getId());
                rowData.createCell(1).setCellValue(user.getUserName());
                rowData.createCell(2).setCellValue(user.getPassword());
                rowData.createCell(3).setCellValue(user.getEmail());
                index++;
            }

            ServletOutputStream ops = response.getOutputStream();
            workbook.write(ops);
            workbook.close();
            ops.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
