/*
# Greedy

*/

// Solutoin 1: O(n) space complexity, O(n) time complexity
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        
        Map<Integer, Integer> seq = new HashMap<>();
        for (int n : nums) {
            if (freq.get(n) == 0) {
                continue;
            }
            
            freq.put(n, freq.get(n) - 1);
            if (seq.getOrDefault(n, 0) > 0) {
                seq.put(n, seq.get(n) - 1);
                seq.put(n + 1, seq.getOrDefault(n + 1, 0) + 1);
            } else if (freq.getOrDefault(n + 1, 0) > 0 && freq.getOrDefault(n + 2, 0) > 0) {
                seq.put(n + 3, seq.getOrDefault(n + 3, 0) + 1);
                freq.put(n + 1, freq.get(n + 1) - 1);
                freq.put(n + 2, freq.get(n + 2) - 1);
            } else {
                return false;
            }
        }
        
        return true;
    }
}


/*
Time complexity: O(n)
Space complexity: O(n)
*/

/**/
// Solution 2: O(1) space complexity, O(n) time complexity
// @clarencewang and @fun4LeetCode
// https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106495/Java-O(n)-time-and-O(1)-space-solution-greedily-extending-shorter-subsequence
class Solution {
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int cur = 0, pre = Integer.MIN_VALUE;
        int p1 = 0, p2 = 0, p3 = 0;
        int c1 = 0, c2 = 0, c3 = 0;
        
        int i = 0;
        while (i < nums.length) {
            cur = nums[i];
            int cnt = 0;
            while (i < nums.length && cur == nums[i]) {
                i++; // exit at a different element
                cnt++; // how many current elements
            }
            
            // current cannot be appended to previous subsequences
            if (cur != pre + 1) {
                if (p1 != 0 || p2 != 0) {
                    return false; // result in len < 3 subsequences
                }
                c1 = cnt;
                c2 = 0;
                c3 = 0;
            } else { // current can be appended
                if (cnt < p1 + p2) {
                    return false; // result in len < 3 subsequences
                }
                c2 = p1; // append to len = 1 first, result in c2
                c3 = p2; // append to len = 2, result in c3
                
                // if more cur, append to p3 and get more c3
                int res = cnt - p1 - p2; // extra cur
                int appendP3 = Math.min(p3, res); // how many p3 we can append
                c3 += appendP3;
                
                c1 = Math.max(0, res - appendP3); // if there is still cur left, result in c1
            }
            
            pre = cur;
            p1 = c1;
            p2 = c2;
            p3 = c3;
        } // end (i < nums.length) loop
        
        return p1 == 0 && p2 == 0;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/
