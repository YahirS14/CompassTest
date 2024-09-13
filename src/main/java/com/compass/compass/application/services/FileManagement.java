package com.compass.compass.application.services;

import com.compass.compass.data.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileManagement {
    public static List<User> readExcelFile(FileInputStream file) throws IOException {
        List<User> contactList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String id = getCellValueAsString(row.getCell(0));
                String firstName = getCellValueAsString(row.getCell(1));
                String lastName = getCellValueAsString(row.getCell(2));
                String email = getCellValueAsString(row.getCell(3));
                String zipCode = getCellValueAsString(row.getCell(4));
                String address = getCellValueAsString(row.getCell(5));

                User contact = new User(id, firstName, lastName, email, zipCode, address);
                contactList.add(contact);
            }
        }

        return contactList;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            case BLANK -> "";
            default -> "";
        };
    }
}