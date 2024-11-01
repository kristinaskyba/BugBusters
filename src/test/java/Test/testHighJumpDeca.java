package Test;
import common.InputResult;
import decathlon.Deca100M;
import decathlon.DecaHighJump;
import decathlon.InvalidResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testHighJumpDeca {

    DecaHighJump event=new DecaHighJump();



    //Test för att räkna ut

    @Test
    public void testCalculatevalidData() throws InvalidResultException {

        double inputResult=201;

        double expectedScore=813;

        double actual= event.calculateResult(inputResult);

        assertEquals(expectedScore,actual);
    }

}
