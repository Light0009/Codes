import java.util.*;
import java.lang.*;
import java.io.*;

public class t9spelling{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int n=Integer.parseInt(br.readLine());
		String[] valuearr=new String[256];
		String[] vstore={"2","22","222","3","33","333","4","44","444","5","55","555","6","66","666","7","77","777","7777","8","88","888","9","99","999","9999"};
		List<String> v=Arrays.asList(vstore);
		int counter=0;
		for (int ascii=97;ascii<123;ascii++){
			valuearr[ascii]=v.get(counter);
			counter++;
		}
		valuearr[' ']="0";
		for (int i=0;i<n;i++){
			StringBuilder text=new StringBuilder();
			text.append(br.readLine());
			StringBuilder out=new StringBuilder();
			out.append("Case #").append(i+1).append(": ");
			for (int j=0;j<text.length();j++){
				char letter=text.charAt(j);
				String outn=valuearr[letter];
				if (out.length()!=0 && out.charAt(out.length()-1)==outn.charAt(0)){
					out.append(" ");
					out.append(valuearr[letter]);
				}
				else{
					out.append(valuearr[letter]);
				}
			}
			String ans=out.toString();
			pw.println(ans);
		}
		pw.close();
		br.close();
	}
}
