package spellCheck;

public class SiteFormat {
	public static String getFormat() {
		return "<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"	<head>\r\n" + 
				"		<title>\r\n" + 
				"		SAS Dictionary \r\n" + 
				"		</title>\r\n" + 
				"		<link rel=\"stylesheet\" type=\"text/css\" href=\"sas.css\">\r\n" + 
				"		<script src=\"main.js\"></script>" +
				"		<link rel=\"shortcut icon\" href=\"favicon.ico\">\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<h1>SAS Dictionary<span>Sponsored by Rutgers MSA</span></h1>\r\n" + 
				"		<div id=\"topnav\">\r\n" + 
				"			<form autocomplete=\"off\" action=\"SpellCheckDriver\" method=\"get\">\r\n" + 
				"			  <input type=\"text\" id=\"search\" placeholder=\"Search...\" name=\"search\">\r\n" + 
				"			  <button type=\"submit\">Submit</button>\r\n" + 
				"			  <select id=\"dropdown\"></select>\r\n" +
				"			</form>\r\n" + 
				"		</div>\r\n" + 
				"		<p>Welcome to the SAS Dictionary! We want to create a website that can spell check a certain range of words so that you will never have to be confused about how to spell a word again.</p>\r\n" + 
				"		<img id=\"img1\" src=\"SAS Pictures\\WCA-Knights.png\" alt=\"Knights\">\r\n" + 
				"	</body>\r\n" + 
				"</html>";
	}
}
