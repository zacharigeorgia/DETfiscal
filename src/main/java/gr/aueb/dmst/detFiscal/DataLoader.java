package gr.aueb.dmst.detFiscal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataLoader implements IDataLoader{
    private ObjectMapper mapper;

    public DataLoader() {
        this.mapper = new ObjectMapper();
    }
    private JsonNode getRoot(String filePath) throws IOException {
        return mapper.readTree(new File(filePath)); //ψαχνει στο json
    }
    @Override
    public List<Revenue> loadRevenues(String filePath) {  //μεθοδοσ επιστεφει την λιστα με τα εσοδα
        var list = new ArrayList<Revenue>();  //η λιστα μ εχει ονομα list
        try {
            var root = getRoot(filePath);
            var categories = root.path("revenues").path("categories"); // πλοηγηση μεσα στο json του λεω τι να παρει απο εκει
        if (categories.isArray()) {
                for (var node : categories) {  //για καθε ενα αντικειμενο node μεσα στην λιστα categories του json
                    var r = new Revenue(); //δημιουργια αντικειμενου απο κλαση revenue
                    r.setName(node.path("name").asText());  //μετατροπη πεδιου name σε javastring που εκχωρειται στο αντικειμενο r
                    r.setAmount(node.path("amount").asDouble()); // το ιδιο αλλα για το ποσο
                    list.add(r); //προσθετω το εσοδο στην λιστα
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //εντοπισμος σφαλματος αμα πιασει το exception
        }
        return list;
    }

    @Override
    public List<Expenditure> loadExpenditures(String filePath) {   //παιρνει το μονοπατι του json και επιστρεφει λιστα με αντικειμενα Expenditure
        var list = new ArrayList<Expenditure>();
        try {
            var root = getRoot(filePath);
            var categories = root.path("expenditures").path("categories");

            if (categories.isArray()) {
                for (var node : categories) {  //για καθενα αντικειμενο node στο json φτιαχνει ενα νεο τυπου Expenditure
                    var e = new Expenditure();
                    e.setName(node.path("name").asText());
                    e.setAmount(node.path("amount").asDouble());

                    // Διάβαζει τους κανόνες (αν υπάρχουν στο JSON)
                    if (node.has("canDecrease")) {
                        e.setCanDecrease(node.path("canDecrease").asBoolean());
                    }
                    if (node.has("maxIncreasePercent")) {
                        e.setMaxIncreasePercent(node.path("maxIncreasePercent").asDouble());
                    }
                    list.add(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public MacroData loadMacroData(String filePath) {  //διαβαζει τα δεδομενα απο την κλαση macrodata
        var macroData = new MacroData(); // Φτιάχνουμε το αντικείμενο
        try {
            var root = getRoot(filePath);

            // 1. Διαβάζουμε τα σύνολα
            var summary = root.path("summary");  //dataloader διαβαζει το json
            if (!summary.isMissingNode()) {
                macroData.setTotalRevenues(summary.path("totalRevenues").asDouble());
                macroData.setTotalExpenditures(summary.path("totalExpenditures").asDouble());
                macroData.setBudgetResult(summary.path("budgetResult").asDouble());
            }

            // 2. Διαβάζουμε τις παραμέτρους σεναρίων
            var params = root.path("economicParameters");
            if (!params.isMissingNode()) {
                macroData.setVatRatePercent(params.path("vatRatePercent").asDouble());
                macroData.setBaseRevenueForVat(params.path("baseRevenueForVat").asDouble());
                macroData.setBaseRevenueForIncomeTax(params.path("baseRevenueForIncomeTax").asDouble());
                macroData.setIncomeTaxRatePercent(params.path("incomeTaxRatePercent").asDouble());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return macroData;
    }
   @Override
    public boolean validateData(String filePath) {
        try {
            var root = getRoot(filePath);
            // Ελέγχουμε αν υπάρχουν τα 3 βασικά συστατικά: Έσοδα, Έξοδα, Σύνολα
            if (root.path("revenues").isMissingNode() ||
                root.path("expenditures").isMissingNode() ||
                root.path("summary").isMissingNode()) {
                return false;
            }
            return true;
        } catch (IOException e) {
            return false; // Δεν βρέθηκε αρχείο ή είναι χαλασμένο
        }
    }
   @Override
    public List<Ministry> loadMinistries(String filePath) {
    var list = new ArrayList<Ministry>();
    try {
        var root = getRoot(filePath);
        var ministriesNode = root.path("ministries").path("list");

        if (ministriesNode.isArray()) {
            for (var node : ministriesNode) {
                var m = new Ministry();
                m.setCode(node.path("code").asText());
                m.setName(node.path("name").asText());
                m.setRegularBudget(node.path("regularBudget").asDouble());
                m.setPublicInvestments(node.path("publicInvestments").asDouble());
                m.setTotal(node.path("total").asDouble());
                list.add(m);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return list;
    }
}