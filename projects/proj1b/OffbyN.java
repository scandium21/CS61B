public class OffbyN implements CharacterComparator {

    public int Offby;

    public OffbyN() {
        Offby = 0;
    }

    public OffbyN(int x) {
        Offby = x;
    }

    public boolean equalChars(char x, char y) {
        if (x > 122 || x < 97 || y > 122 || y < 97 ) {
            throw new IllegalArgumentException("Must have valid characters a - z in a word.");
        }
        if (x - y == Offby || y - x == Offby) {
            return true;
        }
        return false;
    }
}
