/*
# Stack

1. The key point in solving this problem is to figure out how many time each
element i functions as the min(B). The the final result is sum of each i * times.
                            0  1  2  3  4  5  6  7
2. For element 3 in array: [2, 9, 7, 8, 3, 4, 6, 1]. Its previous less element(PLE) 
is 2 at index 0 and its next less element(NLE) is 1 at index 7.
It is the min of subarray:          3 is the min of 4 * 3 arrays
[2, 9, 7, 8, 3, 4, 6, 1]            4 is distance from PLE to 3  
   [9, 7, 8, 3, 4, 6]               3 is distance from 3 to NLE
   [9, 7, 8, 3, 4]
   [9, 7, 8, 3]
      [7, 8, 3, 4, 6]
      [7, 8, 3, 4]
      [7, 8, 3]
         [8, 3, 4, 6]
         [8, 3, 4]
         [8, 3]
            [3, 4, 6]
            [3, 4]
            [3]
So, for element 3, it contributes 3 * 4 * 3 to result.      
                                     PLE          v
3. What if there are equal elements? [3, 9, 7, 8, 3, 4, 6, 1]? Where does
[3, 9, 7, 8, 3] belong? The answer is: always count them for the previous/next 3. 
For example, if PLE is strictly < current, and NLE is <= current, then 
[3, 9, 7, 8, 3] belongs to the PLE 3 but not the current 3. It is the same if 
we make PLE <= current and NLE < current.

4. When finding NLE, we can go from two directions but achieve the same result. 
Shown as the two code blocks.

*/

class Solution {
    public int sumSubarrayMins(int[] A) {
        int len = A.length;
        Deque<Integer> stack = new ArrayDeque<>(); // element indices
        int[] left = new int[len];  // distance to previous less element
        int[] right = new int[len]; // distance to next less element
        
        for (int i = 0; i < len; i++) { // initialize to impossible values
            left[i] = i + 1;
            right[i] = len - i;
        }
        
        // find previous less element
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && A[stack.peekFirst()] > A[i]) {
                stack.pollFirst();
            } // exit when find previous <= current
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peekFirst();
            stack.offerFirst(i);
        }
        
        // find next less element
        stack = new ArrayDeque<>();
        // for (int i = 0; i < len; i++) {
        //     while (!stack.isEmpty() && A[stack.peekFirst()] > A[i]) {
        //         right[stack.peekFirst()] = i - stack.peekFirst();
        //         stack.pollFirst();
        //     } // exit when find previous <= curret
        //     stack.offerFirst(i);
        // }
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[stack.peekFirst()] >= A[i]) {
                stack.pollFirst();
            } // exit when find previous <= current
            right[i] = stack.isEmpty() ? len - i : stack.peekFirst() - i;
            stack.offerFirst(i);
        }
        
        long ans = 0;
        for (int i = 0; i < len; i++) {
            ans += A[i] * left[i] * right[i];
        }
        
        return (int) (ans % (1e9 + 7));
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/