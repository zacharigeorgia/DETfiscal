package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BudgetComparisonWindowFullTest {

    private FederalBudget realBudget;

    @BeforeEach
    void setUp() {
        // Παίρνουμε τον singleton και καθαρίζουμε τις λίστες
        realBudget = FederalBudget.getInstance();
        realBudget.getSummary().getRevenues().clear();
        realBudget.getSummary().getExpenditures().clear();
        realBudget.getSummary().getRevenues2024().clear();
        realBudget.getSummary().getExpenditures2024().clear();

        // Δημιουργούμε δεδομένα 2025
        Revenue rev2025 = new Revenue();
        rev2025.setName("Φόροι");
        rev2025.setAmount(5500.00);
        realBudget.getSummary().addRevenue(rev2025);

        Expenditure exp2025 = new Expenditure();
        exp2025.setName("Έξοδα");
        exp2025.setAmount(2500.00);
        realBudget.getSummary().addExpenditure(exp2025);

        // Δημιουργούμε δεδομένα 2024
        Revenue rev2024 = new Revenue();
        rev2024.setName("Φόροι");
        rev2024.setAmount(5000.00);
        realBudget.getSummary().getRevenues2024().add(rev2024);

        Expenditure exp2024 = new Expenditure();
        exp2024.setName("Έξοδα");
        exp2024.setAmount(4000.00);
        realBudget.getSummary().getExpenditures2024().add(exp2024);
    }

    @Test
    void testTableRowsAndSummaryPanels() throws Exception {
        // Δημιουργούμε παράθυρο με το πραγματικό singleton (δεν ανοίγει display)
        BudgetComparisonWindow window = new BudgetComparisonWindow(realBudget);

        // Πιάνουμε τον scroll pane στον mainPanel μέσω reflection (ή απλού getContentPane)
        JPanel mainPanel = (JPanel) window.getContentPane().getComponent(0);
        JScrollPane scrollPane = (JScrollPane) mainPanel.getComponent(1);
        JTable table = (JTable) scrollPane.getViewport().getView();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        assertEquals(3, model.getRowCount(), "Ο πίνακας πρέπει να έχει 3 γραμμές");

        // Έλεγχος πρώτης γραμμής (Συνολικά Έσοδα)
        assertEquals("Συνολικά Έσοδα", model.getValueAt(0, 0));
        // Τα format strings στον κώδικά σου χρησιμοποιούν dot ή comma ανάλογα locale.
        // Ελέγχουμε αριθμητικά αντί για ακριβές string:
        String val2024 = model.getValueAt(0, 1).toString();
        String val2025 = model.getValueAt(0, 2).toString();

        // Μετατρέπουμε σε double (αν έχει € ή % καταργούμε) — εδώ είναι απλό μορφοποιημένο 5,000.00 ή 5.000,00
        double v2024 = parseFormattedNumber(val2024);
        double v2025 = parseFormattedNumber(val2025);

        assertEquals(5000.00, v2024, 0.01);
        assertEquals(5500.00, v2025, 0.01);

        // Έλεγχος difference και percent (στο UI είναι formatted Strings). Ελέγχουμε το numeric difference:
        String diffStr = model.getValueAt(0, 3).toString();
        double diff = parseFormattedNumber(diffStr.replace("+", "").replace("€", ""));
        assertEquals(500.00, Math.abs(diff), 0.01);

        // Έλεγχος summary panel (bottom)
        JPanel bottomContainer = (JPanel) mainPanel.getComponent(2);
        JPanel summaryPanel = (JPanel) bottomContainer.getComponent(0);
        JPanel panel2024 = (JPanel) summaryPanel.getComponent(0); // 2024
        JLabel rev2024Label = (JLabel) ((JPanel) panel2024.getComponent(1)).getComponent(0);
        assertTrue(rev2024Label.getText().contains("5,000") || rev2024Label.getText().contains("5.000"));
    }

    // Βοηθητική μέθοδος για parsing formatted numbers με dot ή comma
    private double parseFormattedNumber(String s) {
        String cleaned = s.replaceAll("[^0-9,.-]", ""); // αφαιρούμε σύμβολα εκτός αριθμών / κόμμα / τελεία / -
        // Αν υπάρχει comma και τελεία, καταλαβαίνουμε locale: αν τελεία είναι χιλιάδες και κόμμα δεκαδικά
        if (cleaned.matches(".*\\..*,.*")) {
            // μορφή 1.234,56 -> μετατροπή σε 1234.56
            cleaned = cleaned.replace(".", "").replace(",", ".");
        } else if (cleaned.contains(",")) {
            // μορφή 1234,56 -> κόμμα δεκαδικά
            cleaned = cleaned.replace(",", ".");
        } else {
            // ήδη με dot ή ακέραιος
        }
        return Double.parseDouble(cleaned);
    }
}


