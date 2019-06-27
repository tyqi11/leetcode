/*
# Dynamic Programming

1. There are two dynamic programming ways to solve this problem. For the first
one, we use a boolean variable to track we are looking for a bigger number or a
smaller one. If we are looking for a bigger one and the next is bigger than the
previous, or we are looking for a smaller one and the next is smaller, we change
the goal between fingBig or not and also do len++. For other situations, we do 
nothing. For example, [3, 3, 3, 2, 4, 4, 7, 3, 1]
                                3  4  5  6  7  8  start = 3, findBig = false, len = 1
i = 3, findBig = false && nums[i] < nums[i - 1], findBig = true, len = 2, [3, 2]
i = 4, findBig = true && nums[i] > nums[i - 1], findBig = false, len = 3, [3, 2, 4]
i = 5, findBig = false, but nums[i] == nums[i - 1], [3, 2, 4]
i = 6, findBig = false, but nums[i] > nums[i - 1], [3, 2, 7], but we do nothing
i = 7, findBig = false && nums[i] < nums[i - 1], findBig = true, len = 4, [3, 2, 7, 3]
i = 8, findBig = true, but nums[i] < nums[i - 1], [3, 2, 7, 1], but we do nothing
return len = 4, result is [3, 2, 4, 3] or [3, 2, 4, 1] or [3, 2, 7, 3] or [3, 2, 7, 1]

2. We use two arrays up and down (optimize to two variables in implementation as
we just need the previous one result) to track the current trend. When there is
a going up, it counts only if it comes from a last going down, so up = down + 1, 
and vice versa. At the end of the list, we return the bigger countera as the 
biggest length.


*/

// Solution 1: dp
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int start = 1;
        while (start < nums.length && nums[start] == nums[0]) {
            start++;
        } // deal with the starting equal values, like [1, 1, 1, 3, 2, ...]
        if (start == nums.length) {
            return 1;
        } // all the numbers are the same
        int len = 1;
        boolean findBig = (nums[0] < nums[start]);
        for (int i = start; i < nums.length; i++) {
            if ((findBig && nums[i] > nums[i - 1]) 
             || (!findBig && nums[i] < nums[i - 1])) {
                findBig = !findBig;
                len++;
            }
        }
        return len;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/*******************************************************/
// Solution 2: dp
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int up = 1;
        int down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) { // going up
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) { // going down
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

