/*

*/

class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int low = 1;
        int high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        
        while (low < high) {
            int K = low + (high - low) / 2;
            if (canEatAll(piles, K, H)) {
                high = K ;
            } else {
                low = K + 1;
            }
        }
        return low;
    }
    
    private boolean canEatAll(int[] piles, int K, int H) {
        int count = 0;
        
        for (int pile : piles) {
            count += pile / K;
            if (pile % K != 0) {
                count++;
            }
        }
        
        return count <= H;
    }
}

/*
Time complexity: O(nlogn)
Space complexity: O(1)
*/