/*
similar to 378. Kth Smallest Element in A Sorted Matrix
*/

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) {
            return res;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for (int i = 0, j = 0; j < nums2.length; j++) {
            pq.offer(new int[]{nums1[i] + nums2[j], i , j});
        }
        
        while (k > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[1], j = cur[2];
            
            List<Integer> list = new LinkedList<>();
            list.add(nums1[i]);
            list.add(nums2[j]);
            res.add(list);
            
            if (i + 1 < nums1.length) {
                pq.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
            }
            
            k--;
        }
        
        return res;
    }
}


/*
Time complexity: O(klogk)
Space complexity: O(n), n is the length of the nums2 array
*/