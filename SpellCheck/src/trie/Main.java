package trie;
import java.util.Scanner;
import java.io.*;


public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		Trie trie=new Trie();
		File file=new File("Test.txt");
		Scanner in=new Scanner(file);
		Scanner input=new Scanner(System.in);
		Scanner strinput=new Scanner(System.in);
		String str;
		PrintWriter out=new PrintWriter(new FileOutputStream(file,true));
		int act;
		String word;
		while(in.hasNextLine()) {
			str=in.nextLine();
			trie.add(str);
		}
		do {
			System.out.println("1. Add word\n2. Search word\n3. View Words");
			act=input.nextInt();
			switch (act){
				case 1:
					System.out.println("Enter word: ");
					word=strinput.nextLine();
					if (!trie.search(word)) {
						out.println(word);
						trie.add(word);
					}
					break;
				case 2:
					System.out.println("Enter word: ");
					word=strinput.nextLine();
					if (!trie.search(word)) {
						System.out.println("Could not find word, would you like to add it? Enter 1 for yes, or any other number for no: ");
						if (input.nextInt()==1) {
							out.println(word);
							trie.add(word);
						}
					}
					else {
						System.out.println("Word was found");
					}
					break;
				case 3:
					trie.iterate();
					break;
			}
			System.out.println("Do you want to check or add a new word? Enter 1 for yes, or any other number for no: ");
			
		}while(input.nextInt()==1);
		in.close();
		input.close();
		strinput.close();
		out.close();
	}
}