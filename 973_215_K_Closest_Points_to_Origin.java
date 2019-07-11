/*

1. This is almost the same as 215. Kth Largest Element in an Array. Here we compare
the distance when put into the heap. The partion method is not shown here. (and not
so necessary when k is small)

*/

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int dis1 = o1[0] * o1[0] + o1[1] * o1[1];
                int dis2 = o2[0] * o2[0] + o2[1] * o2[1];
                return dis2 - dis1;
            }
        }); // maxHeap, top is the biggest, keep the smallest k
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] ans = new int[K][2];
        int i = 0;
        while (!pq.isEmpty()) {
            ans[i] = pq.poll();
            i++;
        }
        return ans;
    }
}

/*
Time complexity: O(nlogk)
Space complexity: O(k)
*/