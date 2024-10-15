package decathlon;

import common.InputResult;
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
    public void testCalculateScore() {
        double inputTime = 21; // Input in seconds
        double expectedScore = 274;

        double actual = event.calculateResult(inputTime);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withValidTime() {
        // Test with a valid time that falls within the acceptable range
        int expectedScore = event.calc.calculateTrack(5.74352, 28.5, 1.92, 21); // Calculation based on method
        // formula
        int actual = event.calculateResult(21);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(5.74352, 28.5, 1.92, 10); // Calculation based on method
        int result = event.calculateResult(10);
        assertEquals(expectedScore, result);
    }
    @Test
    void testCalculateResult_withExactHighBoundary() {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(5.74352, 28.5, 1.92, 28.5); // Calculation based on method
        int result = event.calculateResult(28.5);
        assertEquals(expectedScore, result);
    }
    @Test
    void testCalculateResult_withLowTime() {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        // InputResult
        event.inputResult = new InputResult() {
            @Override
            public double enterResult() {
                // Return a valid value when asked for new input to stop never ending prompt
                return 21;
            }
        };
        int expectedScore = event.calc.calculateTrack(5.74352, 28.5, 1.92, 21);// Calculation based on method
        // valid time is used
        int actual = event.calculateResult(9.9); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withHighTime() {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        // InputResult
        event.inputResult = new InputResult() {
            @Override
            public double enterResult() {
                // Return a valid value when asked for new input to stop never ending prompt
                return 21;
            }
        };
        int expectedScore = event.calc.calculateTrack(5.74352, 28.5, 1.92, 21);// Calculation based on method
        // valid time is used
        int actual = event.calculateResult(28.6); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
}
