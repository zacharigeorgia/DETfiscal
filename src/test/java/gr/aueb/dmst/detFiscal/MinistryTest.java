package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class MinistryTest {

    @Test
    @DisplayName("Έλεγχος Ministry: Πλήρης κάλυψη πεδίων")
    void testMinistryProperties() {
        Ministry m = new Ministry();
        
        m.setCode("ΥΠ01");
        m.setName("Υπουργείο Ψηφιακής Διακυβέρνησης");
        m.setRegularBudget(500000.0);
        m.setPublicInvestments(200000.0);
        m.setTotal(700000.0);

        assertEquals("ΥΠ01", m.getCode());
        assertEquals("Υπουργείο Ψηφιακής Διακυβέρνησης", m.getName());
        assertEquals(500000.0, m.getRegularBudget());
        assertEquals(200000.0, m.getPublicInvestments());
        assertEquals(700000.0, m.getTotal());

        // Έλεγχος toString
        String output = m.toString();
        assertTrue(output.contains("ΥΠ01"));
        assertTrue(output.contains("700,000.00"));
    }
}