import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffbyN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offbyn = new OffbyN(3);

    // test equalChars method
    @Test
    public void testequalChars() {
        Character a = 'a';
        Character b = 'b';
        Character c = 'c';
        Character d = 'd';


        assertFalse(offbyn.equalChars(a,b));
        assertFalse(offbyn.equalChars(c,b));
        assertTrue(offbyn.equalChars(a,d));
        assertFalse(offbyn.equalChars(a,a));
        assertFalse(offbyn.equalChars(b,a));
    }
}