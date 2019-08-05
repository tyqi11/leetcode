/*
# PriorityQueue

1. This problem is similar to merging k sorted arrays. We put one element from
each array into the PriorityQueue, poll out the smallest each time, and offer 
the next of the smallest into the queue.

2. We initialize the range as [0, MAX], which is the largest interval 32 bit can
represent, by *start* and *end*.

3. At the beginning, we put the first element of the each array into the queue and
record the max of them. Until now, we get a valid interval from minOfFirstElements
to max.

4. When there is one element from one array, we get the smallest of them and compare
end - start with max - currentSmallest and update. Then, each time we offer a new
element into the queue, we update the max. So max is always the largest element
in the queue.

5. Once we reach the end of an array, we stop searching. This make sure that:
1) we take into consideration this ending array
2) we are in the middle of other arrays, so we care them too.


*/

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        Queue<int[]> q = new PriorityQueue<>(nums.size(), new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return nums.get(a[0]).get(a[1]) - nums.get(b[0]).get(b[1]);
            }
        });
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            q.offer(new int[]{i, 0});
            max = Math.max(max, nums.get(i).get(0));
        } // put heads into queue, max is the largest head
        
        while (q.size() == nums.size()) {
            int[] cur = q.poll(); // the smallest in the heap
            int row = cur[0];
            int col = cur[1];
            if (end - start > max - nums.get(row).get(col)) {
                start = nums.get(row).get(col);
                end = max;
            }
            if (col + 1 < nums.get(row).size()) { // a next element exist
                q.offer(new int[]{row, col + 1});
                max = Math.max(max, nums.get(row).get(col + 1));
            }
        }
        
        return new int[]{start, end};
        
    }
}

/*
Time complexity: O(mnlogn), m is the average length of arrays, n = nums.size()
Space complexity: O(n)
*/