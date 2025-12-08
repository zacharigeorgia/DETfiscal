package gr.aueb.dmst.detfiscal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Charts {
    private DefaultCategoryDataset createComparisonDataset(
        FederalBudget budget1, FederalBudget budget2, String categoryName1, String categoryName2)
        /*Δέχεται τον 1ο προϋπολογισμό, μετά τον 2ο και μετά ονόματα κατηγορίας έτος ή χώρα */
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //Data για πρώτο αντικείμενο
        dataset.addValue(budget1.getSummary().calculateTotalRevenues(),
                         "Σύνολο Εσόδων", categoryName1);
        dataset.addValue(budget1.getSummary().calculateTotalExpenditures(),
                         "Σύνολο Εξόδων", categoryName1);
        dataset.addValue(budget1.calculateTotalBudget(),
                         "Ισοζύγιο", categoryName1);

        //Data για δεύτερο αντικείμενο
        dataset.addValue(budget2.getSummary().calculateTotalRevenues(),
                         "Σύνολο Εσόδων", categoryName2);
        dataset.addValue(budget2.getSummary().calculateTotalExpenditures(),
                         "Σύνολο Εξόδων", categoryName2)
        dataset.addValue(budget2.getSummary().calculateTotalBudget(),
                         "Ισοζύγιο", categoryName2);
        
        return dataset;
}
