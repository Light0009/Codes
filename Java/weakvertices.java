import java.util.*;
import java.io.*;

public class weakvertices{
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=0;
		while((n=Integer.parseInt(br.readLine()))!=-1){
			int[][] Adjmatrix=new int[n][n];
			for (int i=0;i<n;i++){
				String[] in=br.readLine().split(" ");
				for (int j=0;j<n;j++){
					Adjmatrix[i][j]=Integer.parseInt(in[j]);
				}
			}
			String result="";
			for(int i=0;i<n;i++){
				boolean check=false;
				for(int j=0;j<n;j++){
					if(Adjmatrix[i][j]==1){
						for(int k=0;k<n;k++){
							if(k==j)continue;
							if(Adjmatrix[i][k]==1 && Adjmatrix[j][k]==1){
								check=true;
								break;
							}
						}
						if(check==true)break;
					}
				}
				if(check==false){
				    result=result+Integer.toString(i)+" ";
			    }
		    }
			System.out.println(result);
		}
	}
}
