/*

*/

// Solution 1: Brutal Force
class Solution {
    public int shortestWay(String source, String target) {
        int j = 0;
        
        int cnt = 0;
        while (j < target.length()) {
            int jstart = j;
            cnt++;
            
            for (int i = 0; i < source.length(); i++) {
                if (source.charAt(i) == target.charAt(j)) {
                    j++;
                    if (j == target.length()) {
                        return cnt;
                    }
                }
            }
            
            // for this character, we iterate the whole source and cannot find a match
            if (j == jstart) {
                return -1;
            }     
        }
        
        return cnt;
    }
}

/*
Time complexity: O(m * n), m is the length of source, n is the length of target
Space complexity: O(1)
*/

/**********************************************************************/
// Solution 2: Dynamic Programming

class Solution {
    public int shortestWay(String source, String target) {
        int m = source.length(), n = target.length();
        
        int[][] dict = new int[m][26];
        
        Arrays.fill(dict[m - 1], -1); // -1 represents no occurence of the char
        
        // at or after index (m-1), the first time we meet this char is at index (m-1)
        // at or after index (m-1), we cannot meet any other character
        dict[m - 1][source.charAt(m - 1) - 'a'] = m - 1; 
        
        for (int x = m - 2; x >= 0; x--) { // O(26 * m), for copy arrays
            dict[x] = Arrays.copyOf(dict[x + 1], 26); // copy the below row
            dict[x][source.charAt(x) - 'a'] = x;
        }
        
        int ans = 0;
        int s = 0; // index of source
        
        for (char c : target.toCharArray()) { // O(n)
            if (dict[0][c - 'a'] == -1) {
                return -1;
            }
            
            if (dict[s][c - 'a'] == -1) {
                ans++;
                s = 0;
            }
            
            // find char c at position: dict[s][c - 'a'], so move pointer to pos + 1
            s = dict[s][c - 'a'] + 1;
            
            if (s == m) {
                ans++;
                s = 0;
            }
        }
        
        return ans + (s == 0 ? 0 : 1); // s != 0, the current source is not counted yet
    }
}

/*
Time complexity: O(26 * m + n), m is the length of source, n is the length of target
Space complexity: O(26 * m)
*/