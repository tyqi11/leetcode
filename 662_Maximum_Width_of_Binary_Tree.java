/*

1. In a binary tree, for a node at index i, its left child is 2 * i and right child 
is 2 * i + 1. We should track the index of the leftmost node in each level, and update
the maxWidth when index grows.

2. For example:
          a         level: 0, index: 1
        /   \       
       b     c      level: 1, index: 2, 3
      / \   / \
     d   e f   g    level: 2, index: 4, 5, 6, 7
level: 0, index: 1, list: [1], max = max(0, 1) = 1
helper(b, 1, 2) --- level: 1, index: 2, list: [1, 2], max = max(1, 1) = 1
                    helper(d, 2, 4) --- level: 2, index: 4, list: [1, 2, 4], max = 1
                                        helper(null, 3, 8)
                                        helper(null, 3, 9)
                    helper(e, 2, 5) --- level: 2, index: 5, list: [1, 2, 4], max = 2
                                        helper(null, 3, 10)
                                        helper(null, 3, 11)
helper(c, 1, 3) --- level: 1, index: 3, list: [1, 2], max = max(2, 2) = 2
                    helper(d, 2, 6) --- level: 2, index: 6, list: [1, 2, 4], max = 3
                                        helper(null, 3, 12)
                                        helper(null, 3, 13)
                    helper(e, 2, 7) --- level: 2, index: 7, list: [1, 2, 4], max = 4
                                        helper(null, 3, 14)
                                        helper(null, 3, 15)

*/

class Solution {
    private int max = 0;
    List<Integer> list;
    
    public int widthOfBinaryTree(TreeNode root) {
        list = new ArrayList<>();
        helper(root, 0, 1);
        return max;
    }
    
    private void helper(TreeNode root, int level, int index) {
        if (root == null) {
            return;
        }
        if (level == list.size()) {
            list.add(index);
        }
        max = Math.max(max, index + 1 - list.get(level));
        helper(root.left, level + 1, index * 2);
        helper(root.right, level + 1, index * 2 + 1);
    }
}

/*
Time complexity: O(n)
Space complexity: O(h)
*/