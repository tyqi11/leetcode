/*
# HashMap 
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums.length == 2) {
            return new int[]{0, 1};
        }
        
        Map<Integer, Integer> map = new HashMap<>(); // (number_needed, its_index)
        
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (map.containsKey(target - n)) {
                return new int[]{map.get(target - n), i};
            } else {
                map.put(n, i);
            }
        }
        
        return null;
    }
}


/*
Time complexity: O(n)
Space complexity: O(n)
*/