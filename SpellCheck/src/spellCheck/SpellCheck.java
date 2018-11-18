package spellCheck;
import java.io.*;
import java.util.*;

public class SpellCheck {
	static PrintWriter writer=SpellCheckDriver.getWriter();
	
	public static boolean isCorrect(String word, List<String> correct) {
		if (correct==null) {
			return false;
		}
		//Word = word with first letter capital
		String Word=Character.toLowerCase(word.charAt(0))+word.substring(1);
		if (correct.contains(word)||correct.contains(Word)) {
			writer.println("			Correct");
			return true;
		}
		//checks if the test word is in all caps and matches a word in the list of correct words
		for (String s:correct) {
			if ((word.equals(word.toUpperCase())&&word.equalsIgnoreCase(s))) {
				writer.println("			Correct");
				return true;
			}
		}
		return false;
	}
	
	public static boolean isSpecial(String word) {
		//checks if word is possible acronym
		//different from purpose of for loop above because this is only if the test word does not match any word on the correct list but is all caps
		if (word.equals(word.toUpperCase())) {
			writer.println("			Possible Acronym");
			return true;
		}
		for (int i=0;i<word.length();i++) {
			//checks if word contains non-alphabet characters
			if (word.charAt(i)<65||(word.charAt(i)>90&&word.charAt(i)<97)||word.charAt(i)>122) {
				writer.println("			Special Case");
				return true;
			}
		}
		return false;
	}
	
	public static boolean isOmitted(String word,List<String> correct) { 
		if (correct==null) {
			return false;
		}
		//keeps track of all possible correct words with one letter omitted
		List<String> corrections=new ArrayList<String>();
		int mismatchedLetters=0;
		boolean omitted=false;
		for (String s:correct) {
			mismatchedLetters=0;
			//checks if test word is missing first or last letter
			if (word.equals(s.substring(1))||word.equals(s.substring(0,s.length()-1))) {
				omitted=true;
				corrections.add(s);
				continue;
			}
			//checks if test word is missing letter in middle
			for (int i=0;i<word.length();i++) {
				if (word.charAt(i)!=s.charAt(i+mismatchedLetters)&&word.charAt(i)!=s.charAt(i+1)) {
					mismatchedLetters+=2;
				}
				else if (word.charAt(i)!=s.charAt(i+mismatchedLetters)) {
					mismatchedLetters+=1;
				}
				if (mismatchedLetters>1) {
					break;
				}
			}
			if (mismatchedLetters==1) {
				omitted=true;
				corrections.add(s);
			}
		}
		printList("Omissions",corrections);
		return omitted;
	}
	
	public static boolean isInserted(String word,List<String> correct) {
		if (correct==null) {
			return false;
		}
		List<String> corrections=new ArrayList<String>();
		int mismatchedLetters=0;
		boolean inserted=false;
		for (String s:correct) {
			mismatchedLetters=0;
			//tests if letter was added to beginning or end of string
			if (s.equals(word.substring(1))||s.equals(word.substring(0,word.length()-1))) {
				inserted=true;
				corrections.add(s);
				continue;
			}
			//tests if letter was added in middle of word
			for (int i=0;i<s.length();i++) {
				if (s.charAt(i)!=word.charAt(i+mismatchedLetters)&&s.charAt(i)!=word.charAt(i+1)) {
					mismatchedLetters+=2;
				}
				else if (s.charAt(i)!=word.charAt(i+mismatchedLetters)) {
					mismatchedLetters+=1;
				}
				if (mismatchedLetters>1) {
					break;
				}
			}
			if (mismatchedLetters==1) {
				corrections.add(s);
				inserted=true;
			}
		}
		printList("Insertion",corrections);
		return inserted;
	}
	
