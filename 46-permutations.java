/*
# Backtracking
*/

class Solution {
    List<List<Integer>> res = new LinkedList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        backtrack(new LinkedList<Integer>(), nums);
        return res;
    }
    
    private void backtrack(List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                backtrack(list, nums);
                list.remove(list.size() - 1);
            }
        }
    }
}


/*
Time complexity: O(m * n)
Space complexity: O(1)
*/