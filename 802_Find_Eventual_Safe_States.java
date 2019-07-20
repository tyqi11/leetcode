/*
# DFS

1. At each node, we set it as UNSAFE when we first reach it. Then we do a DFS of 
all its next nodes. If we reach an UNSAFE node at the *deepest* end, return false, 
and keep the root node UNSAFE. If we reach a SAFE node at the end, change the root 
state to SAFE. And iterate from the next node.


*/

class Solution {
    enum State {
        SAFE,
        UNSAFE
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<Integer> res = new ArrayList<>(n);
        State[] states = new State[n];
        for (int i = 0; i < n; i++) {        // O(V)
            if (isSafe(graph, states, i)) {  // O(E) as a sum
                res.add(i);
            }
        }
        return res;
    }
    
    private boolean isSafe(int[][] graph, State[] states, int cur) {
        if (states[cur] != null) {
            return states[cur] == State.SAFE;
        }
        states[cur] = State.UNSAFE;
        for (int next : graph[cur]) {           
            if (!isSafe(graph, states, next)) {
                return false;
            }
        }
        states[cur] = State.SAFE;
        return true;
    }
}

/*
Time complexity: O(V + E)
Space complexity: O(V + E), O(V) for the states array, O(E) for the call stack
*/