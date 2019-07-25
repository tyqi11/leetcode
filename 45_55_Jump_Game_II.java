/*
# Greedy (BFS)

1. At point 0, we know that we can at most reach *curEnd*. So we go over all the 
place in this one-jump-distance to know how far we can reach after the nex jump.
Then we update *curEnd* to *furthest* and do previous steps again. Once *furthest*
is farther than nums.length - 1, we reach the destination with fewest steps.

*/

class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int curEnd = 0;
        int furthest = 0;
        int jumps = 0;
        int i = 0;
        while (i <= curEnd) { // avoid curEnd stops at value 0
            jumps++;
            while (i <= curEnd) {
                furthest = Math.max(furthest, i + nums[i]);
                i++;
            } // i > curEnd
            if (furthest >= nums.length - 1) {
                return jumps;
            }
            curEnd = furthest;           
        }
        return 0; // or return jumps; the same 
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/


/************************************************************************/
// Follow Up: print out the shortest path

public List<Integer> jump(int[] nums) {
    List<Integer> jumps = new ArrayList<>();
    if (nums == null || nums.length < 2) {
        return jumps;
    }
    int curEnd = 0;
    int furthest = 0;
    int i = 0;
    int next = 0;
    while (i <= curEnd) { // avoid curEnd stops at value 0
        while (i <= curEnd) {
            if (i + nums[i] > furthest) {
                next = i;
                furthest = i + nums[i];
            }
            i++;
        } // i > curEnd
        jumps.add(next);
        if (furthest >= nums.length - 1) {
            return jumps;
        }
        curEnd = furthest;           
    }
    return jumps;
}