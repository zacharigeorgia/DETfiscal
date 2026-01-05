package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BudgetEditWindowTest {

    private FederalBudget mockBudget;
    private BudgetSummary realSummary;
    private ChangeLog changeLog;
    private BudgetEditWindow window;

    @BeforeEach
    public void setUp() throws Exception {
        // Για σταθερό formatting αριθμών (όπως στο UI σου)
        Locale.setDefault(Locale.US);

        // Mock FederalBudget αλλά με πραγματικό BudgetSummary που δημιουργείς (χωρίς αλλαγές κλάσεων)
        mockBudget = mock(FederalBudget.class);
        realSummary = new BudgetSummary();
        changeLog = new ChangeLog();

        // Δημιούργησε αντικείμενα με default constructors + setters όπως τα έχεις
        Revenue r1 = new Revenue(); r1.setName("Φόροι"); r1.setAmount(5000.0);
        Expenditure e1 = new Expenditure(); e1.setName("Παροχές σε εργαζομένους"); e1.setAmount(3000.0);
        Ministry m1 = new Ministry(); m1.setCode("Y01"); m1.setName("Υπουργείο Υγείας");
        m1.setRegularBudget(2000.0); m1.setPublicInvestments(500.0); m1.setTotal(2500.0);

        realSummary.addRevenue(r1);
        realSummary.addExpenditure(e1);
        realSummary.addMinistry(m1);

        // Mockito wiring
        when(mockBudget.getSummary()).thenReturn(realSummary);
        // getDetails may be used elsewhere; provide a simple BudgetDetails that uses realSummary
        BudgetDetails details = new BudgetDetails(new MacroData(), realSummary);
        when(mockBudget.getDetails()).thenReturn(details);

        // Δημιούργησε το παράθυρο (ο constructor κάνει initialize & loadAccounts("Έσοδα"))
        window = new BudgetEditWindow(mockBudget, changeLog);
    }

    // --- βοηθητικές μέθοδοι reflection ---
    private JTable getPrivateTable(BudgetEditWindow w) throws Exception {
        Field tableField = BudgetEditWindow.class.getDeclaredField("table");
        tableField.setAccessible(true);
        return (JTable) tableField.get(w);
    }

    private JPanel getPrivateFooter(BudgetEditWindow w) throws Exception {
        Field footerField = BudgetEditWindow.class.getDeclaredField("footerPanel");
        footerField.setAccessible(true);
        return (JPanel) footerField.get(w);
    }

    private void invokePrivateLoadMinistries(BudgetEditWindow w) throws Exception {
        Method m = BudgetEditWindow.class.getDeclaredMethod("loadMinistries");
        m.setAccessible(true);
        m.invoke(w);
    }

    private void invokePrivateUpdateFooter(BudgetEditWindow w) throws Exception {
        Method m = BudgetEditWindow.class.getDeclaredMethod("updateFooter");
        m.setAccessible(true);
        m.invoke(w);
    }

    @Test
    public void testInitialLoad_RevenuesAndFooter() throws Exception {
        JTable table = getPrivateTable(window);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Το constructor του BudgetEditWindow καλεί loadAccounts("Έσοδα"), άρα ο πίνακας πρέπει να έχει 1 έσοδο
        assertEquals(1, model.getRowCount(), "Πρέπει να υπάρχει 1 έσοδο στον πίνακα");
        assertEquals("Φόροι", model.getValueAt(0, 1));
        assertEquals("5,000.00", model.getValueAt(0, 2)); // Locale.US format

        // Footer: είναι private JPanel με ένα JLabel που περιέχει τα συνολικά
        JPanel footer = getPrivateFooter(window);
        assertNotNull(footer);
        assertTrue(footer.getComponentCount() > 0);
        JLabel statsLabel = (JLabel) footer.getComponent(0);
        String txt = statsLabel.getText();
        assertTrue(txt.contains("5,000.00"), "Footer πρέπει να εμφανίζει συνολικά έσοδα");
        assertTrue(txt.contains("3,000.00"), "Footer πρέπει να εμφανίζει συνολικά έξοδα");
        // balance = 5000 - 3000 = 2000
        assertTrue(txt.contains("2,000.00"), "Footer πρέπει να εμφανίζει ισοζύγιο");
    }

    @Test
    public void testLoadMinistries() throws Exception {
        // Κλήση private loadMinistries
        invokePrivateLoadMinistries(window);

        JTable table = getPrivateTable(window);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Στον πίνακα υπουργείων προστέθηκε 1 γραμμή (το m1)
        assertEquals(1, model.getRowCount());
        assertEquals("Y01", model.getValueAt(0, 1));
        assertEquals("Υπουργείο Υγείας", model.getValueAt(0, 2));
        // regularBudget = 2000 -> formatted "2,000.00"
        assertEquals("2,000.00", model.getValueAt(0, 3));
        assertEquals("500.00", model.getValueAt(0, 4));
        assertEquals("2,500.00", model.getValueAt(0, 5));
    }

    @Test
    public void testSimulateEditAccount_and_footer_and_log() throws Exception {
        // Πάρε table & summary
        JTable table = getPrivateTable(window);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Έλεγχος αρχικής τιμής
        assertEquals("5,000.00", model.getValueAt(0, 2));

        // Αλλάζουμε την underlying Revenue αντικείμενο στο realSummary
        Revenue rev = realSummary.getRevenues().get(0);
        double oldVal = rev.getAmount();
        double newVal = oldVal + 1000.0; // π.χ. 6000

        // Κάνουμε set στο Revenue (όπως θα έκανε editAccount)
        rev.setAmount(newVal);

        // Ενημέρωση του table model όπως κάνει το GUI μετά το setAmount
        model.setValueAt(String.format("%,.2f", newVal), 0, 2);

        // Καλέστε το private updateFooter ώστε να ανανεωθούν τα totals στο footer
        invokePrivateUpdateFooter(window);

        // Εγγραφή στο changeLog (το GUI το κάνει μέσα σε editAccount) — εδώ προσομοιώνουμε την κλήση
        changeLog.addChange(rev.getName(), "Έσοδα", oldVal, newVal);

        // Έλεγχοι
        assertEquals("6,000.00", model.getValueAt(0, 2));
        JPanel footer = getPrivateFooter(window);
        JLabel statsLabel = (JLabel) footer.getComponent(0);
        String txt = statsLabel.getText();
        assertTrue(txt.contains("6,000.00"), "Footer πρέπει να εμφανίζει ανανεωμένα συνολικά έσοδα (6,000.00)");

        // Check changeLog contains entry
        String log = changeLog.getFormattedLog();
        assertTrue(log.contains("Φόροι"), "ChangeLog πρέπει να περιέχει το όνομα");
        assertTrue(log.contains(String.format("%.0f", oldVal)), "ChangeLog πρέπει να περιέχει παλιά τιμή");
        assertTrue(log.contains(String.format("%.0f", newVal)), "ChangeLog πρέπει να περιέχει νέα τιμή");
    }
}

