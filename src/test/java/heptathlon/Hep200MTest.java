package heptathlon;

import common.InputResult;
import decathlon.InvalidResultException;
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
    void testCalculateResult_withLowDistance() throws InvalidResultException {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        // InputResult
        event.inputResult = new InputResult() {
            @Override
            public double enterResult() {
                // Return a valid value when asked for new input to stop never ending prompt
                return 30;
            }
        };
        int expectedScore = event.calc.calculateTrack(4.99087, 42.5, 1.81, 30); // The expected value when the final
        // valid time is used
        int actual = event.calculateResult(19.9); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withHighDistance() throws InvalidResultException {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        // InputResult
        event.inputResult = new InputResult() {
            @Override
            public double enterResult() {
                // Return a valid value when asked for new input to stop never ending prompt
                return 30;
            }
        };
        int expectedScore = event.calc.calculateTrack(4.99087, 42.5, 1.81, 30); // The expected value when the final
        // valid meters are used
        int actual = event.calculateResult(42.6); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
}
