package trie;
import java.util.*;
import java.io.*;

public class Trie {
	private Character c;
	private Hashtable<Character,Trie> children;
	private boolean endOfWord;

	public Trie(){
		children=new Hashtable<Character,Trie>();
		endOfWord=false;
	}
	
	//searches tree to see if word exists
	public boolean contains(String s, Trie t) { 
		if (s.length()==0) {
			return true;
		}
		if (t.children.containsKey(s.charAt(0))) {
			//if end of word is met, but that specific letter is not marked as end of word in trie, word does not exist
			if (s.length()==1&&!t.children.get(s.charAt(0)).endOfWord) {
				return false;
			}
			
			return contains(s.substring(1),t.children.get(s.charAt(0)));
		}
		return false;
	}
	
	public boolean contains(String s) { 
		return contains(s, this);
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
	
	//returns list of all words in trie
	public List<String> getWords(){
		return getWords("",this);
	}
	
	//returns list of all remaining letters to words that start with string s
	public List<String> getWords(String s){
		Trie trie=getLastNode(s,this);
		List<String> words=new ArrayList<>();
		//empty string is placeholder for first word if it is a word in dictionary
		//the for loop at end of method adds the entire value of s to the current value in every position
		//if s is added in this if statement, then s will be concatenated to it in for loop
		if (this.contains(s)&&!s.equals("")) {
			words.add("");
		}
		
		words.addAll(getWords("",trie));
		for (int i=0;i<words.size();i++) {
			words.set(i, s+words.get(i));
		}
		return words;
	}
	
	private List<String> getWords(String s,Trie t) {
		List<String> words=new ArrayList<>();
		if (t==null) {
			return words;
		}
 		Set<Character> set=t.children.keySet();
		for (char c:set) {
			if (t.children.get(c).endOfWord) {
				words.add(s+c);
			}
			words.addAll(getWords(s+c,t.children.get(c)));
		}
		return words;
	}

	//returns node after last node of string s
	private Trie getLastNode(String s, Trie t) {
		if (s.length()==0) {
			return t;
		}
		if (!t.children.containsKey(s.charAt(0))) {
			return null;
		}
		return getLastNode(s.substring(1),t.children.get(s.charAt(0)));
	}
	
	
	public void printWords() {
		printWords("",this);
	}

	//prints all words that have been added to the tree
	private void printWords(String s,Trie t) {
		Set<Character> set=t.children.keySet();
		for (char c:set) {
			if (t.children.get(c).endOfWord) {
				System.out.println(s+c);
			}
			printWords(s+c,t.children.get(c));
		}
	}
}