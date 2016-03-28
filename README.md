# WordCount

##Description

Web Application whose response would be number of times queried word exists in corpus of text files. HTTP url format would be "/?query=WORD" where "WORD" is the word that is to be queried. Respone is a valid json object of format {"count": N} where N is number of times word exists in given files.

##Dependencies

Apache Tomcat 8.0

For Testing, external package 'HTTPUnit-1.7' is required

##Deploy

Copy the .war in build directory to webapps directory in tomcat and access the servlet using
'http://localhost:8080/wordCountServlet/?query=WORD'

Text files are in WEB-INF's data directory




