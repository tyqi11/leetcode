/*
  # Solution 1
  @goelrishabh5
  https://leetcode.com/problems/maximum-of-absolute-value-expression/discuss/340075/c%2B%2B-beats-100-(both-time-and-memory)-with-algorithm-and-image
  arr1[i] - arr1[j] + arr2[i] - arr2[j] + i - j =   (arr1[i] + arr2[i] + i) - (arr1[j] + arr2[j] + j)
  arr1[i] - arr1[j] + arr2[i] - arr2[j] - i + j =   (arr1[i] + arr2[i] - i) - (arr1[j] + arr2[j] - j)
  arr1[i] - arr1[j] - arr2[i] + arr2[j] + i - j =   (arr1[i] - arr2[i] + i) - (arr1[j] - arr2[j] + j)
  arr1[i] - arr1[j] - arr2[i] + arr2[j] - i + j =   (arr1[i] - arr2[i] - i) - (arr1[j] - arr2[j] - j)

- arr1[i] + arr1[j] + arr2[i] - arr2[j] + i - j = - (arr1[i] - arr2[i] - i) + (arr1[j] - arr2[j] - j) 
- arr1[i] + arr1[j] + arr2[i] - arr2[j] - i + j = - (arr1[i] - arr2[i] + i) + (arr1[j] - arr2[j] + j)
- arr1[i] + arr1[j] - arr2[i] + arr2[j] + i - j = - (arr1[i] + arr2[i] - i) - (arr1[j] + arr2[j] - j)
- arr1[i] + arr1[j] - arr2[i] + arr2[j] - i + j = - (arr1[i] + arr2[i] + i) + (arr1[j] + arr2[j] + j)

Since i and j can be swapped, 1 & 8, 2 & 7, 3 & 6 and 4 & 5 are actually the same.
So we care about only 1 - 4. We can find the patterns in parentheses on each line
are the same. So we need the max and min for each pattern to get the max difference.

  # Solution 2 Manhattan Distance (I don't understand.)
  @lee215
  https://leetcode.com/problems/maximum-of-absolute-value-expression/discuss/339968/JavaC%2B%2BPython-Maximum-Manhattan-Distance
*/

class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int max2 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE, min2 = Integer.MAX_VALUE;
        int max3 = Integer.MIN_VALUE, min3 = Integer.MAX_VALUE;
        int max4 = Integer.MIN_VALUE, min4 = Integer.MAX_VALUE;
        
        for (int i = 0; i < arr1.length; i++) {
            max1 = Math.max(max1, arr1[i] + arr2[i] + i);
            max2 = Math.max(max2, arr1[i] + arr2[i] - i);
            max3 = Math.max(max3, arr1[i] - arr2[i] + i);
            max4 = Math.max(max4, arr1[i] - arr2[i] - i);
            min1 = Math.min(min1, arr1[i] + arr2[i] + i);
            min2 = Math.min(min2, arr1[i] + arr2[i] - i);
            min3 = Math.min(min3, arr1[i] - arr2[i] + i);
            min4 = Math.min(min4, arr1[i] - arr2[i] - i);
        }    
        return Math.max(max1 - min1, Math.max(max2 -min2, Math.max(max3 - min3, max4 - min4)));
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/
