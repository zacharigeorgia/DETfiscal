package gr.aueb.dmst.detFiscal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Κλάση για την εμφάνιση σύγκρισης προϋπολογισμού μεταξύ δύο ετών (2024 vs
 * 2025)
 */
public class BudgetComparisonWindow extends JFrame {

    private FederalBudget budget;

    /**
     * Κατασκευαστής
     * 
     * @param budget Το FederalBudget αντικείμενο
     */
    public BudgetComparisonWindow(FederalBudget budget) {
        this.budget = budget;
        initializeWindow();
        createUI();
    }

    // Placeholder μέθοδοι για να μην χτυπάει λάθος ο compiler
    private void initializeWindow() {
        setTitle("Σύγκριση Προϋπολογισμού 2024 vs 2025 - DETfiscal");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createUI() {
    }

    /**
     * Προσθέτει μια γραμμή σύγκρισης στον πίνακα
     */
    private void addComparisonRow(DefaultTableModel model, String category, double value2024, double value2025) {
        double difference = value2025 - value2024;
        double percentageChange = value2024 != 0 ? (difference / value2024) * 100 : 0;

        String diffStr = difference >= 0 ? String.format("+%,.2f", difference) : String.format("%,.2f", difference);
        String percentStr = percentageChange >= 0 ? String.format("+%.2f%%", percentageChange)
                : String.format("%.2f%%", percentageChange);

        Object[] row = {
                category,
                String.format("%,.2f", value2024),
                String.format("%,.2f", value2025),
                diffStr,
                percentStr
        };
        model.addRow(row);
    }

}