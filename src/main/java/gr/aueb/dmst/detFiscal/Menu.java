package gr.aueb.dmst.detfiscal;

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
        String pathMain = "src/main/resources/data/budget_2025.json";
        String path2024 = "src/main/resources/data/budget_2024.json";

        fedBudget.initializeData(pathMain, path2024);

        // panel gia koympia
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));
        // background xroma gia buttonPanel = diafano
        buttonPanel.setOpaque(false);

        // Δημιουργία κουμπιών
        JButton btnYear = new JButton("Compare data | years");
        JButton btnCountry = new JButton("Compare data | Countries");
        JButton btnData = new JButton("Display Data");
        JButton btnSearch = new JButton("Search Account");
        JButton btnSummary = new JButton("Display Summary");
        JButton btnAlter = new JButton("Alter Data");

        styleButton(btnYear);
        styleButton(btnCountry);
        styleButton(btnData);
        styleButton(btnSearch);
        styleButton(btnSummary);
        styleButton(btnAlter);

        // ΛΕΙΤΟΥΡΓΙΚΟΤΗΤΑ
        btnYear.addActionListener(e -> {
            JOptionPane.showMessageDialog(jf,
                    "Σύγκριση δεδομένων " + fedBudget.getYear() + " με προηγούμενο έτος.\n" +
                            "(Η λειτουργία απαιτεί υλοποίηση της μεθόδου getRevenues2024() στο Summary)",
                    "Comparison", JOptionPane.INFORMATION_MESSAGE);
        });

        btnCountry.addActionListener(e -> {
            JOptionPane.showMessageDialog(jf, "Μετάβαση στη Σύγκριση Χωρών");
        });

        btnData.addActionListener(e -> {
            // jf.setVisible(false);
            // fedBudget.showBudgetOverview();
            // new BudgetDetails();
        });

        btnSearch.addActionListener(e -> {
            JOptionPane.showMessageDialog(jf, "4ο κουμπί");
        });

        btnSummary.addActionListener(e -> {
            JOptionPane.showMessageDialog(jf, "5ο κουμπί");

            // BugdetSummary
        });

        btnAlter.addActionListener(e -> {
            JOptionPane.showMessageDialog(jf, "6ο κουμπί");
            // kainoyrgia elpidas
        });

        // Προσθήκη των κουμπιών
        buttonPanel.add(btnYear);
        buttonPanel.add(btnCountry);
        buttonPanel.add(btnData);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnSummary);
        buttonPanel.add(btnAlter);

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