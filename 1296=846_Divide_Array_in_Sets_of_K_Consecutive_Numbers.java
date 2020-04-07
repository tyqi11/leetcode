/*
Exacly same as 846. Hand of Straights.
*/

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        for (int n : map.keySet()) {
            int cur = map.get(n);
            if (cur > 0) {
                for (int i = k - 1; i >=0; i--) {
                    int nxt = map.getOrDefault(n + i, 0);
                    if (nxt < cur) {
                        return false;
                    }
                    map.put(n + i, nxt - cur);
                }
            }
        }
        
        return true;
    }
}

/*
Time complexity: O(nlogn)
Space complexity: O(n)
*/