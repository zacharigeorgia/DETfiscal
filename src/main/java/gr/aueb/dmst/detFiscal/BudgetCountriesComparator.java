package gr.aueb.dmst.detFiscal;
import java.util.Map;
import java.util.HashMap;

public class BudgetCountriesComparator {
   
    public static final Map<String, Double> GERMANYS_VALUES = new HashMap<> ();
    static {
        GERMANYS_VALUES.put("Inflation",2.3 );
        GERMANYS_VALUES.put("GDP", 0.2);
        GERMANYS_VALUES.put("DebtRatio", 63.5);
        GERMANYS_VALUES.put("VatRatePercent", 19);
        GERMANYS_VALUES.put("IncomeTaxRatePercent", 45);
        GERMANYS_VALUES.put("BaseRevenueVat", 20);
        GERMANYS_VALUES.put("BaseRevenueIncome", 45);
    }

    public static final Map<String, Double> BULGARIAS_VALUES = new HashMap<>();
    static {
        BULGARIAS_VALUES.put("Inflation", 3.5);
        BULGARIAS_VALUES.put("GDP", 3.0);
        BULGARIAS_VALUES.put("DebtRatio", 28.5);
        BULGARIAS_VALUES.put("VatRatePercent", 20);
        BULGARIAS_VALUES.put("IncomeTaxRatePercent", 10);
        BULGARIAS_VALUES.put("BaseRevenueVat", 30);
        BULGARIAS_VALUES.put("BaseRevenueIncome", 30);
    }

    public static final Map<String, Double> ITALYS_VALUES = new HashMap<>();
    static {
        ITALYS_VALUES.put("Inflation", 1.7);
        ITALYS_VALUES.put("GDP", 0.4);
        ITALYS_VALUES.put("DebtRatio", 136);
        ITALYS_VALUES.put("VatRatePercent", 22);
        ITALYS_VALUES.put("IncomeTaxRatePercent", 35);
        ITALYS_VALUES.put("BaseRevenueVat", 22);
        ITALYS_VALUES.put("BaseRevenueIncome", 35);
    }

    public static final Map<String, Double> SERBIAS_VALUES = new HashMap<>();
    static {
        SERBIAS_VALUES.put("Inflation", 5);
        SERBIAS_VALUES.put("GDP", 2.8);
        SERBIAS_VALUES.put("DebtRatio", 46);
        SERBIAS_VALUES.put("VatRatePercent",10);
        SERBIAS_VALUES.put("IncomeTaxRatePercent", 10);
        SERBIAS_VALUES.put("BaseRevenueVat", 20);
        SERBIAS_VALUES.put("BaseRevenueIncome", 10);
    }

    private final MacroData GreecesData;
    public BudgetCountriesComparator(MacroData data) {
        GreecesData = data;
    }

