package gr.aueb.dmst.detFiscal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BudgetDetailsTest {

   // δημιουργώ αντικείμενο της BudgetDetails για να τρέξω τα tests

   private MacroData bdgtDetails = new MacroData();

   // test ελέγχου ότι η Inflation λαμβάνει την τιμή που ο χρήστης θέτει
   @Test
   public void testCheckInflation_highValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkInflation(-0.6);
      });
   }

   @Test
   public void testCheckInflation_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkInflation(1.4);
      });
   }

   @Test
   public void testCheckGdp_highValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkGdp(1.5);
      });
   }

   @Test
   public void testCheckGdp_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkGdp(-1.4);
      });
   }

   @Test
   public void testCheckDeptRatio_highValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkDebtRatio(5.1);
      });
   }

   @Test
   public void testCheckDeptRatio_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkDebtRatio(-0.2);
      });
   }

   @Test
    public void testCheckVatRatePercent_highValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkVatRatePercent(1.2);
      });
   }


   @Test
    public void testCheckVatRatePercent_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkVatRatePercent(-0.2);
      });
   }

   @Test
    public void testCheckIncomeTaxRatPercent_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkIncomeTaxRatePercent(-0.2);
      });
   }

   @Test
    public void testCheckIncomeTaxRatPercent_highValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkIncomeTaxRatePercent(1.2);
      });
   }
  
   @Test
   public void testCheckIncomeTaxRatPercent_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkIncomeTaxRatePercent(-0.2);
      });
   }

   @Test
    public void testCheckBaseRevenueForVar_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkBaseRevenueForIncomeTax(-0.2);
      });
   }

    @Test
    public void testCheckBaseRevenueForIncomeTax_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.checkBaseRevenueForIncomeTax(-0.2);
      });
   }

   // tests για τον προϋπολογισμό

   @Test
   public void testCharacterizeTotal_Positive2() {
      String value = bdgtDetails.characterizeTotal(200);
      assertEquals("Πλεόνασμα", value);
   }

   @Test
   public void testCharacterizeTotal_Negative2() {
      String value = bdgtDetails.characterizeTotal(-50);
      assertEquals("Έλλειμμα", value);
   }

   @Test
   public void testCharacterizeTotal_Zero() {
      String value = bdgtDetails.characterizeTotal(0);
      assertEquals("Ισοσκελισμένος Προϋπολογισμός", value);
   }
}
