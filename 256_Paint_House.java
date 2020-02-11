/*
# Dynamic Programming

If I paint the current house i as color j, the cost after this will be: 
1) cost for this: costs[i][j];
2) cost for before: Math.min(k, l) as the previous cannot be j


*/

class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int r = 0, b = 0, g = 0; // current color is x, the min cost
        for (int i = 0; i < n; i++) {
            int pr = r, pb = b;
            r = costs[i][0] + Math.min(pb, g);
            b = costs[i][1] + Math.min(pr, g);
            g = costs[i][2] + Math.min(pr, pb);
        }
        
        return Math.min(r, Math.min(b, g));
    }
}


/*
Time complexity: O(n)
Space complexity: O(1)
*/
