/*

1. Almost the same as 875. Koko Eating Banabas. The key is to get the range 
of capacity. 

*/

class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int low = Integer.MIN_VALUE; // max weight of all
        int high = 0; // sum of weights
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }
        while (low < high) {
            int m = low + (high - low) / 2;
            if (canShip(weights, m, D)) {
                high = m;
            } else {
                low = m + 1;
            }
        }
        return low;
    }
    
    private boolean canShip(int[] weights, int m, int D) {
        int sum = 0;
        int days = 0;
        for (int w : weights) {
            if (sum + w > m) {
                sum = 0;
                days++;
            }
            sum += w;
        }
        days++; // the last shipment
        return days <= D;
    }
}

/*
Time complexity: O(nlogn), n times of binary search
Space complexity: O(1)
*/