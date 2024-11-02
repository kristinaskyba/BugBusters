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

public class Deca400MTest {
    private Deca400M event;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        event = new Deca400M();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testValidInput() throws InvalidResultException {
        assertEquals(640, event.calculateResult(54.0), "Score should be 640 for 54.0 seconds");
    }
                                   //Code sponsored by your truly, King Oskar
    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testBelowLowerBoundary() {
        try {
            event.calculateResult(19.0);
            fail("Should throw InvalidResultException");
        } catch (InvalidResultException e) {
            assertEquals("Value too low", e.getMessage());
        }
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testAboveUpperBoundary() {
        try {
            event.calculateResult(83.0);
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