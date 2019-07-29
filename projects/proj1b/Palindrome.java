public class Palindrome {

    public Deque<Character> wordToDeque(String word) {

        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++)  {
            wordDeque.addLast(word.charAt(i));
        }

        return wordDeque;
    }

    public boolean isPalindrome(String word) {

        Deque<Character> wordDeque = wordToDeque(word);
        while (!(wordDeque.isEmpty() || wordDeque.size() == 1)) {
            if (!wordDeque.removeFirst().equals(wordDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        if (wordDeque.size() <= 1) {
            return false;
        }
        while (!wordDeque.isEmpty() && wordDeque.size()!=1) {

            if (!cc.equalChars(wordDeque.removeFirst(),wordDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
