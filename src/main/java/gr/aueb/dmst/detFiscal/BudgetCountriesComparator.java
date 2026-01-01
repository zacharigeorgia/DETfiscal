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
                        System.out.println("Η οικονομία στην Ελλάδα ήταν πιο σταθερή και η ζωή πιο οικονομική.");
                        System.out.println(cur_value + " ο πληθωρισμός της Ελλάδας " + INFLATION_VALUES.get("Germany")
                                        + " ο πληθωρισμός της Γερμανίας.");
                } else if (cur_value < INFLATION_VALUES.get("Germany")) {
                        System.out.println("Η οικονομία στη Γερμανία ήταν πιο σταθερή και η ζωή πιο οικονομική.");
                        System.out.println(cur_value + " ο πληθωρισμός της Ελλάδας " + INFLATION_VALUES.get("Germany")
                                        + " ο πληθωρισμός της Γερμανίας.");
                } else {
                        System.out.println(
                                        "Η οικονομία της Ελλάδας ήταν το ίδιο σταθερή με της Γερμανίας και η ζωή το ίδιο ακριβή.");
                }

                cur_value = GreecesData.getGdp();
                if (cur_value > GDP_VALUES.get("Germany")) {
                        System.out.println("Η Ελλάδα είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.");
                        System.out.println(
                                        cur_value + " ο πληθωρισμός της Ελλάδας " + GDP_VALUES.get("Germany")
                                                        + " το ΑΕΠ της Γερμανίας.");
                } else if (cur_value < GDP_VALUES.get("Germany")) {
                        System.out.println("Η Γερμανία είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.");
                        System.out
                                        .println(cur_value + " το ΑΕΠ της Ελλάδας " + GDP_VALUES.get("Germany")
                                                        + " το ΑΕΠ της Γερμανίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα είχε την ίδια οικονομική δραστηριότητα και πλούτο ανά άτομο με την Γερμανία.");
                }

                cur_value = GreecesData.getDebtRatio();
                if (cur_value > DEBTRATIO_VALUES.get("Germany")) {
                        System.out.println("H Γερμανία είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις.");
                        System.out.println(cur_value + " ο λόγος χρέους της Ελλάδας " + DEBTRATIO_VALUES.get("Germany")
                                        + " ο λόγος χρέους της Γερμανίας.");
                } else if (cur_value < DEBTRATIO_VALUES.get("Germany")) {
                        System.out.println("H Ελλάδα είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις.");
                        System.out.println(cur_value + " ο λόγος χρέους της Ελλάδας " + DEBTRATIO_VALUES.get("Germany")
                                        + " ο λόγος χρέους της Γερμανίας.");
                } else {
                        System.out.println("Η Ελλάδα είχε την ίδια ευελιξία σε επενδύσεις και κρίσεις με τη Γερμανία.");
                }
                cur_value = GreecesData.getVatRatePercent();
                if (cur_value > VATRATEPERCENT_VALUES.get("Germany")) {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Γερμανία επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο ΦΠΑ  της Ελλάδας " + VATRATEPERCENT_VALUES.get("Germany")
                                        + " ο ΦΠΑ της Γερμανίας.");
                } else if (cur_value < VATRATEPERCENT_VALUES.get("Germany")) {
                        System.out.println(
                                        "Η Γερμανία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(
                                        cur_value + " ο ΦΠΑ της Ελλάδας " + VATRATEPERCENT_VALUES.get("Germany")
                                                        + " ο ΦΠΑ της Γερμανίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, την ανάπτυξη, τις επενδύσεις και την κατανάλωση όσο και η Γερμανία.");
                }
                cur_value = GreecesData.getIncomeTaxRatePercent();
                if (cur_value > INCOMETAXRATEPERCENT_VALUES.get("Germany")) {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Γερμανία επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας "
                                        + INCOMETAXRATEPERCENT_VALUES.get("Germany")
                                        + " ο φορολογικός συντελεστής εισοδήματος της Γερμανίας.");
                } else if (cur_value < INCOMETAXRATEPERCENT_VALUES.get("Germany")) {
                        System.out.println(
                                        "Η Γερμανία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο φορολογικός συντελεστής εισοδήματος της Ελλάδας "
                                        + INCOMETAXRATEPERCENT_VALUES.get("Germany")
                                        + " ο φορολογικός συντελεστής εισοδήματος της Γερμανίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, την ανάπτυξη, τις επενδύσεις και την κατανάλωση όσο και η Γερμανία.");
                }

                cur_value = GreecesData.getBaseRevenueForVat();
                if (cur_value > BASEREVENUEVAT_VALUES.get("Germany")) {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων ΦΠΑ της Ελλάδας "
                                        + BASEREVENUEVAT_VALUES.get("Germany")
                                        + " η βάση εσόδων ΦΠΑ της Γερμανίας.");
                } else if (cur_value < BASEREVENUEVAT_VALUES.get("Germany")) {
                        System.out.println(
                                        "Η Γερμανία έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων ΦΠΑ της Ελλάδας "
                                        + BASEREVENUEVAT_VALUES.get("Germany")
                                        + " η βάση εσόδων ΦΠΑ της Γερμανίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει το ίδιο ποσό απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές με τη Γερμανία.");
                }

                cur_value = GreecesData.getBaseRevenueForIncomeTax();
                if (cur_value > BASEREVENUEINCOME_VALUES.get("Germany")) {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων φόρου εισοδήματος της Ελλάδας "
                                        + BASEREVENUEINCOME_VALUES.get("Germany")
                                        + " η βάση εσόδων φόρου εισοδήματος της Γερμανίας.");
                } else if (cur_value < BASEREVENUEINCOME_VALUES.get("Germany")) {
                        System.out.println(
                                        "Η Γερμανία έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων φόρου εισοδήματος της Ελλάδας "
                                        + BASEREVENUEINCOME_VALUES.get("Germany")
                                        + " η βάση εσόδων φόρου εισοδήματος της Γερμανίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει το ίδιο ποσό απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές με τη Γερμανία.");
                }
                // κώδικας για την Βουλγαρία
                cur_value = GreecesData.getInflation();
                if (cur_value > INFLATION_VALUES.get("Bulgaria")) {
                        System.out.println("Η οικονομία στην Ελλάδα ήταν πιο σταθερή και η ζωή πιο οικονομική.");
                        System.out.println(cur_value + " ο πληθωρισμός της Ελλάδας " + INFLATION_VALUES.get("Bulgaria")
                                        + " ο πληθωρισμός της Βουλγαρίας.");
                } else if (cur_value < INFLATION_VALUES.get("Bulgaria")) {
                        System.out.println("Η οικονομία στη Βουλγαρία ήταν πιο σταθερή και η ζωή πιο οικονομική.");
                        System.out.println(cur_value + " ο πληθωρισμός της Ελλάδας " + INFLATION_VALUES.get("Bulgaria")
                                        + " ο πληθωρισμός της Βουλγαρίας.");
                } else {
                        System.out.println(
                                        "Η οικονομία της Ελλάδας ήταν το ίδιο σταθερή με της Βουλγαρίας και η ζωή το ίδιο ακριβή.");
                }

                cur_value = GreecesData.getGdp();
                if (cur_value > GDP_VALUES.get("Bulgaria")) {
                        System.out.println("Η Ελλάδα είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.");
                        System.out.println(
                                        cur_value + " ο πληθωρισμός της Ελλάδας " + GDP_VALUES.get("Bulgaria")
                                                        + " το ΑΕΠ της Βουλγαρίας.");
                } else if (cur_value < GDP_VALUES.get("Bulgaria")) {
                        System.out.println(
                                        "Η Βουλγαρία είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.");
                        System.out.println(
                                        cur_value + " το ΑΕΠ της Ελλάδας " + GDP_VALUES.get("Bulgaria")
                                                        + " το ΑΕΠ της Βουλγαρίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα είχε την ίδια οικονομική δραστηριότητα και πλούτο ανά άτομο με τη Βουλγαρία.");
                }

                cur_value = GreecesData.getDebtRatio();
                if (cur_value > DEBTRATIO_VALUES.get("Bulgaria")) {
                        System.out.println("H Βουλγαρία είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις.");
                        System.out.println(cur_value + " ο λόγος χρέους της Ελλάδας " + DEBTRATIO_VALUES.get("Bulgaria")
                                        + " ο λόγος χρέους της Βουλγαρίας.");
                } else if (cur_value < DEBTRATIO_VALUES.get("Bulgaria")) {
                        System.out.println("Η Ελλάδα είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις.");
                        System.out.println(cur_value + " ο λόγος χρέους της Ελλάδας " + DEBTRATIO_VALUES.get("Bulgaria")
                                        + " ο λόγος χρέους της Βουλγαρίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα είχε την ίδια ευελιξία σε επενδύσεις και κρίσεις με τη Βουλγαρία.");
                }
                cur_value = GreecesData.getVatRatePercent();
                if (cur_value > VATRATEPERCENT_VALUES.get("Bulgaria")) {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Βουλγαρία επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο ΦΠΑ  της Ελλάδας " + VATRATEPERCENT_VALUES.get("Bulgaria")
                                        + " ο ΦΠΑ της Βουλγαρίας.");
                } else if (cur_value < VATRATEPERCENT_VALUES.get("Bulgaria")) {
                        System.out.println(
                                        "Η Βουλγαρία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο ΦΠΑ της Ελλάδας " + VATRATEPERCENT_VALUES.get("Bulgaria")
                                        + " ο ΦΠΑ της Βουλγαρίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, την ανάπτυξη, τις επενδύσεις και την κατανάλωση όσο και η Βουλγαρία.");
                }
                cur_value = GreecesData.getIncomeTaxRatePercent();
                if (cur_value > INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")) {
                        System.out.println(
                                        "Η Γερμανία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας "
                                        + INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")
                                        + " ο φορολογικός συντελεστής εισοδήματος της Βουλγαρίας.");
                } else if (cur_value < INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")) {
                        System.out.println(
                                        "Η Γερμανία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο φορολογικός συντελεστής εισοδήματος της Ελλάδας "
                                        + INCOMETAXRATEPERCENT_VALUES.get("Bulgaria")
                                        + " ο φορολογικός συντελεστής εισοδήματος της Βουλγαρίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, την ανάπτυξη, τις επενδύσεις και την κατανάλωση όσο και η Βουλγαρία.");
                }

                cur_value = GreecesData.getBaseRevenueForVat();
                if (cur_value > BASEREVENUEVAT_VALUES.get("Bulgaria")) {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων ΦΠΑ της Ελλάδας "
                                        + BASEREVENUEVAT_VALUES.get("Bulgaria")
                                        + " η βάση εσόδων ΦΠΑ της Βουλγαρίας.");
                } else if (cur_value < BASEREVENUEVAT_VALUES.get("Bulgaria")) {
                        System.out.println(
                                        "Η Βουλγαρία έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων ΦΠΑ της Ελλάδας "
                                        + BASEREVENUEVAT_VALUES.get("Bulgaria")
                                        + " η βάση εσόδων ΦΠΑ της Βουλγαρίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει το ίδιο ποσό απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές με τη Βουλγαρία.");
                }

                cur_value = GreecesData.getBaseRevenueForIncomeTax();
                if (cur_value > BASEREVENUEINCOME_VALUES.get("Bulgaria")) {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων φόρου εισοδήματος της Ελλάδας "
                                        + BASEREVENUEINCOME_VALUES.get("Bulgaria")
                                        + " η βάση εσόδων φόρου εισοδήματος της Βουλγαρίας.");
                } else if (cur_value < BASEREVENUEINCOME_VALUES.get("Bulgaria")) {
                        System.out.println(
                                        "Η Βουλγαρία έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων φόρου εισοδήματος της Ελλάδας "
                                        + BASEREVENUEINCOME_VALUES.get("Bulgaria")
                                        + " η βάση εσόδων φόρου εισοδήματος της Βουλγαρίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει το ίδιο ποσό απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές με τη Βουλγαρία.");
                }
                // κώδικας για την Ιταλία
                cur_value = GreecesData.getInflation();
                if (cur_value > INFLATION_VALUES.get("Italy")) {
                        System.out.println("Η οικονομία στην Ελλάδα ήταν πιο σταθερή και η ζωή πιο οικονομική.");
                        System.out.println(cur_value + " ο πληθωρισμός της Ελλάδας " + INFLATION_VALUES.get("Italy")
                                        + " ο πληθωρισμός της Ιταλίας.");
                } else if (cur_value < INFLATION_VALUES.get("Italy")) {
                        System.out.println("Η οικονομία στην Ιταλία ήταν πιο σταθερή και η ζωή πιο οικονομική.");
                        System.out.println(cur_value + " ο πληθωρισμός της Ελλάδας " + INFLATION_VALUES.get("Italy")
                                        + " ο πληθωρισμός της Ιταλίας.");
                } else {
                        System.out.println(
                                        "Η οικονομία της Ελλάδας ήταν το ίδιο σταθερή με της Ιταλίας και η ζωή το ίδιο ακριβή.");
                }

                cur_value = GreecesData.getGdp();
                if (cur_value > GDP_VALUES.get("Italy")) {
                        System.out.println("Η Ελλάδα είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.");
                        System.out.println(
                                        cur_value + " ο πληθωρισμός της Ελλάδας " + GDP_VALUES.get("Italy")
                                                        + " το ΑΕΠ της Ιταλίας.");
                } else if (cur_value < GDP_VALUES.get("Italy")) {
                        System.out.println("Η Ιταλία είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.");
                        System.out.println(cur_value + " το ΑΕΠ της Ελλάδας " + GDP_VALUES.get("Italy")
                                        + " το ΑΕΠ της Ιταλίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα είχε την ίδια οικονομική δραστηριότητα και πλούτο ανά άτομο με την Ιταλία.");
                }

                cur_value = GreecesData.getDebtRatio();
                if (cur_value > DEBTRATIO_VALUES.get("Italy")) {
                        System.out.println("H Ιταλία είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις.");
                        System.out.println(cur_value + " ο λόγος χρέους της Ελλάδας " + DEBTRATIO_VALUES.get("Italy")
                                        + " ο λόγος χρέους της Ιταλίας.");
                } else if (cur_value < DEBTRATIO_VALUES.get("Italy")) {
                        System.out.println("H Ελλάδα είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις");
                        System.out.println(cur_value + " ο λόγος χρέους της Ελλάδας " + DEBTRATIO_VALUES.get("Italy")
                                        + " ο λόγος χρέους της Ιταλίας.");
                } else {
                        System.out.println("Η Ελλάδα είχε την ίδια ευελιξία σε επενδύσεις και κρίσεις με την Ιταλία.");
                }
                cur_value = GreecesData.getVatRatePercent();
                if (cur_value > VATRATEPERCENT_VALUES.get("Italy")) {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ιταλία επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(
                                        cur_value + " ο ΦΠΑ  της Ελλάδας " + VATRATEPERCENT_VALUES.get("Italy")
                                                        + " ο ΦΠΑ της Ιταλίας.");
                } else if (cur_value < VATRATEPERCENT_VALUES.get("Italy")) {
                        System.out.println(
                                        "Η Ιταλία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(
                                        cur_value + " ο ΦΠΑ της Ελλάδας " + VATRATEPERCENT_VALUES.get("Italy")
                                                        + " ο ΦΠΑ της Ιταλίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, την ανάπτυξη, τις επενδύσεις και την κατανάλωση όσο και η Ιταλία.");
                }
                cur_value = GreecesData.getIncomeTaxRatePercent();
                if (cur_value > INCOMETAXRATEPERCENT_VALUES.get("Italy")) {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ιταλία επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας"
                                        + INCOMETAXRATEPERCENT_VALUES.get("Italy")
                                        + "ο φορολογικός συντελεστής εισοδήματος της Ιταλίας");
                } else if (cur_value < INCOMETAXRATEPERCENT_VALUES.get("Italy")) {
                        System.out.println(
                                        "Η Ιταλία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + "ο φορολογικός συντελεστής εισοδήματος της Ελλάδας"
                                        + INCOMETAXRATEPERCENT_VALUES.get("Italy")
                                        + "ο φορολογικός συντελεστής εισοδήματος της Ιταλίας");
                } else {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, την ανάπτυξη, τις επενδύσεις και την κατανάλωση όσο και η Ιταλία.");
                }

                cur_value = GreecesData.getBaseRevenueForVat();
                if (cur_value > BASEREVENUEVAT_VALUES.get("Italy")) {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων ΦΠΑ της Ελλάδας "
                                        + BASEREVENUEVAT_VALUES.get("Italy")
                                        + " η βάση εσόδων ΦΠΑ της Ιταλίας.");
                } else if (cur_value < BASEREVENUEVAT_VALUES.get("Italy")) {
                        System.out.println(
                                        "Η Ιταλία έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων ΦΠΑ της Ελλάδας "
                                        + BASEREVENUEVAT_VALUES.get("Italy")
                                        + " η βάση εσόδων ΦΠΑ της Ιταλίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει τα ίδια ποσά απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές με την Ιταλία.");
                }

                cur_value = GreecesData.getBaseRevenueForIncomeTax();
                if (cur_value > BASEREVENUEINCOME_VALUES.get("Italy")) {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων φόρου εισοδήματος της Ελλάδας "
                                        + BASEREVENUEINCOME_VALUES.get("Italy")
                                        + " η βάση εσόδων φόρου εισοδήματος της Ιταλίας.");
                } else if (cur_value < BASEREVENUEINCOME_VALUES.get("Italy")) {
                        System.out.println(
                                        "Η Ιταλία έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων φόρου εισοδήματος της Ελλάδας "
                                        + BASEREVENUEINCOME_VALUES.get("Italy")
                                        + " η βάση εσόδων φόρου εισοδήματος της Ιταλίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει τα ίδια ποσά απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές με την Ιταλία.");
                }
                // κώδικας για τη Σερβία
                cur_value = GreecesData.getInflation();
                if (cur_value > INFLATION_VALUES.get("Serbia")) {
                        System.out.println("Η οικονομία στην Ελλάδα ήταν πιο σταθερή και η ζωή πιο οικονομική.");
                        System.out.println(cur_value + " ο πληθωρισμός της Ελλάδας " + INFLATION_VALUES.get("Serbia")
                                        + " ο πληθωρισμός της Σερβίας.");
                } else if (cur_value < INFLATION_VALUES.get("Serbia")) {
                        System.out.println("Η οικονομία στη Σερβία ήταν πιο σταθερή και η ζωή πιο οικονομική.");
                        System.out.println(cur_value + " ο πληθωρισμός της Ελλάδας " + INFLATION_VALUES.get("Serbia")
                                        + " ο πληθωρισμός της Σερβίας.");
                } else {
                        System.out.println(
                                        "Η οικονομία της Ελλάδας ήταν το ίδιο σταθερή με της Σερβίας και η ζωή το ίδιο ακριβή.");
                }

                cur_value = GreecesData.getGdp();
                if (cur_value > GDP_VALUES.get("Serbia")) {
                        System.out.println("Η Ελλάδα είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.");
                        System.out.println(
                                        cur_value + " ο πληθωρισμός της Ελλάδας " + GDP_VALUES.get("Serbia")
                                                        + " το ΑΕΠ της Σερβίας.");
                } else if (cur_value < GDP_VALUES.get("Serbia")) {
                        System.out.println("Η Σερβία είχε μεγαλύτερη οικονομική δραστηριότητα και πλούτο ανά άτομο.");
                        System.out.println(cur_value + " το ΑΕΠ της Ελλάδας " + GDP_VALUES.get("Serbia")
                                        + " το ΑΕΠ της Σερβίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα είχε την ίδια οικονομική δραστηριότητα και πλούτο ανά άτομο με τη Σερβία.");
                }

                cur_value = GreecesData.getDebtRatio();
                if (cur_value > DEBTRATIO_VALUES.get("Serbia")) {
                        System.out.println("H Σερβία είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις.");
                        System.out.println(cur_value + " ο λόγος χρέους της Ελλάδας " + DEBTRATIO_VALUES.get("Serbia")
                                        + " ο λόγος χρέους της Σερβίας.");
                } else if (cur_value < DEBTRATIO_VALUES.get("Serbia")) {
                        System.out.println("H Σερβία είχε μεγαλύτερη ευελιξία σε επενδύσεις και κρίσεις.");
                        System.out.println(cur_value + " ο λόγος χρέους της Ελλάδας " + DEBTRATIO_VALUES.get("Serbia")
                                        + " ο λόγος χρέους της Σερβίας.");
                } else {
                        System.out.println("Η Ελλάδα είχε την ίδια ευελιξία σε επενδύσεις και κρίσεις με τη Σερβία.");
                }
                cur_value = GreecesData.getVatRatePercent();
                if (cur_value > VATRATEPERCENT_VALUES.get("Serbia")) {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Σερβία επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(
                                        cur_value + "ο ΦΠΑ  της Ελλάδας " + VATRATEPERCENT_VALUES.get("Serbia")
                                                        + " ο ΦΠΑ της Σερβίας.");
                } else if (cur_value < VATRATEPERCENT_VALUES.get("Serbia")) {
                        System.out.println(
                                        " Η Σερβία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(
                                        cur_value + " ο ΦΠΑ της Ελλάδας " + VATRATEPERCENT_VALUES.get("Serbia")
                                                        + " ο ΦΠΑ της Σερβίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, την ανάπτυξη, τις επενδύσεις και την κατανάλωση όσο και η Σερβία.");
                }
                cur_value = GreecesData.getIncomeTaxRatePercent();
                if (cur_value > INCOMETAXRATEPERCENT_VALUES.get("Serbia")) {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Σερβία επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο φορολογικός συντελεστής εισοδήματος  της Ελλάδας "
                                        + INCOMETAXRATEPERCENT_VALUES.get("Serbia")
                                        + " ο φορολογικός συντελεστής εισοδήματος της Σερβίας.");
                } else if (cur_value < INCOMETAXRATEPERCENT_VALUES.get("Serbia")) {
                        System.out.println(
                                        "Η Σερβία αποσκοπεί στην αύξηση των κρατικών εσόδων, ενώ η Ελλάδα επιδιώκει την ανάπτυξη, τις επενδύσεις και την κατανάλωση.");
                        System.out.println(cur_value + " ο φορολογικός συντελεστής εισοδήματος της Ελλάδας "
                                        + INCOMETAXRATEPERCENT_VALUES.get("Serbia")
                                        + " ο φορολογικός συντελεστής εισοδήματος της Σερβίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα αποσκοπεί στην αύξηση των κρατικών εσόδων, την ανάπτυξη, τις επενδύσεις και την κατανάλωση όσο και η Σερβία.");
                }

                cur_value = GreecesData.getBaseRevenueForVat();
                if (cur_value > BASEREVENUEVAT_VALUES.get("Serbia")) {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων ΦΠΑ της Ελλάδας "
                                        + BASEREVENUEVAT_VALUES.get("Serbia")
                                        + " η βάση εσόδων ΦΠΑ της Σερβίας.");
                } else if (cur_value < BASEREVENUEVAT_VALUES.get("Serbia")) {
                        System.out.println(
                                        "Η Σερβία έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων ΦΠΑ της Ελλάδας "
                                        + BASEREVENUEVAT_VALUES.get("Serbia")
                                        + " η βάση εσόδων ΦΠΑ της Σερβίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει το ίδιο ποσό απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές με τη Σερβία.");
                }

                cur_value = GreecesData.getBaseRevenueForIncomeTax();
                if (cur_value > BASEREVENUEINCOME_VALUES.get("Serbia")) {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων φόρου εισοδήματος της Ελλάδας και "
                                        + BASEREVENUEINCOME_VALUES.get("Serbia")
                                        + " η βάση εσόδων φόρου εισοδήματος της Σερβίας");
                } else if (cur_value < BASEREVENUEINCOME_VALUES.get("Serbia")) {
                        System.out.println(
                                        "Η Σερβία έχει τη δυνατότητα να εισπράξει περισσότερα απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές.");
                        System.out.println(cur_value + " η βάση εσόδων φόρου εισοδήματος της Ελλάδας "
                                        + BASEREVENUEINCOME_VALUES.get("Serbia")
                                        + " η βάση εσόδων φόρου εισοδήματος της Σερβίας.");
                } else {
                        System.out.println(
                                        "Η Ελλάδα έχει τη δυνατότητα να εισπράξει το ίδιο ποσό απ' τους φόρους εφαρμόζοντας τις ίδιες πολιτικές με τη Σερβία.");
                }
        }
}
