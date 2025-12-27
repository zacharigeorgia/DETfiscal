package gr.aueb.dmst.detFiscal;

import javax.swing.*;
import java.awt.*;

public class BudgetEditWindow extends JFrame {

    public BudgetEditWindow(FederalBudget fedBudget, ChangeLog changeLog) {

        // Βασικές ρυθμίσεις παραθύρου
        setTitle("Επεξεργασία Δεδομένων");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("...", SwingConstants.CENTER));
    }
}