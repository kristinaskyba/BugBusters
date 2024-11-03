package heptathlon;

import common.InputResult;
import decathlon.InvalidResultException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hep200MTest {
    Hep200M event = new Hep200M();
    @BeforeEach
    void setUp() {
        event = new Hep200M();
    }
    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputResult = 30; // Input in meters
        double expectedScore = 482;

        double actual = event.calculateResult(inputResult);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidDistance() throws InvalidResultException {

        // Test with a valid time that falls within the acceptable range
        int expectedScore = event.calc.calculateTrack(4.99087, 42.5, 1.81, 30); // Calculation based on method formula
        int actual = event.calculateResult(30);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(4.99087, 42.5, 1.81, 20);
        int result = event.calculateResult(20);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(4.99087, 42.5, 1.81, 42.5);
        int result = event.calculateResult(42.5);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withLowDistance() {
        try {
            event.calculateResult(19);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
    @Test
    void testCalculateResult_withHighDistance() {
        try {
            event.calculateResult(43);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
}
