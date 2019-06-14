/*

1. First we consider the corner cases. If the guess is the exactly the secret, return.

2. For all 10 numbers that may appear, we create a new int array to store whether it appears
before, and which does it appear.

3. We compare each char in both secret and guess. 
3.1 if at the same position, the two chars are the same, then it is a bull, A++;
3.2 if the two chars are different, one appearance in secret leads to count[i]++, and one 
appearance in guess leads to count[i]--.
3.2.1 if counts[s] < 0: s appears in guess before. We find one digit that "match the secret number"
but locate in the wrong position", B++. Then counts[s]++ as a indication that this one appearance
in guess is already taken into consideration.
3.2.2 similarly, if counts[g] > 0: g appear in secret before. B++ and counts[g]-- to indicate 
the appearance in secret is already counted.

4. After going through the whole string, we get bulls and cows.

*/

class Solution {
    public String getHint(String secret, String guess) {
        if (secret.equals(guess)) {
            return secret.length() + "A0B";
        }
        int[] counts = new int[10];
        int A = 0;
        int B = 0;
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                A++;
            } else {
                if (counts[s] < 0) {
                    B++;
                }
                counts[s]++;
                if (counts[g] > 0) {
                    B++;
                }
                counts[g]--;
            }
        }
        return A + "A" + B + "B";
    }
}

/*
Time complexity: O(n)
Space complexity: O(1)
*/