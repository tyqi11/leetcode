/*
# Greedy (BFS)


*/

class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int curEnd = 0;
        int furthest = 0;
        int jumps = 0;
        int i = 0;
        while (i <= curEnd) { // avoid curEnd stops at value 0
            while (i <= curEnd) {
                furthest = Math.max(furthest, i + nums[i]);
                i++;
            } // i > curEnd
            curEnd = furthest;
            jumps++;
            if (curEnd >= nums.length - 1) {
                return jumps;
            }
        }
        return 0;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/


/************************************************************************/
// TO BE COMPLETED

/*Follow Up: print out the shortest path*/
// (thanks to ???)
public int minJumps(int[] inputArr){
// handle the base cases
    int currFarthestIdx = inputArr[0];
    int nextFarthestIdx = inputArr[0];
    int nextFarthestIdx_startPos = 0;
    int currStep = 1;
    ArrayList<Integer> path = new ArrayList<Integer>();
    path.add(nextFarthestIdx_startPos);
    for(int idx = 0; idx <=  currFarthestIdx; idx++){
        if(nextFarthestIdx < idx + inputArr[idx]){
             nextFarthestIdx = idx + inputArr[idx];
             nextFarthestIdx_startPos = idx;
        }
        if(idx == currFarthestIdx){
             // handles the case where the last index is not reachable
              if(nextFarthestIdx <= idx){
                     return -1;
              }
              currFarthestIdx = nextFarthestIdx;
              path.add(nextFarthestIdx_startPos);
              currStep++;
              if(currFarthestIdx <= inputArr.length-1){
                     return currStep;
              }
        }
        return -1; // just a dummy val
    }
}  