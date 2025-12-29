 package gr.aueb.dmst.detFiscal;

import javax.swing.*;
import java.awt.*;

public class Dropdowns {
    public static void main(String[] args) {
        
         // Χώρες dropdown
        String[] countries = { "Επιλέξτε Χώρα", "Ελλάδα", "Γαλλία", "Γερμανία", "Ιταλία", "Ισπανία", "Κύπρος" };
        JComboBox<String> countryBox = new JComboBox<>(countries);

        gbc.gridy = 2; // Γραμμή 1
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
    }
}