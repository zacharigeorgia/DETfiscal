package gr.aueb.dmst.detFiscal;
import gr.aueb.dmst.detFiscal.BudgetSummary;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import java.util.HashMap;

public class BudgetComparator extends DataLoader {
    
    public void compareYearsDetailed() {
        currentBudget.getSummary();
        summary.getRevenues();
        summary.getExpenditures();
        summary.getRevenues2024();
        summary.getExpenditures2024();
        accountObject.getName();
        accountObject.getAmount();
    }
}    

