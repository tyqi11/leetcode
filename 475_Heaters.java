/*
# Two pointes

1. First we need to sort the houses and heaters arrays so that all of them are in 
ascending order.

2. Next we iterate over all the houses, find the nearest heater to it, and check the
radius the heater must have to provide heat to this current house. How to understand
heaters[i + 1] - house <= house - heaters[i]?
                                    _______  
.. heater[i] ......................| house | ....... heater[i + 1]
                    |                           |
             house - heater[i]   >=    heater[i + 1] - house
when the equation is right, the house is closer to the next heater, so we do i++ to
go to the next heater and count the distance.

*/

class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int i = 0; // heater index
        int radius = 0; // start from 0, as there may be more heaters than houses
        for (int house : houses) {
            while (i + 1 < heaters.length && heaters[i + 1] - house <= house - heaters[i]) {
                i++;
            }
            radius = Math.max(radius, Math.abs(heaters[i] - house));
        }
        
        return radius;
    }
}

/*
Time complexity: O(nlogn + mlogm), n is the length of houses, m is the length of heaters
Space complexity: O(1), if we do in-place sort, or O(m + n)
*/