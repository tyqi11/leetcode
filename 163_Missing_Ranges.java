/*
The problem says "the range of elements are in the inclusive range [lower, upper]",
so we use the first solution below. If the lower bound can be greater than nums[0],
we may need to use the second solution.
*/

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new LinkedList<>();
        
        if (nums == null || nums.length == 0) {
            res.add(addMissing(lower, upper));
            return res;
        }
		
        if (lower < nums[0]) {
            res.add(addMissing(lower, nums[0] - 1));
        }
            
        for (int i = 0; i + 1 < nums.length; i++) {
            if (nums[i + 1] == nums[i] + 1 || nums[i + 1] == nums[i]) {
                continue;
            }
            res.add(addMissing(nums[i] + 1, nums[i + 1] - 1));
        }

        if (upper > nums[nums.length - 1]) {
            res.add(addMissing(nums[nums.length - 1] + 1, upper));
        }
                
        return res;
    }
    
    private String addMissing(int i, int j) {
        return (i == j) ? ("" + i) : (i + "->" + j);
    }
}


/*
Time complexity: O()
Space complexity: O()
*/

/***************************************************************/
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new LinkedList<>();
        if (lower > upper) {
            return res;
        } else if (nums == null || nums.length == 0) {
            res.add(addMissing(lower, upper));
            return res;
        } else if (lower == upper) {
            for (int n : nums) {
                if (n == lower) {
                    return res;
                }
            }
            res.add(String.valueOf(lower));
            return res;
        }
        
        int next = lower; // next number we want
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < next) {
                continue;
            } else if (nums[i] == next) {
                next++;
                continue;
            }
            
            // nums[i] > next
            res.add(addMissing(next, nums[i] - 1));
            if (nums[i] == Integer.MAX_VALUE) {
                return res;
            }
            next = nums[i] + 1;           
        }
        
        if (next <= upper) {
            res.add(addMissing(next, upper));
        }
        
        return res;
    }
    
    private String addMissing(int n1, int n2) {
        return (n1 == n2) ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
    }
}