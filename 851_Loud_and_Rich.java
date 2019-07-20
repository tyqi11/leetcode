/*
# DFS

1. After initializing the adjacency list, iterate over all persons. Set the default
most quiet person as himself, then iterating all the people richer than him. During
this time, also iterating people richer than those richer than himsel, keep updatint 
the most quiet person.

2. dfs returns the person index, but we do not need it in the main loudAndRich function.
We just need dfs to update the universal variable answer.

*/

class Solution {
    int[] answer;
    List<List<Integer>> graph;
    
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        answer = new int[n]; // store the person, not its quietness
        Arrays.fill(answer, -1);
        graph = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {           // O(V) 
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge : richer) {			   // O(E) 
            graph.get(edge[1]).add(edge[0]);
        } // person and a list of persions richer than him
        
        for (int i = 0; i < n; i++) {         // O(V) 
            dfs(quiet, i);                    // O(E) 
        }
        
        return answer;
    }
    
    private int dfs(int[] quiet, int cur) {
        if (answer[cur] > -1) {
            return answer[cur];
        }
        answer[cur] = cur;
        for (int rich : graph.get(cur)) {
            int quietPerson = dfs(quiet, rich);
            if (quiet[answer[cur]] > quiet[quietPerson]) {
                answer[cur] = quietPerson;
            }
        }
        return answer[cur];
    }
}

/*
Time complexity: O(V + E), O(V) for 
Space complexity: O(V), O(V) for the graph list, and O(V) for the call stack
*/