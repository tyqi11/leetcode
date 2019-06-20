/*
# Greedy

1. When we meet a problem with an array as input, we mostly check if it's null or 
its length < 1 first, but in this problem, you can not do that. It you are given 
an empty array, you can still find answers to meet the 1 <= sum <= n requirment.
For example, you are given [] and n = 7, you should add 1, 2, 4, and return 3.

2. Let's talk about the logic behind the code first. For example, after adding 
numbers, we have [1, 2, 3, 4, 5, 13, 66] and n = 180. With [1, 2, 3, 4, 5] we 
have [1, (1+2+3+4+5 = 15)]. Now we need 16, what should we add? If the next number 
is less than 16 (in this case 13). As we already get [1, 15], we can get 16 by 
3 + 13. So with 13, we can get all the numbers between [1, 28]. Now we need 29. 
But 66 is too big for 29. So we add 29, to get numbers between [1, 57]. And 66 is 
still too big for 58. We add 58. Not we [1, 115]. Now 66 < 116. So we have [1, 182]. 
Now we cover 180 and finish the calculation. We add 29 and 58 after [1, 2, 3, 4 ,5].

3. At each point, we check the biggest sum we can get and compare it with the 
current number in the array. If nums[i] > sum + 1, we cannot get sum + 1. We have
to add this number. If nums[i] <= sum + 1, the biggest sum we can get go from [1, sum] 
to [1, sum + nums[i]]. In the next round, we check the new sum with the same current
number. To avoid overflow, we create a long variable preSum instead of int. We do
the calculation and check until the biggest sum > n.

3. If we are given an empty array or we iterate until the last number in the array,
but we still cannot reach n. We do not need to compare but just add the next number
we need, and increase the preSum.

*/

class Solution {
    public int minPatches(int[] nums, int n) {
        if (n <= 0) {
            return 0;
        }
        long preSum = 0;
        int count = 0;
        int i = 0;
        while (preSum < n) {
            if (i >= nums.length || preSum < nums[i] - 1) {
                preSum += preSum + 1; // add: preSum + 1
                count++;
            } else {
                preSum += nums[i++];
            }
        }
        return count;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/