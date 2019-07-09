/*

1. Key 1: res.add(new ArrayList<>(curList)). We need to initialize a new List to add
to the result list, as the curList will always change when we pass it into new functions.

2. Key 2: curList.remove(curList.size() - 1). After each step, we need to remove the
last of the current list.  

*/

class Solution {
    
    public List<List<Integer>> res;
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        helper(root, new ArrayList<>(), sum);
        return res;
    }
    
    private void helper(TreeNode root, List<Integer> curList, int sum) {
        if (root == null) {
            return;
        }
        curList.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<>(curList));
        } else {
            helper(root.left, curList, sum - root.val);
            helper(root.right, curList, sum - root.val);
        }
        curList.remove(curList.size() - 1);
    }
}

/*
Time complexity: O(n)
Space complexity: O(h)
*/