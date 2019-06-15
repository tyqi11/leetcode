/*
# Dynamic Programming

1. As the given numRows is non-negative, we should consider the corner case: numRows = 0 
first. We cannot return null but an empty List.

2. numRows = 1 is also a special case. We take care of it first. There are only one 
element 1. We create a new inner List, add 1 and add it to the result outer List.

3. For numRow >= 2, we split the calculation into 3 parts, the first 1, the numbers in 
between which needs to be calculated, and the last 1. For the first and last part, we 
just add 1. For the middle part, we need to get the numbers from the previous row and 
add. Take care of the relationship between the row and the index in each row.

*/

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows == 0) {
            return res;
        } 
        List<Integer> curList = new ArrayList<>();
        curList.add(1);
        res.add(curList);
        for (int row = 2; row <= numRows; row++) {
            curList = new ArrayList<>();
            curList.add(1);
            List<Integer> preList = res.get(row - 2);
            for (int i = 2; i < row; i++) {
                curList.add(preList.get(i - 2) + preList.get(i - 1));
            }
            curList.add(1);
            res.add(curList);            
        }
        return res;
    }
}

/*
Time complexity: O(n^2), n = numRows
    In the outer loop, it iterates numRows times. And in each inner loop, it itertes
1, 2, .. n times. So the sum should be 1+2+3... +n = (n+1)*n/2, -> O(n^2).
Space complexity: O(1)
    As we need to store each number we update, as the output.
*/