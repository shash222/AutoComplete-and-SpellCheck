package trie;
import java.util.*;

public class Trie {
	Character c;
	Hashtable<Character,Trie> children;
	boolean endOfWord;
	
	public Trie() {
		children=new Hashtable<Character,Trie>();
		endOfWord=false;
	}
	
	//searches tree to see if word exists
	public boolean search(String s, Trie t) {
		if (s.length()==0) {
			return true;
		}
		if (t.children.containsKey(s.charAt(0))) {
			return search(s.substring(1),t.children.get(s.charAt(0)));
		}
		return false;
	}
	
	public boolean search(String s) {
		return search(s, this);
	}

	//adds words to tree
	public void add(String s, Trie trie) {
		Hashtable<Character,Trie> children=trie.children;
		if (s.length()==0) {
			return;
		}
		if (children.containsKey(s.charAt(0))) {
			add(s.substring(1),children.get(s.charAt(0)));
		}
		else {
			children.put(s.charAt(0), new Trie());
			children.get(s.charAt(0)).c=s.charAt(0);
			add(s.substring(1),children.get(s.charAt(0)));
		}
		if (s.length()==1) {
			children.get(s.charAt(0)).endOfWord=true;
		}
		
	}
	
	public void add(String s) {
		add(s,this);		
	}

	public void iterate() {
		iterate("",this);
	}
	
	
	//prints all words that have been added to the tree
	public void iterate(String s,Trie t) {
		Set<Character> set=t.children.keySet();
		for (char c:set) {
			if (t.children.get(c).endOfWord) {
				System.out.println(s+c);
			}
			iterate(s+c,t.children.get(c));
		}
	}
}