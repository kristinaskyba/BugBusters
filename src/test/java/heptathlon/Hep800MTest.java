package heptathlon;

import common.InputResult;
import decathlon.Deca100M;
import heptathlon.Hep800M;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hep800MTest {

    Hep800M event = new Hep800M();
    @BeforeEach
    void setUp() {
        event = new Hep800M();
    }
    @Test
    public void testCalculateScore() {
        double inputTime = 210; // Input in seconds
        double expectedScore = 137;

        double actual = event.calculateResult(inputTime);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidTime() {
        // Test with a valid time that falls within the acceptable range
        int expectedScore = event.calc.calculateTrack(0.11193, 254, 1.88, 220); // Calculation based on method
        // formula
        int actual = event.calculateResult(220);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(0.11193, 254, 1.88, 70); // Calculation based on method
        int result = event.calculateResult(70);
        assertEquals(expectedScore, result);
    }
    @Test
    void testCalculateResult_withExactHighBoundary() {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateTrack(0.11193, 254, 1.88, 254); // Calculation based on method
        int result = event.calculateResult(254);
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
                return 220;
            }
        };
        int expectedScore = event.calc.calculateTrack(0.11193, 254, 1.88, 220); // Calculation based on method
        // valid time is used
        int actual = event.calculateResult(69); // Initial invalid input triggers new input
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
                return 220;
            }
        };
        int expectedScore = event.calc.calculateTrack(0.11193, 254, 1.88, 220); // Calculation based on method
        // valid time is used
        int actual = event.calculateResult(255); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
}


