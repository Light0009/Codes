import java.util.*;
import java.io.*;
import java.lang.StringBuilder;

public class detaileddifferences{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n=Integer.parseInt(br.readLine());
		for (int i=0;i<n;i++){
			StringBuilder top=new StringBuilder();
			top.append(br.readLine());
			StringBuilder bottom=new StringBuilder();
			bottom.append(br.readLine());
			int numofletters=top.length();
			StringBuilder out=new StringBuilder();
			for (int j=0;j<numofletters;j++){
				if (top.charAt(j)==bottom.charAt(j)){
					out.append(".");
				}
				else{
					out.append("*");
				}
			}
			String t=top.toString();
			pw.println(t);
			String b=bottom.toString();
			pw.println(b);
			String ans=out.toString();
			pw.println(ans);
			pw.println();
		}
		pw.close();
		br.close();
	}
}