    public void compareCountriesMacro() {

    double cur_value;
    cur_value = GreecesData.getInflation();
    //κώδικας για τη Γερμανία
        if (cur_value > GERMANYS_VALUES.get("Inflation")) {
            System.out.println("Ο πληθωρισμός της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + GERMANYS_VALUES.get("Inflation") + "ο πληθωρισμός της Γερμανίας");
        } else if (cur_value < GERMANYS_VALUES.get("Inflation") ) {
            System.out.println( "Ο πληθωρισμός της Γερμανίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + GERMANYS_VALUES.get("Inflation") + "ο πληθωρισμός της Γερμανίας");
        } else {
            System.out.println("ο πληθωρισμός της Ελλάδας είναι ίσος με της Γερμανίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > GERMANYS_VALUES.get("GDP")) {
            System.out.println("Το ΑΕΠ της Ελλάδας ήταν μεγαλύτερο για το 2025");
            System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + GERMANYS_VALUES.get("GDP") + "το ΑΕΠ της Γερμανίας");
        } else if(cur_value < GERMANYS_VALUES.get("GDP")) {   
            System.out.println( "Το ΑΕΠ της Γερμανίας είναι μεγαλύτερο της Ελλάδας");
            System.out.println(cur_value + "το ΑΕΠ της Ελλάδας" + GERMANYS_VALUES.get("GDP") + "το ΑΕΠ της Γερμανίας");
        } else {
            System.out.println("το ΑΕΠ της Ελλάδας είναι ίσο με της Γερμανίας και ισούται με" + cur_value);
        }
        
        cur_value = GreecesData.getDebtRatio();
        if (cur_value > GERMANYS_VALUES.get("DebtRatio")) {
            System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value +  "ο λόγος χρέους της Ελλάδας" + GERMANYS_VALUES.get("DebtRatio") + "ο λόγος χρέους της Γερμανίας");
        } else if(cur_value < GERMANYS_VALUES.get("DebtRatio")) {   
            System.out.println( "Ο λόγος χρέους της Γερμανίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + GERMANYS_VALUES.get("DebtRatio") + "ο λόγος χρέους της Γερμανίας");
        } else {
            System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της Γερμανίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getVatRatePercent();
        if (cur_value > GERMANYS_VALUES.get("VatRatePercent")) {
            System.out.println("Ο ΦΠΑ της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο ΦΠΑ  της Ελλάδας" + GERMANYS_VALUES.get("VatRatePercent") + "ο ΦΠΑ της Γερμανίας");
        } else if(cur_value < GERMANYS_VALUES.get("VatRatePercent")) {   
            System.out.println( "Ο ΦΠΑ της Γερμανίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο ΦΠΑ της Ελλάδας" + GERMANYS_VALUES.get("VatRatePercent") + "ο ΦΠΑ της Γερμανίας");
        } else {
            System.out.println("ο ΦΠΑ της Ελλάδας είναι ίσος με της Γερμανίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getIncomeTaxRatePercent();
        if (cur_value > GERMANYS_VALUES.get("IncomeTaxRatePercent")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας" + GERMANYS_VALUES.get("IncomeTaxRatePercent") + "ο φορολογικός συντελεστής εισοδήματος της Γερμανίας");
        } else if(cur_value < GERMANYS_VALUES.get("IncomeTaxRatePercent")) {   
            System.out.println( "Ο φορολογικός συντελεστής εισοδήματος της Γερμανίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας" + GERMANYS_VALUES.get("IncomeTaxRatePercent") + "ο φορολογικός συντελεστής εισοδήματος της Γερμανίας");
        } else {
            System.out.println("ο φορολογικός συντελεστής εισοδήματος της Ελλάδας είναι ίσος με της Γερμανίας και ισούται με" + cur_value);
        }
    
        cur_value = GreecesData.getBaseRevenueForVat(); 
        if (cur_value > GERMANYS_VALUES.get("BaseRevenueForVat")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value +  "η βάση εσόδων ΦΠΑ της Ελλάδας" + GERMANYS_VALUES.get("BaseRevenueForVat") + "η βάση εσόδων ΦΠΑ της Γερμανίας");
        } else if(cur_value < GERMANYS_VALUES.get("BaseRevenueForVat")) {   
            System.out.println( "η βάση εσόδων ΦΠΑ της Γερμανίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + GERMANYS_VALUES.get("BaseRevenueForVat") + "η βάση εσόδων ΦΠΑ της Γερμανίας");
        } else {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας είναι ίση με της Γερμανίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForIncomeTax();
        if (cur_value > GERMANYS_VALUES.get("BaseRevenueFofIncomeTax")) {
            System.out.println("η βάση εσόδων φόρου εισοδήματος της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value +  "η βάση εσόδων φόρου εισοδήματος της Ελλάδας" + GERMANYS_VALUES.get("BaseRevenueFofIncomeTax") + "η βάση εσόδων φόρου εισοδήματος της Γερμανίας");
        } else if(cur_value < GERMANYS_VALUES.get("BaseRevenueFofIncomeTax")) {   
            System.out.println( "Η βάση εσόδων φόρου εισοδήματος της Γερμανίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας" + GERMANYS_VALUES.get("BaseRevenueFofIncomeTax") + "η βάση εσόδων φόρου εισοδήματος της Γερμανίας");
        } else { 
            System.out.println("η βάση φόρου εισοδήματος της Ελλάδας είναι ίση με της Γερμανίας και ισούται με" + cur_value);
        }
        // κώδικας για την Βουλγαρία
        cur_value = GreecesData.getInflation();
        if (cur_value > BULGARIAS_VALUES.get("Inflation")) {
            System.out.println("Ο πληθωρισμός της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + BULGARIAS_VALUES.get("Inflation") + "ο πληθωρισμός της Βουλγαρίας");
        } else if (cur_value < BULGARIAS_VALUES.get("Inflation")) {
            System.out.println( "Ο πληθωρισμός της Βουλγαρίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + BULGARIAS_VALUES.get("Inflation") + "ο πληθωρισμός της Βουλγαρίας");
        } else {
            System.out.println("ο πληθωρισμός της Ελλάδας είναι ίσος με της Βουλγαρίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > BULGARIAS_VALUES.get("GDP")) {
            System.out.println("Το ΑΕΠ της Ελλάδας ήταν μεγαλύτερο για το 2025");
            System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + BULGARIAS_VALUES.get("GDP") + "το ΑΕΠ της Βουλγαρίας");
        } else if(cur_value < BULGARIAS_VALUES.get("GDP")) {   
            System.out.println( "Το ΑΕΠ της Βουλγαρίας είναι μεγαλύτερο της Ελλάδας");
            System.out.println(cur_value + "το ΑΕΠ της Ελλάδας" + BULGARIAS_VALUES.get("GDP") + "το ΑΕΠ της Βουλγαρίας");
        } else {
            System.out.println("το ΑΕΠ της Ελλάδας είναι ίσο με της Βουλγαρίας και ισούται με" + cur_value);
        }
        
        cur_value = GreecesData.getDebtRatio();
        if (cur_value > BULGARIAS_VALUES.get("DebtRatio")) {
            System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value +  "ο λόγος χρέους της Ελλάδας" + BULGARIAS_VALUES.get("DebtRatio") + "ο λόγος χρέους της Βουλγαρίας");
        } else if(cur_value < BULGARIAS_VALUES.get("DebtRatio")) {   
            System.out.println( "Ο λόγος χρέους της Βουλγαρίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + BULGARIAS_VALUES.get("DebtRatio") + "ο λόγος χρέους της Βουλγαρίας");
        } else {
            System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της Βουλγαρίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getVatRatePercent();
        if (cur_value > BULGARIAS_VALUES.get("VatRatePercent")) {
            System.out.println("Ο ΦΠΑ της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο ΦΠΑ  της Ελλάδας" + BULGARIAS_VALUES.get("VatRatePercent") + "ο ΦΠΑ της Βουλγαρίας");
        } else if(cur_value < BULGARIAS_VALUES.get("VatRatePercent")) {   
            System.out.println( "Ο ΦΠΑ της Βουλγαρίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο ΦΠΑ της Ελλάδας" + BULGARIAS_VALUES.get("VatRatePercent") + "ο ΦΠΑ της Βουλγαρίας");
        } else {
            System.out.println("ο ΦΠΑ της Ελλάδας είναι ίσος με της Βουλγαρίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getIncomeTaxRatePercent();
        if (cur_value > BULGARIAS_VALUES.get("IncomeTaxRatePercent")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας" + BULGARIAS_VALUES.get("IncomeTaxRatePercent") + "ο φορολογικός συντελεστής εισοδήματος της Βουλγαρίας");
        } else if(cur_value < BULGARIAS_VALUES.get("IncomeTaxRatePercent")) {   
            System.out.println( "Ο φορολογικός συντελεστής εισοδήματος της Βουλγαρίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας" + BULGARIAS_VALUES.get("IncomeTaxRatePercent") + "ο φορολογικός συντελεστής εισοδήματος της Βουλγαρίας");
        } else {
            System.out.println("ο φορολογικός συντελεστής εισοδήματος της Ελλάδας είναι ίσος με της Βουλγαρίας και ισούται με" + cur_value);
        }
    
        cur_value = GreecesData.getBaseRevenueForVat(); 
        if (cur_value > BULGARIAS_VALUES.get("BaseRevenueForVat")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value +  "η βάση εσόδων ΦΠΑ της Ελλάδας" + BULGARIAS_VALUES.get("BaseRevenueForVat") + "η βάση εσόδων ΦΠΑ της Βουλγαρίας");
        } else if(cur_value < BULGARIAS_VALUES.get("BaseRevenueForVat")) {   
            System.out.println( "η βάση εσόδων ΦΠΑ της Βουλγαρίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + BULGARIAS_VALUES.get("BaseRevenueForVat") + "η βάση εσόδων ΦΠΑ της Βουλγαρίας");
        } else {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας είναι ίση με της Βουλγαρίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForIncomeTax();
        if (cur_value > BULGARIAS_VALUES.get("BaseRevenueForIncomeTax")) {
            System.out.println("η βάση εσόδων φόρου εισοδήματος της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value +  "η βάση εσόδων φόρου εισοδήματος της Ελλάδας" + BULGARIAS_VALUES.get("BaseRevenueForIncomeTax") + "η βάση εσόδων φόρου εισοδήματος της Βουλγαρίας");
        } else if(cur_value < BULGARIAS_VALUES.get("BaseRevenueForIncomeTax")) {   
            System.out.println( "Η βάση εσόδων φόρου εισοδήματος της Βουλγαρίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας" + BULGARIAS_VALUES.get("BaseRevenueForIncomeTax") + "η βάση εσόδων φόρου εισοδήματος της Βουλγαρίας");
        } else { 
            System.out.println("η βάση φόρου εισοδήματος της Ελλάδας είναι ίση με της Βουλγαρίας και ισούται με" + cur_value);
        }
        // κώδικας για την Ιταλία
        cur_value = GreecesData.getInflation();
        if (cur_value > ITALYS_VALUES.get("Inflation")) {
            System.out.println("Ο πληθωρισμός της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + ITALYS_VALUES.get("Inflation") + "ο πληθωρισμός της Ιταλίας");
        } else if (cur_value < ITALYS_VALUES.get("Inflation")) {
            System.out.println( "Ο πληθωρισμός της Ιταλίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + ITALYS_VALUES.get("Inflation") + "ο πληθωρισμός της Ιταλίας");
        } else {
            System.out.println("ο πληθωρισμός της Ελλάδας είναι ίσος με της Ιταλίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > ITALYS_VALUES.get("GDP")) {
            System.out.println("Το ΑΕΠ της Ελλάδας ήταν μεγαλύτερο για το 2025");
            System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + ITALYS_VALUES.get("GDP") + "το ΑΕΠ της Ιταλίας");
        } else if(cur_value < ITALYS_VALUES.get("GDP")) {   
            System.out.println( "Το ΑΕΠ της Ιταλίας είναι μεγαλύτερο της Ελλάδας");
            System.out.println(cur_value + "το ΑΕΠ της Ελλάδας" + ITALYS_VALUES.get("GDP") + "το ΑΕΠ της Ιταλίας");
        } else {
            System.out.println("το ΑΕΠ της Ελλάδας είναι ίσο με της Ιταλίας και ισούται με" + cur_value);
        }
        
        cur_value = GreecesData.getDebtRatio();
        if (cur_value > ITALYS_VALUES.get("DebtRatio")) {
            System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value +  "ο λόγος χρέους της Ελλάδας" + ITALYS_VALUES.get("DebtRatio") + "ο λόγος χρέους της Ιταλίας");
        } else if(cur_value < ITALYS_VALUES.get("DebtRatio")) {   
            System.out.println( "Ο λόγος χρέους της Ιταλίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + ITALYS_VALUES.get("DebtRatio") + "ο λόγος χρέους της Ιταλίας");
        } else {
            System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της Ιταλίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getVatRatePercent();
        if (cur_value > ITALYS_VALUES.get("VatRatePercent")) {
            System.out.println("Ο ΦΠΑ της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο ΦΠΑ  της Ελλάδας" + ITALYS_VALUES.get("VatRatePercent") + "ο ΦΠΑ της Ιταλίας");
        } else if(cur_value < ITALYS_VALUES.get("VatRatePercent")) {   
            System.out.println( "Ο ΦΠΑ της Ιταλίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο ΦΠΑ της Ελλάδας" + ITALYS_VALUES.get("VatRatePercent") + "ο ΦΠΑ της Ιταλίας");
        } else {
            System.out.println("ο ΦΠΑ της Ελλάδας είναι ίσος με της Ιταλίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getIncomeTaxRatePercent();
        if (cur_value > ITALYS_VALUES.get("IncomeTaxRatePercent")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας" + ITALYS_VALUES.get("IncomeTaxRatePercent") + "ο φορολογικός συντελεστής εισοδήματος της Ιταλίας");
        } else if(cur_value < ITALYS_VALUES.get("IncomeTaxRatePercent")) {   
            System.out.println( "Ο φορολογικός συντελεστής εισοδήματος της Ιταλίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας" + ITALYS_VALUES.get("IncomeTaxRatePercent") + "ο φορολογικός συντελεστής εισοδήματος της Ιταλίας");
        } else {
            System.out.println("ο φορολογικός συντελεστής εισοδήματος της Ελλάδας είναι ίσος με της Ιταλίας και ισούται με" + cur_value);
        }
    
        cur_value = GreecesData.getBaseRevenueForVat(); 
        if (cur_value > ITALYS_VALUES.get("BaseRevenueForVat")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value +  "η βάση εσόδων ΦΠΑ της Ελλάδας" + ITALYS_VALUES.get("BaseRevenueForVat") + "η βάση εσόδων ΦΠΑ της Ιταλίας");
        } else if(cur_value < ITALYS_VALUES.get("BaseRevenueForVat")) {   
            System.out.println( "η βάση εσόδων ΦΠΑ της Ιταλίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + ITALYS_VALUES.get("BaseRevenueForVat") + "η βάση εσόδων ΦΠΑ της Ιταλίας");
        } else {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας είναι ίση με της Ιταλίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForIncomeTax();
        if (cur_value > ITALYS_VALUES.get("BaseRevenueForIncomeTax")) {
            System.out.println("η βάση εσόδων φόρου εισοδήματος της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value +  "η βάση εσόδων φόρου εισοδήματος της Ελλάδας" + ITALYS_VALUES.get("BaseRevenueForIncomeTax") + "η βάση εσόδων φόρου εισοδήματος της Ιταλίας");
        } else if(cur_value < ITALYS_VALUES.get("BaseRevenueForIncomeTax")) {   
            System.out.println( "Η βάση εσόδων φόρου εισοδήματος της Ιταλίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας" + ITALYS_VALUES.get("BaseRevenueForIncomeTax") + "η βάση εσόδων φόρου εισοδήματος της Ιταλίας");
        } else { 
            System.out.println("η βάση φόρου εισοδήματος της Ελλάδας είναι ίση με της Ιταλίας και ισούται με" + cur_value);
        }
        //κώδικας για τη Σερβία
        cur_value = GreecesData.getInflation();
        if (cur_value > SERBIAS_VALUES.get("Inflation")) {
            System.out.println("Ο πληθωρισμός της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + SERBIAS_VALUES.get("Inflation") + "ο πληθωρισμός της Σερβίας");
        } else if (cur_value < SERBIAS_VALUES.get("Inflation")) {
            System.out.println( "Ο πληθωρισμός της Σερβίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + SERBIAS_VALUES.get("Inflation") + "ο πληθωρισμός της Σερβίας");
        } else {
            System.out.println("ο πληθωρισμός της Ελλάδας είναι ίσος με της Σερβίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > SERBIAS_VALUES.get("GDP")) {
            System.out.println("Το ΑΕΠ της Ελλάδας ήταν μεγαλύτερο για το 2025");
            System.out.println(cur_value +  "ο πληθωρισμός της Ελλάδας" + SERBIAS_VALUES.get("GDP") + "το ΑΕΠ της Σερβίας");
        } else if(cur_value < SERBIAS_VALUES.get("GDP")) {   
            System.out.println( "Το ΑΕΠ της Σερβίας είναι μεγαλύτερο της Ελλάδας");
            System.out.println(cur_value + "το ΑΕΠ της Ελλάδας" + SERBIAS_VALUES.get("GDP") + "το ΑΕΠ της Σερβίας");
        } else {
            System.out.println("το ΑΕΠ της Ελλάδας είναι ίσο με της Σερβίας και ισούται με" + cur_value);
        }
        
        cur_value = GreecesData.getDebtRatio();
        if (cur_value > SERBIAS_VALUES.get("DebtRatio")) {
            System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value +  "ο λόγος χρέους της Ελλάδας" + SERBIAS_VALUES.get("DebtRatio") + "ο λόγος χρέους της Σερβίας");
        } else if(cur_value < SERBIAS_VALUES.get("DebtRatio")) {   
            System.out.println( "Ο λόγος χρέους της Σερβίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + SERBIAS_VALUES.get("DebtRatio") + "ο λόγος χρέους της Σερβίας");
        } else {
            System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της Σερβίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getVatRatePercent();
        if (cur_value > SERBIAS_VALUES.get("VatRatePercent")) {
            System.out.println("Ο ΦΠΑ της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο ΦΠΑ  της Ελλάδας" + SERBIAS_VALUES.get("VatRatePercent") + "ο ΦΠΑ της Σερβίας");
        } else if(cur_value < SERBIAS_VALUES.get("VatRatePercent")) {   
            System.out.println( "Ο ΦΠΑ της Σερβίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο ΦΠΑ της Ελλάδας" + SERBIAS_VALUES.get("VatRatePercent") + "ο ΦΠΑ της Σερβίας");
        } else {
            System.out.println("ο ΦΠΑ της Ελλάδας είναι ίσος με της Σερβίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getIncomeTaxRatePercent();
        if (cur_value > SERBIAS_VALUES.get("IncomeTaxRatePercent")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο ΦΠΑ  της Ελλάδας" + SERBIAS_VALUES.get("IncomeTaxRatePercent") + "ο ΦΠΑ της Σερβίας"); 
        } else if(cur_value < SERBIAS_VALUES.get("IncomeTaxRatePercent")) {   
            System.out.println( "Ο φορολογικός συντελεστής εισοδήματος της Σερβίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας" + SERBIAS_VALUES.get("IncomeTaxRatePercent") + "ο φορολογικός συντελεστής εισοδήματος της Σερβίας");
        } else {
            System.out.println("ο φορολογικός συντελεστής εισοδήματος της Ελλάδας είναι ίσος με της Σερβίας και ισούται με" + cur_value);
        }
    
        cur_value = GreecesData.getBaseRevenueForVat(); 
        if (cur_value > SERBIAS_VALUES.get("BaseRevenueForVat")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value +  "η βάση εσόδων ΦΠΑ της Ελλάδας" + SERBIAS_VALUES.get("BaseRevenueForVat") + "η βάση εσόδων ΦΠΑ της Σερβίας");
        } else if(cur_value < SERBIAS_VALUES.get("BaseRevenueForVat")) {   
            System.out.println( "η βάση εσόδων ΦΠΑ της Σερβίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + SERBIAS_VALUES.get("BaseRevenueForVat") + "η βάση εσόδων ΦΠΑ της Σερβίας");
        } else {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας είναι ίση με της Σερβίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForIncomeTax();
        if (cur_value > SERBIAS_VALUES.get("BaseRevenueForIncomeTax")) {
            System.out.println("η βάση εσόδων φόρου εισοδήματος της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value +  " η βάση εσόδων φόρου εισοδήματος της Ελλάδας και " + SERBIAS_VALUES.get("BaseRevenueForIncomeTax") + " η βάση εσόδων φόρου εισοδήματος της Σερβίας");
        } else if(cur_value < SERBIAS_VALUES.get("BaseRevenueForIncomeTax")) {   
            System.out.println( "Η βάση εσόδων φόρου εισοδήματος της Σερβίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας" + SERBIAS_VALUES.get("BaseRevenueForIncomeTax") + "η βάση εσόδων φόρου εισοδήματος της Σερβίας");
        } else { 
            System.out.println("η βάση φόρου εισοδήματος της Ελλάδας είναι ίση με της Σερβίας και ισούται με" + cur_value);
        }
    }   
}
