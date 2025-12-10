import java.util.*;
import java.io.*;
import java.lang.Math;

public class pot{
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n=Integer.parseInt(br.readLine());
		double ans=0;
		for (int i=0;i<n;i++){
			String numstore=br.readLine();
			double num=Double.parseDouble(numstore.substring(0,numstore.length()-1));
			double power=Double.parseDouble(numstore.substring(numstore.length()-1));
			ans=ans+Math.pow(num,power);
		}
		pw.println((int)ans);
		pw.close();
		br.close();
	}
}
