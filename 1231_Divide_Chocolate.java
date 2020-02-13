/*
# Binary Search

(questions about the moving criteria)
*/

class Solution {
    public int maximizeSweetness(int[] sweetness, int K) {
        if (sweetness == null || sweetness.length == 0 || K >= sweetness.length) {
            return 0;
        }
        
        int sum = 0;
        for (int s : sweetness) {
            sum += s;
        }
        
        int lo = 1, hi = sum;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2 + 1; // sweetness limit for each 
            int cuts = cutsWithCurSweetness(K, mid, sweetness);
            if (cuts > K) { // too many cuts, increase each sweetness
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        
        return lo;
    }
    
    private int cutsWithCurSweetness(int K, int mid, int[] sweetness) {
        int cur = 0, cuts = 0;
        for (int s : sweetness) {
            if (cur + s >= mid) {
                cur = 0;
                cuts++;
                if (cuts > K) {
                    break;
                }
            } else {
                cur += s;
            }
        }
        return cuts;
    }
}




/*
Time complexity: O()
Space complexity: O()
*/
