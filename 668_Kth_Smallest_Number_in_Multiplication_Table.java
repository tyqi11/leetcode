/*
# Binary Search

The reason why we cannot use:
		while (left <= right) {
			...
			if (c == k) {
				return mid;
			}
			...
		}
is that mid may not be a valid number in the multiplication table. And there
might be more than one number meeting the requirement of c == k, but we only
want the smallest one.

*/

class Solution {
    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int c = count(m, n, mid);
            if (c >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    private int count(int m, int n, int mid) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int temp = Math.min(mid / i, n);
            count += temp;
        }
        return count;
    }
}

/*
Time complexity: O(m * log(m * n)), log(m * n) times of binary search, each
                                    time iterate m times for count.
Space complexity: O(1)
*/