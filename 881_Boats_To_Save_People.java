/*
# Two pointers

1. It is the best if we can put current heaviest and current lightest in the 
same boat. If not, at least we can put only the heaviest into the boat. 

2. So, for each round, j moves, means one boat filled. If it happens to fit
two people, i moves, so that i and j meets earlier. The steps j moves, is the
number of boats.


*/

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {
            return 0;
        }
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
            j--;
        }
        return people.length - 1 - j;
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/