/*

When we need to go through elements in ascending/descending order, we often put them 
in a heap or sort the array.

*/

class Solution {
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }); // maxHeap, top is the largest
        for (int stone: stones) {
            q.offer(stone);
        } // O(nlogn)
        while (q.size() >= 2) {
            int first = q.poll();
            int second = q.poll();
            if (first > second) {
                q.offer(first - second);
            }
        }
        return q.isEmpty() ? 0 : q.peek();
    }
}

/*
Time complexity: O(nlogn)
Space complexity: O(n)
*/