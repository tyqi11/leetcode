/*
# Backtracking
lines with (*) are the differences comparing to 46 to deal with duplicates
*/


class Solution {
    List<List<Integer>> res = new LinkedList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }    
        
        Arrays.sort(nums); // (*)
        boolean[] used = new boolean[nums.length]; // (*)
        backtrack(new LinkedList<>(), nums, used);
        
        return res;
    }
    
    private void backtrack(List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i != 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            } // (*)     
            
            used[i] = true;
            list.add(nums[i]);
            backtrack(list, nums, used);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
}

/* e.g. (1, 1, 1, 1, 2, ...), when we want the third element
we first meed  ^, and we add it to the list, after coming back to the stage,
we move to find the next potential third element, we cannot include the
fourth 1 as it will cause duplication.               
*/

/*
Time complexity: O(m * n)
Space complexity: O(1)
*/