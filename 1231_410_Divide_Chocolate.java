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
            int mid = lo + (hi - lo + 1) / 2; // minimum sweetness 
            if (canDivide(K, mid, sweetness)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        
        return lo;
    }
    
    private boolean canDivide(int K, int mid, int[] sweetness) {
        int cur = 0, pieces = 0;
        for (int s : sweetness) {
            cur += s;
            if (cur >= mid) {
                cur = 0;
                pieces++;
                if (pieces > K) {
                    return true;
                }
            }
        }
        return false;
    }
}
/*
Time complexity: O()
Space complexity: O()
*/
