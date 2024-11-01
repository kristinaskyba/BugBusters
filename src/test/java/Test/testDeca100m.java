package Test;


import common.InputResult;
import decathlon.Deca100M;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class testDeca100m {

    //  DecaLongJump event = new DecaLongJump();
    //kalla på deca klassen
    Deca100M event;


    @BeforeEach
    public void setUp() {
        // creates a new instance of Deca100 for the test
        event = new Deca100M();



    }
    @Test
    public void testCalculateScore() throws Exception{
        //Test to see if score is calculated correct
        double inputResult=5;
        //expected value based on excel
        double expectedScore=2640;

        double actual= event.calculateResult(inputResult);

        assertEquals(expectedScore,actual);
    }


    @Test
    public void testBelowMinimumBoundary() throws Exception{
        // testing value just below max boundary

        double inputResult=5.0;


        assertDoesNotThrow(() -> event.calculateResult(inputResult));
    }

    @Test
    public void testBoundaryMax()  throws Exception{
        // testing a value above maximum boundary
        double inputResult = 17.8;

        assertDoesNotThrow(() -> event.calculateResult(inputResult));
    }


 public void testAboveMax() throws Exception {
     double inputResult = 17.8;

     assertDoesNotThrow(() -> event.calculateResult(inputResult));
 }
}





