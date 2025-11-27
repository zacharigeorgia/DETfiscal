package gr.aueb.dmst.detfiscal;
import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;  

public class BudgetDetailsTest {

// δημιουργώ αντικείμενο της BudgetDetails για να τρέξω τα tests

    private BudgetDetails bdgtDetails = new BudgetDetails();

// tests για τον προϋπολογισμό

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

//test ελέγχου ότι η Inflation λαμβάνει την τιμή που ο χρήστης θέτει

     @Test
     public void setInflation_ValidValueInGet() {
        bdgtDetails.setInflation(0.6);
        assertEquals(0.6, bdgtDetails.getInflation());
     }

     @Test
     public void setGDP_ValidValueInGet() {
        bdgtDetails.setGDP(0.1);
        assertEquals(0.1, bdgtDetails.getGDP());
     }
     
     @Test
     public void setDeptRatio_ValidValueInGet() {
        bdgtDetails.setDeptRatio(3.2);
        assertEquals(3.2, bdgtDetails.getDeptRatio());
     }
}
