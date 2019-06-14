/*

*/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        int start = 0;
        int pre = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                pre += tank;
                tank = 0;
            }
        }
        return (tank + pre >= 0) ? start : -1;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/