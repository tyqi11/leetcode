/*

1. At each gas station, we have *tank* amount of gas in our tank, accumulating from 
station *start*. And the accumulation from index i to start is *pre*.

2. To go from i to i+1, there should be: tank = tank + gas[i] - cost[i] > 0.
If tank < 0, we need to change the start point from start to i + 1, because the 
accumulation from start to i + 1 has a negative impact on future travel. And at the 
same time, we do pre += tank to add the accumulation in this part to all the previous
storage. Then reset tank to 0.

3. Iterate over all the gas stations. When we reach the last station, we get the last 
*start* point that is not considered impossible, and the accumulation from 0 to the 
start point. This accumulation also describes the amout of gas we may use from this last
station to the start (stations are in a circle). If tank + pre > 0, we can go all the way
back to start. Return start. Else, return -1, as there will be no valid start point.

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