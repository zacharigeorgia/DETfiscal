package gr.aueb.dmst.detFiscal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class CountryComparisonWindow extends JFrame {

        private FederalBudget budget;
        private JTable table;
        private DefaultTableModel tableModel;

        public CountryComparisonWindow(FederalBudget budget) {
                this.budget = budget;
                initializeWindow();
                createUI();
        }

        private void initializeWindow() {
                setTitle("Συγκριτικός Πίνακας Χωρών - DETfiscal");
                setSize(1100, 650); // Φαρδύ παράθυρο για να χωράνε οι 6 στήλες
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        private void createUI() {
                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.setBackground(new Color(240, 240, 240));

                // 1. HEADER
                JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                headerPanel.setBackground(new Color(7, 25, 82));
                headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                JLabel titleLabel = new JLabel("Σύγκριση Δεικτών: Ελλάδα vs Ευρώπη");
                titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
                titleLabel.setForeground(Color.WHITE);
                headerPanel.add(titleLabel);

                mainPanel.add(headerPanel, BorderLayout.NORTH);

                // 2. ΟΡΙΣΜΟΣ ΣΤΗΛΩΝ (Σταθερές - Hardcoded)
                String[] columnNames = {
                                "Οικονομικός Δείκτης",
                                "Ελλάδα (2025)",
                                "Γερμανία",
                                "Βουλγαρία",
                                "Ιταλία",
                                "Σερβία"
                };

                // 3. ΔΗΜΙΟΥΡΓΙΑ ΠΙΝΑΚΑ
                tableModel = new DefaultTableModel(columnNames, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false; // Ο χρήστης δεν μπορεί να αλλάξει τα νούμερα
                        }
                };

                table = new JTable(tableModel);
                table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                table.setRowHeight(40);
                table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
                table.getTableHeader().setBackground(new Color(7, 25, 82));
                table.getTableHeader().setForeground(Color.WHITE);

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                mainPanel.add(scrollPane, BorderLayout.CENTER);

                // Φοοτερ
                JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                footerPanel.setBackground(new Color(240, 240, 240));
                footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));

                // --- Χειριστήρια Γραφήματος ---
                JLabel chartLabel = new JLabel("Γράφημα Σύγκρισης για: ");
                chartLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

                String[] chartCountries = { "Germany", "Bulgaria", "Italy", "Serbia" };
                JComboBox<String> countryCombo = new JComboBox<>(chartCountries);
                countryCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

                JButton chartButton = new JButton("Προβολή");
                chartButton.setBackground(new Color(7, 25, 82));
                chartButton.setForeground(Color.WHITE);
                chartButton.setFont(new Font("Segoe UI", Font.BOLD, 12));

                chartButton.addActionListener(e -> {
                        String selected = (String) countryCombo.getSelectedItem();
                        Charts.plotMultiCountryComparison(selected);
                });

                // Προσθήκη στο Footer
                footerPanel.add(chartLabel);
                footerPanel.add(countryCombo);
                footerPanel.add(chartButton);

                // Λίγο κενό ανάμεσα στα κουμπιά του γραφήματος και το κλείσιμο
                footerPanel.add(Box.createHorizontalStrut(40));

                mainPanel.add(footerPanel, BorderLayout.SOUTH);

                // 5. ΓΕΜΙΣΜΑ ΔΕΔΟΜΕΝΩΝ
                fillTableData();

                add(mainPanel);
        }

        private void fillTableData() {
                BudgetDetails grDetails = budget.getDetails();

                // Γραμμή 1: Πληθωρισμός
                addRow("Πληθωρισμός",
                                grDetails.getInflation(),
                                BudgetCountriesComparator.INFLATION_VALUES, "%");

                // Γραμμή 2: ΑΕΠ
                addRow("ΑΕΠ (Ρυθμός Ανάπτυξης)",
                                grDetails.getGdp() / 1E11,
                                BudgetCountriesComparator.GDP_VALUES, "%");

                // Γραμμή 3: Λόγος Χρέους
                addRow("Λόγος Χρέους/ΑΕΠ",
                                grDetails.getDebtRatio(),
                                BudgetCountriesComparator.DEBTRATIO_VALUES, "%");

                // Γραμμή 4: ΦΠΑ
                addRow("ΦΠΑ (Βασικός Συντ.)",
                                grDetails.getVatRatePercent(),
                                BudgetCountriesComparator.VATRATEPERCENT_VALUES, "%");

                // Γραμμή 5: Φόρος Εισοδήματος
                addRow("Φόρος Εισοδήματος",
                                grDetails.getIncomeTaxRatePercent(),
                                BudgetCountriesComparator.INCOMETAXRATEPERCENT_VALUES, "%");

                // Γραμμή 6: Βάση Εσόδων ΦΠΑ
                addRow("Βάση Εσόδων ΦΠΑ",
                                grDetails.getBaseRevenueForVat(),
                                BudgetCountriesComparator.BASEREVENUEVAT_VALUES, "€");

                // Γραμμή 7: Βάση Εσόδων Φόρου Εισοδήματος
                addRow("Βάση Εσόδων Φορ. Εισ.",
                                grDetails.getBaseRevenueForIncomeTax(),
                                BudgetCountriesComparator.BASEREVENUEINCOME_VALUES, "€");
        }

        /**
         * Βοηθητική μέθοδος που φτιάχνει τη γραμμή αναζητώντας συγκεκριμένα κλειδιά.
         */
        private void addRow(String indicator, double greeceVal, Map<String, Double> map, String unit) {

                // Ανάκτηση τιμών από τον Map χρησιμοποιώντας τα ακριβή ονόματα κλειδιών
                Double valGermany = map.get("Germany");
                Double valBulgaria = map.get("Bulgaria");
                Double valItaly = map.get("Italy");
                Double valSerbia = map.get("Serbia");

                // Χειρισμός περιπτώσεων που μπορεί να λείπει τιμή (null check)
                String sGermany = (valGermany != null) ? String.format("%.2f%s", valGermany, unit) : "-";
                String sBulgaria = (valBulgaria != null) ? String.format("%.2f%s", valBulgaria, unit) : "-";
                String sItaly = (valItaly != null) ? String.format("%.2f%s", valItaly, unit) : "-";
                String sSerbia = (valSerbia != null) ? String.format("%.2f%s", valSerbia, unit) : "-";

                // Προσθήκη της γραμμής στον πίνακα
                tableModel.addRow(new Object[] {
                                indicator,
                                String.format("%.2f%s", greeceVal, unit),
                                sGermany,
                                sBulgaria,
                                sItaly,
                                sSerbia
                });
        }
}