	public static boolean isTransposed(String word,List<String> correct) {
		if (correct==null) {
			return false;
		}
		List<String> corrections=new ArrayList<String>();
		int mismatchedLetters=0;
		boolean transposed=false;
		for (String s:correct) {
			mismatchedLetters=0;
			//compares test string against list of correct words to see if any two letters next to each other are transposed
			for (int i=0;i<word.length()-1;i++) {
				if (word.charAt(i)!=s.charAt(i)      &&
					(word.charAt(i+1)==s.charAt(i)   &&
					word.charAt(i)==s.charAt(i+1)))
				{
					mismatchedLetters+=2;
				}
				else if (word.charAt(i)!=s.charAt(i)) {
					break;
				}
			}
			if (mismatchedLetters==2) {
				transposed=true;
				corrections.add(s);
			}
		}
		printList("Transposed",corrections);
		return transposed;
	}
	
	public static boolean isSubstituted(String word,List<String> correct) {
		if (correct==null) {
			return false;
		}
		List<String> corrections=new ArrayList<String>();
		int mismatchedLetters=0;
		boolean substituted=false;
		for (String s:correct) {
			mismatchedLetters=0;
			//checks test word against list of correct word to see if a letter has been substituted
			for (int i=0;i<word.length();i++) {
				if (Character.toLowerCase(word.charAt(i))!=Character.toLowerCase(s.charAt(i))) {
					mismatchedLetters++;
				}
				if (mismatchedLetters>1) {
					break;
				}
			}
			if (mismatchedLetters==1) {
				substituted=true;
				corrections.add(s);
			}
		}
		printList("Substitutions",corrections);
		return substituted;
	}
	
	public static boolean isCapital(String word,List<String> correct) {
		if (correct==null) {
			return false;
		}
		List<String> corrections=new ArrayList<>();
		boolean capitalized=false;
		//checks if test word has a capital character that should be lower case
		for (String s:correct) {
			if (word.toLowerCase().equals(s.toLowerCase())) {
				capitalized=true;
				corrections.add(s);
			}
		}
		printList("Capitalization",corrections);
		return capitalized;
	}
	
	public static void printList(String correction, List<String> list){
		//prints all possible corrections of each type of correction
		if (list.size()==0) {
			return;
		}
		writer.print("			"+correction+": ");
		for (int i=0;i<list.size();i++) {
			writer.print(list.get(i));
			if (i<list.size()-1) {
				writer.print(", ");
			}
		}
		writer.println();
	}
	
	public static List<String> createLists(File file) throws FileNotFoundException {
		//Returns list of correct and test words (each called separately)
		Scanner in=new Scanner(file);
		List<String> words=new ArrayList<>();
		while (in.hasNextLine()) {
			words.add(in.nextLine());
		}
		return words;
	}
	
	public static void spellCheck(List<String> correctWords, String word) {
		//Stores correct words in hashmap for O(1) access
		HashMap<Integer,List<String>> map=new HashMap<>();
		boolean noSuggestion=false;
		for (int i=0;i<correctWords.size();i++) {
			if (map.get(correctWords.get(i).length())==null) {
				map.put(correctWords.get(i).length(),new ArrayList<>());
			}
			map.get(correctWords.get(i).length()).add(correctWords.get(i));
		}
		if (!isCorrect(word,map.get(word.length()))&&!isSpecial(word)) {
			//as long as not a single correction was found, alerts that no suggestions were found
			if (
				!isOmitted(word,map.get(word.length()+1))     &
				!isInserted(word,map.get(word.length()-1))    &
				!isTransposed(word,map.get(word.length()))    &
				!isSubstituted(word,map.get(word.length()))   &
				!isCapital(word,map.get(word.length())))
			{
				noSuggestion=true;
			}
		}
		if (noSuggestion) {
			writer.println("			No suggestions");
		}
		writer.println();
	}
	
	public static void start(String word) throws FileNotFoundException{
		File file=new File("C:\\Users\\sman0\\git\\AutoComplete-and-SpellCheck\\SpellCheck\\trieTest.txt");
		List<String> words=createLists(file);
		spellCheck(words,word);
	}

}


/*
 * Index page loads
 * User enters word into search bar
 * User hits enter
 * SpellCheckDriver page opens
 * SpellCheckDriver page requests for inputed word (get not post, don't need to hide data)
 * Driver page calls start function in spellcheck and sends word that was entered
 * word is checked across all methods and printed to page
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */












