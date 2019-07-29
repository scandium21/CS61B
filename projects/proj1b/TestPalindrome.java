import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        CharacterComparator cc = new OffByOne();
        String[] testwords = {"a", "A", "", "noon", "racecar", "batman", "flake"};
        boolean[] expected = {true, true, true, true, true, false, false};
        boolean[] expectedoffbyone = {false, false, false, false, false, false, true};
        for (int i = 0; i < testwords.length; i++) {
            boolean testp = palindrome.isPalindrome(testwords[i]);
            boolean testobo = palindrome.isPalindrome(testwords[i],cc);
            assertEquals(expected[i], testp);
            assertEquals(expectedoffbyone[i], testobo);
        }

    }
}