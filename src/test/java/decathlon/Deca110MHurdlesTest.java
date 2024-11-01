package decathlon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Deca110MHurdlesTest {

    Deca110MHurdles event = new Deca110MHurdles();

    @BeforeEach
    void setUp() {
        event = new Deca110MHurdles();
    }

    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputTime = 21; // Input in seconds
        double expectedScore = 274;

        double actual = event.calculateResult(inputTime);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withValidTime() throws InvalidResultException {
        // Test with a valid time that falls within the acceptable range
        int expectedScore = event.calc.calculateTrack(5.74352, 28.5, 1.92, 21); // Calculation based on method
        // formula
        int actual = event.calculateResult(21);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(5.74352, 28.5, 1.92, 10); // Calculation based on method
        int result = event.calculateResult(10);
        assertEquals(expectedScore, result);
    }

    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(5.74352, 28.5, 1.92, 28.5); // Calculation based on method
        int result = event.calculateResult(28.5);
        assertEquals(expectedScore, result);
    }

    @Test
    void testCalculateResult_withLowTime() {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        // InputResult
       /* event.inputResult = new InputResult() {
            @Override
            public double enterResult() {
                // Return a valid value when asked for new input to stop never ending prompt
                return 21;
            }
        };
        int expectedScore = event.calc.calculateTrack(5.74352, 28.5, 1.92, 21);
        // Calculation based on method valid time is used
       int actual = event.calculateResult(9.9); // Initial invalid input triggers new input
      assertEquals(expectedScore, actual);
        */
        try {
            event.calculateResult(9.9);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException e) {
            // Exception was thrown as expected, so the test will pass.
        }
    }

    @Test
    void testCalculateResult_withHighTime() {
        try {
            event.calculateResult(28.6);
            Assertions.fail("Expected InvalidResultException was not thrown.");
        } catch (InvalidResultException ignored) {
        }
    }
}
