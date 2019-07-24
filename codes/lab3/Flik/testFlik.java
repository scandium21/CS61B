import static org.junit.Assert.*;
import org.junit.Test;

public class testFlik {

    /** Performs a few arbitrary tests to see if the Flik method is correct */
    @Test
    public void testFlik() {
        int a = 127;
        int b = 128;
        int c = 128;

        boolean expab = false;
        boolean expbc = true;

        assertEquals(expab,Flik.isSameNumber(a,b));
        assertEquals(expbc,Flik.isSameNumber(b,c));
        assertTrue(Flik.isSameNumber(b,c));
    }

}
