package gr.aueb.dmst.detFiscal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class BudgetDetailsTest {

   // δημιουργώ αντικείμενο της BudgetDetails για να τρέξω τα tests
   private MacroData dt;
   private BudgetDetails bdgtDetails;

  @BeforeEach
   public void setup() {
      this.dt = new MacroData();
      this.bdgtDetails = new BudgetDetails(dt);
   }

   @Test
   public void testCheckInflation_highValue() {
      dt.setInflation(-0.6);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkInflation();
      });
   }

   @Test
   public void testCheckInflation_lowValue() {
      dt.setInflation(1.4);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkInflation();
      });
   }

   @Test
   public void testCheckGdp_highValue() {
      dt.setGdp(1.5);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkGdp();
      });
   }

   @Test
   public void testCheckGdp_lowValue() {
      dt.setGdp(-1.4);
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkGdp();
      });
   }

   @Test
   public void testCheckDebtRatio_highValue() {
      dt.setDebtRatio(5.1);
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
      dt.setVatRatePercent(1.2);
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
      dt.setIncomeTaxRatePercent(1.2);
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
      String value = bdgtDetails.characterizeTotal();
      double balance = 200;
      assertEquals("Πλεόνασμα", value);
   }

   @Test
   public void testCharacterizeTotal_Negative() {
      String value = bdgtDetails.characterizeTotal();
      double balance = -50;
      assertEquals("Έλλειμμα", value);
   }

   @Test
   public void testCharacterizeTotal_Zero() {
      String value = bdgtDetails.characterizeTotal();
      double balance = 0;
      assertEquals("Ισοσκελισμένος Προϋπολογισμός", value);
   }
}
