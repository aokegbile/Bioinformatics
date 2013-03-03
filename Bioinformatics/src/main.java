import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.jni.Time;

import needlemanWunsch.NeedlemanWunsch;
import util.Cell;


public class main {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		String sequence1 = args[0];
		String sequence2 = args[1];
		NeedlemanWunsch a;
		if (args.length>2)
		{int mismatch = Integer.parseInt(args [2]);
		int match = Integer.parseInt(args[3]);
		int gap = Integer.parseInt(args[4]);
		a = new NeedlemanWunsch(sequence1, sequence2,mismatch,match,gap);
		a.getAlignment();
		}
		else{
		a = new NeedlemanWunsch(sequence1,sequence2);
		a.getAlignment();
		}
		String output ="";
 PrintStream out = System.out;
out.println
("<!DOCTYPE html>\n" +
"<html>\n" +
"<head><title>Global Alignment</title></head>\n" +
"<body bgcolor=\"#fdf5e6\">\n" +
"<h1>");
output+=("<!DOCTYPE html>\n" +
		"<html>\n" +
		"<head><title>Global Alignment</title></head>\n" +
		"<body bgcolor=\"#fdf5e6\">\n" +
		"<h1>");
out.println("Global Alignment<br><br></h1>");
output+=("Global Alignment<br><br></h1>");


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
out.println("<table align =\"center\" border = 1>");
out.println(printHTMLRow(c.get(0))+"</h4>");
out.println(printHTMLRow(c.get(1)));
out.println(printHTMLRow(c.get(2))+"</h4>");
out.println("</table>");




out.println("<input type=\"button\" value=\"Reset !\" onClick=\"window.location='https://students.cis.uab.edu/akin/'\">");
out.println("<br>");
out.println("<br>");

out.println(printHTMLTable(a.getScoreTable()));

out.println("<br>");out.println("<br>");
out.println("Alignment score =  ");

out.println(a.getAlignmentScore());
out.println("<br>");out.println("<br>");
out.println("<br>");


		
		

	}
	public static String printHTMLTable(Cell [][] a){
		
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
	public static String printHTMLRow(String a){
		
		String end ="<table><tr>";
		for(int i = 0; i<a.length();i++){
			end+= ("<td align = center valign = inherit width = 24 px>"+ a.charAt(i)+"</td>");
		}
		end+= "</tr></table>";
		
		return end;
		
	}

}
