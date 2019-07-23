/*

1. When iterating over all the lamps, for each lamp on, add 1 to the row, column,
diagonal, back-diagonal, and set itself true.

2. When iterating over queries, for each query, if there is one lamp on in its 
row/column/diagonal/back-diagonal, the lamp is on. Else, the lamp is off.

3. If the lamp is on, turn off the lamps around it means decrease 1 to the row,
column, diagonal, back-diagonal, and set itself false.

*/

class Solution {
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> rows = new HashMap<>(); // rows light
        Map<Integer, Integer> cols = new HashMap<>();
        Map<Integer, Integer> diag1 = new HashMap<>();
        Map<Integer, Integer> diag2 = new HashMap<>();
        Map<Integer, Boolean> lights = new HashMap<>(); // lamp is ON or OFF
        
        for (int[] lamp : lamps) {
            int x = lamp[0];
            int y = lamp[1];
            rows.put(x, rows.getOrDefault(x, 0) + 1);
            cols.put(y, cols.getOrDefault(y, 0) + 1);
            diag1.put(x - y, diag1.getOrDefault(x - y, 0) + 1);
            diag2.put(x + y, diag2.getOrDefault(x + y, 0) + 1);
            lights.put(N * x + y, true);
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            
            if (rows.getOrDefault(x, 0) > 0 || cols.getOrDefault(y, 0) > 0 
             || diag1.getOrDefault(x - y, 0) > 0 || diag2.getOrDefault(x + y, 0) > 0) {
                ans[i] = 1;
            } else {
                ans[i] = 0;
            }
            
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    int x1 = x + j;
                    int y1 = y + k;
                    if (lights.containsKey(N * x1 + y1) && lights.get(N * x1 + y1)) {
                        rows.put(x1, rows.getOrDefault(x1, 1) - 1);
                        cols.put(y1, cols.getOrDefault(y1, 1) - 1);
                        diag1.put(x1 - y1, diag1.getOrDefault(x1 - y1, 1) - 1);
                        diag2.put(x1 + y1, diag2.getOrDefault(x1 + y1, 1) - 1);
                        lights.put(N * x1 + y1, false);
                    }
                }
            }
        } // end searching queries
        
        return ans;
    }
}

/*
Time complexity: O(L + Q), L is the number of lamps, and Q is the number of queries
Space complexity: O(L), for maps
*/


