package gr.aueb.dmst.detFiscal;

import javax.swing.*;
import java.awt.*;

public class BudgetEditWindow extends JFrame {

    private final FederalBudget fedBudget;
    private final ChangeLog changeLog;

    private JTextField countryField;
    private JTextField yearField;
    private JButton saveButton;
    private JButton cancelButton;

    public BudgetEditWindow(FederalBudget fedBudget, ChangeLog changeLog) {

        this.fedBudget = FederalBudget.getInstance();
        this.changeLog = new ChangeLog();
        // Βασικές ρυθμίσεις παραθύρου
        setTitle("Επεξεργασία Δεδομένων");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(7, 25, 82));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Πεδίο Χώρας
        JLabel countryLabel = new JLabel("Όνομα Χώρας:");
        countryLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(countryLabel, gbc);

        countryField = new JTextField(fedBudget.getCountryName());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        mainPanel.add(countryField, gbc);

        // Πεδίο Έτους
        JLabel yearLabel = new JLabel("Έτος:");
        yearLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        mainPanel.add(yearLabel, gbc);

        yearField = new JTextField(String.valueOf(fedBudget.getYear()));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        mainPanel.add(yearField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        saveButton = new JButton("Αποθήκευση");
        saveButton.setBackground(Color.WHITE); // Προαιρετικό στυλ

        cancelButton = new JButton("Ακύρωση");
        cancelButton.setBackground(Color.WHITE); // Προαιρετικό στυλ

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Να πιάσει όλο το πλάτος
        mainPanel.add(buttonPanel, gbc);

        // Προσθήκη του panel στο παράθυρο
        add(mainPanel);

        cancelButton.addActionListener(e -> dispose());
        saveButton.addActionListener(e -> saveChanges());

    }

    // αποθήκευση αλλαγών λειτουργικό
    private void saveChanges() {
        String newCountry = countryField.getText().trim();
        String newYearStr = yearField.getText().trim();
        boolean changed = false;

        // Validation (Έλεγχος)
        if (newCountry.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Το όνομα δεν μπορεί να είναι κενό!");
            return;
        }

        try {
            int newYear = Integer.parseInt(newYearStr);

            // Έλεγχος αλλαγής χώρας
            if (!newCountry.equals(fedBudget.getCountryName())) {
                String old = fedBudget.getCountryName();
                fedBudget.setCountryName(newCountry);
                changeLog.log("Changed Country: " + old + " -> " + newCountry);
                changed = true;
            }

            // Έλεγχος αλλαγής έτους
            if (newYear != fedBudget.getYear()) {
                int oldYear = fedBudget.getYear();
                fedBudget.setYear(newYear);
                changeLog.log("Changed Year: " + oldYear + " -> " + newYear);
                changed = true;
            }

            if (changed) {
                JOptionPane.showMessageDialog(this, "Οι αλλαγές αποθηκεύτηκαν!");
                dispose(); // Κλείσιμο
            } else {
                JOptionPane.showMessageDialog(this, "Δεν βρέθηκαν αλλαγές.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Το έτος πρέπει να είναι αριθμός!");
        }
    }
}