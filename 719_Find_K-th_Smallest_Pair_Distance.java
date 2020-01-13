/*
# Two Pointers (similar to 378 solving with binary search)

1. The pair distances fall into a range of [0, max - min]. So we set a distance *mid*, 
and search to check how many pairs' distance <= mid. 
1.1 If count < k, mid is smaller than need. So we enlarge the range. The next possible
mid is based on left = mid + 1.
1.2 If count == k, there are exacly k small pairs. BUT, count may not be a valid 
answer. As in [1, 1, 3]. There are 1 pair whose distance <= 1. But their distance is
actually 0, not 1. So we need to narrow down the search range. right = mid - 1 is 
wrong because mid is a possible anwer. we cannot leave it out. So right = mid.
1.3 If count > k, narrow down the range by right = mid. Why not right = mid - 1? 
Because there may be a few same distances. For example, the distances from small to
large are: ..., 3, 3, 3, 3, 4, ...  count > k, but right = mid - 1 will leave the right
				^ k      ^ count
answer out.
So, 1.2 and 1.3 are combinded as if count >= k, right = mid.

*/

class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        
        int lo = 0;
        int hi = nums[n - 1] - nums[0];
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0;
            for (int i = 0, j = i + 1; i < n; i++) {
                while (j < n && nums[j] - nums[i] <= mid) {
                    j++;
                }
                count += j - i - 1;
            }
            
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        
        return lo;
    }
}

/*
Time complexity: O(nlogn), O(nlogn) for Arrays.sort() and 
						   logn times of O(n) iteration
Space complexity: O(1)
*/