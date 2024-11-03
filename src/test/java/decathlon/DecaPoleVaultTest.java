package decathlon;
import common.InputResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecaPoleVaultTest {
    DecaPoleVault event = new DecaPoleVault();
    @BeforeEach
    void setUp() {
        event = new DecaPoleVault();
    }
    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputResult = 501; // Input in centimeters
        double expectedScore = 913;

        double actual = event.calculateResult(inputResult);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidDistance() throws InvalidResultException {
        // Test with a valid time that falls within the acceptable range (e.g., 600 centimeters)
        int expectedScore = event.calc.calculateField(0.2797, 100, 1.35, 600.0); // Calculation based on method formula
        int actual = event.calculateResult(600.0);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 100 centimeters, acceptable)
        int expectedScore = event.calc.calculateField(0.2797, 100, 1.35, 100.0);
        int result = event.calculateResult(100.0);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 1000 centimeters, acceptable)
        int expectedScore = event.calc.calculateField(0.2797, 100, 1.35, 1000.0);
        int result = event.calculateResult(1000.0);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withLowDistance() {
        try {
            event.calculateResult(99);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
    @Test
    void testCalculateResult_withHighDistance() {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        try {
            event.calculateResult(1001);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException ignored) {
        }
}
}
