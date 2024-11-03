package heptathlon;

import common.InputResult;
import decathlon.Deca100M;
import decathlon.InvalidResultException;
import heptathlon.Hep800M;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hep800MTest {

    Hep800M event = new Hep800M();
    @BeforeEach
    void setUp() {
        event = new Hep800M();
    }
    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputTime = 210; // Input in seconds
        double expectedScore = 137;

        double actual = event.calculateResult(inputTime);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidTime() throws InvalidResultException {
        // Test with a valid time that falls within the acceptable range
        int expectedScore = event.calc.calculateTrack(0.11193, 254, 1.88, 220); // Calculation based on method
        // formula
        int actual = event.calculateResult(220);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(0.11193, 254, 1.88, 70); // Calculation based on method
        int result = event.calculateResult(70);
        assertEquals(expectedScore, result);
    }
    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(0.11193, 254, 1.88, 254); // Calculation based on method
        int result = event.calculateResult(254);
        assertEquals(expectedScore, result);
    }
    @Test
    void testCalculateResult_withLowTime() {
        try {
            event.calculateResult(69);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
    @Test
    void testCalculateResult_withHighTime() {
        try {
            event.calculateResult(255);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
}


