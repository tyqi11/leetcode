/*
# DFS

1. We go deep to the leaf nodes and return level by level. At each node, there
is a res array containing two results: current level not robbed, and current level
robbed. So when return, the parent level can do similar calculation like
current = Math.max(twoStepsBefore + currentValue, oneStepBefore).

2. For example: 
level leaf:
					        3
					     /     \
				        4       5
					  /   \       \
					 1     3       1
				  [0, 1] [0, 3]  [0, 1]
level middle:
robHelper(4): left = [0, 1], right = [0, 3]
			  res[0]: current not robbed, children rob or not, maximum
			  		 = max(0, 1) + max(0, 3) = 4
			  res[1]: current level robbed, children level not robbed
			         = 4 + 0 + 0 = 4			       
robHelper(5): left = [0, 0], right = [0, 1]
			  res[0]: current not robbed, children rob or not, maximum
			  		 = max(0, 0) + max(0, 1) = 1
			  res[1]: current level robbed, children level not robbed
			         = 5 + 0 + 0 = 5	
robHelper(3): left = [4, 4], right = [1, 5]
			  res[0] = max(4, 4) + max(1, 5) = 9
			  res[1] = 3 + 4 + 1 = 8
return Math.max(9, 8) = 9

3. If you want a detailed explanation, check @fun4LeetCode's great passage at:
https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem

*/

class Solution {
    public int rob(TreeNode root) {
        int[] res = robHelper(root); // [not robbed, robbed]
        return Math.max(res[0], res[1]);
    }
    
    private int[] robHelper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
}

/*
Time complexity: O(n), number of roots
Space complexity: O(n), res array for each node, O(h) for call stack
*/