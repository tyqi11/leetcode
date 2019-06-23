/*

1. We can use split to separate the string into local name and domain name, and
into before + and after +, and combine different parts.

2. Or we can use indices to know the start and end position of different parts
and combine them. This way is faster.

*/

class Solution {
    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }
        Set<String> ans = new HashSet<>();
        for (String email : emails) {
            int end = email.length();
            int atIdx = email.indexOf("@");
            int plusIdx = email.indexOf("+");
            String res = "";
            if (plusIdx == -1) {
                res = email.substring(0, atIdx).replace(".", "") + email.substring(atIdx, end);
            } else {
                res = email.substring(0, plusIdx).replace(".", "") + email.substring(atIdx, end);
            }
            ans.add(res);
        } // iterate over each email address
        return ans.size();
    }
}

// or (use str.split(), slower and takes more space)

class Solution {
    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }
        Set<String> ans = new HashSet<>();
        for (String email : emails) {
            String[] parts = email.split("@");
            String[] local = parts[0].split("\\+"); // ???
            ans.add(local[0].replace(".", "") + "@" + parts[1]);
        } // iterate over each email address
        return ans.size();
    }
}

/*
Time complexity: O(n)
Space complexity: O(n)
*/