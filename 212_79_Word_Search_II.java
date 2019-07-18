/*
# DFS

1. First we build a Trie from the words array. Mark the end of a word with
a *word* variable. A trie is like:
                             root
                     /     |      \    \
                    p      a       r    i
                  / |      |\      | \   \
                u   e      n t(at) a  e   s(is)
              /     |      |       |   \
             t(put) a(pea) d(and)  i    d(red)
                    |              |
                    c              n(rain)
                    |              |
                    e(peace)       y(rainy)
2. For each starting point in board, do a dfs to check if this might be a
beginning char of words.

3. Do dfs recursively. At each char, if it is visited or there is no such
char in this place, return. Else, include the current char. Check if this
is already the end of a word first, and then check all its neighbors for
the next chat in words.

*/

class Solution {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word; // mark the end of a word
    }
    
    List<String> res = new ArrayList<>();
    int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words); // O(charsInWords)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root); // do dfs from each char in board
            }
        } // (m * n) times of dfs, each dfs takes at most O(charsInWords)
        return res;
    }
    
    private void dfs(char[][] board, int x, int y, TrieNode p) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        char c = board[x][y];
        if (c == '#' || p.next[c - 'a'] == null) {
            return;
        } // visited, or there is not char c at this place
        p = p.next[c - 'a']; // inclue current char
        if (p.word != null) { // reach the end of word
            res.add(p.word);
            p.word = null; // avoid counting this word again
        }
        
        board[x][y] = '#'; // this char is visited cannot be used again
        for (int[] n : neighbors) {
            dfs(board, x + n[0], y + n[1], p); 
        } // do validity chech at the beginning of dfs
        board[x][y] = c; // release this char
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) {
                    p.next[i] = new TrieNode();
                }
                p = p.next[i];
            }
            p.word = w; // save the word at the end of its chars
        }
        return root;
    }
}

/*
Time complexity: O(m * n * charsInWords), charsInWords is the length sum of all 
                                          strings in words array.
Space complexity: O(charsInWords)
*/