/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 7;
        int offby = 0;
        In in = new In("words.txt");
        Palindrome palindrome = new Palindrome();
        CharacterComparator cc = new OffbyN(offby);

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
            }
        }
    }
}