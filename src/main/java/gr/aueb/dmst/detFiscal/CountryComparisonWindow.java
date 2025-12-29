package gr.aueb.dmst.detFiscal;

import javax.swing.*;

public class CountryComparisonWindow extends JFrame {

    public CountryComparisonWindow(FederalBudget fedBudget) {
        // Ρυθμίσεις παραθύρου
        super("Compare Data | Countries");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Κλείνει μόνο αυτό το παράθυρο, όχι όλη την εφαρμογή

        // Περιεχόμενο (Placeholder)
        JPanel panel = new JPanel();
        panel.add(new JLabel("___"));

        this.add(panel);
    }
}