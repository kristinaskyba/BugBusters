import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import heptathlon.HeptShotPut;

public class HeptShotPutTest {

    private HeptShotPut heptShotPut;

    @BeforeEach
    public void setUp() {
        heptShotPut = new HeptShotPut();  // Skapar instans av HeptShotPut före varje test
    }

    @Test
    public void testValidShotPutDistance() {
        // Testar ett giltigt värde för kulstötning
        double distance = 15.0;  // Exempel på ett rimligt värde för kulstötning i meter
        int expectedScore = 861;  // Här anger jag det korrekta resultatet för 15.0 meter baserat på min beräkningsformel
        int actualScore = heptShotPut.calculateResult(distance);  // Kör testet
        assertEquals(expectedScore, actualScore, "Poängen för 15.0 meter borde vara " + expectedScore);
    }
}

