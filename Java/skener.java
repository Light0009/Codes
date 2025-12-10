import java.util.*;
import java.io.*;

public class skener{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String in=br.readLine();
		String[] instore=in.split(" ");
		int r=Integer.parseInt(instore[0]);
		int c=Integer.parseInt(instore[1]);
		int zr=Integer.parseInt(instore[2]);
		int zc=Integer.parseInt(instore[3]);
		for (int i=0;i<r;i++){
			String rowi=br.readLine();
			String[] rowstore=rowi.split("");
			String enlargerowi="";
			for (int j=0;j<rowi.length();j++){
				for (int k=0;k<zc;k++){
					enlargerowi+=rowstore[j];
				}
			}
			for (int m=0;m<zr;m++){
				pw.println(enlargerowi);
			}
		}
		pw.close();
		br.close();
	}
}
