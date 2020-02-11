/*
We care about the previous two posts.
1: k options
2: if these two same: k * 1
   if these two diff: k * (k - 1)
3: if previous two are the same, this must be different: d1 = same * (k - 1)
   if previous two are different, this can be any color:
      to make these two same: same = diff * 1
      to make these two diff: d2 = diff * (k - 1)
Choices together is: same + diff
*/

class Solution {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        } 
        if (n == 1) {
            return k;
        }
        
        int same = k * 1;
        int diff = k * (k - 1);
        
        for (int i = 3; i <= n; i++) {
            int d1 = same * (k - 1);
            same = diff * 1;
            diff = diff * (k - 1) + d1;
        }
        
        return same + diff;
    }
}


/*
Time complexity: O(n)
Space complexity: O(1)
*/
