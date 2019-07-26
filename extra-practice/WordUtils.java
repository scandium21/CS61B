public class WordUtils {
    
    /** Finds the longest word from a list of words */
    public static String getLongest (List61B<String> s) {
        String longS = s.getFirst();
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).compareTo(longS) > 0) {
                longS = s.get(i);
            }
        }
        return longS;
    }

    public static void main(String[] args) {
        List61B<String> someList = new AList<>();
        someList.addLast("elk");
        someList.addLast("are");
        someList.addLast("watching");
        System.out.println(getLongest(someList));
    }
}