package Test;

import common.InputResult;
import decathlon.DecaJavelinThrow;
import decathlon.InvalidResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class testJavelinThrowDeca {

    private InputResult mockInputResult;
    DecaJavelinThrow DecaJavelinThrow = new DecaJavelinThrow();

    @BeforeEach
    public void setUp() {
        mockInputResult = mock(InputResult.class);
        DecaJavelinThrow.inputResult=mockInputResult;
    }


    DecaJavelinThrow event = new DecaJavelinThrow();


    @Test
    public void testValidScore() throws InvalidResultException {
        //test of calculation beeing correct according to excel
        double inputResult = 7;

        double expectedScore = 0;

        double actual = event.calculateResult(inputResult);

        assertEquals(expectedScore, actual);
    }


    @Test
    public void testBoundaryTooLow() throws InvalidResultException {

        when(mockInputResult.enterResult()).thenReturn(7.0); //simulate user input
        double distance = 6.99;  //value under acceptable limit


        int score = DecaJavelinThrow.calculateResult(distance);


        verify(mockInputResult).enterResult();
    }

    @Test
    public void testLowestValue() throws InvalidResultException {

        double distance = 7.0;  //lowest acceptable value
        int expectedScore = 0;  //expected result based on excel


        int score = DecaJavelinThrow.calculateResult(distance);


        assertEquals(expectedScore, score);
    }



    @Test
    public void testUpperBoundaryTooHigh() throws InvalidResultException {

        when(mockInputResult.enterResult()).thenReturn(110.0); //highest acceptable value

        double distance = 110.01;


        int score = DecaJavelinThrow.calculateResult(distance);


        verify(mockInputResult).enterResult();
    }




    @Test
    public void testUpperBoundaryValid() throws InvalidResultException {

        double distance = 110.0;  // highest acceptable value
        int expectedScore = 1513;  // expected result based on excel


        int score = DecaJavelinThrow.calculateResult(distance);


        assertEquals(expectedScore, score, "The score should be correctly calculated for the upper boundary.");
    }





}









