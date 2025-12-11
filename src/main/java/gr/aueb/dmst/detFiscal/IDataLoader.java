package gr.aueb.dmst.detFiscal;
import java.util.List;

public interface IDataLoader {

    List<Revenue> loadRevenues(String filePath);

    List<Expenditure> loadExpenditures(String filePath);

    MacroData loadMacroData(String filePath);

    List<Ministry> loadMinistries(String filePath);

    boolean validateData(String filePath);
}