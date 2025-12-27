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
                setSize(1100, 600); // Φαρδύ παράθυρο για να χωράνε οι 6 στήλες
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

                // Στοίχιση στο κέντρο για τα νούμερα (προαιρετικό)
                // ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setHorizontalAlignment(JLabel.CENTER);

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                mainPanel.add(scrollPane, BorderLayout.CENTER);

                // 4. ΓΕΜΙΣΜΑ ΔΕΔΟΜΕΝΩΝ
                fillTableData();

                add(mainPanel);
        }

        /**
         * Γεμίζει τον πίνακα παίρνοντας δεδομένα από το BudgetDetails (Ελλάδα)
         * και από την BudgetCountriesComparator (Άλλες χώρες).
         */
        private void fillTableData() {
                BudgetDetails grDetails = budget.getDetails();

                // Γραμμή 1: Πληθωρισμός
                addRow("Πληθωρισμός",
                                grDetails.getInflation(),
                                BudgetCountriesComparator.INFLATION_VALUES, "%");

                // Γραμμή 2: ΑΕΠ
                addRow("ΑΕΠ (Ρυθμός Ανάπτυξης)",
                                grDetails.getGdp(),
                                BudgetCountriesComparator.GDP_VALUES, "%");

                // Γραμμή 3: Λόγος Χρέους
                addRow("Λόγος Χρέους/ΑΕΠ",
                                grDetails.getDebtRatio(),
                                BudgetCountriesComparator.DEBTRATIO_VALUES, "%");

                // Γραμμή 4: ΦΠΑ (Πολλαπλασιάζουμε επί 100 αν στην Ελλάδα είναι 0.24)
                addRow("ΦΠΑ (Βασικός Συντ.)",
                                grDetails.getVatRatePercent() * 100,
                                BudgetCountriesComparator.VATRATEPERCENT_VALUES, "%");

                // Γραμμή 5: Φόρος Εισοδήματος
                addRow("Φόρος Εισοδήματος",
                                grDetails.getIncomeTaxRatePercent() * 100,
                                BudgetCountriesComparator.INCOMETAXRATEPERCENT_VALUES, "%");

                // Γραμμή 6: Βάση Εσόδων ΦΠΑ (σε Δις)
                addRow("Βάση Εσόδων ΦΠΑ (Δις)",
                                grDetails.getBaseRevenueForVat(),
                                BudgetCountriesComparator.BASEREVENUEVAT_VALUES, "€");

                // Γραμμή 7: Βάση Εσόδων Φόρου Εισοδήματος (σε Δις)
                addRow("Βάση Εσόδων Φορ. Εισ. (Δις)",
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