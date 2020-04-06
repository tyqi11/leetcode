/*
The point is using what separators to mark the beginning of a new string?
*/

// Solution 1: adding the length of a string to know where is the end
public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str : strs) {
            sb.append(str.length()).append('/').append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int slash = s.indexOf("/", i);
            int len = Integer.valueOf(s.substring(i, slash));
            i = slash + len + 1;
            res.add(s.substring(slash + 1, i));
        }
        
        return res;
    }
}

/***************************************************************/
// Solution 2: adding a special character like "*". But we first need to make
// sure we are not mixing the "*" in the string with the "*" we added as a separator.
// Also here another way to do the iterate over the strings using stream (Java 8).
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        return strs.stream()
                   .map(s -> s.replace("/", "//")
                   .replace("*", "/*") + "*")
                   .collect(Collectors.joining());
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/') { // skip the "/" we added
                sb.append(s.charAt(++i)); 
            } else if (s.charAt(i) == '*') { // the "*" we added as a separator
                res.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(s.charAt(i));
            }
        }
        
        return res;
    }
}