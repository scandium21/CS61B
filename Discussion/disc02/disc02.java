/** Implement square and squareDestructive which are static methods that both
take in an IntList L and return an IntList with its integer values all squared.
square does this non-destructively with recursion by creating new IntLists while
squareDestructive uses a recursive approach to change the instance variables of
the input IntList L */

public class disc02 {
    public static IntList square(IntList L) {
        if (L == null) {
            return L;
        } 
        else {
            IntList rest = square(L.rest);
            IntList M = new IntList(L.first * L.first, rest);
            return M;
        }
    }
    
    
    public static IntList squareDestructive (IntList L) {
        
        if (L == null)
            return null;
        L.first = L.first * L.first;
        squareDestructive(L.rest);
        return L;
        
        // L.first = L.first * L.first;
        // if (L.rest == null){
        //     return L;
        // }
        // L.rest = squareDestructive(L.rest);
        // return L;
        
    }
}