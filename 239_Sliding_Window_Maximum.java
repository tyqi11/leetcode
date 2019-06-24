/*
# Deque
# Partition

1. We keep a deque containing potential max elements (push position index into
the deque instead of the number itself to track the sliding window width). 
From left to right, if the current one is greater than the previous potential
maximum, we can pop up the previous as it can not be the greatest anymore. We keep
a descending order in the deque. So the first in the queue is the greatest. 
(credit to: https://www.youtube.com/watch?v=J6o_Wz-UGvc)

2. Partition the array into length of k. maxLeft[] is the max of each partition,
going from left . maxRight[] is the max of each partition going from right.
idx            0  1  2    3  4  5    6  7  
		      a1 a2 a3 | a4 a5 a6 | a7 a8  
res[]                0 |  1  2  3    4  5  
res[0]   left [ <--  ] |          |      
        right []       |           Math.max(left[i + k - 1], right[i])
---------------------------------------------------
res[1]   left          | []       |
        right    [ ->] |
---------------------------------------------------
res[2]   left          | [ <-]    |
        right       [] | 
---------------------------------------------------
res[3]   left          | [  <-- ] |
        right          | []       |  we do not need right[i]

when i % k == 0, we just need left[i + k - 1], and right[i] does not matter, so
we make right[i] = right[i].

*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return nums;
        }
        int n = nums.length;
        int[] res = new int[n - k + 1]; // {(n - 1) - [(k - 1) - 1]}
        int idx = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0;i < n; i++) {
            if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            if (i >= k - 1) {
                res[idx++] = nums[dq.peekFirst()];
            } // peek not poll
        }
        return res;
    }
}

//          First --- Last
// <--pollFirst()     <-- offerLast

/*
Time complexity: O(n)
Space complexity: O(1)
*/

/*********************************************************************/

// Solution 2
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return nums;
        }
        int n = nums.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        maxLeft[0] = nums[0];
        maxRight[n - 1] = nums[n- 1];
        for (int i = 1; i < n; i++) {
            maxLeft[i] = (i % k == 0) ? nums[i] : Math.max(maxLeft[i - 1], nums[i]);
            int j = n - i - 1;
            maxRight[j] = (j % k == 0) ? nums[j] : Math.max(maxRight[j + 1], nums[j]);
        }
        
        int[] res = new int[n - k + 1];
        for (int idx = 0; idx + k - 1 < n; idx++) {
            res[idx] = Math.max(maxRight[idx], maxLeft[idx + k - 1]);
        }
        return res;
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/