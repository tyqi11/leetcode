/*

1. Use +1 and -1 to indicate tiring and not-tiring days. If the difference of 
tiring - not-tiring is strictly more than 0, the whole interval is well-performing.
If not, we put it into a HashMap and record the first time it appears. Next time,
when a new *diff* appears, the interval between appearance of *diff - 1* and *diff*
must be a well-performing interval.


*/

class Solution {
    public int longestWPI(int[] hours) {
        Map<Integer, Integer> map = new HashMap<>(); // <diff, firstTimeAppears>
        int diff = 0;
        int ans = 0;
        for (int i = 0; i < hours.length; i++) {
            diff += (hours[i] > 8 ? 1 : -1);
            if (diff > 0) {
                ans = i + 1;
            } else {
                map.putIfAbsent(diff, i);
                if (map.containsKey(diff - 1)) {
                    ans = Math.max(ans, i - map.get(diff - 1));
                }
            }
        }
        
        return ans;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n), for HashMap, but it will be much smaller than n
*/
