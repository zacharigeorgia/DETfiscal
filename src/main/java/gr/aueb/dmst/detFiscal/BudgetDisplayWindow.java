package gr.aueb.dmst.detFiscal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Κλάση για την εμφάνιση των στοιχείων του προϋπολογισμού (Έσοδα/Έξοδα)
 * σε ένα παράθυρο με πίνακα.
 */
public class BudgetDisplayWindow extends JFrame {

    private FederalBudget budget;

    /**
     * Κατασκευαστής - δημιουργεί το παράθυρο εμφάνισης
     * 
     * @param budget Το FederalBudget αντικείμενο
     */
    public BudgetDisplayWindow(FederalBudget budget) {
        this.budget = budget;
        initializeWindow();
        createUI();
    }

    // Placeholder μέθοδοι
    private void initializeWindow() {
    }

    private void createUI() {
    }
}