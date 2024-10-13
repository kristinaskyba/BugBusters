package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import decathlon.DecaShotPut;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class DecaShotPutTest {

    private DecaShotPut event;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        event = new DecaShotPut();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testValidInput() {
        assertEquals(790, event.calculateResult(15.0), "Score should be 790 for 15.0m throw");
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testBelowLowerBoundary() {
        simulateInput("15.0");
        event.calculateResult(1.4);
        assertTrue(outContent.toString().contains("Value too low"), "Should print 'Value too low' for input below 1.5");
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testAboveUpperBoundary() {
        simulateInput("15.0");
        event.calculateResult(30.1);
        assertTrue(outContent.toString().contains("Value too high"), "Should print 'Value too high' for input above 30");
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