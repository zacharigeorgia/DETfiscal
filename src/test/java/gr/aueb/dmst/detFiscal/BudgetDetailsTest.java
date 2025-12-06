package gr.aueb.dmst.detfiscal;
import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;  

public class BudgetDetailsTest {

// δημιουργώ αντικείμενο της BudgetDetails για να τρέξω τα tests

    private BudgetDetails bdgtDetails = new BudgetDetails();

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
