package gr.aueb.dmst.detFiscal;

import java.util.Map;
import java.util.HashMap;

public class BudgetCountriesComparator {

    public static final Map<String, Double> INFLATION_VALUES = new HashMap<>();
    static {
        INFLATION_VALUES.put("Germany", 2.3);
        INFLATION_VALUES.put("Bulgaria", 3.5);
        INFLATION_VALUES.put("Italy", 1.7);
        INFLATION_VALUES.put("Serbia", 5.0);
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
        DEBTRATIO_VALUES.put("Italy", 136.0);
        DEBTRATIO_VALUES.put("Serbia", 46.0);
    }

    public static final Map<String, Double> VATRATEPERCENT_VALUES = new HashMap<>();
    static {
        VATRATEPERCENT_VALUES.put("Germany", 19.0);
        VATRATEPERCENT_VALUES.put("Bulgaria", 20.0);
        VATRATEPERCENT_VALUES.put("Italy", 22.0);
        VATRATEPERCENT_VALUES.put("Serbia", 10.0);
    }

    public static final Map<String, Double> INCOMETAXRATEPERCENT_VALUES = new HashMap<>();
    static {
        INCOMETAXRATEPERCENT_VALUES.put("Germany", 45.0);
        INCOMETAXRATEPERCENT_VALUES.put("Bulgaria", 10.0);
        INCOMETAXRATEPERCENT_VALUES.put("Italy", 35.0);
        INCOMETAXRATEPERCENT_VALUES.put("Serbia", 10.0);
    }

    public static final Map<String, Double> BASEREVENUEVAT_VALUES = new HashMap<>();
    static {
        BASEREVENUEVAT_VALUES.put("Germany", 200000000000.0);
        BASEREVENUEVAT_VALUES.put("Bulgaria", 300000000000.0);
        BASEREVENUEVAT_VALUES.put("Italy", 220000000000.0);
        BASEREVENUEVAT_VALUES.put("Serbia", 200000000000.0);
    }

    public static final Map<String, Double> BASEREVENUEINCOME_VALUES = new HashMap<>();
    static {
        BASEREVENUEINCOME_VALUES.put("Germany", 450000000000.0);
        BASEREVENUEINCOME_VALUES.put("Bulgaria", 300000000000.0);
        BASEREVENUEINCOME_VALUES.put("Italy", 350000000000.0);
        BASEREVENUEINCOME_VALUES.put("Serbia", 100000000000.0);
    }

    private final MacroData GreecesData;

    public BudgetCountriesComparator(MacroData data) {
        GreecesData = data;
    }

