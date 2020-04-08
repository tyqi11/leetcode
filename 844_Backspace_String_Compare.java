/*
The key point is when can we start comparing the two characters?
If the char is '#', we cannot. We use cnt to mark number of backspaces.
If the char is not '#', we first need to check if this needs to be "backspaced". 
						We decrease cnt until it is 0.
So we can compare now? No.
There is the possibility that in cases like "abc#######", the pointer is moved
out of range, to -1. So if the pointer is out of range, we set a character,
which is impossible to be in the string, to it.

That's all we need now. When we compare, if they are different, return false.
Else, move to the next.

*/

class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int cntS = 0, cntT = 0;
        
        while (i >= 0 || j >= 0) {
            while (i >= 0 && (cntS > 0 || S.charAt(i) == '#')) {
                if (S.charAt(i) == '#') {
                    cntS++;
                } else {
                    cntS--;
                }
                i--;
            }
            char s = (i < 0 ? '@' : S.charAt(i));
            
            while (j >= 0 && (cntT > 0 || T.charAt(j) == '#')) {
                if (T.charAt(j) == '#') {
                    cntT++;
                } else {
                    cntT--;
                }
                j--;
            }
            char t = (j < 0 ? '@' : T.charAt(j));
            
            if (s != t) {
                return false;
            }
            i--;
            j--;
        }
        
        return true;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/