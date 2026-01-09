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

    public String getComparisonReport() {
        StringBuilder sb = new StringBuilder();
        double cur_value;

        // --- ΓΕΡΜΑΝΙΑ ---
        sb.append("=== ΣΥΓΚΡΙΣΗ ΜΕ ΓΕΡΜΑΝΙΑ ===\n");

        cur_value = GreecesData.getInflation();
        if (cur_value > INFLATION_VALUES.get("Germany")) {
            sb.append("- Η οικονομία στη Γερμανία ήταν πιο σταθερή και η ζωή πιο οικονομική.\n");
            sb.append("  (Ελλάδα: ").append(cur_value).append("% vs Γερμανία: ").append(INFLATION_VALUES.get("Germany"))
                    .append("%)\n");
        } else if (cur_value < INFLATION_VALUES.get("Germany")) {
            sb.append("- Η οικονομία στην Ελλάδα ήταν πιο σταθερή και η ζωή πιο οικονομική.\n");
            sb.append("  (Ελλάδα: ").append(cur_value).append("% vs Γερμανία: ").append(INFLATION_VALUES.get("Germany"))
                    .append("%)\n");
        } else {
            sb.append("- Η οικονομία της Ελλάδας ήταν το ίδιο σταθερή με της Γερμανίας.\n");
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > GDP_VALUES.get("Germany")) {
            sb.append("- Η Ελλάδα είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.\n");
        } else if (cur_value < GDP_VALUES.get("Germany")) {
            sb.append("- Η Γερμανία είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.\n");
        } else {
            sb.append("- Η Ελλάδα είχε την ίδια οικονομική δραστηριότητα με τη Γερμανία.\n");
        }

        cur_value = GreecesData.getDebtRatio();
        if (cur_value > DEBTRATIO_VALUES.get("Germany")) {
            sb.append("- H Γερμανία είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις.\n");
        } else if (cur_value < DEBTRATIO_VALUES.get("Germany")) {
            sb.append("- H Ελλάδα είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις.\n");
        } else {
            sb.append("- Η Ελλάδα είχε την ίδια ευελιξία με τη Γερμανία.\n");
        }

        cur_value = GreecesData.getVatRatePercent();
        if (cur_value > VATRATEPERCENT_VALUES.get("Germany")) {
            sb.append("- Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Γερμανία στην ανάπτυξη.\n");
        } else if (cur_value < VATRATEPERCENT_VALUES.get("Germany")) {
            sb.append("- Η Γερμανία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα στην ανάπτυξη.\n");
        } 

        sb.append("\n"); // Κενή γραμμή

        // --- ΒΟΥΛΓΑΡΙΑ ---
        sb.append("=== ΣΥΓΚΡΙΣΗ ΜΕ ΒΟΥΛΓΑΡΙΑ ===\n");

        cur_value = GreecesData.getInflation();
        if (cur_value > INFLATION_VALUES.get("Bulgaria")) {
            sb.append("- Η οικονομία στη Βουλγαρία ήταν πιο σταθερή.\n");
        } else if (cur_value < INFLATION_VALUES.get("Bulgaria")) {
            sb.append("- Η οικονομία στην Ελλάδα ήταν πιο σταθερή.\n");
        } else {
            sb.append("- Η οικονομία της Ελλάδας ήταν το ίδιο σταθερή με της Βουλγαρίας.\n");
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > GDP_VALUES.get("Bulgaria")) {
            sb.append("- Η Ελλάδα είχε μεγαλύτερη οικονομική δραστηριότητα.\n");
        } else if (cur_value < GDP_VALUES.get("Bulgaria")) {
            sb.append("- Η Βουλγαρία είχε μεγαλύτερη οικονομική δραστηριότητα.\n");
        } else {
            sb.append("- Η Ελλάδα είχε την ίδια οικονομική δραστηριότητα με τη Βουλγαρία.\n");
        }

        cur_value = GreecesData.getDebtRatio();
        if (cur_value > DEBTRATIO_VALUES.get("Bulgaria")) {
            sb.append("- H Βουλγαρία είχε μεγαλύτερη ευελιξία σε επενδύσεις.\n");
        } else if (cur_value < DEBTRATIO_VALUES.get("Bulgaria")) {
            sb.append("- Η Ελλάδα είχε μεγαλύτερη ευελιξία σε επενδύσεις.\n");
        } else {
            sb.append("- Η Ελλάδα είχε την ίδια ευελιξία με τη Βουλγαρία.\n");
        }

        cur_value = GreecesData.getIncomeTaxRatePercent();
        if (cur_value > INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")) {
            sb.append("- Η Ελλάδα έχει υψηλότερο φόρο εισοδήματος από τη Βουλγαρία.\n");
        } else if (cur_value < INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")) {
            sb.append("- Η Βουλγαρία έχει υψηλότερο φόρο εισοδήματος από την Ελλάδα.\n");
        }

        sb.append("\n");

        // --- ΙΤΑΛΙΑ ---
        sb.append("=== ΣΥΓΚΡΙΣΗ ΜΕ ΙΤΑΛΙΑ ===\n");

        cur_value = GreecesData.getInflation();
        if (cur_value > INFLATION_VALUES.get("Italy")) {
            sb.append("- Η οικονομία στην Ιταλία ήταν πιο σταθερή.\n");
        } else if (cur_value < INFLATION_VALUES.get("Italy")) {
            sb.append("- Η οικονομία στην Ελλάδα ήταν πιο σταθερή.\n");
        } else {
            sb.append("- Η οικονομία της Ελλάδας ήταν το ίδιο σταθερή με της Ιταλίας.\n");
        }

        cur_value = GreecesData.getGdp();
        if (cur_value > GDP_VALUES.get("Italy")) {
            sb.append("- Η Ελλάδα είχε μεγαλύτερη ανάπτυξη.\n");
        } else if (cur_value < GDP_VALUES.get("Italy")) {
            sb.append("- Η Ιταλία είχε μεγαλύτερη ανάπτυξη.\n");
        } else {
            sb.append("- Η Ελλάδα είχε την ίδια οικονομική δραστηριότητα με την Ιταλία.\n");
        }

        cur_value = GreecesData.getDebtRatio();
        if (cur_value > DEBTRATIO_VALUES.get("Italy")) {
            sb.append("- H Ιταλία είχε μεγαλύτερη ευελιξία (μικρότερο χρέος).\n");
        } else if (cur_value < DEBTRATIO_VALUES.get("Italy")) {
            sb.append("- H Ελλάδα είχε μεγαλύτερη ευελιξία (μικρότερο χρέος).\n");
        }

        sb.append("\n");

        // --- ΣΕΡΒΙΑ ---
        sb.append("=== ΣΥΓΚΡΙΣΗ ΜΕ ΣΕΡΒΙΑ ===\n");

        cur_value = GreecesData.getInflation();
        if (cur_value > INFLATION_VALUES.get("Serbia")) {
            sb.append("- Η οικονομία στη Σερβία ήταν πιο σταθερή.\n");
        } else if (cur_value < INFLATION_VALUES.get("Serbia")) {
            sb.append("- Η οικονομία στην Ελλάδα ήταν πιο σταθερή.\n");
        } else {
            sb.append("- Η οικονομία της Ελλάδας ήταν το ίδιο σταθερή με της Σερβίας.\n");
        }

        cur_value = GreecesData.getDebtRatio();
        if (cur_value > DEBTRATIO_VALUES.get("Serbia")) {
            sb.append("- H Σερβία είχε μεγαλύτερη ευελιξία σε επενδύσεις.\n");
        } else if (cur_value < DEBTRATIO_VALUES.get("Serbia")) {
            sb.append("- H Ελλάδα είχε μεγαλύτερη ευελιξία σε επενδύσεις.\n");
        } else {
            sb.append("- Η Ελλάδα είχε την ίδια οικονομική δραστηριότητα με τη Σερβία.\n");
        }

        return sb.toString();
    }
}
