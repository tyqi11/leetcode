/*
# Binary Search

*/

class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }    
        
        int lo = 1, hi = x;
        while (lo <= hi) { // because return mid in the middle
            int mid = lo + (hi - lo) / 2;
            if (x / mid == mid) {
                return mid;
            } else if (x / mid > mid) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        
        return hi; // righthand of hi is wrong answers
    }              // lefthand of lo is not big enough answers
}


/*
Time complexity: O()
Space complexity: O()
*/
