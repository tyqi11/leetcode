/*
# Two pointers
# Dynamic Programming
# Using Stacks

1. Two pointers method is easiest to write and understand. When Using two 
pointers method, you should keep one thing in mind: no matter how many 
blocks are in between, as long as height1 < height2, you can fill water 
next to height1. In the code below, at one time, when you calculate leftMax, 
it must be smaller than current right. Because when you go into that loop, 
height[left] < height[right]. If leftMax is renewed, leftMax < height[right].
If leftMax is not renewed, it was a previous height[left]. And it reaches
leftMax, because it was smaller that a previous height[right]. Similarly, 
when you meet rightMax, you can feel free to fill water next to height[right].
There will always be a bigger height[left] to hold the water together.


*/

class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int total = 0;
        while (left <= right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                total += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                total += rightMax - height[right];
                right--;
            }
        }
        return total;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/