
import needlemanWunsch.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Cell;

@WebServlet("/GlobalAlign")
public class GlobalAlignServlet extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sequence1 = request.getParameter("Sequence1");
		String sequence2 = request.getParameter("Sequence2");
		NeedlemanWunsch a = new NeedlemanWunsch(sequence1, sequence2);
		a.getAlignment();
		

	
	response.setContentType("text/html");
PrintWriter out = response.getWriter();
out.println
("<!DOCTYPE html>\n" +
"<html>\n" +
"<head><title>Global Alignment</title></head>\n" +
"<body bgcolor=\"#fdf5e6\">\n" +
"<h1>");
out.println("Global Alignment<br><br></h1>");
out.println("<h4>Sequence 1:</h4>");

String akin1 = a.getAlignment()[0];
String akin2 = a.getAlignment()[1];
String akin3 = "";
for (int i =0; i<akin1.length(); i++){
	if (akin1.charAt(i) ==akin2.charAt(i)){
		akin3 +="|";
	}
	else if(akin2.charAt(i) =='-'){
		akin3+="?";
	}
	else akin3+="#";
}
List<String> c = new ArrayList<String>();
c.removeAll(c);
c.add(akin1);
c.add(akin3);
c.add(akin2);
Iterator<String> iter = c.iterator();
while(iter.hasNext()){
	out.println("<h3>"+printHTMLRow(iter.next())+"</h3>");
	
}

out.println("<h4>Sequence 2:</h4>");
out.println("<input type=\"button\" value=\"Reset !\" onClick=\"window.location='http://localhost:8080/Bioinformatics'\">");
out.println("<br>");

out.println(printHTMLTable(a.getScoreTable()));
out.println("Alignment score =  ");

out.println(a.getAlignmentScore());
out.println("<br>");





out.println("</body></html>");
}

public String printHTMLTable(Cell [][] a){
	
	String end = "<table>";
	
	for (int i =0;i<a.length;i++)
		{
			end+="<tr align = center>";
			for(int j = 0; j<a[i].length;j++)
				{
					end+="<td>   ";
					end += a[i][j].getScore();
				}
				end+="</td>";
		}
		end+="</tr>";
	return end;
	
}
public String printHTMLRow(String a){
	
	String end ="<table><tr>";
	for(int i = 0; i<a.length();i++){
		end+= ("<td align = center valign = inherit width = 24 px>"+ a.charAt(i)+"</td>");
	}
	end+= "</tr></table>";
	
	return end;
	
}
}
