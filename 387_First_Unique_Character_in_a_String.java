/*

1. My solution is to create an array of size 26 to store the index of 26 letters' 
appearance. To indicate no appearance, we fill each place with -1. (Why we do not 
use the default 0？ Because we need 0 for the appearance of the first character 
in the string.) During iteration, if the letter has not shown before, that is 
chars[s.charAt(i) - 'a'] == -1, we change it to its index. If chars[s.charAt(i) - 'a'] 
is already the index, it is not unique. We set it to -2. After the iteration, 
-1 and -2 shows the letter appears no times or more than one time. We go through 
the chars array to find the first unique character.

2. The above algorithm takes O(n) = O(26 + n + 26) time complexity. We can also 
record the appearance times of each characters in the first round. Then iterate
over the string again to check in order, which letter is the first to appear only
once. This method takes O(n) =  O(2 * n) time complexity.


3. A universal way to solve the problem is to use LinkedHashMap. It is not better 
in this case but worth listing here as we are so unfamiliar with LinkedHashMap.
*/

class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int[] chars = new int[26];
        Arrays.fill(chars, -1);
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i) - 'a'] == -1) {
                chars[s.charAt(i) - 'a'] = i;
            } else if (chars[s.charAt(i) - 'a'] > -1) {
                chars[s.charAt(i) - 'a'] = -2;
            } // else if == -2, we do not care anymore
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < chars.length; j++) {
            if (chars[j] >= 0) {
                min = (chars[j] < min) ? chars[j] : min;
            }
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;        
    }
}

/*
Time complexity: O(n)
Space complexity: O(1), an array of size 26.
*/

/**********************************************************/
// O(2 * n) method
class Solution {
    public int firstUniqChar(String s) {
        int[] chars = new int[26];
        for(int i = 0; i < s.length(); i ++) {
            chars[s.charAt(i) - 'a'] ++;
        }
        for(int i = 0; i < s.length(); i ++) {
            if(chars[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}

/****************************************************************/
// LinkedHashMap
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        
        Map<Character, Integer> map = new LinkedHashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.add(c)) { // first time appearance
                map.put(c, i);
            } else {
                if (map.containsKey(c)) { // second time
                    map.remove(c);
                } // else not in map, because removed, third time
            }
        }
        
        return map.size() == 0 ? -1 : map.values().iterator().next();
    }
}
