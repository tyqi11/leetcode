/*

*/
// can be optimized with find greatest common divisor
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int d : deck) {
            map.put(d, map.getOrDefault(d, 0) + 1);
        }

        int min = deck.length;
        for (int i : map.values()) {
            if (i == 1) {
                return false;
            } // each group X cards, X >= 2
            min = Math.min(min, i);
        }

        // this iteration from 2 to min is time-consuming, optimize with gcd
        for (int i = 2; i <= min; i++) {
            if (canSplit(map, i)) {
                return true;
            }
        }

        return false;

        /**
        int res = 0;
        for (int i : map.values()) {
           res = gcd(i, res);
        } 
        
        private int gcd(int a, int b) {
            return b > 0 ? gcd(b, a % b) : a;
        }
        
        return res > 1; // IMP, X >= 2
        */

    }
    
    private boolean canSplit(HashMap<Integer, Integer> map, int i) {
        for (int j : map.values()) {
            if (j % i != 0) {
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