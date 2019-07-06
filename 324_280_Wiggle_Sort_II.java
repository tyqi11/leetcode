/*
1. According to the post @StepanPochman https://leetcode.com/discuss/76965/3-lines-python-with-explanation-proof, 
we should put elements i:
(1) if i < median, put into the last even slots;
(2) if i > median, put into the first odd slots;
(3) if i = median, remaining ones.
So we care about elements on index 1 3 5 ... if they are larger than median.
Then we care about elements on index 0 2 4 ... if they are smaller than median.
But how do we iterate like 1 3 5 0 2 4 ...

2. We use index mapping: newIndex = (1 + 2 * oldIndex) % (n | 1). After this,
(1) For an array with even length: 0 1 2 3 4 5 --> 1 3 5 0 2 4
(1) For an array with odd length: 0 1 2 3 4 5 6 --> 1 3 5 0 2 4 6
So for an index i growing from 0 to n - 1, we do not care the element nums[oldIndex],
we only care about nums[newIndex], and make changes also according to the new index.

3. There are many ways to find the median of a unsorted array of integers. The time 
complexity of the wiggleSort may depend on the sorting time complexity. For average
cases, QuickSort takes O(n).
*/

class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        double median = findMedian(nums);
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int i = 0;
        while (i < n) {
        // we use i to get index 1, 3, 5, ..., 0, 2, 4, 6, ... in order
            int idx = index(i, n);
            if (nums[idx] > median && i > left) {
                swap(nums, idx, index(left++, n));
            } else if (nums[idx] < median && i < right) {
                swap(nums, idx, index(right--, n));
            } else {
                i++;
            }
        }
    }    
    
    // index-mapping algorithm
    private int index(int num, int n) {
        return (1 + 2 * num) % (n | 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // if QuickSort, O(n) for average case
    // here O(nlogn) using heap
    private double findMedian(int[] nums) {
        PriorityQueue<Integer> small = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        PriorityQueue<Integer> large = new PriorityQueue<>();
        
        for (int num : nums) {
            large.add(num);
            small.add(large.poll()); 
            // add 1 and poll 1 to large, large.size() does not change
            // add 1 to small, small.size() + 1
            if (large.size() < small.size()) {
                large.add(small.poll());
            } // keep large.size() = small.size() / small.size() + 1
        }
        
        if (large.size() > small.size()) {
            return large.peek();
        } else { // large.size() == small.size()
            return 0.5* (large.peek() + small.peek());
        }
    }
    
}