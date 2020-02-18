/*
# Binary Search

*/

class Solution {
    public double minmaxGasDist(int[] stations, int K) {
        double lo = 0.0;
        double hi = stations[stations.length - 1] - stations[0];
        
        while (lo + 1e-6 < hi) {
            double mid = lo + (hi - lo) / 2;
            if (addKWorks(mid, K, stations)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        
        return lo;
    }
    
    private boolean addKWorks(double dist, int K, int[] stations) {
        int s = 0;
        for (int i = 1; i < stations.length; i++) {
            s += Math.ceil((stations[i] - stations[i - 1]) / dist) - 1;
            if (s > K) {
                return false;
            }
        }
        
        return true;
    }
}


/*
Time complexity: O()
Space complexity: O()
*/
