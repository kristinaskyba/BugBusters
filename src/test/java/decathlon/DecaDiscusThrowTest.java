package decathlon;

import common.InputResult;
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
    void testCalculateResult_withLowDistance() throws InvalidResultException {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        // InputResult
        DecaDiscusThrow.inputResult = new InputResult() {
            @Override
            public double enterResult() {
                // Return a valid value when asked for new input to stop never ending prompt
                return 4.0;
            }
        };
        int expectedScore = event.calc.calculateField(12.91, 4, 1.1, 4.0); // The expected value when the final
        // valid time is used
        int actual = event.calculateResult(3.9); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withHighDistance() throws InvalidResultException {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        // InputResult
        DecaDiscusThrow.inputResult = new InputResult() {
            @Override
            public double enterResult() {
                // Return a valid value when asked for new input to stop never ending prompt
                return 85.0;
            }
        };
        int expectedScore = event.calc.calculateField(12.91, 4, 1.1, 85.0); // The expected value when the final
        // valid meters are used
        int actual = event.calculateResult(85.1); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
    }


