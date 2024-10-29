package gui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import common.Competitor;
import decathlon.*;
import excel.ExcelPrinter;
import heptathlon.*;


public class MainGUI {

    private JTextField nameField;
    private JTextField resultField;
    private JComboBox<String> disciplineBox;
    private JTextArea outputArea;
    private JTable competitorTable;
    private DefaultTableModel tableModel;

    private JRadioButton disciplineSelector;
    private ArrayList<Competitor> competitors = new ArrayList<>();
    public static void main(String[] args) {
        new MainGUI().createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Track and Field Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 1));

        // Input for competitor's name
        nameField = new JTextField(20);
        panel.add(new JLabel("Enter Competitor's Name:"));
        panel.add(nameField);

        // Dropdown for selecting discipline
        String[] disciplines = {
                "100m", "400m", "1500m", "110m Hurdles",
                "Long Jump", "High Jump", "Pole Vault",
                "Discus Throw", "Javelin Throw", "Shot Put",
                "Hep 100m Hurdles", "Hep 200m", "Hep 800m",
                "Hep High Jump", "Hep Javelin Throw",
                "Hep Long Jump", "Hep Shot Put"
        };
        disciplineBox = new JComboBox<>(disciplines);
        panel.add(new JLabel("Select Discipline:"));
        panel.add(disciplineBox);

        // Input for result
        resultField = new JTextField(10);
        panel.add(new JLabel("Enter Result:"));
        panel.add(resultField);

        // Button to calculate and display result
        JButton calculateButton = new JButton("Calculate Score");
        calculateButton.addActionListener(new CalculateButtonListener());
        panel.add(calculateButton);

        JButton exportButton = new JButton("Export to Excel");
        exportButton.addActionListener(new ExportButtonListener());  // New export button listener
        panel.add(exportButton);  // Add export button to the panel

        // Output area
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);

        // Table for displaying competitors and their results
        String[] columnNames = {"Name", "100m", "400m", "1500m", "110m Hurdles",
                "Long Jump", "High Jump", "Pole Vault",
                "Discus Throw", "Javelin Throw", "Shot Put",
                "Hep 100M Hurdles", "Hep 200M", "Hep 800M", "Hep High Jump",
                "Hep Javelin Throw", "Hep Long Jump", "Hep Shot Put", "Total Score"};
        tableModel = new DefaultTableModel(columnNames, 0);
        competitorTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(competitorTable);
        tableScrollPane.setPreferredSize(new Dimension(750, 200));



        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);  // Top panel with inputs
        frame.add(tableScrollPane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String discipline = (String) disciplineBox.getSelectedItem();
            String resultText = resultField.getText();

            try {
                double result = Double.parseDouble(resultText);

                int score = 0;
                switch (discipline) {
                    case "100m":
                        Deca100M deca100M = new Deca100M();
                        score = deca100M.calculateResult(result);
                        break;
                    case "400m":
                        Deca400M deca400M = new Deca400M();
                        score = deca400M.calculateResult(result);
                        break;
                    case "1500m":
                        Deca1500M deca1500M = new Deca1500M();
                        score = deca1500M.calculateResult(result);
                        break;
                    case "110m Hurdles":
                        Deca110MHurdles deca110MHurdles = new Deca110MHurdles();
                        score = deca110MHurdles.calculateResult(result);
                        break;
                    case "Long Jump":
                        DecaLongJump decaLongJump = new DecaLongJump();
                        score = decaLongJump.calculateResult(result);
                        break;
                    case "High Jump":
                        DecaHighJump decaHighJump = new DecaHighJump();
                        score = decaHighJump.calculateResult(result);
                        break;
                    case "Pole Vault":
                        DecaPoleVault decaPoleVault = new DecaPoleVault();
                        score = decaPoleVault.calculateResult(result);
                        break;
                    case "Discus Throw":
                        DecaDiscusThrow decaDiscusThrow = new DecaDiscusThrow();
                        score = decaDiscusThrow.calculateResult(result);
                        break;
                    case "Javelin Throw":
                        DecaJavelinThrow decaJavelinThrow = new DecaJavelinThrow();
                        score = decaJavelinThrow.calculateResult(result);
                        break;
                    case "Shot Put":
                        DecaShotPut decaShotPut = new DecaShotPut();
                        score = decaShotPut.calculateResult(result);
                        break;
                    case "Hep 100m Hurdles":
                        Hep100MHurdles hep100mHurdles = new Hep100MHurdles();
                        score = hep100mHurdles.calculateResult(result);
                        break;
                    case "Hep 200m":
                        Hep200M hep200M = new Hep200M();
                        score = hep200M.calculateResult(result);
                        break;
                    case "Hep 800m":
                        Hep800M hep800M = new Hep800M();
                        score = hep800M.calculateResult(result);
                        break;
                    case "Hep High Jump":
                        HeptHightJump hepHighJump = new HeptHightJump();
                        score = hepHighJump.calculateResult(result);
                        break;
                    case "Hep Javelin Throw":
                        HeptJavelinThrow hepJavelinThrow = new HeptJavelinThrow();
                        score = hepJavelinThrow.calculateResult(result);
                        break;
                    case "Hep Long Jump":
                        HeptLongJump hepLongJump = new HeptLongJump();
                        score = hepLongJump.calculateResult(result);
                        break;
                    case "Hep Shot Put":
                        HeptShotPut hepShotPut = new HeptShotPut();
                        score = hepShotPut.calculateResult(result);
                        break;
                }


                if (competitors.size() >= 40) {
                    JOptionPane.showMessageDialog(null, "Maximum number of competitors reached (40).", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    var maybeExistingCompetitor = competitors.stream().filter(c -> c.getName().trim().toLowerCase().equals(name.toLowerCase().trim())).findAny();
                    var competitor = maybeExistingCompetitor.orElse( new Competitor(name));
                    competitor.setScore(discipline, score);

                    if(maybeExistingCompetitor.isEmpty()){
                        competitors.add(competitor);
                        tableModel.addRow(competitor.getRowData());

                    }else{
                        int competitorRowIndex = 0;
                        for(Competitor c:competitors){
                            if(c.getName().trim().toLowerCase().equals(name.toLowerCase().trim())) {
                                break;
                            }
                            competitorRowIndex++;
                        }
                        var competitorRowData = competitors.get(competitorRowIndex).getRowData();
                        for(int columnIndex = 0; columnIndex < competitorRowData.length; columnIndex++){
                            tableModel.setValueAt(competitorRowData[columnIndex], competitorRowIndex, columnIndex);
                        }
                    }
                    tableModel.fireTableDataChanged();
                }



                outputArea.append("Competitor: " + name + "\n");
                outputArea.append("Discipline: " + discipline + "\n");
                outputArea.append("Individual event score: " + score + "\n");
                outputArea.append("Total Score: " + score + "\n\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the result.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidResultException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Result", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class ExportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                exportToExcel();
                JOptionPane.showMessageDialog(null, "Results exported successfully!", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to export results to Excel.", "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportToExcel() throws IOException {
        java.util.List<String> names = java.util.List.of(ExcelPrinter.DECA_SHEET_NAME, ExcelPrinter.HEPA_SHEET_NAME);
        for(String name : names){
            String[][] data = new String[competitors.size()][];
            int i = 0;
            for (Competitor competitor : competitors) {
                Object[] rowData;
                if(ExcelPrinter.DECA_SHEET_NAME.equals(name)){
                    rowData = competitor.getDecathlonData();
                }else{
                    rowData = competitor.getHepathlonData();
                }

                // Ensure the array size matches the number of columns in rowData
                data[i] = new String[rowData.length];

                // Safely copy rowData to data array
                for (int j = 0; j < rowData.length; j++) {
                    data[i][j] = (rowData[j] != null) ? rowData[j].toString() : "";  // Handle null values
                }
                i++;
            }

            ExcelPrinter printer = new ExcelPrinter("TrackAndFieldResults");
            printer.add(data, name);
            printer.write("target/"+name+"_");
        }

    }
}
