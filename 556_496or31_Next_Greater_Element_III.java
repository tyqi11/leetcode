/*

1. This is almost the same as 31. Next Permutation. But one extra step to
change the given 32-bit integer to an array.

2. I used n % 10 to get each digit and record the highest digit index, which
makes it a little faster than Integer.toString(n).toCharArray();

3. And I do overflow check before I added the last index into the result to
avoid using changing integer to long and changing it back.

*/

class Solution {
    public int nextGreaterElement(int n) {
        int[] digits = new int[10]; // 2,147,483,647, ten digits
        int i = 9;
        while (n != 0) {
            digits[i--] = n % 10;
            n /= 10;
        }
        int highest = i + 1;
                
        i = 8;
        while (i >= highest && digits[i] >= digits[i + 1]) {
            i--;
        } // i = highest - 1 or digits[i] decreases
        
       
    	// no next greater exists
        if (i < highest) {
            return -1;
        }
               
        int j = 9;
        while (j > i && digits[j] <= digits[i]) {
            j--;
        }
        swap(digits, i, j);   
        reverse(digits, i + 1);
        
        int ans = 0;
        for (i = highest; i < 9; i++) { // exit before last digit
            ans = 10 * ans + digits[i];
        }
        // check overflow before last digit
        if (ans > 214748364 || (ans == 214748364 && digits[9] > 7)) {
            return -1;
        } else {
            return ans * 10 + digits[9];
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
    private void reverse(int[] nums, int start) {
        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
}

/*
Time complexity: O()
Space complexity: O()
*/