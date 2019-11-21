class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(List<Integer> conn :connections) {
            int u = conn.get(0);
            int v = conn.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
        
        int[] visitTime = new int[n]; 
        helper(graph, -1, 0, 1, visitTime);
        
        return res;
    }
    
    
    public void helper(List<Integer>[] graph, int parent, int node, int timer, int[] visitTime) {
        visitTime[node] = timer;
        
        for(int neighbor : graph[node]) {
            if(neighbor == parent) {
                continue;
            }
            if(visitTime[neighbor] == 0) {
                helper(graph, node, neighbor, timer + 1, visitTime);
            }
            visitTime[node] = Math.min(visitTime[node], visitTime[neighbor]);
            if(timer < visitTime[neighbor]) {
                res.add(Arrays.asList(node, neighbor));
            }
        }       
        
    }
}