package decathlon;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testValidInput() throws InvalidResultException {
        assertEquals(790, event.calculateResult(15.0), "Score should be 790 for 15.0m throw");
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testBelowLowerBoundary() {
        try {
            event.calculateResult(1.4);  //Oskar was here
            fail("Should throw InvalidResultException");
        } catch (InvalidResultException e) {
            assertEquals("Value too low", e.getMessage());
        }
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testAboveUpperBoundary() {
        try {
            event.calculateResult(30.1);  // Above 30m threshold from DecaShotPut class
            fail("Should throw InvalidResultException");
        } catch (InvalidResultException e) {
            assertEquals("Value too high", e.getMessage());
        }
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}