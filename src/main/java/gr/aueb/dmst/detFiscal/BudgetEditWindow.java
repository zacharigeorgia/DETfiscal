
package gr.aueb.dmst.detFiscal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BudgetEditWindow extends JFrame {

    private FederalBudget budget;
    private ChangeLog changeLog;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> typeComboBox;
    private JButton editButton;
    private JButton viewLogButton;
    private List<? extends Account> currentAccounts;
    private List<Ministry> currentMinistries;
    private String currentType;
    private JPanel footerPanel; // Αναφορά στο footer panel για ενημέρωση
    private boolean isMinistryMode; // Flag για να ξέρουμε αν είμαστε σε λειτουργία υπουργείων

    public BudgetEditWindow(FederalBudget budget, ChangeLog changeLog) {
        this.budget = budget;

        this.budget = FederalBudget.getInstance();
        this.changeLog = changeLog;
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
        editButton.addActionListener(e -> editSelectedAccount());
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
        JPanel footerPanel = createFooterPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Φορτώνει τους λογαριασμούς στον πίνακα
     * 
     * @param type Ο τύπος ("Έσοδα" ή "Έξοδα")
     */
    private void loadAccounts(String type) {
        isMinistryMode = false;
        currentType = type;
        tableModel.setRowCount(0); // Καθαρίζουμε τον πίνακα

        // Αλλάζουμε τις στήλες για λογαριασμούς
        String[] columnNames = { "#", "Όνομα", "Τρέχον Ποσό (€)" };
        tableModel.setColumnIdentifiers(columnNames);

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

    /**
     * Φορτώνει τα υπουργεία στον πίνακα
     */
    private void loadMinistries() {
        isMinistryMode = true;
        currentType = "Υπουργεία";
        tableModel.setRowCount(0); // Καθαρίζουμε τον πίνακα

        // Αλλάζουμε τις στήλες για υπουργεία
        String[] columnNames = { "#", "Κωδικός", "Όνομα", "Κανονικός Προϋπολογισμός (€)", "Δημόσιες Επενδύσεις (€)",
                "Σύνολο (€)" };
        tableModel.setColumnIdentifiers(columnNames);

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
    }

    /**
     * Επεξεργάζεται τον επιλεγμένο λογαριασμό ή υπουργείο
     */
    private void editSelectedAccount() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Παρακαλώ επιλέξτε ένα στοιχείο από τον πίνακα.",
                    "Ειδοποίηση",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isMinistryMode) {
            editMinistry(selectedRow);
        } else {
            editAccount(selectedRow);
        }
    }

    /**
     * Επεξεργάζεται έναν λογαριασμό (Έσοδο ή Έξοδο)
     */
    private void editAccount(int selectedRow) {
        Account account = currentAccounts.get(selectedRow);
        String accountName = account.getName();
        double currentAmount = account.getAmount();

        // Δημιουργία dialog για επεξεργασία
        JDialog editDialog = new JDialog(this, "Επεξεργασία Λογαριασμού", true);
        editDialog.setSize(500, 250);
        editDialog.setLocationRelativeTo(this);

        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Πληροφορίες λογαριασμού
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JLabel nameLabel = new JLabel("Όνομα:");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        infoPanel.add(nameLabel);

        JLabel nameValue = new JLabel(accountName);
        nameValue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoPanel.add(nameValue);

        JLabel typeLabel = new JLabel("Τύπος:");
        typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        infoPanel.add(typeLabel);

        JLabel typeValue = new JLabel(currentType);
        typeValue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoPanel.add(typeValue);

        JLabel currentLabel = new JLabel("Τρέχον Ποσό:");
        currentLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        infoPanel.add(currentLabel);

        JLabel currentValue = new JLabel(String.format("%,.2f €", currentAmount));
        currentValue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoPanel.add(currentValue);

        dialogPanel.add(infoPanel, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel newAmountLabel = new JLabel("Νέο Ποσό (€):");
        newAmountLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        inputPanel.add(newAmountLabel);

        JTextField amountField = new JTextField(15);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        amountField.setText(String.format("%.2f", currentAmount));
        inputPanel.add(amountField);

        dialogPanel.add(inputPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Αποθήκευση");
        styleButton(saveButton);
        saveButton.addActionListener(e -> {
            try {
                String amountText = amountField.getText().trim().replace(",", ".");
                double newAmount = Double.parseDouble(amountText);

                // Validation

                // Ενημέρωση του λογαριασμού
                double oldAmount = account.getAmount();
                account.setAmount(newAmount);

                // Καταγραφή αλλαγής
                changeLog.addChange(accountName, currentType, oldAmount, newAmount);

                // Ενημέρωση footer

                // Εμφάνιση μηνύματος επιτυχίας με impact analysis

                editDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editDialog,
                        "Παρακαλώ εισάγετε ένα έγκυρο αριθμό.",
                        "Σφάλμα",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(editDialog,
                        ex.getMessage(),
                        "Σφάλμα Επικύρωσης",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Ακύρωση");
        styleButton(cancelButton);
        cancelButton.addActionListener(e -> editDialog.dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

        editDialog.add(dialogPanel);
        editDialog.setVisible(true);
    }

    /**
     * Επεξεργάζεται ένα υπουργείο
     */
    private void editMinistry(int selectedRow) {
        Ministry ministry = currentMinistries.get(selectedRow);
        String ministryName = ministry.getName();
        String ministryCode = ministry.getCode();
        double currentRegular = ministry.getRegularBudget();
        double currentInvestments = ministry.getPublicInvestments();
        double currentTotal = ministry.getTotal();

        // Δημιουργία dialog για επεξεργασία
        JDialog editDialog = new JDialog(this, "Επεξεργασία Υπουργείου", true);
        editDialog.setSize(550, 350);
        editDialog.setLocationRelativeTo(this);

        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Πληροφορίες υπουργείου
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JLabel codeLabel = new JLabel("Κωδικός:");
        codeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        infoPanel.add(codeLabel);

        JLabel codeValue = new JLabel(ministryCode);
        codeValue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoPanel.add(codeValue);

        JLabel nameLabel = new JLabel("Όνομα:");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        infoPanel.add(nameLabel);

        JLabel nameValue = new JLabel(ministryName);
        nameValue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoPanel.add(nameValue);

        JLabel totalLabel = new JLabel("Τρέχον Σύνολο:");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        infoPanel.add(totalLabel);

        JLabel totalValue = new JLabel(String.format("%,.2f €", currentTotal));
        totalValue.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoPanel.add(totalValue);

        dialogPanel.add(infoPanel, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel regularLabel = new JLabel("Κανονικός Προϋπολογισμός (€):");
        regularLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        inputPanel.add(regularLabel);

        JTextField regularField = new JTextField(15);
        regularField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        regularField.setText(String.format("%.2f", currentRegular));
        inputPanel.add(regularField);

        JLabel investmentsLabel = new JLabel("Δημόσιες Επενδύσεις (€):");
        investmentsLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        inputPanel.add(investmentsLabel);

        JTextField investmentsField = new JTextField(15);
        investmentsField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        investmentsField.setText(String.format("%.2f", currentInvestments));
        inputPanel.add(investmentsField);

        dialogPanel.add(inputPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Αποθήκευση");
        styleButton(saveButton);
        saveButton.addActionListener(e -> {
            try {
                String regularText = regularField.getText().trim().replace(",", ".");
                String investmentsText = investmentsField.getText().trim().replace(",", ".");
                double newRegular = Double.parseDouble(regularText);
                double newInvestments = Double.parseDouble(investmentsText);

                // Validation
                if (newRegular < 0 || newInvestments < 0) {
                    throw new IllegalArgumentException("Τα ποσά δεν μπορούν να είναι αρνητικά!");
                }

                // Ενημέρωση του υπουργείου
                double oldTotal = ministry.getTotal();
                ministry.setRegularBudget(newRegular);
                ministry.setPublicInvestments(newInvestments);
                double newTotal = newRegular + newInvestments;
                ministry.setTotal(newTotal);

                // Καταγραφή αλλαγής (χρησιμοποιούμε το total για το changeLog)
                changeLog.addChange(ministryName, "Υπουργείο", oldTotal, newTotal);

                // Ενημέρωση πίνακα
                tableModel.setValueAt(String.format("%,.2f", newRegular), selectedRow, 3);
                tableModel.setValueAt(String.format("%,.2f", newInvestments), selectedRow, 4);
                tableModel.setValueAt(String.format("%,.2f", newTotal), selectedRow, 5);

                // Ενημέρωση footer

                // Μήνυμα ότι η ανάλυση επιπτώσεων δεν είναι διαθέσιμη ακόμα για υπουργεία
                String ministryMessage = "Η αλλαγή αποθηκεύτηκε επιτυχώς!\n\n" +
                        "Σημείωση: Η ανάλυση επιπτώσεων και η επιρροή στο δημόσιο χρέος\n" +
                        "για αλλαγές προϋπολογισμού υπουργείων δεν είναι διαθέσιμη ακόμα.\n" +
                        "Αυτή η λειτουργία είναι διαθέσιμη μόνο για αλλαγές εσόδων και εξόδων.";

                JOptionPane.showMessageDialog(editDialog,
                        ministryMessage,
                        "Επιτυχία",
                        JOptionPane.INFORMATION_MESSAGE);

                editDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editDialog,
                        "Παρακαλώ εισάγετε έγκυρους αριθμούς.",
                        "Σφάλμα",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(editDialog,
                        ex.getMessage(),
                        "Σφάλμα Επικύρωσης",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Ακύρωση");
        styleButton(cancelButton);
        cancelButton.addActionListener(ev -> editDialog.dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

        editDialog.add(dialogPanel);
        editDialog.setVisible(true);
    }

    /**
     * Εμφανίζει μήνυμα επιτυχίας με ανάλυση επιπτώσεων
     * 
     * @param parentDialog   Το parent dialog
     * @param impactAnalysis Η ανάλυση επιπτώσεων
     */
    private void showSuccessWithImpact(JDialog parentDialog, String impactAnalysis) {
        JDialog impactDialog = new JDialog(parentDialog, "Ανάλυση Επιπτώσεων", true);
        impactDialog.setSize(600, 400);
        impactDialog.setLocationRelativeTo(parentDialog);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header
        JLabel headerLabel = new JLabel("Η αλλαγή αποθηκεύτηκε επιτυχώς!");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        headerLabel.setForeground(new Color(0, 128, 0));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Impact Analysis
        JTextArea impactTextArea = new JTextArea(impactAnalysis);
        impactTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        impactTextArea.setEditable(false);
        impactTextArea.setBackground(Color.WHITE);
        impactTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JScrollPane scrollPane = new JScrollPane(impactTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Κατάλαβα");
        styleButton(closeButton);
        closeButton.addActionListener(e -> impactDialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        impactDialog.add(mainPanel);
        impactDialog.setVisible(true);
    }

    /**
     * Εμφανίζει το log των αλλαγών
     */
    private void showChangeLog() {
        JDialog logDialog = new JDialog(this, "Ιστορικό Αλλαγών", true);
        logDialog.setSize(800, 500);
        logDialog.setLocationRelativeTo(this);

        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextArea logTextArea = new JTextArea(changeLog.getFormattedLog());
        logTextArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        logTextArea.setEditable(false);
        logTextArea.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(logTextArea);
        logPanel.add(scrollPane, BorderLayout.CENTER);

        logDialog.add(logPanel);
        logDialog.setVisible(true);
    }

    // footer
    private JPanel createFooterPanel() {
        footerPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        footerPanel.setBackground(new Color(7, 25, 82));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        return footerPanel;
    }

    private JLabel createFooterLabel(String title, double value) {
        JLabel label = new JLabel("<html><div style='text-align: center;'>" +
                "<b>" + title + ":</b><br>" + String.format("%,.2f €", value) + "</div></html>");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private void styleButton(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(7, 25, 82));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
