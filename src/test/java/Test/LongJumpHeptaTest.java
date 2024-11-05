package Test;

import decathlon.InvalidResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

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
    public void testBelowLowerBoundary() {
        try {
            event.calculateResult(209);  // Below 210cm threshold from HeptLongJump class
            fail("Should throw InvalidResultException");
        } catch (InvalidResultException e) {
            assertEquals("Value too low", e.getMessage());
        }
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testAboveUpperBoundary() {
        try {
            event.calculateResult(1001);  // Above 1000cm threshold from HeptLongJump class
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