    public void compareCountriesMacro() {

        double cur_value;
        cur_value = GreecesData.getInflation();
        // κώδικας για τη Γερμανία
        if (cur_value > INFLATION_VALUES.get("Germany")) {
            System.out.println("Ο πληθωρισμός της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get("Germany")
                    + "ο πληθωρισμός της Γερμανίας");
        } else if (cur_value < INFLATION_VALUES.get("Germany")) {
            System.out.println("Ο πληθωρισμός της Γερμανίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get("Germany")
                    + "ο πληθωρισμός της Γερμανίας");
        } else {
            System.out.println("ο πληθωρισμός της Ελλάδας είναι ίσος με της Γερμανίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > GDP_VALUES.get("Germany")) {
            System.out.println("Το ΑΕΠ της Ελλάδας ήταν μεγαλύτερο για το 2025");
            System.out.println(
                    cur_value + "ο πληθωρισμός της Ελλάδας" + GDP_VALUES.get("Germany") + "το ΑΕΠ της Γερμανίας");
        } else if (cur_value < GDP_VALUES.get("Germany")) {
            System.out.println("Το ΑΕΠ της Γερμανίας είναι μεγαλύτερο της Ελλάδας");
            System.out.println(cur_value + "το ΑΕΠ της Ελλάδας" + GDP_VALUES.get("Germany") + "το ΑΕΠ της Γερμανίας");
        } else {
            System.out.println("το ΑΕΠ της Ελλάδας είναι ίσο με της Γερμανίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getDebtRatio();
        if (cur_value > DEBTRATIO_VALUES.get("Germany")) {
            System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get("Germany")
                    + "ο λόγος χρέους της Γερμανίας");
        } else if (cur_value < DEBTRATIO_VALUES.get("Germany")) {
            System.out.println("Ο λόγος χρέους της Γερμανίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get("Germany")
                    + "ο λόγος χρέους της Γερμανίας");
        } else {
            System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της Γερμανίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getVatRatePercent();
        if (cur_value > VATRATEPERCENT_VALUES.get("Germany")) {
            System.out.println("Ο ΦΠΑ της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο ΦΠΑ  της Ελλάδας" + VATRATEPERCENT_VALUES.get("Germany") + "ο ΦΠΑ της Γερμανίας");
        } else if (cur_value < VATRATEPERCENT_VALUES.get("Germany")) {
            System.out.println("Ο ΦΠΑ της Γερμανίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(
                    cur_value + "ο ΦΠΑ της Ελλάδας" + VATRATEPERCENT_VALUES.get("Germany") + "ο ΦΠΑ της Γερμανίας");
        } else {
            System.out.println("ο ΦΠΑ της Ελλάδας είναι ίσος με της Γερμανίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getIncomeTaxRatePercent();
        if (cur_value > INCOMETAXRATEPERCENT_VALUES.get("Germany")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(
                    "Ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας" + INCOMETAXRATEPERCENT_VALUES.get("Germany")
                            + "ο φορολογικός συντελεστής εισοδήματος της Γερμανίας");
        } else if (cur_value < INCOMETAXRATEPERCENT_VALUES.get("Germany")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Γερμανίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας"
                    + INCOMETAXRATEPERCENT_VALUES.get("Germany")
                    + "ο φορολογικός συντελεστής εισοδήματος της Γερμανίας");
        } else {
            System.out.println(
                    "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας είναι ίσος με της Γερμανίας και ισούται με"
                            + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForVat();
        if (cur_value > BASEREVENUEVAT_VALUES.get("Germany")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + BASEREVENUEVAT_VALUES.get("Germany")
                    + "η βάση εσόδων ΦΠΑ της Γερμανίας");
        } else if (cur_value < BASEREVENUEVAT_VALUES.get("Germany")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Γερμανίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + BASEREVENUEVAT_VALUES.get("Germany")
                    + "η βάση εσόδων ΦΠΑ της Γερμανίας");
        } else {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας είναι ίση με της Γερμανίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForIncomeTax();
        if (cur_value > BASEREVENUEINCOME_VALUES.get("Germany")) {
            System.out.println("η βάση εσόδων φόρου εισοδήματος της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας"
                    + BASEREVENUEINCOME_VALUES.get("Germany") + "η βάση εσόδων φόρου εισοδήματος της Γερμανίας");
        } else if (cur_value < BASEREVENUEINCOME_VALUES.get("Germany")) {
            System.out.println("Η βάση εσόδων φόρου εισοδήματος της Γερμανίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας"
                    + BASEREVENUEINCOME_VALUES.get("Germany") + "η βάση εσόδων φόρου εισοδήματος της Γερμανίας");
        } else {
            System.out.println(
                    "η βάση φόρου εισοδήματος της Ελλάδας είναι ίση με της Γερμανίας και ισούται με" + cur_value);
        }
        // κώδικας για την Βουλγαρία
        cur_value = GreecesData.getInflation();
        if (cur_value > INFLATION_VALUES.get("Bulgaria")) {
            System.out.println("Ο πληθωρισμός της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get("Bulgaria")
                    + "ο πληθωρισμός της Βουλγαρίας");
        } else if (cur_value < INFLATION_VALUES.get("Bulgaria")) {
            System.out.println("Ο πληθωρισμός της Βουλγαρίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get("Bulgaria")
                    + "ο πληθωρισμός της Βουλγαρίας");
        } else {
            System.out.println("ο πληθωρισμός της Ελλάδας είναι ίσος με της Βουλγαρίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > GDP_VALUES.get("Bulgaria")) {
            System.out.println("Το ΑΕΠ της Ελλάδας ήταν μεγαλύτερο για το 2025");
            System.out.println(
                    cur_value + "ο πληθωρισμός της Ελλάδας" + GDP_VALUES.get("Bulgaria") + "το ΑΕΠ της Βουλγαρίας");
        } else if (cur_value < GDP_VALUES.get("Bulgaria")) {
            System.out.println("Το ΑΕΠ της Βουλγαρίας είναι μεγαλύτερο της Ελλάδας");
            System.out.println(cur_value + "το ΑΕΠ της Ελλάδας" + GDP_VALUES.get("Bulgaria") + "το ΑΕΠ της Βουλγαρίας");
        } else {
            System.out.println("το ΑΕΠ της Ελλάδας είναι ίσο με της Βουλγαρίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getDebtRatio();
        if (cur_value > DEBTRATIO_VALUES.get("Bulgaria")) {
            System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get("Bulgaria")
                    + "ο λόγος χρέους της Βουλγαρίας");
        } else if (cur_value < DEBTRATIO_VALUES.get("Bulgaria")) {
            System.out.println("Ο λόγος χρέους της Βουλγαρίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get("Bulgaria")
                    + "ο λόγος χρέους της Βουλγαρίας");
        } else {
            System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της Βουλγαρίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getVatRatePercent();
        if (cur_value > VATRATEPERCENT_VALUES.get("Bulgaria")) {
            System.out.println("Ο ΦΠΑ της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο ΦΠΑ  της Ελλάδας" + VATRATEPERCENT_VALUES.get("Bulgaria") + "ο ΦΠΑ της Βουλγαρίας");
        } else if (cur_value < VATRATEPERCENT_VALUES.get("Bulgaria")) {
            System.out.println("Ο ΦΠΑ της Βουλγαρίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(
                    cur_value + "ο ΦΠΑ της Ελλάδας" + VATRATEPERCENT_VALUES.get("Bulgaria") + "ο ΦΠΑ της Βουλγαρίας");
        } else {
            System.out.println("ο ΦΠΑ της Ελλάδας είναι ίσος με της Βουλγαρίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getIncomeTaxRatePercent();
        if (cur_value > INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(
                    "Ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας" + INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")
                            + "ο φορολογικός συντελεστής εισοδήματος της Βουλγαρίας");
        } else if (cur_value < INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Βουλγαρίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας"
                    + INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")
                    + "ο φορολογικός συντελεστής εισοδήματος της Βουλγαρίας");
        } else {
            System.out.println(
                    "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας είναι ίσος με της Βουλγαρίας και ισούται με"
                            + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForVat();
        if (cur_value > BASEREVENUEVAT_VALUES.get("Bulgaria")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + BASEREVENUEVAT_VALUES.get("Bulgaria")
                    + "η βάση εσόδων ΦΠΑ της Βουλγαρίας");
        } else if (cur_value < BASEREVENUEVAT_VALUES.get("Bulgaria")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Βουλγαρίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + BASEREVENUEVAT_VALUES.get("Bulgaria")
                    + "η βάση εσόδων ΦΠΑ της Βουλγαρίας");
        } else {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας είναι ίση με της Βουλγαρίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForIncomeTax();
        if (cur_value > BASEREVENUEINCOME_VALUES.get("Bulgaria")) {
            System.out.println("η βάση εσόδων φόρου εισοδήματος της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας"
                    + BASEREVENUEINCOME_VALUES.get("Bulgaria") + "η βάση εσόδων φόρου εισοδήματος της Βουλγαρίας");
        } else if (cur_value < BASEREVENUEINCOME_VALUES.get("Bulgaria")) {
            System.out.println("Η βάση εσόδων φόρου εισοδήματος της Βουλγαρίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας"
                    + BASEREVENUEINCOME_VALUES.get("Bulgaria") + "η βάση εσόδων φόρου εισοδήματος της Βουλγαρίας");
        } else {
            System.out.println(
                    "η βάση φόρου εισοδήματος της Ελλάδας είναι ίση με της Βουλγαρίας και ισούται με" + cur_value);
        }
        // κώδικας για την Ιταλία
        cur_value = GreecesData.getInflation();
        if (cur_value > INFLATION_VALUES.get("Italy")) {
            System.out.println("Ο πληθωρισμός της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get("Italy")
                    + "ο πληθωρισμός της Ιταλίας");
        } else if (cur_value < INFLATION_VALUES.get("Italy")) {
            System.out.println("Ο πληθωρισμός της Ιταλίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get("Italy")
                    + "ο πληθωρισμός της Ιταλίας");
        } else {
            System.out.println("ο πληθωρισμός της Ελλάδας είναι ίσος με της Ιταλίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > GDP_VALUES.get("Italy")) {
            System.out.println("Το ΑΕΠ της Ελλάδας ήταν μεγαλύτερο για το 2025");
            System.out
                    .println(cur_value + "ο πληθωρισμός της Ελλάδας" + GDP_VALUES.get("Italy") + "το ΑΕΠ της Ιταλίας");
        } else if (cur_value < GDP_VALUES.get("Italy")) {
            System.out.println("Το ΑΕΠ της Ιταλίας είναι μεγαλύτερο της Ελλάδας");
            System.out.println(cur_value + "το ΑΕΠ της Ελλάδας" + GDP_VALUES.get("Italy") + "το ΑΕΠ της Ιταλίας");
        } else {
            System.out.println("το ΑΕΠ της Ελλάδας είναι ίσο με της Ιταλίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getDebtRatio();
        if (cur_value > DEBTRATIO_VALUES.get("Italy")) {
            System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get("Italy")
                    + "ο λόγος χρέους της Ιταλίας");
        } else if (cur_value < DEBTRATIO_VALUES.get("Italy")) {
            System.out.println("Ο λόγος χρέους της Ιταλίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get("Italy")
                    + "ο λόγος χρέους της Ιταλίας");
        } else {
            System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της Ιταλίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getVatRatePercent();
        if (cur_value > VATRATEPERCENT_VALUES.get("Italy")) {
            System.out.println("Ο ΦΠΑ της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο ΦΠΑ  της Ελλάδας" + VATRATEPERCENT_VALUES.get("Italy") + "ο ΦΠΑ της Ιταλίας");
        } else if (cur_value < VATRATEPERCENT_VALUES.get("Italy")) {
            System.out.println("Ο ΦΠΑ της Ιταλίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(
                    cur_value + "ο ΦΠΑ της Ελλάδας" + VATRATEPERCENT_VALUES.get("Italy") + "ο ΦΠΑ της Ιταλίας");
        } else {
            System.out.println("ο ΦΠΑ της Ελλάδας είναι ίσος με της Ιταλίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getIncomeTaxRatePercent();
        if (cur_value > INCOMETAXRATEPERCENT_VALUES.get("Italy")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας"
                    + INCOMETAXRATEPERCENT_VALUES.get("Italy") + "ο φορολογικός συντελεστής εισοδήματος της Ιταλίας");
        } else if (cur_value < INCOMETAXRATEPERCENT_VALUES.get("Italy")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ιταλίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας"
                    + INCOMETAXRATEPERCENT_VALUES.get("Italy") + "ο φορολογικός συντελεστής εισοδήματος της Ιταλίας");
        } else {
            System.out.println(
                    "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας είναι ίσος με της Ιταλίας και ισούται με"
                            + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForVat();
        if (cur_value > BASEREVENUEVAT_VALUES.get("Italy")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + BASEREVENUEVAT_VALUES.get("Italy")
                    + "η βάση εσόδων ΦΠΑ της Ιταλίας");
        } else if (cur_value < BASEREVENUEVAT_VALUES.get("Italy")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Ιταλίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + BASEREVENUEVAT_VALUES.get("Italy")
                    + "η βάση εσόδων ΦΠΑ της Ιταλίας");
        } else {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας είναι ίση με της Ιταλίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForIncomeTax();
        if (cur_value > BASEREVENUEINCOME_VALUES.get("Italy")) {
            System.out.println("η βάση εσόδων φόρου εισοδήματος της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας"
                    + BASEREVENUEINCOME_VALUES.get("Italy") + "η βάση εσόδων φόρου εισοδήματος της Ιταλίας");
        } else if (cur_value < BASEREVENUEINCOME_VALUES.get("Italy")) {
            System.out.println("Η βάση εσόδων φόρου εισοδήματος της Ιταλίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας"
                    + BASEREVENUEINCOME_VALUES.get("Italy") + "η βάση εσόδων φόρου εισοδήματος της Ιταλίας");
        } else {
            System.out.println(
                    "η βάση φόρου εισοδήματος της Ελλάδας είναι ίση με της Ιταλίας και ισούται με" + cur_value);
        }
        // κώδικας για τη Σερβία
        cur_value = GreecesData.getInflation();
        if (cur_value > INFLATION_VALUES.get("Serbia")) {
            System.out.println("Ο πληθωρισμός της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get("Serbia")
                    + "ο πληθωρισμός της Σερβίας");
        } else if (cur_value < INFLATION_VALUES.get("Serbia")) {
            System.out.println("Ο πληθωρισμός της Σερβίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο πληθωρισμός της Ελλάδας" + INFLATION_VALUES.get("Serbia")
                    + "ο πληθωρισμός της Σερβίας");
        } else {
            System.out.println("ο πληθωρισμός της Ελλάδας είναι ίσος με της Σερβίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > GDP_VALUES.get("Serbia")) {
            System.out.println("Το ΑΕΠ της Ελλάδας ήταν μεγαλύτερο για το 2025");
            System.out
                    .println(cur_value + "ο πληθωρισμός της Ελλάδας" + GDP_VALUES.get("Serbia") + "το ΑΕΠ της Σερβίας");
        } else if (cur_value < GDP_VALUES.get("Serbia")) {
            System.out.println("Το ΑΕΠ της Σερβίας είναι μεγαλύτερο της Ελλάδας");
            System.out.println(cur_value + "το ΑΕΠ της Ελλάδας" + GDP_VALUES.get("Serbia") + "το ΑΕΠ της Σερβίας");
        } else {
            System.out.println("το ΑΕΠ της Ελλάδας είναι ίσο με της Σερβίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getDebtRatio();
        if (cur_value > DEBTRATIO_VALUES.get("Serbia")) {
            System.out.println("Ο λόγος χρέους της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get("Serbia")
                    + "ο λόγος χρέους της Σερβίας");
        } else if (cur_value < DEBTRATIO_VALUES.get("Serbia")) {
            System.out.println("Ο λόγος χρέους της Σερβίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο λόγος χρέους της Ελλάδας" + DEBTRATIO_VALUES.get("Serbia")
                    + "ο λόγος χρέους της Σερβίας");
        } else {
            System.out.println("ο λόγος χρέους της Ελλάδας είναι ίσος με της Σερβίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getVatRatePercent();
        if (cur_value > VATRATEPERCENT_VALUES.get("Serbia")) {
            System.out.println("Ο ΦΠΑ της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο ΦΠΑ  της Ελλάδας" + VATRATEPERCENT_VALUES.get("Serbia") + "ο ΦΠΑ της Σερβίας");
        } else if (cur_value < VATRATEPERCENT_VALUES.get("Serbia")) {
            System.out.println("Ο ΦΠΑ της Σερβίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(
                    cur_value + "ο ΦΠΑ της Ελλάδας" + VATRATEPERCENT_VALUES.get("Serbia") + "ο ΦΠΑ της Σερβίας");
        } else {
            System.out.println("ο ΦΠΑ της Ελλάδας είναι ίσος με της Σερβίας και ισούται με" + cur_value);
        }
        cur_value = GreecesData.getIncomeTaxRatePercent();
        if (cur_value > INCOMETAXRATEPERCENT_VALUES.get("Serbia")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Ελλάδας ήταν μεγαλύτερος για το 2025");
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας"
                    + INCOMETAXRATEPERCENT_VALUES.get("Serbia") + "ο φορολογικός συντελεστής εισοδήματος της Σερβίας");
        } else if (cur_value < INCOMETAXRATEPERCENT_VALUES.get("Serbia")) {
            System.out.println("Ο φορολογικός συντελεστής εισοδήματος της Σερβίας είναι μεγαλύτερος της Ελλάδας");
            System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας"
                    + INCOMETAXRATEPERCENT_VALUES.get("Serbia") + "ο φορολογικός συντελεστής εισοδήματος της Σερβίας");
        } else {
            System.out.println(
                    "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας είναι ίσος με της Σερβίας και ισούται με"
                            + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForVat();
        if (cur_value > BASEREVENUEVAT_VALUES.get("Serbia")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + BASEREVENUEVAT_VALUES.get("Serbia")
                    + "η βάση εσόδων ΦΠΑ της Σερβίας");
        } else if (cur_value < BASEREVENUEVAT_VALUES.get("Serbia")) {
            System.out.println("η βάση εσόδων ΦΠΑ της Σερβίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων ΦΠΑ της Ελλάδας" + BASEREVENUEVAT_VALUES.get("Serbia")
                    + "η βάση εσόδων ΦΠΑ της Σερβίας");
        } else {
            System.out.println("η βάση εσόδων ΦΠΑ της Ελλάδας είναι ίση με της Σερβίας και ισούται με" + cur_value);
        }

        cur_value = GreecesData.getBaseRevenueForIncomeTax();
        if (cur_value > BASEREVENUEINCOME_VALUES.get("Serbia")) {
            System.out.println("η βάση εσόδων φόρου εισοδήματος της Ελλάδας ήταν μεγαλύτερη για το 2025");
            System.out.println(cur_value + " η βάση εσόδων φόρου εισοδήματος της Ελλάδας και "
                    + BASEREVENUEINCOME_VALUES.get("Serbia") + " η βάση εσόδων φόρου εισοδήματος της Σερβίας");
        } else if (cur_value < BASEREVENUEINCOME_VALUES.get("Serbia")) {
            System.out.println("Η βάση εσόδων φόρου εισοδήματος της Σερβίας είναι μεγαλύτερη της Ελλάδας");
            System.out.println(cur_value + "η βάση εσόδων φόρου εισοδήματος της Ελλάδας"
                    + BASEREVENUEINCOME_VALUES.get("Serbia") + "η βάση εσόδων φόρου εισοδήματος της Σερβίας");
        } else {
            System.out.println(
                    "η βάση φόρου εισοδήματος της Ελλάδας είναι ίση με της Σερβίας και ισούται με" + cur_value);
        }
    }
}
