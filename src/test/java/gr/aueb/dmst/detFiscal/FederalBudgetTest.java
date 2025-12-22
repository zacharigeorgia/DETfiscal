package gr.aueb.dmst.detFiscal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FederalBudgetTest {

    private FederalBudget budget;
    private IDataLoader mockLoader;

    @BeforeEach
    void setUp() throws Exception {
        // παίρνουμε το singleton
        budget = FederalBudget.getInstance();

        // καθαρίζουμε το summary για να μην κουβαλάει state
        budget.getSummary().getRevenues().clear();
        budget.getSummary().getExpenditures().clear();
        budget.getSummary().getMinistries().clear();

        // mock DataLoader
        mockLoader = Mockito.mock(IDataLoader.class);

        // inject mock DataLoader με reflection
        Field loaderField = FederalBudget.class.getDeclaredField("dataLoader");
        loaderField.setAccessible(true);
        loaderField.set(budget, mockLoader);
    }

    // -----------------------------
    // 1. Singleton Test
    // -----------------------------
    @Test
    void testSingletonInstance() {
        FederalBudget b1 = FederalBudget.getInstance();
        FederalBudget b2 = FederalBudget.getInstance();

        assertSame(b1, b2, "FederalBudget πρέπει να είναι Singleton");
    }

    // -----------------------------
    // 2. calculateTotalBudget
    // -----------------------------
    @Test
    void testCalculateTotalBudget() {
        Revenue r1 = new Revenue();
        r1.setAmount(1000);

        Revenue r2 = new Revenue();
        r2.setAmount(500);

        Expenditure e1 = new Expenditure();
        e1.setAmount(400);

        budget.getSummary().addRevenue(r1);
        budget.getSummary().addRevenue(r2);
        budget.getSummary().addExpenditure(e1);

        double balance = budget.calculateTotalBudget();

        assertEquals(1100, balance, 0.001);
    }

    // -----------------------------
    // 3. initializeData με mocking
    // -----------------------------
    @Test
    void testInitializeDataLoadsDataCorrectly() {
        Revenue revenue = new Revenue();
        revenue.setAmount(1000);

        Expenditure expenditure = new Expenditure();
        expenditure.setAmount(600);

        Ministry ministry = new Ministry();
        ministry.setName("Υπουργείο Οικονομικών");

        MacroData macro = new MacroData();
        macro.setInflation(5.0);
        macro.setGdp(180.0);

        when(mockLoader.loadRevenues("main.json"))
                .thenReturn(List.of(revenue));
        when(mockLoader.loadExpenditures("main.json"))
                .thenReturn(List.of(expenditure));
        when(mockLoader.loadMinistries("main.json"))
                .thenReturn(List.of(ministry));
        when(mockLoader.loadMacroData("main.json"))
                .thenReturn(macro);

        when(mockLoader.loadRevenues("2024.json"))
                .thenReturn(List.of());
        when(mockLoader.loadExpenditures("2024.json"))
                .thenReturn(List.of());
        when(mockLoader.loadMinistries("2024.json"))
                .thenReturn(List.of());

        budget.initializeData("main.json", "2024.json");

        assertEquals(1000,
                budget.getSummary().calculateTotalRevenues(),
                0.001);

        assertEquals(600,
                budget.getSummary().calculateTotalExpenditures(),
                0.001);

        assertEquals(5.0,
                budget.getDetails().getInflation(),
                0.001);
    }

    // -----------------------------
    // 4. compareWith (λογικός έλεγχος)
    // -----------------------------
    @Test
    void testCompareWithDifferentBudgets() {
        FederalBudget other = FederalBudget.getInstance();

        budget.getSummary().addRevenue(new Revenue() {{
            setAmount(1000);
        }});

        other.getSummary().addRevenue(new Revenue() {{
            setAmount(500);
        }});

        assertTrue(
                budget.calculateTotalBudget() >
                other.calculateTotalBudget()
        );
    }
}
