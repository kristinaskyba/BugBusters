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
    InputResult mockInputResult;

    @BeforeEach
    public void setUp() {
        // creates a new instance of Deca100 for the test
        event = new Deca100M();

        // Mocka InputResult

        mockInputResult = mock(InputResult.class);


        //here we are putting our mock into the deca100M class
        event.inputResult = mockInputResult;

    }
    @Test
    public void testCalculateScore(){
        //Test to see if score is calculated correct
        double inputResult=5;
        //expected value based on excel
        double expectedScore=2640;

        double actual= event.calculateResult(inputResult);

        assertEquals(expectedScore,actual);
    }


    @Test
    public void testMinimumBoundary() {
        // simulating that we are entering a acceptable value withing the limits

        when(mockInputResult.enterResult()).thenReturn(5.0);

        // Testin value under acceptable critiera
        int resultTooLow = event.calculateResult(4.99);
        assertTrue(resultTooLow > 0);
    }

    @Test
    public void testBoundaryMax() {
        // simulating that we are entering a acceptable value withing the limits
        when(mockInputResult.enterResult()).thenReturn(11.0);

        // Testin value above acceptable critiera
        int resultTooHigh = event.calculateResult(17.8);
        assertTrue(resultTooHigh > 0);
    }
}





