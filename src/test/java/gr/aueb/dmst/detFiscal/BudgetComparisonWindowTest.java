package gr.aueb.dmst.detFiscal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BudgetComparisonWindowFullTest {

    private FederalBudget mockBudget;
    private BudgetSummary mockSummary;

    @BeforeEach
    void setUp() {
        mockBudget = mock(FederalBudget.class);
        mockSummary = mock(BudgetSummary.class);

        when(mockBudget.getSummary()).thenReturn(mockSummary);
        when(mockBudget.calculateTotalBudget()).thenReturn(3000.0);

        when(mockSummary.calculateTotalRevenues2024()).thenReturn(5000.0);
        when(mockSummary.calculateTotalRevenues()).thenReturn(5500.0);
        when(mockSummary.calculateTotalExpenditures2024()).thenReturn(4000.0);
        when(mockSummary.calculateTotalExpenditures()).thenReturn(2500.0);
        when(mockSummary.calculateBalance2024()).thenReturn(1000.0);
    }

    @Test
    void testTableRowsAndSummaryPanels() {
        BudgetComparisonWindow window = new BudgetComparisonWindow(mockBudget);

        JScrollPane scrollPane = (JScrollPane) ((JPanel) window.getContentPane().getComponent(0)).getComponent(1);
        JTable table = (JTable) scrollPane.getViewport().getView();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        assertEquals(3, model.getRowCount(), "Ο πίνακας πρέπει να έχει 3 γραμμές");

        // --- Διορθωμένα με ελληνικό format ---
        assertEquals("Συνολικά Έσοδα", model.getValueAt(0, 0));
        assertEquals("5.000,00", model.getValueAt(0, 1));
        assertEquals("5.500,00", model.getValueAt(0, 2));
        assertEquals("+500,00", model.getValueAt(0, 3));
        assertEquals("+10,00%", model.getValueAt(0, 4));

        JPanel mainPanel = (JPanel) window.getContentPane().getComponent(0);
        JPanel bottomContainer = (JPanel) mainPanel.getComponent(2);
        JPanel summaryPanel = (JPanel) bottomContainer.getComponent(0);

        JPanel panel2024 = (JPanel) summaryPanel.getComponent(0);
        JLabel rev2024 = (JLabel) ((JPanel) panel2024.getComponent(1)).getComponent(0);
        JLabel exp2024 = (JLabel) ((JPanel) panel2024.getComponent(1)).getComponent(1);
        JLabel bal2024 = (JLabel) ((JPanel) panel2024.getComponent(1)).getComponent(2);

        assertEquals("Έσοδα: 5.000,00 €", rev2024.getText());
        assertEquals("Έξοδα: 4.000,00 €", exp2024.getText());
        assertTrue(bal2024.getText().contains("1.000,00 €"));

        JPanel panel2025 = (JPanel) summaryPanel.getComponent(1);
        JLabel rev2025 = (JLabel) ((JPanel) panel2025.getComponent(1)).getComponent(0);
        JLabel exp2025 = (JLabel) ((JPanel) panel2025.getComponent(1)).getComponent(1);
        JLabel bal2025 = (JLabel) ((JPanel) panel2025.getComponent(1)).getComponent(2);

        assertEquals("Έσοδα: 5.500,00 €", rev2025.getText());
        assertEquals("Έξοδα: 2.500,00 €", exp2025.getText());
        assertTrue(bal2025.getText().contains("3.000,00 €"));
        assertEquals(new Color(0, 128, 0), bal2025.getForeground(), "Το ισοζύγιο είναι θετικό, άρα πράσινο");
    }
}

