/*
# Two pointers

1. It is easy to think of two pointes method, but how to increase count every time
we introduce a new element into the product? The key is count += j - i + 1.

2. For example,
		0	1	2	3	4	5
nums = [3,	2,	6,	4,	88, 1, .....], k = 50
		    j
[i, j] = [3]
		 [3, 2], 		2 new arrays: [2], [3,2]
		 [3, 2, 6], 	3 new arrays: [6], [2, 6], [3, 2, 6]
0,3		 [3, 2, 5, 4], product > 50
1,3		 	[2, 5, 4],	3 new arrays: [4], [5, 4], [2, 5, 4]
1,4		 	[2, 5, 4, 88], product > 50
...
4,4		 			 [88], i == j, product >= k, product = 1, i++
5,4						 [1], j - i + 1 = 0, count += 0
5,5, i = j again,		 [1], 1 new array: 1

*/

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        
        int product = 1;
        int count = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            product *= nums[j];
            while (i <= j && product >= k) {
                product /= nums[i++];
            }
            count += j - i + 1;
        }
        return count;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/