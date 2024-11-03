package heptathlon;

import common.InputResult;
import decathlon.InvalidResultException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeptJavelinThrowBVATest {
    HeptJavelinThrow event = new HeptJavelinThrow();
    @BeforeEach
    void setUp() {
        event = new HeptJavelinThrow();
    }
    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputResult = 50; // Input in meters
        double expectedScore = 860; //


        double actual = event.calculateResult(inputResult);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidDistance() throws InvalidResultException {

        // Test with a valid time that falls within the acceptable range (e.g., 250 meters)
        int expectedScore = event.calc.calculateField(15.9803, 3.8, 1.04, 50); // Calculation based on method formula
        int actual = event.calculateResult(50);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateField(15.9803, 3.8, 1.04, 3.8);
        int result = event.calculateResult(3.8);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateField(15.9803, 3.8, 1.04, 110);
        int result = event.calculateResult(110);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withLowDistance() {
        try {
            event.calculateResult(3.7);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
    @Test
    void testCalculateResult_withHighDistance() {
        try {
            event.calculateResult(111);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
}
