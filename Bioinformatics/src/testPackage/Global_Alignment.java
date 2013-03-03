package testPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test2")
public class Global_Alignment extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html");
PrintWriter out = response.getWriter();
out.println
("<!DOCTYPE html>\n" +
"<html>\n" +
"<head t t e est Se et /t t e / ead ><title>A Test Servlet</title></head>\n" +
"<body bgcolor=\"#fdf5e6\">\n" +
"<h1>Test2</h1>\n" +
"<p p g /p >Simple servlet for testing.</p>\n" +
"</body></html>");
}


 }
