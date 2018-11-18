package spellCheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SpellCheckDriver
 */
@WebServlet("/SpellCheckDriver")
public class SpellCheckDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PrintWriter write;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpellCheckDriver() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static PrintWriter getWriter() {
    	return write;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String word = request.getParameter("search");
		write=response.getWriter();
		write.println(SiteFormat.getFormat());
		SpellCheck.start(word);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
