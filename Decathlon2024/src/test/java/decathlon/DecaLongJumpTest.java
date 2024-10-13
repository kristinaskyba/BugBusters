package decathlon;

import common.InputResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecaLongJumpTest {
    DecaLongJump event = new DecaLongJump();
    @BeforeEach
    void setUp() {
        event = new DecaLongJump();
    }
    @Test
    public void testCalculateScore() {
        double inputResult = 220; // Input in centimeters
        double expectedScore = 0;
        double actual = event.calculateResult(inputResult);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidDistance() {
        // Test with a valid time that falls within the acceptable range (e.g., 400 centimeters)
        int expectedScore = event.calc.calculateField(0.14354, 220, 1.4, 400.0); // Calculation based on method formula
        int actual = event.calculateResult(400.0);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() {
        // Test with a valid time on the boundary (exactly 220 centimeters, acceptable)
        int expectedScore = event.calc.calculateField(0.14354, 220, 1.4, 220.0);
        int result = event.calculateResult(220.0);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withExactHighBoundary() {
        // Test with a valid time on the boundary (exactly 1000 centimeters, acceptable)
        int expectedScore = event.calc.calculateField(0.14354, 220, 1.4, 1000.0);
        int result = event.calculateResult(1000.0);
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
                return 220.0;
            }
        };
        int expectedScore = event.calc.calculateField(0.14354, 220, 1.4, 220.0); // The expected value when the final
        // valid time is used
        int actual = event.calculateResult(219.9); // Initial invalid input triggers new input
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
                return 1000.0;
            }
        };
        int expectedScore = event.calc.calculateField(0.14354, 220, 1.4, 1000.0); // The expected value when the final
        // valid meters are used
        int actual = event.calculateResult(1000.1); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
}
