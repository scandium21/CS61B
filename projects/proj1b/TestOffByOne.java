import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // test equalChars method
    @Test
    public void testequalChars() {
        Character a = 'a';
        Character b = 'a';
        Character c = 'b';


        assertFalse(offByOne.equalChars(a,b));
        assertTrue(offByOne.equalChars(c,b));
        assertTrue(offByOne.equalChars(b,c));
        assertFalse(offByOne.equalChars(a,a));
        assertFalse(offByOne.equalChars(b,a));
    }
}