package gr.aueb.dmst.detFiscal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BudgetDetailsTest {

   // δημιουργώ αντικείμενο της BudgetDetails για να τρέξω τα tests

   private BudgetDetails bdgtDetails = new BudgetDetails();

   // test ελέγχου ότι η Inflation λαμβάνει την τιμή που ο χρήστης θέτει
   @Test
   public void testSetInflation_highValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setInflation(-0.6);
      });
   }

   @Test
   public void testSetInflation_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setInflation(1.4);
      });
   }

   @Test
   public void testSetGdp_highValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setGdp(1.5);
      });
   }

   @Test
   public void testSetGdp_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setGdp(-1.4);
      });
   }

   @Test
   public void testSetDeptRatio_highValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setDebtRatio(5.1);
      });
   }

   @Test
   public void testSetDeptRatio_lowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setDebtRatio(-0.2);
      });
   }

   @Test
   public void testCharacterizeTotal_Positive() {
      String value = bdgtDetails.characterizeTotal(200);
      assertEquals("Πλεόνασμα", value);
   }

   @Test
   public void testCharacterizeTotal_Negative() {
      String value = bdgtDetails.characterizeTotal(-50);
      assertEquals("Έλλειμμα", value);
   }

   @Test
   public void setInflation_ValidValueInGet() {
      bdgtDetails.setInflation(0.6);
      assertEquals(0.6, bdgtDetails.getInflation());
   }

   @Test
   public void setInflation_invalidLowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setInflation(-0.1);
      });
   }

   @Test
   public void setInflation_invalidHighValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setInflation(1.1);
      });
   }

   @Test
   public void setGDP_ValidValueInGet() {
      bdgtDetails.setGdp(0.1);
      assertEquals(0.1, bdgtDetails.getGdp());
   }

   @Test
   public void setGDP_invalidLowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setGdp(-1.5);
      });
   }

   @Test
   public void setGDP_invalidHighValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setGdp(1.2);
      });
   }

   @Test
   public void setDeptRatio_ValidValueInGet() {
      bdgtDetails.setDebtRatio(3.2);
      assertEquals(3.2, bdgtDetails.getDeptRatio());
   }

   @Test
   public void setDeptRatio_invalidLowValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setDebtRatio(-1.1);
      });
   }

   @Test
   public void setDeptRatio_invalidHighValue() {
      assertThrows(IllegalArgumentException.class, () -> {
         bdgtDetails.setDebtRatio(5.2);
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
