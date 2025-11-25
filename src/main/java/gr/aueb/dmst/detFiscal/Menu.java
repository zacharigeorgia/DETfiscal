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

        // στοίχηση και layout
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(7, 25, 82));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; // Στήλη 0
        gbc.fill = GridBagConstraints.HORIZONTAL; // Να γεμίζουν τον οριζόντιο χώρο

        // titlos
        JLabel titleLabel = new JLabel("DETfiscal", SwingConstants.NORTH_EAST);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);

        gbc.gridy = 0; // Γραμμή 0
        mainPanel.add(titleLabel, gbc);

        // Χώρες dropdown
        String[] countries = { "Επιλέξτε Χώρα", "Ελλάδα", "Γαλλία", "Γερμανία", "Ιταλία", "Ισπανία", "Κύπρος" };
        JComboBox<String> countryBox = new JComboBox<>(countries);

        gbc.gridy = 1; // Γραμμή 1
        mainPanel.add(countryBox, gbc);

        // Έτη dropdown
        // Δημιουργία λίστας ετών (π.χ. από 2010 έως 2025)
        String[] years = new String[17];
        years[0] = "Επιλέξτε Έτος";
        int startYear = 2010;
        for (int i = 1; i < years.length; i++) {
            years[i] = String.valueOf(startYear + i - 1);
        }
        JComboBox<String> yearBox = new JComboBox<>(years);

        gbc.gridy = 2; // Γραμμή 2
        mainPanel.add(yearBox, gbc);

        jf.add(mainPanel);
        jf.setVisible(true);
    }
}