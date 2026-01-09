package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;

public class ChangeLogTest {

    private ChangeLog changeLog;

    @BeforeEach
    void setUp() {
        Locale.setDefault(Locale.US);
        changeLog = new ChangeLog();
    }

    @Test
    @DisplayName("Έλεγχος καταγραφής αλλαγής")
    void testAddChangeFormatting() {
        changeLog.addChange("ΦΠΑ", "Έσοδα", 100.0, 120.0);
        String log = changeLog.getFormattedLog();
        
        // Ο κώδικάς σου χρησιμοποιεί: Changed %s '%s': %.2f -> %.2f
        assertTrue(log.contains("Changed Έσοδα 'ΦΠΑ'"));
        assertTrue(log.contains("100.00"));
        assertTrue(log.contains("120.00"));
    }

    @Test
    @DisplayName("Έλεγχος ύπαρξης ημερομηνίας")
    void testLogTimestamp() {
        changeLog.log("Test Message");
        String log = changeLog.getFormattedLog();
        // Ελέγχουμε αν υπάρχει η ημερομηνία σε μορφή [YYYY-MM-DD]
        assertTrue(log.matches("(?s).*\\[\\d{4}-\\d{2}-\\d{2}.*"));
    }
}