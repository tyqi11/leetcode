/*
# Dynamic Programming

1. At each gas station, we have *tank* amount of gas in our tank, accumulating 
from station *start*. And the accumulation from 0 to potential start is *pre*.

2. To go from i to i+1, there should be: tank += gas[i] - cost[i] > 0. 
If tank > 0, we continue moving to the next station.
If tank < 0, we cannot move to the next, and the accumulation between [start, i] 
has a negative impact on future travel. So we set start = i + 1. And pre += tank 
to add the accumulation during this part. Then reset tank to 0.

3. When we reach the last station, we get the last *start* point, that is a valid
start considering [start, end]. But we need to check, with current *tank*, if it is
able to go around all previous stations. If tank + pre > 0, we can go all the way
back to start. Return start. Else, return -1.
                0  1  2     ...    start   ...    end
                __ __ __ _________ ____  ________ __
                [      pre       ] [      tank     ]

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