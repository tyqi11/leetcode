/*

1. I did not understand the problem in the right way the first time I met it. In 
this problem, each specific letter in magazine can be used in ransomNote only once. 
So we need to check how many each letters magazine provides and if they are enough
for us to use in ransomNote.

2. The code below is not hard to understand. The second loop is a combination of
decrease the count in the array(O(n)) and check if chars[] < 0 (O(n)). In this 
way, we can efficiently terminate the loop as soon as a letter is not enough. 

*/

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0) {
            return true;
        } else if (magazine == null || magazine.length() == 0) {
            return false;
        }  
        int[] chars = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            chars[magazine.charAt(i) - 'a']++;
        }
        for (int j = 0; j < ransomNote.length(); j++) {
           if (--chars[ransomNote.charAt(j) - 'a'] < 0) {
               return false;
           };
        }
        return true;
    }
}

/*
Time complexity: O(n), from O(2n)
Space complexity: O(1)
*/