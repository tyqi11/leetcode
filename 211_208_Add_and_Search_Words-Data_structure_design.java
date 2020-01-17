class WordDictionary {
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
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word, 0, root);    
    }
    
    private boolean match(String word, int idx, TrieNode node) {
        if (idx == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(idx);
        if (c != '.') {
            return node.children[c - 'a'] != null && match(word, idx + 1, node.children[c - 'a']);
        } else {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null && match(word, idx + 1, node.children[i])) {
                    return true;
                }
            }
            return false;
        }      
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */