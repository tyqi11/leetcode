/*

1. To solve this problem, the most important is to know when can we delete the 
previous and append the new. 
For example,
example 1: [z]abcabc, we cannot remove [z] in result, because there is no z after []
example 2: [d]yzdzzz, we cannot remove [d] in result, because after [], letters
                      are not lexicographically before d.
example 3: [d]zadzzd, we can remove the [d] after we meet a, as a is lexicographically
                      before d, and there are d's left after a. We will have other
                      chances to add d again.                   
So, when the result is 1) not empty, 2) the current letter *c* is before the previous 
letter *p* in a dictionary, and 3) we still have chances to add *p* later, we can
remove the *p* and add *c*.

2. In the implementation, first we count the appearance of each letter so at any
time, we know if we have chance to meet requirement (3). 

3. To record if the current letter is added to the result, or it still waits to
be compared again, we create another boolean array. We may or may not add the letter
before we get to its last appearance.

4. We iterate over each letter in the string, if the current has not been add to
the result, we will check if it meets all the three requirements in 1. We remove 
s previous until it does not meet requirements. Then we add the current to the result.
Also update the visited array when remove and add letters.

5. In the inner while loop, sb.length() is always changing as we always want to 
remove the last. So you cannot set int n = sb.length() to make it more concise.
It will give you wrong answers.

*/

class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] count = new int[26];
        boolean[] visited = new boolean[26];
        char[] ch = s.toCharArray();
        for (char c : ch) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int idx;
        for (char c : ch) {
            idx = c - 'a';
            count[idx]--;
            if (visited[idx]) {
                continue;
            }
            
            while (sb.length() > 0 
            	&& c < sb.charAt(sb.length() - 1) 
            	&& count[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                visited[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(c);
            visited[idx] = true;
        }
        return sb.toString();
    }
}

/*
Time complexity: O(n), O(n) for adding to count array, O(n) for building string
Space complexity: O(n), O(1) for count array and visited array, 
                        O(n) for char array and O(n) for StringBuilder
*/