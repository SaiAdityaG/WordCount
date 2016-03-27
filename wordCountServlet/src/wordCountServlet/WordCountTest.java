package wordCountServlet;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.*;
import com.meterware.servletunit.*;

public class WordCountTest {
	@Test
	public void testContentType() throws IOException, SAXException {
		ServletRunner sr = new ServletRunner();
		String baseURL = "http://localhost:8080/wordCountServlet/?query=";
	    sr.registerServlet("/","wordCountServlet.WordCount");
	    ServletUnitClient sc = sr.newClient();
	    
	    WebRequest request   = new GetMethodWebRequest(baseURL+"unique");
	    WebResponse response = sc.getResponse( request );
	    assertEquals( "content type", "application/json", response.getContentType() );
	    
	}
	@Test
	public void testCount() throws IOException, SAXException {
		ServletRunner sr = new ServletRunner();
		String baseURL = "http://localhost:8080/wordCountServlet/?query=";
	    sr.registerServlet("/","wordCountServlet.WordCount");
	    ServletUnitClient sc = sr.newClient();
	    
	    WebRequest request   = new GetMethodWebRequest(baseURL+"unique");
	    WebResponse response = sc.getResponse( request );
	    assertEquals( "requested resource", "{"+"\"count\""+":8}", response.getText() );
	    
	    request   = new GetMethodWebRequest(baseURL+"globe");
	    response = sc.getResponse( request );
	    assertEquals( "requested resource", "{"+"\"count\""+":8}", response.getText() );
	    
	    request   = new GetMethodWebRequest(baseURL+"india");
	    response = sc.getResponse( request );
	    assertEquals( "requested resource", "{"+"\"count\""+":8}", response.getText() );
	    
	    
	   
	}
	
	@Test
	public void testEmptyWord() throws IOException, SAXException {
		ServletRunner sr = new ServletRunner();
		String baseURL = "http://localhost:8080/wordCountServlet/?query=";
	    sr.registerServlet("/","wordCountServlet.WordCount");
	    ServletUnitClient sc = sr.newClient();
	    
	    WebRequest request   = new GetMethodWebRequest(baseURL);
	    WebResponse response = sc.getResponse( request );
	    assertEquals( "requested resource", "{"+"\"count\""+":0}", response.getText() );
	    
	   
	}

}
