/*
# Greedy
# Dynamic Programming 

1. There are many ways to solve this problem. The clearest and best one is greedy 
algorithm. We use *pos* to record the furthest position, at which we can jump to 
the end of the array. Once we find one such position, we change the destination 
as the current postion (a GOOD position). Because we know that as long as we can 
arrive at the current position (GOOD), we can arrive at the end position. After 
going through the array, if the last GOOD position is the start index, we know that 
from the start index, we can arrive at the end. Return true.

2. To make Jump Game II a easier transition from Jump Game, we can write the 
greedy algorithm in another way.

3. In many cases, we do not need to know every state of the positions from start 
to end to know the state of the end. We go from end to start. For position i 
before the end, we iterate through all the positions it can arrive. If there is 
one that has been checked to be able to arrive at end, we know that we can go 
from position i to end. Then we go one step earlier (left), and go through the 
same process. Return true if the start position is a valid position to reach the 
end.

*/

// Solution 1: Greedy
class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        int furthest = 0;
        for (int i = 0; i < nums.length && i <= furthest; i++) {
            furthest = Math.max(furthest, i + nums[i]);
            if (furthest >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/*************************************************************/
// Solution 2: 
class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        int pos = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= pos) {
                pos = i;
            }
        }
        return pos == 0;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/
