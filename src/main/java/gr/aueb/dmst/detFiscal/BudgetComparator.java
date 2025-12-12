package gr.aueb.dmst.detFiscal;
import gr.aueb.dmst.detFiscal.BudgetSummary;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import java.nio.file.attribute.GroupPrincipal;
import java.util.HashMap;

public class BudgetComparator extends DataLoader {
   
        public static final Map<String, Double> INFLATION_VALUES = new HashMap<> ();
        static {
            INFLATION_VALUES.put("Germany",2.3 );
            INFLATION_VALUES.put("Bulgary", 3.5);
        }

        public static final Map<String, Double> GDP_VALUES = new HashMap<>();
        static {
            GDP_VALUES.put("Germany", 0.2);
            GDP_VALUES.put("Bulgary", 3.0)
        }

        public static final Map<String, Double> DEPTRATIO_VALUES = new HashMap<>();
        static {
            DEPTRATIO_VALUES.put("Germany", 63.5);
            DEPTRATIO_VALUES.put("Bulgary", 28.5);
        }

        public static final Map<String, Double> VATRATEPERCENT_VALUES = new HashMap<>();
        static {
            VATRATEPERCENT_VALUES.put("Germany", 19);
            VATRATEPERCENT_VALUES.put("Bulgary", 20);
        }

        public static final Map<String, Double> INCOMETAXRATEPERCENT_VALUES = new HashMap<>();
        static {
            INCOMETAXRATEPERCENT_VALUES.put("Germany", 45);
            INCOMETAXRATEPERCENT_VALUES.put("Bulgary", 10);
        }

        public static final Map<String, Double> BASEREVENUE_VALUES = new HashMap<>();
        static {
            BASEREVENUE_VALUES.put("Germany", 20.3);
            BASEREVENUE_VALUES.put("Bulgary", 29.9);
        }

    public String compareCountriesMacro() {
    }
}
