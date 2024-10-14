import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import heptathlon.HeptJavelinThrow;

public class HeptJavelinThrowTest {

    private HeptJavelinThrow heptJavelinThrow;

    @BeforeEach
    public void setUp() {
        heptJavelinThrow = new HeptJavelinThrow();  // Skapar instans av HeptJavelinThrow före varje test
    }

    @Test
    public void testValidJavelinThrowDistance() {
        // Testar ett giltigt värde för spjutkastning
        double distance = 60.0;  // Exempel på ett rimligt värde för spjutkastning i meter
        int expectedScore = 1055;  // Korrekt resultat för 60.0 meter baserat på beräkningsformeln
        int actualScore = heptJavelinThrow.calculateResult(distance);  // Kör testet
        assertEquals(expectedScore, actualScore, "Poängen för 60.0 meter borde vara " + expectedScore);
    }
}
