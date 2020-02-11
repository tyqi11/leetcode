/*
1. My solution is to search from left to right for the closest ONE element to x, 
which takes O(n), and can be optimized to O(logn) with binary search. Then use two 
pointers to go to two ends and get K elements using O(k). Another O(klogk) to 
sort the list. This is O(n + k + klogk). This solution is intuitive but does not
take advantage of the given information.

2. Lee's/Stephan's solution takes fully advantage of the given sorted array and is not so hard
to understand also.

*/
// Solution 1
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int targetIdx = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < x) {
                targetIdx = i;
                minDiff = x - arr[i];
            } else if (arr[i] == x) {
                targetIdx = i;
                break;
            } else {
                if (arr[i] - x < minDiff) {
                    targetIdx = i;
                } 
                break;
            }
        }
        
        list.add(arr[targetIdx]);
        int i = targetIdx - 1;
        int j = targetIdx + 1;
        while (list.size() < k) {
            if (j >= arr.length || (i >= 0 && x - arr[i] <= arr[j] - x)) {
                list.add(arr[i]);
                i--;
            } else {
                list.add(arr[j]);
                j++;
            }
        }
        
        Collections.sort(list);
        return list;
    }
}

/***************************************************************************/
// Solution 1 optized with binary search

		int targetIdx = -1;
        int lo = 0;
        int hi = arr.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == x) {
                targetIdx = mid;
                break;
            } else if (arr[mid] < x) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        
        if (Math.abs(x - arr[lo]) <= Math.abs(x - arr[hi])) {
            targetIdx = lo;
        } else {
            targetIdx = hi;
        }

/**************************************************************/
// Solution 3:
/* we are searching for the start index of the result range, like [i, i + k - 1].
So, the rightmost start point(hi) is (arr.length - k);
.........arr[mid], arr[mid + 1], ........, arr[mid + k - 1], arr[mid + k], ......
           [                                       ]
                        [                                         ]
if x - arr[mid] > arr[mid + k] - x, we move the start index to the right, so that
we exclude a big difference and include a smaller one.

*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - k;
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = lo; i < lo + k; i++) {
            list.add(arr[i]);
        }
        
        return list;
    }
}

/*
Time complexity: O(log(n - k) + k)
Space complexity: O(1)
*/
