/*
# Simple but bad with HashMap
# Efficient but hard with Queue
*/

// Solution 1: HashMap
class Logger {
    Map<String, Integer> map;

    /** Initialize your data structure here. */
    public Logger() {
       map  = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        int prev = map.getOrDefault(message, -1);
        if (prev == -1 || timestamp - prev >= 10) {
            map.put(message, timestamp);
            return true;
        } 
        return false;
    }
}


/*
Time complexity: O()
Space complexity: O(n)
*/

/*****************************************************/
// Solution 2: Queue
class Logger {
    class Data {
        String str;
        int exp;
        public Data(String s, int e) {
            str = s;
            exp = e;
        }
    }

    Queue<Data> q;
    Set<String> set;
    /** Initialize your data structure here. */
    public Logger() {
        q = new LinkedList<>();
        set = new HashSet<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (!q.isEmpty() && q.peek().exp <= timestamp) {
            Data cur = q.poll();
            set.remove(cur.str);
        }
        
        if (!set.add(message)) {
            return false;
        }
        
        q.offer(new Data(message, timestamp + 10));
        return true;
    }
}
