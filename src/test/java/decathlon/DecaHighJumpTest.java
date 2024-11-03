package decathlon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecaHighJumpTest {

    DecaHighJump event = new DecaHighJump();

    @BeforeEach
    void setUp() {
        event = new DecaHighJump();
    }

    @Test
    void testCalculateResult_withValidDistance() throws InvalidResultException {
        int expectedScore = event.calc.calculateField(0.8465, 75, 1.42, 80); // Calculation based on method formula
        int actual = event.calculateResult(80);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 4m, acceptable)
        int expectedScore = event.calc.calculateField(0.8465, 75, 1.42, 75); // Calculation based on method formula
        int actual = event.calculateResult(75);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 85m, acceptable)
        int expectedScore = event.calc.calculateField(0.8465, 75, 1.42, 300); // Calculation based on method formula
        int actual = event.calculateResult(300);
        assertEquals(expectedScore, actual);
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
        } catch (InvalidResultException ignored) {
        }
    }
}
