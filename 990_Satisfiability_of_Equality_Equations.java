/*
# Union-Find

1. Go through all the "==" equations, union all the nodes connected by "==".

2. Go through all the "!=" equations, if a "!=" equation is contradict to 
an existing "==" equation, return false.

*/
class Solution {
    int[] uf = new int[26];
    public boolean equationsPossible(String[] equations) {
        for (int i = 0; i < 26; i++) {
            uf[i] = i;
        }
        for (String e : equations) {
            char[] chars = e.toCharArray();
            if (chars[1] == '=') {
                uf[find(chars[0] - 'a')] = find(chars[3] - 'a');
            }
        }
        for (String e : equations) {
            char[] chars = e.toCharArray();
            if (chars[1] == '!' && find(chars[0] - 'a') == find(chars[3] - 'a')) {
                return false;
            }
        }
        return true;
    }
    
    private int find(int x) {
        if (uf[x] != x) {
            uf[x] = find(uf[x]);
        }
        return uf[x];
    }
}

/*
Time complexity: O(n)
Space complexity: O(1), for the uf array
*/