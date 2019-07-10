/*

credit to: @votrubac: https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221939/C%2B%2B-with-picture-post-order-traversal
            0a
        1/      \0
       0b        0c
     3/  \-1   2/  \-1
     4   0d    3    0e
At node 4 : left = 0, right =  0, moves = 0, return 4 - 1 + left + right = 3
At node 0d: left = 0, right =  0, moves = 0, return 0 - 1 + left + right = -1
At node 0b: left = 3, right = -1, moves = 4, return 0 - 1 + left + right = 1
At node 3 : left = 0, right =  0, moves = 0, return 3 - 1 + left + right = 2
At node 0e: left = 0, right =  0, moves = 0, return 0 - 1 + left + right = -1
At node 0c: left = 2, right = -1, moves = 7, return 0 - 1 + left + right = 0
At node 0a: left = 1, right =  0, moves = 8, return 0 - 1 + left + right = 0

How to understand: return root.val - 1 + left + right?
root has *val* coins, it saves *1* for itself, and pass the needs of its children:
*left* and *right* to its parent. So it returns root.val - 1 + left + right.


*/

class Solution {
    int moves = 0;
    
    public int distributeCoins(TreeNode root) {
        traverse(root);
        return moves;
    }
    
    private int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = traverse(root.left);
        int right = traverse(root.right);
        moves += Math.abs(left) + Math.abs(right);
        return root.val - 1 + left + right;
    }
}

/*
Time complexity: O(n)
Space complexity: O(h)
*/