/*
# Two pointers (sliding window)

1. To finish the searching in O(n), we think of using two pointers to track the start
and end index of the result substring. To track if we have covered all the characters
in t, we use a map array to record how many times each target character has appeared.

2. There are two steps in finding the minimum sliding window. The first is to find a 
sliding window no matter how long it goes to the right, the second is to shorten the 
window from the left side. 
2.1 For each letter in s, we decrease its counter in the array. After decreasing, it 
counter >= 0, it must not be 0 before. It means that this is a letter we want in t. So
we do count++. count is how many letters in t we have found.
2.2 when count == t.length(), we found all the letters we need in t. Now we shorten
as much as possible. (*possible* means count == t.length()) We move the start index 
one place to the right. If its counter becomes > 0, it means this letter was supposed 
to be concluded in the window. We will go out of the while loop, and enlarge from right
again. If the counter is still <= 0, excluding this letter will not excluding one we
need in t, so we continue doing the same narrowing down. 

3. Check if there is an answer before returning.

*/

class Solution {
    public String minWindow(String s, String t) {
        int[] map = new int[128]; // 128 or 256
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }
        int start = 0;
        int minStart = 0; // record the result start
        int min = Integer.MAX_VALUE;
        int count = 0; // how many letters in t we have found
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]--; // enlarge the window
            if (map[s.charAt(i)] >= 0) { // we need this char
                count++;
            }
            while (count == t.length()) { // find a window
                if (min > i - start + 1) {
                    min = i - start + 1;
                    minStart = start;
                }
                map[s.charAt(start)]++; // shortern the window
                if (map[s.charAt(start)] > 0) {
                    count--; // we lose one char we need
                }
                start++;
            }
        }
        return (min == Integer.MAX_VALUE) ? "" : s.substring(minStart, minStart + min);
    }
}

/*
Time complexity: O(n)
Space complexity: O(1), for the map array
*/