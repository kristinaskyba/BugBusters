package heptathlon;

import common.InputResult;
import decathlon.InvalidResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeptJavelinThrowBVATest {
    HeptJavelinThrow event = new HeptJavelinThrow();
    @BeforeEach
    void setUp() {
        event = new HeptJavelinThrow();
    }
    @Test
    public void testCalculateScore() throws InvalidResultException {
        double inputResult = 50; // Input in meters
        double expectedScore = 860; //


        double actual = event.calculateResult(inputResult);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withValidDistance() throws InvalidResultException {

        // Test with a valid time that falls within the acceptable range (e.g., 250 meters)
        int expectedScore = event.calc.calculateField(15.9803, 3.8, 1.04, 50); // Calculation based on method formula
        int actual = event.calculateResult(50);
        assertEquals(expectedScore, actual);
    }
    @Test
    void testCalculateResult_withExactLowBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateField(15.9803, 3.8, 1.04, 3.8);
        int result = event.calculateResult(3.8);
        assertEquals(expectedScore, result); ///wrong calculation
    }
    @Test
    void testCalculateResult_withExactHighBoundary() throws InvalidResultException {
        // Test with a valid time on the boundary (exactly 5 seconds, acceptable)
        int expectedScore = event.calc.calculateField(15.9803, 3.8, 1.04, 110);
        int result = event.calculateResult(110);
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
                return 50;
            }
        };
        int expectedScore = event.calc.calculateField(15.9803, 3.8, 1.04, 50); // The expected value when the final
        // valid time is used
        int actual = event.calculateResult(3.7); // Initial invalid input triggers new input
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
                return 50;
            }
        };
        int expectedScore = event.calc.calculateField(15.9803, 3.8, 1.04, 50);// The expected value when the final
        // valid meters are used
        int actual = event.calculateResult(110.1); // Initial invalid input triggers new input
        assertEquals(expectedScore, actual);
    }
}
