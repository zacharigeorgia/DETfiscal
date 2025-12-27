package gr.aueb.dmst.detFiscal;

import javax.swing.*;
import java.awt.*;

public class Menu {
    public static void main(String[] args) {
        // δημιουργία frame
        JFrame jf = new JFrame("Home page");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 600);
        jf.setLocationRelativeTo(null);

        // panel = kentriko parathiro
        JPanel mainPanel = new JPanel();
        // στοίχηση και layout
        mainPanel.setBackground(new Color(7, 25, 82));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; // Στήλη 0
        gbc.fill = GridBagConstraints.NONE; // Να γεμίζουν τον οριζόντιο χώρο

        // titlos
        JLabel titleLabel = new JLabel("DETfiscal", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);

        gbc.gridy = 0; // Γραμμή 0
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(titleLabel, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 10.0;

        FederalBudget fedBudget = FederalBudget.getInstance();
        /*
         * @param jsonPath
         */
        String pathMain = "C:\\Users\\saraf\\Desktop\\spinel\\DETfiscal\\src\\main\\resources\\data\\sample_budget_2025.json";
        String path2024 = "C:\\Users\\saraf\\Desktop\\spinel\\DETfiscal\\src\\main\\resources\\data\\sample_budget_2024.json";

        fedBudget.initializeData(pathMain, path2024);

        // Δημιουργία ChangeLog για καταγραφή αλλαγών στη συνεδρία
        ChangeLog changeLog = new ChangeLog();

        // panel gia koympia
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 20, 20));
        // background xroma gia buttonPanel = diafano
        buttonPanel.setOpaque(false);

        // Δημιουργία κουμπιών
        JButton btnYear = new JButton("Compare data | years");
        JButton btnCountry = new JButton("Compare data | Countries");
        JButton btnData = new JButton("Display Data");
        JButton btnSummary = new JButton("Display Summary");
        JButton btnAlter = new JButton("Alter Data");
        JButton btnChangeLog = new JButton("View Change Log");

        styleButton(btnYear);
        styleButton(btnCountry);
        styleButton(btnData);
        styleButton(btnSummary);
        styleButton(btnAlter);
        styleButton(btnChangeLog);

        // ΛΕΙΤΟΥΡΓΙΚΟΤΗΤΑ
        btnYear.addActionListener(e -> {
            BudgetComparisonWindow comparisonWindow = new BudgetComparisonWindow(fedBudget);
            comparisonWindow.setVisible(true);
        });

        btnCountry.addActionListener(e -> {
            CountryComparisonWindow countryWindow = new CountryComparisonWindow(fedBudget);
            countryWindow.setVisible(true);
        });

        btnData.addActionListener(e -> {
            BudgetDisplayWindow displayWindow = new BudgetDisplayWindow(fedBudget);
            displayWindow.setVisible(true);
        });

        btnSummary.addActionListener(e -> {
            double inflation = fedBudget.getDetails().getInflation();
            double balance = fedBudget.calculateTotalBudget();
            String status = fedBudget.getDetails().characterizeTotal(); // Π.χ. Surplus/Deficit

            String message = "Economic Indicators:\n" +
                    "Inflation Rate: " + inflation + "%\n" +
                    "Budget Status: " + status;

            JOptionPane.showMessageDialog(jf, message, "Summary Details", JOptionPane.INFORMATION_MESSAGE);
        });

        btnAlter.addActionListener(e -> {
            BudgetEditWindow editWindow = new BudgetEditWindow(fedBudget, changeLog);
            editWindow.setVisible(true);
        });

        btnChangeLog.addActionListener(e -> {
            // Εμφάνιση του log αλλαγών
            JDialog logDialog = new JDialog(jf, "Ιστορικό Αλλαγών", true);
            logDialog.setSize(800, 500);
            logDialog.setLocationRelativeTo(jf);

            JPanel logPanel = new JPanel(new BorderLayout());
            logPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            JTextArea logTextArea = new JTextArea(changeLog.getFormattedLog());
            logTextArea.setFont(new Font("Courier New", Font.PLAIN, 11));
            logTextArea.setEditable(false);
            logTextArea.setBackground(Color.WHITE);

            JScrollPane scrollPane = new JScrollPane(logTextArea);
            logPanel.add(scrollPane, BorderLayout.CENTER);

            JButton closeButton = new JButton("Κλείσιμο");
            styleButton(closeButton);
            closeButton.addActionListener(ev -> logDialog.dispose());

            JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel2.add(closeButton);
            logPanel.add(buttonPanel2, BorderLayout.SOUTH);

            logDialog.add(logPanel);
            logDialog.setVisible(true);
        });

        // Προσθήκη των κουμπιών
        buttonPanel.add(btnYear);
        buttonPanel.add(btnCountry);
        buttonPanel.add(btnData);
        buttonPanel.add(btnSummary);
        buttonPanel.add(btnAlter);
        buttonPanel.add(btnChangeLog);

        // buttonPanel στο mainPanel
        gbc.gridy = 1; // Μπαίνει 1 grid κάτω από τον τίτλο
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(50, 10, 10, 10);

        mainPanel.add(buttonPanel, gbc);

        // Filler για να σπρώξει τα πάντα πάνω
        gbc.gridy = 2;
        gbc.weighty = 1.0; // Όλο το κάθετο βάρος εδώ
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JLabel(), gbc); // άδειο

        // Φτιάχνουμε ένα "αόρατο" στοιχείο στην επόμενη γραμμή
        gbc.gridy = 1;
        // Αυτό λέει στο Layout: "Πιάσε όλο τον ελεύθερο χώρο που περισσεύει κάτω".
        gbc.weighty = 1.0;

        // Προσθέτουμε ένα άδειο label απλά για να πιάσει τον χώρο
        mainPanel.add(new JLabel(), gbc);

        jf.add(mainPanel);
        jf.setVisible(true);
    }

    // Μεθοδος για Ομορφα κουμπια:

    private static void styleButton(JButton btn) {
        // Χρώματα
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(7, 25, 82)); // xrvma grammata
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // Αφαίρεση του περιγράμματος εστίασης (το κουτάκι που βγαίνει όταν πατάς κλικ)
        btn.setFocusPainted(false);
        // Αφαίρεση κλασικού περιγράμματος κουμπιού για πιο flat look
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        // Αλλαγή κέρσορα σε χεράκι όταν πάει από πάνω
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    };
}