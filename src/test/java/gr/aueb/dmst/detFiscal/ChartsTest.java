package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChartsTest {

    private FederalBudget budget;

    @BeforeEach
    public void setUp() {
        // Παίρνουμε το singleton
        budget = FederalBudget.getInstance();

        // Καθαρίζουμε τα δεδομένα (για να μην έχουμε διπλοεγγραφές σε multi test runs)
        budget.getSummary().getRevenues().clear();
        budget.getSummary().getExpenditures().clear();
        budget.getSummary().getMinistries().clear();
        budget.getSummary().getRevenues2024().clear();
        budget.getSummary().getExpenditures2024().clear();
        budget.getSummary().getMinistries2024().clear();

        // --- Προσθήκη δεδομένων για 2025 ---
        Revenue rev1 = new Revenue();
        rev1.setName("Φόροι");
        rev1.setAmount(1000);
        budget.getSummary().addRevenue(rev1);

        Expenditure exp1 = new Expenditure();
        exp1.setName("Παροχές σε εργαζομένους");
        exp1.setAmount(500);
        budget.getSummary().addExpenditure(exp1);

        Ministry min1 = new Ministry();
        min1.setName("Υπουργείο Υγείας");
        min1.setRegularBudget(300);
        min1.setPublicInvestments(200);
        min1.setTotal(500);
        budget.getSummary().addMinistry(min1);

        // --- Προσθήκη δεδομένων για 2024 ---
        Revenue rev2024 = new Revenue();
        rev2024.setName("Φόροι");
        rev2024.setAmount(900);
        budget.getSummary().getRevenues2024().add(rev2024);

        Expenditure exp2024 = new Expenditure();
        exp2024.setName("Παροχές σε εργαζομένους");
        exp2024.setAmount(450);
        budget.getSummary().getExpenditures2024().add(exp2024);

        Ministry min2024 = new Ministry();
        min2024.setName("Υπουργείο Υγείας");
        min2024.setRegularBudget(280);
        min2024.setPublicInvestments(170);
        min2024.setTotal(450);
        budget.getSummary().getMinistries2024().add(min2024);
    }

    @Test
    public void testPlotMultiYearComparison() {
        // Αυτό πρέπει να εμφανίσει ένα chart με 2025 vs 2024
        Charts.plotMultiYearComparison();
    }

    @Test
    public void testPlotMultiCountryComparison() {
        // Δοκιμή για σύγκριση με άλλη χώρα
        Charts.plotMultiCountryComparison("Germany"); // ή όποια χώρα έχεις στο BudgetCountriesComparator
    }
}



