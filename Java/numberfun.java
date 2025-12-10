import java.util.*;
import java.io.*;
import java.lang.Math;

public class numberfun{
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n=Integer.parseInt(br.readLine());
		for (int i=0;i<n;i++){
			String test=br.readLine();
			String[] store=test.split(" ");
			int a=Integer.parseInt(store[0]);
			int b=Integer.parseInt(store[1]);
			int c=Integer.parseInt(store[2]);
			if (a+b==c || Math.abs(a-b)==c || a*b==c || (double)a/b==c || (double)b/a==c){
				pw.println("Possible");
			}
			else{
				pw.println("Impossible");
			}
		}
		pw.close();
		br.close();
	}
}
