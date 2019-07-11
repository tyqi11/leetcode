/*

1. Our goal is to put the largest number to the right place, and find the next largest
number and move to the right place, and so on. Steps:
1.1 Find the index of the target number
1.2 Flip from 0 to index, now the largest is at index 0
1.3 Flip from 0 to largest - 1, as largest -1 is the final and right index of largest
1.4 Add the flip sequence to the result.
1.5 Find the next target.
To avoid unnecessary flips, we check if the two flips will do opposite changes first.
                 0  1  2  3
2. For example: [3, 2, 4, 1]           0  1  2  3
round 1: target: 4, index: 2, flip 1: [4, 2, 3, 1], (0, index)
                              flip 2: [1, 3, 2, 4], (0, target - 1 = 3)
round 2: target: 3, index: 1, flip 3: [3, 1, 2, 4], (0, index)
                              flip 4: [2, 1, 3, 4], (0, target - 1 = 2)                             
round 3: target: 2, index: 0, flip x: [2, 1, 3, 4], index = 0, no change
                              flip 5: [1, 2, 3, 4], (0, target - 1 = 1)
                              
*/

class Solution {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        int target = A.length;
        for (int i = 0; i < A.length; i++) {
            int index = find(A, target);
            if (index != target - 1) {
                flip(A, index);
                flip(A, target - 1);
            }
            res.add(index + 1);
            res.add(target);
            target--;
        } 
        return res;
    }
    
    private int find(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    private void flip(int[] nums, int end) {
        int i = 0;
        int j = end;
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
}

/*
Time complexity: O(n^2)
Space complexity: O(1)
*/