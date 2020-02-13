/*
# Solution 1: negate the value
Each number has a unique corresponding index. So to find the duplicate number
means meeting that index twice.
So, each time we reach a number, we mark its correct index by setting the value
on that index to negative. Next time we meet the same number again, the value on
the index is negative.

# Solution 2: put into the right bucket (swap)

*/

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return res;
        }
        
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                res.add(index + 1);
            }
            nums[index] = -nums[index];
        }
        
        return res;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/********************************************************/
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return res;
        }
        
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i] - 1 && nums[i] != nums[nums[i] - 1]) {
                swap(i, nums[i] - 1, nums);
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res.add(nums[i]);
            }
        }
        
        return res;
    }
    
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[nums[i]-1];
        nums[temp-1] = temp;
    }
}


/*
Time complexity: O(n)
Space complexity: O(1)
*/
