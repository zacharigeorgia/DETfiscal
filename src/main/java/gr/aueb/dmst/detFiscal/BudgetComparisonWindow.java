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

}