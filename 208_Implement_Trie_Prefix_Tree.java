class Trie {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
        
        TrieNode() {
            
        }
    }
    
    TrieNode root = new TrieNode();
    
    /** Initialize your data structure here. */
    public Trie() {
        
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode n = root;
        for (char c : word.toCharArray()) {
            if (n.children[c - 'a'] == null) {
                n.children[c - 'a'] = new TrieNode();
            }
            n = n.children[c - 'a'];            
        }
        n.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode n = root;
        for (char c : word.toCharArray()) {
            if (n.children[c - 'a'] == null) {
                return false;
            }
            n = n.children[c - 'a'];
        }
        return n.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode n = root;
        for (char c : prefix.toCharArray()) {
            if (n.children[c - 'a'] == null) {
                return false;
            }
            n = n.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


