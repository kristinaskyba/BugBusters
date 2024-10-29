package Test;

import decathlon.InvalidResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import heptathlon.HeptLongJump;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class LongJumpHeptaTest {

    private HeptLongJump event;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        event = new HeptLongJump();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testValidInput() throws InvalidResultException {
        assertEquals(73, event.calculateResult(279), "Score should be 73 for 279cm jump");
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testBelowLowerBoundary() throws InvalidResultException {
        simulateInput("210");
        event.calculateResult(209);
        assertTrue(outContent.toString().contains("Value too low"), "Should print 'Value too low' for input below 210cm");
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testAboveUpperBoundary() throws InvalidResultException {
        simulateInput("400");
        event.calculateResult(1001);
        assertTrue(outContent.toString().contains("Value too high"), "Should print 'Value too high' for input above 400cm");
    }

    private void simulateInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}