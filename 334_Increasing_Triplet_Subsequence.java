/*

(credit to: @gcarrillo: https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79004/Concise-Java-solution-with-comments.)
1. We record the first and second mimimum integer. Once there is a qualified
third one, return true. Take the following example: [1, 0, 2, -1, 1, 3]

round1: first = 1, second = Integer.MAX_VALUE;
round2: first = 0, second = Integer.MAX_VALUE;
round3: first = 0, second = 2;
round4: first =-1, second = 2; 
// first starts a new set of i,j,k, before second = 2, there must be a first < 2,
so when there comes a value > second, there must be three qualified values
round5: first =-1, second = 1;
// second is updated, now, our requirement for third decrease to > 1. The last
3 is valid. If the last is 2, it still works. This is the best part.

2. This algorithm can only return true or false, but cannot return the exact
subsequence.

*/

class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= first) {      // must be <=, not <
                first = n;
            } else if (n <= second) {  // must be <=, not <
                second = n;
            } else {
                return true;
            }
        }
        return false;
    }
}

/*
if < instead of <=, when 1, 1, 1, 1, 1, f = MAX, s = MAX
first 1 changes f = 1, second 1 changes s = 1, third 1 return true
*/

/*
Time complexity: O(n)
Space complexity: O(1)
*/