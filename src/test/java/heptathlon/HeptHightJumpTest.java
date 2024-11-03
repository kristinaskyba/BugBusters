package heptathlon;

import common.InputResult;
import decathlon.InvalidResultException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeptHightJumpTest {
    HeptHightJump event = new HeptHightJump();
    @BeforeEach
    void setUp() {
        event = new HeptHightJump();
    }
    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputResult = 255; // Input in meters
        double expectedScore = 2023; //wrong calculation if input is 254, should be result 20 according to excel
        // because private double A = 0.13454 instead of A = 0.14354

        double actual = event.calculateResult(inputResult);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidDistance() throws InvalidResultException {

        // Test with a valid time that falls within the acceptable range (e.g., 250 meters)
        int expectedScore = event.calc.calculateField(1.84523, 75, 1.348, 254); // Calculation based on method formula
        int actual = event.calculateResult(254);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateField(1.84523, 75, 1.348, 75);
        int result = event.calculateResult(75);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateField(1.84523, 75, 1.348, 300);
        int result = event.calculateResult(300);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withLowDistance() {
        try {
            event.calculateResult(74);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
    @Test
    void testCalculateResult_withHighDistance() {
        try {
            event.calculateResult(301);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }
}
