package trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import com.google.gson.*;
/**
 * Servlet implementation class TrieDriver
 */
@WebServlet("/TrieDriver")
public class TrieDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Trie trie=null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrieDriver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (trie==null) {
			trie=new Trie();
			File file=new File("C:\\Users\\sman0\\git\\AutoComplete-and-SpellCheck\\SpellCheck\\trieTest.txt");
			Scanner in=new Scanner(file);
			String str;
			while(in.hasNextLine()) {
				str=in.nextLine();
				trie.add(str);
			}
			in.close();
		}
		
		String entry=request.getQueryString();
		List<String> words=trie.getWords(entry);
		String json="[\n";
		for (int i=0;i<words.size();i++) {
			json+="{\"word\":\""+words.get(i)+"\"}";
			if (i<words.size()-1) {
				json+=",";
			}
			json+="\n";
		}
		json+="]";
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		out.write(json);
//		System.out.println(json);
	//	System.out.println();
/*		System.out.println(entry);
		if (words!=null) {
			for (int i=0;i<words.size();i++) {
				System.out.println(words.get(i));
			}
			System.out.println();
		}*/
	}
	
/*	public static void main(String[] args) throws FileNotFoundException {
		trie=new Trie();
		File file=new File("C:\\Users\\sman0\\git\\AutoComplete-and-SpellCheck\\SpellCheck\\trieTest.txt");
		Scanner in=new Scanner(file);
		String str;
		while(in.hasNextLine()) {
			str=in.nextLine();
			trie.add(str);
		}
		in.close();

		
		List<String> list=trie.getWords("c");
		for (int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		System.out.println();
	}*/
}
