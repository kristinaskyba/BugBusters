package Test;

import org.junit.jupiter.api.Test;
import common.InputResult;
import decathlon.Deca400M;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDeca400m {


    Deca400M event=new Deca400M();




    @Test
    public void testCalculateScore(){
        //Test för att räkna ut lägsta möljiga score
        double inputResult=20;
        //rätt värde på expected enligt excel dokument m.uträkning
        double expectedScore=2698;
        //retunerar felvärde ifrån systemet.
        double actual= event.calculateResult(inputResult);

        assertEquals(expectedScore,actual);
    }

}
