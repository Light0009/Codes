import java.util.*;
import java.io.*;

public class apaxiaaans{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String inname=br.readLine();
		String[] charstore=inname.split("");
		String outname="";
		for (int i=0;i<inname.length();i++){
			if (outname.length()!=0 && charstore[i].equals(outname.substring(outname.length()-1))){
				continue;
			}
			else{
				outname+=charstore[i];
			}
		}
		pw.println(outname);
		pw.close();
		br.close();
	}
}
