package decathlon;

import common.InputResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecaHighJumpTest {
    DecaHighJump event = new DecaHighJump();
    @BeforeEach
    void setUp() {
        event = new DecaHighJump();
    }
    @Test
    public void testCalculateScore() {
        double inputResult = 201; // Input in centimeters
        double expectedScore = 813;

        double actual = event.calculateResult(inputResult);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidDistance() {
        // Test with a valid time that falls within the acceptable range (e.g., 225 centimeters)
        int expectedScore = event.calc.calculateField(0.8465, 75, 1.42, 225.0); // Calculation based on method formula
        int actual = event.calculateResult(225.0);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() {
        // Test with a valid time on the boundary (exactly 75 centimeters, acceptable)
        int expectedScore = event.calc.calculateField(0.8465, 75, 1.42, 75.0);
        int result = event.calculateResult(75.0);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withExactHighBoundary() {
        // Test with a valid time on the boundary (exactly 300 centimeters, acceptable)
        int expectedScore = event.calc.calculateField(0.8465, 75, 1.42, 300.0);
        int result = event.calculateResult(300.0);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withLowDistance() {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        // InputResult
        event.inputResult = new InputResult() {
            @Override
            public double enterResult() {
                // Return a valid value when asked for new input to stop never ending prompt
                return 75.0;
            }
        };
        int expectedScore = event.calc.calculateField(0.8465, 75, 1.42, 75.0); // The expected value when the final
        // valid time is used
        int actual = event.calculateResult(74.9); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withHighDistance() {
        // Since I couldn't simulate users input, and it is prompting valid time with if-else, I did it manually via
        // InputResult
        event.inputResult = new InputResult() {
            @Override
            public double enterResult() {
                // Return a valid value when asked for new input to stop never ending prompt
                return 300.0;
            }
        };
        int expectedScore = event.calc.calculateField(0.8465, 75, 1.42, 300.0); // The expected value when the final
        // valid meters are used
        int actual = event.calculateResult(300.1); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }

}
