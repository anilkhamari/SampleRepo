Excel Reader
============

Small Maven-based utility that reads an Excel file (.xls or .xlsx) and prints each row to the console.

Requirements
------------
- Java 11+
- Maven

Build & Run (PowerShell)
-------------------------
1. From the repository root run (example using an absolute path to an Excel file):

```powershell
mvn -f .\excel-reader exec:java -Dexec.mainClass="com.example.helloworld.excel.ExcelPrinter" -Dexec.args="C:\\path\\to\\file.xlsx"
```

2. Or build and run the resulting jar via the exec plugin:

```powershell
mvn -f .\excel-reader package
mvn -f .\excel-reader exec:java -Dexec.mainClass="com.example.helloworld.excel.ExcelPrinter" -Dexec.args="C:\\path\\to\\file.xlsx"
```

Notes
-----
- Supports both .xls and .xlsx formats via Apache POI.
- Output prints one row per line, tab-separated columns.
