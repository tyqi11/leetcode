/*

1. Almost the same as 875. Koko Eating Banabas. The key is to get the range 
of capacity. 

*/

class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int lo = 0, hi = 0;
        for (int w : weights) {
            lo = Math.max(w, lo);
            hi += w;
        }
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2; // max weight capacity
            if (canShipInD(mid, D, weights)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    private boolean canShipInD(int cap, int D, int[] weights) {
        int d = 1, cur = 0;
        for (int w : weights) {
            cur += w;
            if (cur > cap) {
                cur = w;
                d++;
                if (d > D) {
                    return false;
                }
            }
        }
        
        return true;
    }
}

/*
Time complexity: O(nlogn), n times of binary search
Space complexity: O(1)
*/