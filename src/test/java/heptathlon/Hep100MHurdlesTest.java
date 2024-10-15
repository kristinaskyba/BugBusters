package heptathlon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import heptathlon.Hep100MHurdles;

public class Hep100MHurdlesTest {

    private Hep100MHurdles hep100MHurdles;

    @BeforeEach
    public void setUp() {
        hep100MHurdles = new Hep100MHurdles();  // Skapar instans av Hep100MHurdles före varje test
    }

    @Test
    public void testValidRunningTime() {
        // Testar en giltig tid för 100 meter häck
        double runningTime = 15;  // Exempel på ett giltigt värde för 100 meter häck
        int expectedScore = 842;   // Här anger jag det korrekta resultatet för 15 sekunder baserat på formeln
        int actualScore = hep100MHurdles.calculateResult(runningTime);  // Kör testet
        assertEquals(expectedScore, actualScore, "Poängen för 15 sekunder borde vara " + expectedScore);
    }
}
