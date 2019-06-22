/*
# Bucket Sort
# Radix Sort

1. If all the numbers in the array are equally distributed, then the gap between
two is (max  - min) / (n - 1). So the max gap we want must be >= that number.
We use this number as the bucket size, so that we do not need to compate numbers 
in the same bucket. To make sure all the number can be placed in a bucket, we
create n buckets.

2. We iterate over all the numbers to get the max and min in each bucket. The 
biggest gap lies in the difference between the max of first and the min of the 
next one. Be careful that there might be empty buckets, so you cannot compare by
bucketMin[i + 1] - bucketMax[i] as bucket[i] might be empty.

3. Another way is radix sort, who is a O(n) time complexity sort method, whose
speed is highly related to the number of digits of the maximum number. For 
input like [1, 10000000], this is so inefficient. A visualization is at: 
https://www.cs.usfca.edu/~galles/visualization/RadixSort.html. After watching
the video, it is very easy to understand the code.


*/

// Solution 1: bucket sort
class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min == max) {
            return 0;
        }
        int size = (int)Math.ceil(1.0 * (max - min) / (n - 1)); 
        int[] bucketMin = new int[n];
        int[] bucketMax = new int[n];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        for (int num : nums) {
            int idx = (num - min) / size;
            bucketMin[idx] = Math.min(bucketMin[idx], num);
            bucketMax[idx] = Math.max(bucketMax[idx], num);
        }
        
        int maxGap = 0;
        for (int i = 0; i < n; i++) { 
            if (bucketMax[i] != Integer.MIN_VALUE) {
                maxGap = Math.max(maxGap, bucketMin[i] - min);
                min = bucketMax[i];
            }
        }
        return maxGap;
        
    }
}

/*
Time complexity: O(n), from O(n + b), b is number of buckets, b < n
Space complexity: O(n), from O(2 * b), b is number of buckets, b < n
*/

/*************************************************************/

// Solution 2: radix sort
class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        } 
        int n = nums.length;
        
        int max = nums[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
        }
        
        int divisor = 1;        
        int[] sorted = new int[n];
        while (max / divisor != 0) { // go through all digits
            int[] count = new int[10];
            for (int i = 0; i < n; i++) {
                int idx = (nums[i] / divisor) % 10;
                count[idx]++;
            }
            
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            
            for (int i = n - 1; i >= 0; i--) {
             //   int idx = count[(nums[i] / divisor) % 10] - 1;
             //   sorted[idx] = nums[i];
             // the above is false, as the internal value in count is not changed 
                sorted[--count[(nums[i] / divisor) % 10]] = nums[i];
            }
            
            for (int i = 0; i < n; i++) {
                nums[i] = sorted[i];
            }
            
            divisor *= 10;            
        }
        
        int maxGap = 0;
        for (int i = 1; i < n; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }
        return maxGap;
    }
}

/*
Time complexity: O(n), from O(k * (3n + 10)), can be very slow
Space complexity: O(n), from O(n + 10) for sorted array and count array 
*/

/*


*/