/*
# Two Pointers

0. We may want to start solving this problem by checking L's and R's around '.'.
But it is hard to conclude them into simple situations. So we think in the opposite
way by checking when we meet 'L' and 'R' and analyze how they influence nearby places.

1. We use two pointers to indicate last time we see L and R. And split the 
problem into 4 situations. 

1.1 ...L...R...R 	 	'.' between two R's will turn to 'R'
       l   r   ^ cur
1.2 ...R...L...R 		previous results does not affect current point, do nothing
  	   r   l   ^ cur
1.3 ...R...L...L 		'.' between two L's will turn to L
       r   l   ^ cur
1.4 ...L...R...L 		'.' changes depend on their distance to 'R' and 'L'
       l   r   ^ cur

2. In the code, 
1) set initial time of seeing L and R as -1 instead of 0,
2) for case 1.1, if input is ".L.R.", the last '.' will be missed. So, the for loop
should go from 0 to s.length(), and we take care of i == s.length() before judging
d[i].
3) for case 1.3, if input is ".L.R...LR..L..", the first '.' will be missed. So, we
take care of this situation.

*/

class Solution {
    public String pushDominoes(String dominoes) {
        char[] d = dominoes.toCharArray();
        int l = -1; // last time see L
        int r = -1; // last time see R
        
        for (int i = 0; i <= dominoes.length(); i++) {
            if (i == dominoes.length() || d[i] == 'R') {          
                if (r > l) {
                    while (++r < i) {  // 1.1
                        d[r] = 'R';
                    }
                }
                r = i;               // 1.2
            } else if (d[i] == 'L') {
                if (l > r || (r == -1 && l == -1)) {
                    while (++l < i) {  // 1.3
                        d[l] = 'L';
                    }
                } else {             // 1.4
                    for (int m = r + 1; m < i; m++) {
                        if (m - r == i - m) {
                            continue;
                        } else if (m - r < i - m) {
                            d[m] = 'R';
                        } else {
                            d[m] = 'L';
                        }
                    }
                }
                l = i;
            }
        } // end iterating all chars
        
        return new String(d);
    }
}


/*
Time complexity: O(n)
Space complexity: O(n)
*/