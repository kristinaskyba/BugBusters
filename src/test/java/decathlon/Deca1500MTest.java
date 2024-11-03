package decathlon;

import common.InputResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Deca1500MTest {
    Deca1500M event = new Deca1500M();

    @BeforeEach
    void setUp() {
        event = new Deca1500M();
    }

    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputTime = 151.0; // Input in seconds
        double expectedScore = 1709;

        double actual = event.calculateResult(inputTime);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withValidTime() throws InvalidResultException {
        // Test with a valid time that falls within the acceptable range (e.g., 151 seconds)
        int expectedScore = event.calc.calculateTrack(0.03768, 480, 1.85, 151.0); // Calculation based on method formula
        int actual = event.calculateResult(151.0);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 150 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(0.03768, 480, 1.85, 150.0);
        int result = event.calculateResult(150.0);
        assertEquals(expectedScore, result);
    }

    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 480 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(0.03768, 480, 1.85, 480.0);
        int result = event.calculateResult(480.0);
        assertEquals(expectedScore, result);
    }

    @Test
    void testCalculateResult_withLowTime() {
        try {
            event.calculateResult(149);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }

    @Test
    void testCalculateResult_withHighTime() {
        try {
            event.calculateResult(481);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException ignored) {
        }

}
}
