/*
# Binary search 

1. The difference between 274 and 275 is that 275 gives us a sorted ascending 
array. We can use the sort method in 274, which has O(n) time complexity. But 
we are encouraged to optimize to O(logn), which reminds us of binary search. 
(This also reminds me that I can use binary search in previous sort algorithm 
too, as this is the same question after sorting.)

2. Do the binary search:
2.1 if citations[mid] == n - mid, we get the right answer.
2.2 if citations[mid] > n - mid, citations are more than papers, we check the 
left half to include more papers, and decrease citations.
2.3 if citations[mid] < n - mid, citations are less than papers, we check the 
rigth half to include fewer paper and increase citations.

3. When we move, we do not include mid as boundary by left = mid + 1 and 
right = mid - 1. So the boundaries are not checked. Left-hand side of left pointer
and right-hand side of right pointer are checked. So when left = right + 1, 
left reaches the right-hand side of right pointer. That is the answer.

*/

class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == n - mid) {
                return n - mid;
            } else if (citations[mid] < n - mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return n - left;

    }
}

/*
Time comlexity: O(logn)
Space complexity: O(1)
*/