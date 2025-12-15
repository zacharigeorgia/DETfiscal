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
        setTitle("Εμφάνιση Προϋπολογισμού - DETfiscal");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(7, 25, 82));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Προϋπολογισμός " + budget.getYear());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JPanel createTablePanel(String type, List<? extends Account> accounts, double total) {
        JPanel panel = new JPanel(new BorderLayout());

        // Δημιουργία πίνακα
        String[] columnNames = { "#", "Όνομα", "Ποσό (€)" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(7, 25, 82));
        table.getTableHeader().setForeground(Color.WHITE);

        // Προσθήκη δεδομένων
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            Object[] row = {
                    i + 1,
                    account.getName(),
                    String.format("%,.2f", account.getAmount())
            };
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Συνολικό ποσό στο κάτω μέρος του tab
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(Color.WHITE);
        JLabel totalLabel = new JLabel(String.format("Σύνολο %s: %,.2f €", type, total));
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalPanel.add(totalLabel);
        panel.add(totalPanel, BorderLayout.SOUTH);

        return panel;
    }
}