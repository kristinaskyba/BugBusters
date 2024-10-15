package decathlon;

import common.InputResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Deca1500MTest {
    Deca1500M event = new Deca1500M();

    @BeforeEach
    void setUp() {
        event = new Deca1500M();
    }

    @Test
    public void testCalculateScore() {
        double inputTime = 151.0; // Input in seconds
        double expectedScore = 1709;

        double actual = event.calculateResult(inputTime);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withValidTime() {
        // Test with a valid time that falls within the acceptable range (e.g., 151 seconds)
        int expectedScore = event.calc.calculateTrack(0.03768, 480, 1.85, 151.0); // Calculation based on method formula
        int actual = event.calculateResult(151.0);
        assertEquals(expectedScore, actual);
    }

    @Test
    void testCalculateResult_withExactLowBoundary() {
        // Test with a valid time on the boundary (exactly 150 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(0.03768, 480, 1.85, 150.0);
        int result = event.calculateResult(150.0);
        assertEquals(expectedScore, result);
    }

    @Test
    void testCalculateResult_withExactHighBoundary() {
        // Test with a valid time on the boundary (exactly 480 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(0.03768, 480, 1.85, 480.0);
        int result = event.calculateResult(480.0);
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
                return 150.0;
            }
        };
        int expectedScore = event.calc.calculateTrack(0.03768, 480, 1.85, 150.0); // The expected value when the final
        // valid time is used
        int actual = event.calculateResult(148.0); // Initial invalid input triggers new input
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
                return 480.0;
            }
        };
        int expectedScore = event.calc.calculateTrack(0.03768, 480, 1.85, 480.0); // The expected value when the final
        // valid time is used
        int actual = event.calculateResult(480.1); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);

}
}
