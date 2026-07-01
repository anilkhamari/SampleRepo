package com.example.helloworld.excel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Simple utility that reads an Excel file (.xls or .xlsx) and prints each row to the console.
 */
public class ExcelPrinter {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Usage: mvn -f excel-reader exec:java -Dexec.args=\"<path-to-excel-file>\"");
            System.out.println("Example: mvn -f excel-reader exec:java -Dexec.args=\"C:\\tmp\\sample.xlsx\"");
            return;
        }

        Path file = Paths.get(args[0]);
        if (!Files.exists(file)) {
            System.err.println("File not found: " + file.toAbsolutePath());
            return;
        }

        try (InputStream in = Files.newInputStream(file);
             Workbook wb = WorkbookFactory.create(in)) {

            DataFormatter formatter = new DataFormatter();

            for (Sheet sheet : wb) {
                System.out.println("--- Sheet: " + sheet.getSheetName() + " ---");
                for (Row row : sheet) {
                    int last = row.getLastCellNum();
                    if (last < 0) { // empty row
                        System.out.println();
                        continue;
                    }
                    List<String> values = new ArrayList<>();
                    for (int c = 0; c < last; c++) {
                        Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        String text = formatter.formatCellValue(cell);
                        values.add(text);
                    }
                    System.out.println(String.join("\t", values));
                }
            }

        } catch (IOException e) {
            System.err.println("I/O error reading file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error opening workbook: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
