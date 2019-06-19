/*
# Floyd's Tortoise and Hare (Cycle Detection) 

1. There are two straightforward ways to solve this problem. First we can
sort the array in O(nlogn) time and O(n) or O(1) space,so the duplicate 
numbers will be placed next to each other. Second we can use a hashset to 
record numbers, with O(n) time and O(n) space.

2. This is a similar question of finding a circle in a linked list. See
[142. Linked List Cycle II]


*/

class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

/*
Time complexity: O(n)
Space complexity: (1)
*/