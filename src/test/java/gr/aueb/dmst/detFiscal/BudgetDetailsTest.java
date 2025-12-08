package gr.aueb.dmst.detfiscal;
import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;  

public class BudgetDetailsTest {

// δημιουργώ αντικείμενο της BudgetDetails για να τρέξω τα tests

    private BudgetDetails bdgtDetails = new BudgetDetails();

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
         bdgtDetails.setDeptRatio(5.1);
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
     public void testCharacterizeTotal_Zero() {
        String value = bdgtDetails.characterizeTotal(0);
        assertEquals("Ισοσκελισμένος Προϋπολογισμός", value);
     }
}
