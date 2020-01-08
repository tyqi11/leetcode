/*
*/

class Solution {
    
    public int depthSum(List<NestedInteger> nestedList) {
        
        return helper(nestedList, 1);
    }
    
    private int helper(List<NestedInteger> nestedList, int depth) {
        if (nestedList == null) {
            return 0;
        }

        int sum = 0; 
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                sum += addInteger(n, depth);
            } else {
                sum += helper(n.getList(), depth + 1);
            }
        }
        return sum;
    }
    
    private int addInteger(NestedInteger n, int depth) {
        return n.getInteger() * depth;
    }
  
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/