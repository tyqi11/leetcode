/*
# Two pointers

1. We use two pointers going from left and right separately. Check current
area, comparing with the maximum, and move the smaller height, until the 
pointers meet.

*/

class Solution {
    public int maxArea(int[] height) {
        int i = 0; 
        int j = height.length - 1;
        int max = 0;
        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            max = (area > max) ? area : max;
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/