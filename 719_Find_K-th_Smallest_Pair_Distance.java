/*
# Two Pointers

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
        
        // distance range: [0, max - min]
        int n = nums.length;
        int left = 0;
        int right = nums[n - 1] - nums[0];  // max - min
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;  // no. of pairs with distance <= m
            for (int i = 0, j = 0; i < n; i++) {
                while (j < n && nums[j] <= nums[i] + mid) {
                    j++;
                } // exit when nums[j] - nums[i] > mid
                count += j - i - 1;
            }
            
            if (count < k) {
                left = mid + 1;
            } else { 			// mid may not be a valid distance
                right = mid; 	// when count == k, cannot return
            }
        }
        
        return left;
    }
}

/*
Time complexity: O(nlogn), O(nlogn) for Arrays.sort() and 
						   logn times of O(n) iteration
Space complexity: O(1)
*/