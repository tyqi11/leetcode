/*
# Dynamic Programming

1. First put all <capital, profit> pairs into a heap, with capital ascending.

2. Poll all the pairs meeting current capital in our hand into a new heap, with
profit descending. Every time, we choose the project that 1) requires less capital
than we have, 2) can bring maximum net profit. Then we repeat this process until
k projects.


*/

class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Queue<int[]> pqCap = new PriorityQueue<>((a, b) -> (a[0] - b[0])); // minHeap, min capital first
        Queue<int[]> pqPro = new PriorityQueue<>((a, b) -> (b[1] - a[1])); // maxHeap, max profit first
        for (int i = 0; i < Profits.length; i++) {        // O(n)
            pqCap.add(new int[]{Capital[i], Profits[i]}); // O(logn)
        }
        
        for (int i = 0; i < k; i++) {
            while (!pqCap.isEmpty() && pqCap.peek()[0] <= W) {
                pqPro.add(pqCap.poll());
            }
            if (pqPro.isEmpty()) {
                break;
            }
            W += pqPro.poll()[1];
        }
        return W; 
    }
}

/*
Time complexity: O(nlogn)
Space complexity: O(n)
*/