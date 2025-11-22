package gr.aueb.dmst.detFiscal;
import java.util.List;
import java.util.Map;

public interface IDataLoader {

    List<Revenue> loadRevenues(String filePath);

    List<Expenditure> loadExpenditures(String filePath);

    // Χρήση MAP: Επιστρέφει ζεύγη "κλειδί-τιμή" για τα γενικά στοιχεία
    Map<String, Double> loadMacroData(String filePath);

    boolean validateData(String filePath);
}