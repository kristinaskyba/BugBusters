package gui;

import decathlon.Deca100M;
import decathlon.Deca400M;
import excel.ExcelPrinter;
import org.junit.jupiter.api.Test;


class MainGUITest {
    @Test
    public void testMaxCompetitors() throws Exception {
        ExcelPrinter excelPrinter = new ExcelPrinter("TestCompetitors");

        for (int i = 1; i <= 50; i++) {
            String name = "Competitor " + i;
            String discipline = "100m";
            double result = 21.0 + i;

            Deca400M deca400M = new Deca400M();
            int score = deca400M.calculateResult(result);

            Object[][] data = {
                    {name, discipline, result, score}
            };

            // Add the result to Excel
            excelPrinter.add(data, "Results");
        }

        // After all competitors are added, save the Excel file
        excelPrinter.write();

    }


}