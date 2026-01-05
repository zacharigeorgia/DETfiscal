package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    // Δημιουργούμε μια μικρή concrete κλάση για το τεστ
    private static class TestAccount extends Account {
        public TestAccount(String name, double amount) {
            this.name = name;
            this.amount = amount;
        }

        @Override
        public void increase(double x) {
            this.amount += x;
        }

        @Override
        public void decrease(double x) {
            this.amount -= x;
        }
    }

    @Test
    void testGettersSetters() {
        TestAccount acc = new TestAccount("Φόροι", 1000);

        // Έλεγχος αρχικών τιμών
        assertEquals("Φόροι", acc.getName());
        assertEquals(1000, acc.getAmount(), 0.001);

        // Αλλαγή ποσού με setter
        acc.setAmount(1200);
        assertEquals(1200, acc.getAmount(), 0.001);
    }

    @Test
    void testIncreaseDecrease() {
        TestAccount acc = new TestAccount("Έσοδα", 500);

        acc.increase(200);
        assertEquals(700, acc.getAmount(), 0.001);

        acc.decrease(100);
        assertEquals(600, acc.getAmount(), 0.001);
    }
}
