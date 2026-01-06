package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BudgetDisplayWindowFullTest {

    private FederalBudget mockBudget;
    private BudgetSummary mockSummary;
    private BudgetDetails mockDetails;

    private Revenue revenue1, revenue2;
    private Expenditure expenditure1, expenditure2;
    private Ministry ministry1;

    @BeforeEach
    void setUp() {
        // Εξαναγκάζουμε US locale για consistent number formatting
        Locale.setDefault(Locale.US);

        // Δημιουργία mock αντικειμένων
        mockBudget = mock(FederalBudget.class);
        mockSummary = mock(BudgetSummary.class);
        mockDetails = mock(BudgetDetails.class);

        // Dummy revenues και expenditures
        revenue1 = new Revenue();
        revenue1.setName("Φόρος Εισοδήματος");
        revenue1.setAmount(3000.0);

        revenue2 = new Revenue();
        revenue2.setName("ΦΠΑ");
        revenue2.setAmount(2000.0);

        expenditure1 = new Expenditure();
        expenditure1.setName("Μισθοί");
        expenditure1.setAmount(1500.0);

        expenditure2 = new Expenditure();
        expenditure2.setName("Υποδομές");
        expenditure2.setAmount(2500.0);

        ministry1 = new Ministry();
        ministry1.setCode("Υ01");
        ministry1.setName("Υπουργείο Παιδείας");
        ministry1.setRegularBudget(2000.0);
        ministry1.setPublicInvestments(1000.0);
        ministry1.setTotal(3000.0);

        // Ρυθμίσεις mock summary
        when(mockBudget.getSummary()).thenReturn(mockSummary);
        when(mockBudget.getDetails()).thenReturn(mockDetails);
        when(mockSummary.getRevenues()).thenReturn(List.of(revenue1, revenue2));
        when(mockSummary.getExpenditures()).thenReturn(List.of(expenditure1, expenditure2));
        when(mockSummary.getMinistries()).thenReturn(List.of(ministry1));
        when(mockSummary.calculateTotalRevenues()).thenReturn(5000.0);
        when(mockSummary.calculateTotalExpenditures()).thenReturn(4000.0);
        when(mockSummary.calculateTotalRevenues2024()).thenReturn(4500.0);
        when(mockSummary.calculateTotalExpenditures2024()).thenReturn(3500.0);
        when(mockBudget.calculateTotalBudget()).thenReturn(1000.0);
        when(mockDetails.characterizeTotal()).thenReturn("Πλεόνασμα");
    }

    @Test
    void testFullWindowDisplay() {
        // Δημιουργία παραθύρου με mock budget
        BudgetDisplayWindow window = new BudgetDisplayWindow(mockBudget);

        // Πάμε στον tabbedPane
        JTabbedPane tabbedPane = (JTabbedPane) window.getContentPane().getComponent(1);

        // --- Έλεγχος Έσοδα ---
        JPanel revenuesPanel = (JPanel) tabbedPane.getComponentAt(0);
        JScrollPane revScroll = (JScrollPane) revenuesPanel.getComponent(0);
        JTable revTable = (JTable) revScroll.getViewport().getView();
        DefaultTableModel revModel = (DefaultTableModel) revTable.getModel();

        assertEquals(2, revModel.getRowCount());
        assertEquals("Φόρος Εισοδήματος", revModel.getValueAt(0, 1));
        assertEquals("3,000.00", revModel.getValueAt(0, 2));
        assertEquals("ΦΠΑ", revModel.getValueAt(1, 1));
        assertEquals("2,000.00", revModel.getValueAt(1, 2));

        // --- Έλεγχος Έξοδα ---
        JPanel expendituresPanel = (JPanel) tabbedPane.getComponentAt(1);
        JScrollPane expScroll = (JScrollPane) expendituresPanel.getComponent(0);
        JTable expTable = (JTable) expScroll.getViewport().getView();
        DefaultTableModel expModel = (DefaultTableModel) expTable.getModel();

        assertEquals(2, expModel.getRowCount());
        assertEquals("Μισθοί", expModel.getValueAt(0, 1));
        assertEquals("1,500.00", expModel.getValueAt(0, 2));
        assertEquals("Υποδομές", expModel.getValueAt(1, 1));
        assertEquals("2,500.00", expModel.getValueAt(1, 2));

        // --- Έλεγχος Υπουργείων ---
        JPanel ministriesPanel = (JPanel) tabbedPane.getComponentAt(2);
        JScrollPane minScroll = (JScrollPane) ministriesPanel.getComponent(0);
        JTable minTable = (JTable) minScroll.getViewport().getView();
        DefaultTableModel minModel = (DefaultTableModel) minTable.getModel();

        assertEquals(1, minModel.getRowCount());
        assertEquals("Υ01", minModel.getValueAt(0, 1));
        assertEquals("Υπουργείο Παιδείας", minModel.getValueAt(0, 2));
        assertEquals("2,000.00", minModel.getValueAt(0, 3));
        assertEquals("1,000.00", minModel.getValueAt(0, 4));
        assertEquals("3,000.00", minModel.getValueAt(0, 5));

        // --- Έλεγχος Footer ---
        JPanel footerPanel = (JPanel) window.getContentPane().getComponent(2);
        JLabel revenuesLabel = (JLabel) footerPanel.getComponent(0);
        JLabel expendituresLabel = (JLabel) footerPanel.getComponent(1);
        JLabel balanceLabel = (JLabel) footerPanel.getComponent(2);
        JLabel statusLabel = (JLabel) footerPanel.getComponent(3);

        assertTrue(revenuesLabel.getText().contains("5,000.00 €"));
        assertTrue(expendituresLabel.getText().contains("4,000.00 €"));
        assertTrue(balanceLabel.getText().contains("1,000.00 €"));
        assertTrue(statusLabel.getText().contains("Πλεόνασμα"));
    }
}

