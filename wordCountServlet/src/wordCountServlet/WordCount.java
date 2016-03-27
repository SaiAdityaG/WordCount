package wordCountServlet;


import java.io.*;
import java.util.*;
import java.nio.file.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class WordCount
 */
@WebServlet("/WordCount")
public class WordCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Map<String, Integer> m = new HashMap<String, Integer>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordCount() {
        super();
        // TODO Auto-generated constructor stub
    }
    private void ParseFolder(ServletConfig config){
    	Path path = Paths.get("WebContent","WEB-INF", "data");
		String relativeWebPath = path.toString();
		String absoluteDiskPath = config.getServletContext().getRealPath(relativeWebPath);
		File folder = new File(absoluteDiskPath);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  ParseFile(listOfFiles[i]);
		      }
		    }
    }
    private void ParseFile(File file){
    	try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       String[] s = line.split("[^A-Za-z']+");
		       for(String word:s){
		    	   word = word.toLowerCase();
		    	   int count = m.containsKey(word) ? m.get(word) : 0;
					m.put(word, count + 1);
		       }
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		ParseFolder(config);
	   
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s = request.getParameter("query");
		s = s.toLowerCase();
		Integer i =m.containsKey(s) ? m.get(s) : 0;
		
		
		String json = "{"+"\"count\""+":"+i.toString()+"}";
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
