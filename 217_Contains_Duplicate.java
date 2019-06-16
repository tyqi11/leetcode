/*

1. There are two ways to handle this problem. If we are allowed to change the input, we can sort 
first and check the elements from left to right. This method saves extra space while takes 
O(nlogn + n) time, and also changes the input. If we are not allowed, we can use a hashset to 
record all the elements appeared before.

*/
 
// Solution 1
class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Arrays.sort(nums); // O(nlogn)
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        } // O(n)
        return false;
    }
}

/*
Time complexity: O(nlogn)
Space complexity: O(1)
*/

/************************************************/
// Solution 2

class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/

