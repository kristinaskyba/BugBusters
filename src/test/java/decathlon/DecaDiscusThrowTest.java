package decathlon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecaDiscusThrowTest {
    DecaDiscusThrow event = new DecaDiscusThrow();

    @BeforeEach
    void setUp() {
        event = new DecaDiscusThrow();
    }

    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputResult = 50; // Input in meters
        double expectedScore = 870;

        double actual = event.calculateResult(inputResult);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withValidDistance() throws InvalidResultException {
        // Test with a valid time that falls within the acceptable range (e.g., 11 meters)
        int expectedScore = event.calc.calculateField(12.91, 4, 1.1, 11.0); // Calculation based on method formula
        int actual = event.calculateResult(11.0);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 4m, acceptable)
        int expectedScore = event.calc.calculateField(12.91, 4, 1.1, 4.0);
        int result = event.calculateResult(4.0);
        assertEquals(expectedScore, result);
    }

    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 85m, acceptable)
        int expectedScore = event.calc.calculateField(12.91, 4, 1.1, 85.0);
        int result = event.calculateResult(85.0);
        assertEquals(expectedScore, result);
    }

    @Test
    void testCalculateResult_withLowDistance() {
        try {
            event.calculateResult(3.9);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }

    @Test
    void testCalculateResult_withHighDistance() {
        try {
            event.calculateResult(86);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException ignored) {
        }
    }
}


