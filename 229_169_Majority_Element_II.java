/*
# Boyer-Moore Voting Algorithm

1. Difference: 169 wants a majority element which appears > n/2 times
               229 wants majority elements which appear > n/3 times
   It is for sure that the answer contains at most two numbers.

2. We track two majority elements. If we increase, we increase only one counter.
We the current number is not equal to either of the majorities, decrease both. 

3. After the iteration, we get two majorities but we need to double-check if they
appear >n/3 times. Increase one counter once to avoid get two same result.

*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length  == 0) {
            return result;
        }
        int maj1 = nums[0];
        int maj2 = nums[0];
        int counter1 = 0;
        int counter2 =0;
        for (int i = 0; i < nums.length; i++) {
             if (nums[i] == maj1) {
                 counter1++;
             } else if (nums[i] == maj2) {
                 counter2++;
             } else if (counter1 == 0) {
                 maj1 = nums[i];
                 counter1++;
             } else if (counter2 == 0) {
                 maj2 = nums[i];
                 counter2++;
             } else { // if (nums[i] != maj1 && nums[i] != maj2)
                 counter1--;
                 counter2--;
             }
        }
        // re-check if the two choices appear > n/3 times
        counter1 = 0;
        counter2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == maj1) {
                counter1++;
            } else if (nums[i] == maj2) {
                counter2++;
            }
        } // use else if to avoid maj1 == maj2
        if (counter1 > nums.length / 3) {
            result.add(maj1);
        } 
        if (counter2 > nums.length / 3) {
            result.add(maj2);
        }
        return result;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/