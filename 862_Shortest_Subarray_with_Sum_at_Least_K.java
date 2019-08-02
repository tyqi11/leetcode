/*
# Deque

1. When we need the sum of a subarray, we think about sum[i,j] = sum[0,j] - sum[0,i-1].
So we create B[i] represents the sum[0, i - 1].

2. We put the indices into the deque. But, if B[i] <= B[lastInDeque], as 
i > lastInDeque, in the future when there is a B[j] - B[lastInDeque] >= K there 
must be B[j] - B[i] >= K, and j - lastInDeque > j - i, so we poll out lastInDeque 
as it will never be the final answer. So the values stored in the deque are in 
ascending order.

3. When checking B[i] - B[firstInDeque], if there is a valid answer, we check and 
update the ans, and keep checking until the difference < k. Why pollFirst() 
instead of peekFirst()? Because if it is already an answer, when a future j 
comes, j > i, so j - firstInDeque is must larger.

*/

class Solution {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int ans = n + 1; // initialize to an impossible value
        
        int[] B = new int[n + 1]; // B[i] is sum of A[0, i - 1]
        
        for (int i = 0; i < n; i++) {
            B[i + 1] = B[i] + A[i];
        }
        
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n + 1; i++) {
            while (d.size() > 0 && B[i] - B[d.peekFirst()] >= K) {
                ans = Math.min(ans, i - d.pollFirst());
            }
            while (d.size() > 0 && B[i] <= B[d.peekLast()]) {
                d.pollLast();
            }
            d.offerLast(i);
        }
        
        return ans == n + 1 ? -1 : ans;
    }
}

/*
Time complexity: O(n), for initializing B array and iterating
Space complexity: O(n), for B array and iterating
*/