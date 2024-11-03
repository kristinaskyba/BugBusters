package decathlon;

import common.InputResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecaLongJumpTest {
    DecaLongJump event = new DecaLongJump();
    @BeforeEach
    void setUp() {
        event = new DecaLongJump();
    }
    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputResult = 220; // Input in centimeters
        double expectedScore = 0;
        double actual = event.calculateResult(inputResult);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidDistance() throws InvalidResultException {
        // Test with a valid time that falls within the acceptable range (e.g., 400 centimeters)
        int expectedScore = event.calc.calculateField(0.14354, 220, 1.4, 400.0); // Calculation based on method formula
        int actual = event.calculateResult(400.0);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 220 centimeters, acceptable)
        int expectedScore = event.calc.calculateField(0.14354, 220, 1.4, 220.0);
        int result = event.calculateResult(220.0);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 1000 centimeters, acceptable)
        int expectedScore = event.calc.calculateField(0.14354, 220, 1.4, 1000.0);
        int result = event.calculateResult(1000.0);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withLowDistance() {
        try {
            event.calculateResult(219);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
    @Test
    void testCalculateResult_withHighDistance() {
        try {
            event.calculateResult(1001);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
}
