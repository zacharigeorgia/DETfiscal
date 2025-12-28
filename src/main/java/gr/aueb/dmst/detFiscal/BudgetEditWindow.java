
package gr.aueb.dmst.detFiscal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BudgetEditWindow extends JFrame {

    private FederalBudget budget;
    private ChangeLog changeLog;
    private Scenario scenarioAnalyzer;

    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> typeComboBox;
    private JPanel footerPanel;

    private JButton editButton;
    private JButton viewLogButton;

    private List<? extends Account> currentAccounts;
    private List<Ministry> currentMinistries;
    private String currentType;
    private boolean isMinistryMode; // Flag για να ξέρουμε αν είμαστε σε λειτουργία υπουργείων

    public BudgetEditWindow(FederalBudget budget, ChangeLog changeLog) {
        this.budget = budget;
        this.changeLog = changeLog;
        this.scenarioAnalyzer = new Scenario();
        initializeWindow();
        createUI();
        loadAccounts("Έσοδα"); // Προεπιλογή
    }

    private void initializeWindow() {
        setTitle("Επεξεργασία Προϋπολογισμού - DETfiscal");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // ui
    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // Header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(7, 25, 82));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Επεξεργασία Προϋπολογισμού");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel typeLabel = new JLabel("Τύπος:");
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        controlPanel.add(typeLabel);

        typeComboBox = new JComboBox<>(new String[] { "Έσοδα", "Έξοδα", "Υπουργεία" });
        typeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        typeComboBox.addActionListener(e -> {
            String selected = (String) typeComboBox.getSelectedItem();
            if ("Υπουργεία".equals(selected)) {
                loadMinistries();
            } else {
                loadAccounts(selected);
            }
        });
        controlPanel.add(typeComboBox);

        editButton = new JButton("Επεξεργασία Επιλεγμένου");
        styleButton(editButton);
        editButton.addActionListener(e -> editSelectedRow());
        controlPanel.add(editButton);

        viewLogButton = new JButton("Προβολή Log Αλλαγών");
        styleButton(viewLogButton);
        viewLogButton.addActionListener(e -> showChangeLog());
        controlPanel.add(viewLogButton);

        mainPanel.add(controlPanel, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = { "#", "Όνομα", "Τρέχον Ποσό (€)" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(7, 25, 82));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Footer
        footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(7, 25, 82));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        updateFooter(); // Αρχική ενημέρωση
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Φορτώνει τους λογαριασμούς στον πίνακα

    private void loadAccounts(String type) {
        isMinistryMode = false;
        currentType = type;
        tableModel.setColumnIdentifiers(new String[] { "#", "Όνομα Λογαριασμού", "Ποσό (€)" });
        tableModel.setRowCount(0); // Καθαρίζουμε τον πίνακα

        if ("Έσοδα".equals(type)) {
            currentAccounts = budget.getSummary().getRevenues();
        } else {
            currentAccounts = budget.getSummary().getExpenditures();
        }

        for (int i = 0; i < currentAccounts.size(); i++) {
            Account account = currentAccounts.get(i);
            Object[] row = {
                    i + 1,
                    account.getName(),
                    String.format("%,.2f", account.getAmount())
            };
            tableModel.addRow(row);
        }
    }

    // Φορτώνει τα υπουργεία στον πίνακα

    private void loadMinistries() {
        isMinistryMode = true;
        currentType = "Υπουργεία";
        tableModel.setColumnIdentifiers(new String[] { "#", "Κωδικός", "Όνομα", "Σύνολο (€)" });
        tableModel.setRowCount(0);

        currentMinistries = budget.getSummary().getMinistries();

        for (int i = 0; i < currentMinistries.size(); i++) {
            Ministry ministry = currentMinistries.get(i);
            Object[] row = {
                    i + 1,
                    ministry.getCode(),
                    ministry.getName(),
                    String.format("%,.2f", ministry.getRegularBudget()),
                    String.format("%,.2f", ministry.getPublicInvestments()),
                    String.format("%,.2f", ministry.getTotal())
            };
            tableModel.addRow(row);
        }
        updateFooter();
    }

    /**
     * Επεξεργάζεται τον επιλεγμένο λογαριασμό ή υπουργείο
     */
    private void editSelectedRow() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε μια γραμμή πρώτα!", "Προσοχή",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ανάλογα με το τι βλέπουμε, καλούμε την κατάλληλη μέθοδο
        if (isMinistryMode) {
            editMinistry(row);
        } else {
            editAccount(row);
        }
    }

    /**
     * Επεξεργάζεται έναν λογαριασμό (Έσοδο ή Έξοδο)
     */
    private void editAccount(int row) {
        Account acc = currentAccounts.get(row);
        String oldAmountStr = String.valueOf(acc.getAmount());

        // Ζητάμε τη νέα τιμή
        String input = JOptionPane.showInputDialog(this,
                "Επεξεργασία: " + acc.getName() + "\nΤρέχον Ποσό: " + String.format("%,.2f", acc.getAmount()),
                oldAmountStr);

        if (input != null && !input.trim().isEmpty()) {
            try {
                // Μετατροπή και Validation
                double newAmount = Double.parseDouble(input.replace(",", ".")); // Διορθώνει τυχόν κόμματα
                if (newAmount < 0)
                    throw new NumberFormatException();

                double oldAmount = acc.getAmount();

                // 1. ΕΝΗΜΕΡΩΣΗ ΔΕΔΟΜΕΝΩΝ
                acc.setAmount(newAmount);

                // 2. ΕΝΗΜΕΡΩΣΗ ΠΙΝΑΚΑ (Οπτικά)
                tableModel.setValueAt(String.format("%,.2f", newAmount), row, 2);

                // 3. ΚΑΤΑΓΡΑΦΗ ΣΤΟ LOG
                changeLog.addChange(acc.getName(), currentType, oldAmount, newAmount);

                // 4. ΕΝΗΜΕΡΩΣΗ FOOTER
                updateFooter();

                // 5. ΑΝΑΛΥΣΗ ΕΠΙΠΤΩΣΕΩΝ (Scenario)
                // Ελέγχουμε αν είναι έσοδο για την παράμετρο του Scenario
                boolean isRevenue = currentType.equals("Έσοδα");
                String analysis = scenarioAnalyzer.analyzeScenario(acc.getName(), oldAmount, newAmount, isRevenue,
                        budget.getDetails());

                JOptionPane.showMessageDialog(this, "Η αλλαγή αποθηκεύτηκε!\n\n" + analysis, "Επιτυχία",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Παρακαλώ δώστε έγκυρο θετικό αριθμό.", "Σφάλμα",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Επεξεργάζεται ένα υπουργείο
     */
    private void editMinistry(int row) {
        Ministry min = currentMinistries.get(row);
        // Για τα υπουργεία, ας υποθέσουμε ότι αλλάζουμε το Σύνολο για απλότητα
        // (ή θα μπορούσαμε να ζητάμε Regular/Investment χωριστά)

        String input = JOptionPane.showInputDialog(this,
                "Επεξεργασία: " + min.getName() + "\nΤρέχον Σύνολο: " + String.format("%,.2f", min.getTotal()),
                String.valueOf(min.getTotal()));

        if (input != null) {
            try {
                double newTotal = Double.parseDouble(input.replace(",", "."));
                if (newTotal < 0)
                    throw new NumberFormatException();

                double oldTotal = min.getTotal();

                // Ενημέρωση (εδώ απλοϊκά βάζουμε όλο το ποσό στο Regular Budget για να
                // ταιριάζει το σύνολο)
                min.setRegularBudget(newTotal);
                min.setPublicInvestments(0); // Μηδενίζουμε το άλλο για να βγει το σύνολο σωστό (ή φτιάξε πιο σύνθετο
                                             // dialog)
                min.setTotal(newTotal);

                // Ενημέρωση Πίνακα
                tableModel.setValueAt(String.format("%,.2f", newTotal), row, 3);

                // Καταγραφή
                changeLog.addChange(min.getName(), "Υπουργείο", oldTotal, newTotal);

                updateFooter();

                JOptionPane.showMessageDialog(this, "Το προϋπολογισμός του Υπουργείου ενημερώθηκε.", "Επιτυχία",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Μη έγκυρος αριθμός.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateFooter() {
        footerPanel.removeAll();

        // Υπολογισμός συνόλων από το Singleton
        double totalRev = budget.getSummary().calculateTotalRevenues();
        double totalExp = budget.getSummary().calculateTotalExpenditures();
        double balance = totalRev - totalExp;

        JLabel statsLabel = new JLabel(String.format(
                "<html><b>Σύνολα:</b> Έσοδα: <font color='green'>%,.2f €</font> | Έξοδα: <font color='red'>%,.2f €</font> | Ισοζύγιο: <b>%,.2f €</b></html>",
                totalRev, totalExp, balance));

        statsLabel.setForeground(Color.WHITE);
        statsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        footerPanel.add(statsLabel);

        footerPanel.revalidate();
        footerPanel.repaint();
    }

    private void showChangeLog() {
        // Δημιουργία διαλόγου (popup παράθυρο)
        JDialog logDialog = new JDialog(this, "Ιστορικό Αλλαγών", true);
        logDialog.setSize(600, 400);
        logDialog.setLocationRelativeTo(this);

        // Περιοχή κειμένου για να δείξουμε το log
        JTextArea logArea = new JTextArea(changeLog.getFormattedLog());
        logArea.setEditable(false); // Μόνο για ανάγνωση
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Γραμματοσειρά "μηχανής"
        logArea.setMargin(new Insets(10, 10, 10, 10));

        // Προσθήκη scrollbar αν είναι μεγάλο το κείμενο
        JScrollPane scrollPane = new JScrollPane(logArea);
        logDialog.add(scrollPane);

        // Εμφάνιση
        logDialog.setVisible(true);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(7, 25, 82));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
    }
}
