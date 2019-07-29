public class OffByOne implements CharacterComparator {

    public boolean equalChars(char x, char y) {
        if (x > 122 || x < 97 || y > 122 || y < 97 ) {
            throw new IllegalArgumentException("Must have valid characters a - z in a word.");
        }
        if (x - y == 1 || y - x == 1) {
            return true;
        }
        return false;
    }
}
