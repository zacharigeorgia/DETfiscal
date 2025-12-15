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
        INFLATION_VALUES.put("Bulgaria", 3.5);
        INFLATION_VALUES.put("Italy", 1.7);
        INFLATION_VALUES.put("Serbia", 5);
    }

    public static final Map<String, Double> GDP_VALUES = new HashMap<>();
    static {
        GDP_VALUES.put("Germany", 0.2);
        GDP_VALUES.put("Bulgaria", 3.0);
        GDP_VALUES.put("Italy", 0.4);
        GDP_VALUES.put("Serbia", 2.8);
    }

    public static final Map<String, Double> DEBTRATIO_VALUES = new HashMap<>();
    static {
        DEBTRATIO_VALUES.put("Germany", 63.5);
        DEBTRATIO_VALUES.put("Bulgaria", 28.5);
        DEBTRATIO_VALUES.put("Italy", 136);
        DEBTRATIO_VALUES.put("Serbia", 46);
    }

    public static final Map<String, Double> VATRATEPERCENT_VALUES = new HashMap<>();
    static {
        VATRATEPERCENT_VALUES.put("Germany", 19);
        VATRATEPERCENT_VALUES.put("Bulgaria", 20);
        VATRATEPERCENT_VALUES.put("Italy", 22);
        VATRATEPERCENT_VALUES.put("Serbia",10);
    }

    public static final Map<String, Double> INCOMETAXRATEPERCENT_VALUES = new HashMap<>();
    static {
        INCOMETAXRATEPERCENT_VALUES.put("Germany", 45);
        INCOMETAXRATEPERCENT_VALUES.put("Bulgaria", 10);
        INCOMETAXRATEPERCENT_VALUES.put("Italy", 35);
        INCOMETAXRATEPERCENT_VALUES.put("Serbia", 10);
    }

    public static final Map<String, Double> BASEREVENUEVAT_VALUES = new HashMap<>();
    static {
        BASEREVENUEVAT_VALUES.put("Germany", 20);
        BASEREVENUEVAT_VALUES.put("Bulgaria", 30);
        BASEREVENUEVAT_VALUES.put("Italy", 22);
        BASEREVENUEVAT_VALUES.put("Serbia", 20);
    }

    public static final Map<String, Double> BASEREVENUEINCOME_VALUES = new HashMap<>();
    static {
        BASEREVENUEINCOME_VALUES.put("Germany", 45);
        BASEREVENUEINCOME_VALUES.put("Bulgaria", 30);
        BASEREVENUEINCOME_VALUES.put("Italy", 35);
        BASEREVENUEINCOME_VALUES.put("Serbia", 10);
    }

    private final MacroData GreecesData;
    public BudgetComparator(MacroData data) {
        GreecesData =data;
    }

    public void compareCountriesMacro() {

        double cur_value;
        int i;
        String country; 
        do {
            if (i == 1) {
                country = "Germany";
            } else if(i == 2) {
                country = "Bulgaria"; 
            } else if(i == 3) {
                country = "Italy";
            } else if (i == 4) {
                country = "Serbia";
            }
            if (i == 1 || i == 2 || i == 3 || i ==4) {
                cur_value = GreecesData.getInflation();
                if (cur_value > INFLATION_VALUES.get(country)) {
                    System.out.println("Ο πληθωρισμός της Ελλάδας ήταν μεγαλύτερος για το 2025");
                    System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get(country) + "ο πληθωρισμός της" + country);
                } else if (cur_value < INFLATION_VALUES.get(country)) {
                    System.out.println( "Ο πληθωρισμός της" + country + "είναι μεγαλύτερος της Ελλάδας");
                    System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get(country) + "ο πληθωρισμός της" + country);
                } else {
                    System.out.println("ο πληθωρισμός της Ελλάδας είναι ίσος με της" + country + "και ισούται με" + cur_value);
                }
                cur_value = GreecesData.getGdp();
                if (cur_value > GDP_VALUES.get(country)) {
                    System.out.println("Το ΑΕΠ της Ελλάδας ήταν μεγαλύτερο για το 2025");
                    System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + GDP_VALUES.get(country) + "το ΑΕΠ της" + country);
                } else if(cur_value <GDP_VALUES.get(country)) {   
                    System.out.println( "Το ΑΕΠ της" + country + "είναι μεγαλύτερο της Ελλάδας");
                    System.out.println(cur_value + "το ΑΕΠ της Ελλάδας" + GDP_VALUES.get(country) + "το ΑΕΠ της" + country);
                } else {
                    System.out.println("το ΑΕΠ της Ελλάδας είναι ίσο με της" + country + "και ισούται με" + cur_value);
                }
                cur_value = GreecesData.getDeptRatio();
                if (cur_value > DEBTRATIO_VALUES.get(country)) {
                    System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
                    System.out.println(cur_value +  "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get(country) + "ο λόγος χρέους της" + country);
                } else if(cur_value <DEBTRATIO_VALUES.get(country)) {   
                    System.out.println( "Ο λόγος χρέους της" + country + "είναι μεγαλύτερος της Ελλάδας");
                    System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get(country) + "ο λόγος χρέους της" + country);
                } else {
                    System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της" + country + "και ισούται με" + cur_value);
                }
                cur_value = GreecesData.getVatRatePercent();
                if (cur_value > VATRATEPERCENT_VALUES.get(country)) {
                    System.out.println("Ο ΦΠΑ της Ελλάδας ήταν μεγαλύτερος για το 2025");
                    System.out.println("Ο ΦΠΑ  της Ελλάδας" + VATRATEPERCENT_VALUES.get(country) + "ο λόγος χρέους της" + country);
                } else if(cur_value <VATRATEPERCENT_VALUES.get(country)) {   
                    System.out.println( "Ο ΦΠΑ της" + country + "είναι μεγαλύτερος της Ελλάδας");
                    System.out.println(cur_value + "ο ΦΠΑ της Ελλάδας" + VATRATEPERCENT_VALUES.get(country) + "ο ΦΠΑ της" + country);
                } else {
                    System.out.println("ο ΦΠΑ της Ελλάδας είναι ίσος με της" + country + "και ισούται με" + cur_value);
                }
                cur_value = GreecesData.getIncomeTaxRatePercent();
                if (cur_value > INCOMETAXRATEPERCENT_VALUES.get(country)) {
                    System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ελλάδας ήταν μεγαλύτερος για το 2025");
                    System.out.println(cur_value +  "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας" + INCOMETAXRATEPERCENT_VALUES.get(country) + "ο φορολογικός συντελεστής εισοδήματος της" + country);
                } else if(cur_value <INCOMETAXRATEPERCENT_VALUES.get(country)) {   
                    System.out.println( "Ο φορολογικός συντελεστής εισοδήματος της" + country + "είναι μεγαλύτερος της Ελλάδας");
                    System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας" + INCOMETAXRATEPERCENT_VALUES.get(country) + "ο φορολογικός συντελεστής εισοδήματος της" + country);
                } else {
                    System.out.println("ο φορολογικός συντελεστής εισοδήματος της Ελλάδας είναι ίσος με της" + country + "και ισούται με" + cur_value);
                }
                cur_value = GreecesData.getBaseRevenueForVat(); 
                if (cur_value > BASEREVENUEVAT_VALUES.get(country)) {
                    System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
                    System.out.println(cur_value +  "ο λόγος χρέους της Ελλάδας" + BASEREVENUEVAT_VALUES.get(country) + "ο λόγος χρέους της" + country);
                } else if(cur_value <BASEREVENUEVAT_VALUES.get(country)) {   
                    System.out.println( "Ο λόγος χρέους της" + country + "είναι μεγαλύτερος της Ελλάδας");
                    System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + BASEREVENUEVAT_VALUES.get(country) + "ο λόγος χρέους της" + country);
                } else {
                    System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της" + country + "και ισούται με" + cur_value);
                }
                cur_value = GreecesData.getBaseRevenueForIncomeTax();
                if (cur_value > BASEREVENUEINCOME_VALUES.get(country)) {
                    System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
                    System.out.println(cur_value +  "ο λόγος χρέους της Ελλάδας" + BASEREVENUEINCOME_VALUES.get(country) + "ο λόγος χρέους της" + country);
                } else if(cur_value <BASEREVENUEINCOME_VALUES.get(country)) {   
                    System.out.println( "Ο λόγος χρέους της" + country + "είναι μεγαλύτερος της Ελλάδας");
                    System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + BASEREVENUEINCOME_VALUES.get(country) + "ο λόγος χρέους της" + country);
                } else {
                    System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της" + country +  "και ισούται με" + cur_value);
                }
            }  
        } while (i != 0);  
    }
}
