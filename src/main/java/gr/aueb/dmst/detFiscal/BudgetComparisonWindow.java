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
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(7, 25, 82));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel headerLabel = new JLabel("Σύγκριση Προϋπολογισμού 2024 vs 2025");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        String[] columnNames = { "Κατηγορία", "2024 (€)", "2025 (€)", "Διαφορά (€)", "Μεταβολή (%)" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(7, 25, 82));
        table.getTableHeader().setForeground(Color.WHITE);
        add(mainPanel);
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

    /**
     * Δημιουργεί ένα panel με σύνοψη για ένα έτος
     */
    private JPanel createYearSummaryPanel(String year, double revenues, double expenditures, double balance) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(7, 25, 82), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLabel yearLabel = new JLabel("Έτος " + year, SwingConstants.CENTER);
        yearLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        yearLabel.setForeground(new Color(7, 25, 82));
        panel.add(yearLabel, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        dataPanel.setBackground(Color.WHITE);

        JLabel revLabel = new JLabel(String.format("Έσοδα: %,.2f €", revenues));
        revLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dataPanel.add(revLabel);

        JLabel expLabel = new JLabel(String.format("Έξοδα: %,.2f €", expenditures));
        expLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dataPanel.add(expLabel);

        String balanceStatus = balance >= 0 ? "Πλεόνασμα" : "Έλλειμμα";
        Color balanceColor = balance >= 0 ? new Color(0, 128, 0) : new Color(200, 0, 0);
        JLabel balLabel = new JLabel(String.format("Ισοζύγιο: %,.2f € (%s)", balance, balanceStatus));
        balLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        balLabel.setForeground(balanceColor);
        dataPanel.add(balLabel);

        panel.add(dataPanel, BorderLayout.CENTER);

        return panel;
    }

}