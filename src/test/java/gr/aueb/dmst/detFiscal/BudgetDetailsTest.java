package gr.aueb.dmst.detFiscal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class BudgetDetailsTest {

   // δημιουργώ αντικείμενο της BudgetDetails για να τρέξω τα tests
   private MacroData dt;
   private BudgetDetails bdgtDetails;
   private BudgetSummary bdgtSummary;

  @BeforeEach
   public void setup() {
      this.dt = new MacroData();
      this.bdgtSummary = new BudgetSummary();
      this.bdgtDetails = new BudgetDetails(dt, bdgtSummary);
      
   }

   @Test
   public void testCheckInflation_highValue() {
      dt.setInflation(104);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkInflation();
      });
   }

   @Test
   public void testCheckInflation_lowValue() {
      dt.setInflation(-0.6);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkInflation();
      });
   }

   @Test
   public void testCheckGdp_highValue() {
      dt.setGdp(105);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkGdp();
      });
   }

   @Test
   public void testCheckGdp_lowValue() {
      dt.setGdp(-104);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkGdp();
      });
   }

   @Test
   public void testCheckDebtRatio_highValue() {
      dt.setDebtRatio(105);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkDebtRatio();
      });
   }

   @Test
   public void testCheckDebtRatio_lowValue() {
      dt.setDebtRatio(-0.2);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkDebtRatio();
      });
   }

   @Test
    public void testCheckVatRatePercent_highValue() {
      dt.setVatRatePercent(102);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkVatRatePercent();
      });
   }


   @Test
    public void testCheckVatRatePercent_lowValue() {
      dt.setVatRatePercent(-0.2);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkVatRatePercent();
      });
   }

   @Test
    public void testCheckIncomeTaxRatePercent_lowValue() {
      dt.setIncomeTaxRatePercent(-0.2);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkIncomeTaxRatePercent();
      });
   }

   @Test
    public void testCheckIncomeTaxRatePercent_highValue() {
      dt.setIncomeTaxRatePercent(102);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkIncomeTaxRatePercent();
      });
   }
  
   @Test
    public void testCheckBaseRevenueForVat_lowValue() {
      dt.setBaseRevenueForVat(-0.2);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkBaseRevenueForVat();
      });
   }

    @Test
    public void testCheckBaseRevenueForIncomeTax_lowValue() {
      dt.setBaseRevenueForIncomeTax(-0.2);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkBaseRevenueForIncomeTax();
      });
   }

   // tests για τον προϋπολογισμό

   @Test
   public void testCharacterizeTotal_Positive() {
      BudgetSummary summaryStub = new BudgetSummary() {
         @Override
         public double calculateBalance() {
            return 200;
         }
      };
      BudgetDetails bdgtd = new BudgetDetails(dt, summaryStub);
      bdgtd.valueForBalance();
      assertEquals("Πλεόνασμα", bdgtd.characterizeTotal());
   }

   @Test
   public void testCharacterizeTotal_Negative() {
      BudgetSummary summaryStub = new BudgetSummary() {
         @Override
         public double calculateBalance() {
            return -50;
         }
      };
      BudgetDetails bdgtd = new BudgetDetails(dt, summaryStub);
      bdgtd.valueForBalance();
      assertEquals("Έλλειμμα", bdgtd.characterizeTotal());
   }

   @Test
   public void testCharacterizeTotal_Zero() {
      BudgetSummary summaryStub = new BudgetSummary() {
         @Override
         public double calculateBalance() {
            return 0;
         }
      };
      BudgetDetails bdgtd = new BudgetDetails(dt, summaryStub);
      bdgtd.valueForBalance();
      assertEquals("Ισοσκελισμένος Προϋπολογισμός", bdgtd.characterizeTotal());
   }
}
