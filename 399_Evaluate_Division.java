 /*
# DFS
# Union Find

1. The DFS solution is easier to think of. When we want a / z, we search through
a / b, b / c ... y / z, and multiply the results as a whole. And when storing 
a / b, we also need b / a so we can avoid divisions.

2. The core of the Union Find method is a / b = (a / root) / (b / root). If two
elements have different roots, there is no answer. If they have the same root,
we should make sure we store the ration of element to its root.
 
For example, given: a / b = 2.0, b / c = 3.0, query: a / c
union(a, b, 2.0) parent a, b   ratio a, 2.0
						b, b         b, 1.0
union(b, c, 3.0) parent a, b   ratio a, 2.0
						b, c         b, 3.0	 
						c, c         c, 1.0
find(a)			 parent a, c   ratio a, 6.0
						b, c         b, 3.0	 
						c, c         c, 1.0					
find(c)					
ans[0] = ratio.get(a) / ratio.get(c) = 6.0

*/

// Solution 1: DFS
class Solution {
    double[] ans;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        ans = new double[queries.size()];
        Map<String, Map<String, Double>> graph = new HashMap<>(); // <a, <b, 2.0>>
        
        for (int i = 0; i < equations.size(); i++) {
            String key = equations.get(i).get(0);
            String val = equations.get(i).get(1);
            graph.putIfAbsent(key, new HashMap<>());
            graph.get(key).put(val, values[i]);
            graph.putIfAbsent(val, new HashMap<>());
            graph.get(val).put(key, 1 / values[i]);
        }
        
        for (int i = 0; i < queries.size(); i++) {
            String first = queries.get(i).get(0);
            String second = queries.get(i).get(1);
            ans[i] = dfs(graph, first, second, new HashSet<>());
        }
        
        return ans;
    }
        
    private double dfs(Map<String, Map<String, Double>> graph, String first, String second, Set<String> visited) {
        if (!graph.containsKey(first)) {
            return -1.0;
        }
        
        Map<String, Double> vals = graph.get(first);
        if (vals.containsKey(second)) {
            return vals.get(second);
        }
        
        visited.add(first);
        for (Map.Entry<String, Double> next : vals.entrySet()) {
            if (!visited.contains(next.getKey())) {
                double quotient = dfs(graph, next.getKey(), second, visited);
                if (quotient != -1.0) {
                    return next.getValue() * quotient;
                }
            }
        }
        
        return -1.0;
    }
}

/*
Time complexity: O(V + E), E is equations.size(), V is number of queries
Space complexity: O(V + E), O(E) for the graph map, and O(V) for the visited array
*/

/*******************************************************************/
// Solution 2: Union Find
class Solution {
    Map<String, String> parent;
    Map<String, Double> ratio;
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        parent = new HashMap<>();
        ratio = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }
        
        double[] ans = new double[queries.size()];
        for (int i = 0; i < ans.length; i++) {
            String first = queries.get(i).get(0);
            String second = queries.get(i).get(1);
            
            // first or second never appears in equations, 
            // or they have different ancestor so cannot be compared
            if (!parent.containsKey(first) || !parent.containsKey(second)
               || !find(first).equals(find(second))) {
                ans[i] = -1.0;
            } else { // first / second = (first / ancestor) / (second / ancestor)
                ans[i] = ratio.get(first) / ratio.get(second);
            }
        }
        
        return ans;
    }
    
    private void union(String first, String second, double val) {
        // all the charcters appeared should be in the parent and ratio map
        if (!parent.containsKey(first)) {
            parent.put(first, first);
            ratio.put(first, 1.0);
        }
        
        if (!parent.containsKey(second)) {
            parent.put(second, second);
            ratio.put(second, 1.0);
        }
        
        String ancestor1 = find(first);
        String ancestor2 = find(second);
        
        // anc1 / anc2 = (first / ratio.get(first)) / (second / ratio.get(second))
        //             = ratio.get(second) / ratio.get(first) * (first / second)
        parent.put(ancestor1, ancestor2);
        ratio.put(ancestor1, ratio.get(second) / ratio.get(first) * val);
        
        // it is the same if we do parent.put(ancestor2, ancestor1)
        // as long as we change the ratio accordingly
        // parent.put(ancestor2, ancestor1);
        // // anc2 / anc1 = (second / ratio.get(second)) / (first / ratio.get(first))
        // //             = ratio.get(first) / ratio.get(second) * (second / first)
        // ratio.put(ancestor2, ratio.get(first) / ratio.get(second) / val);
    }
    
    private String find(String s) {
        if (s.equals(parent.get(s))) {
            return s;
        }
        
        String father = parent.get(s);
        String ancestor = find(father);
        parent.put(s, ancestor);
        // s / ancestor = (s / father) * (father / ancestor)
        ratio.put(s, ratio.get(s) * ratio.get(father));
        
        return ancestor;
    }
}

/*
Time complexity: O(V + E)
Space complexity: O(V)
*/