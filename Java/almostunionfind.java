import java.util.*;
import java.io.*;

class UnionFind {                                              
  public int[] p;
  public int[] size;
  public long[] sum;
  public int[] root;

  public UnionFind(int N) {
    p = new int[N];
    size = new int[N];
    sum=new long[N];
    root=new int[N];
    for (int i = 1; i < N; i++) {
      p[i] = i;
      sum[i]=i;
      size[i]=1;
      root[i]=i;
    }
  }

  public int findSet(int i) { 
    if (p[i] == i) return i;
    else{
    	p[i]=findSet(p[i]);
    	return p[i];
    }
  }

  public Boolean isSameSet(int i, int j) { return findSet(root[i]) == findSet(root[j]); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { 
    	int a=root[i],b=root[j];
      int x = findSet(a), y = findSet(b);
      if (size[x] > size[y]) {
      	size[x]=size[x]+size[y];
      	sum[x]=sum[x]+sum[y];
      	size[y]=0;
      	sum[y]=0;
      	if (root[y]!=p[y]){
      		p[y]=x;
      	}
      	else{
      		root[y]=x;
      		p[y]=x;
      	}
      }
      else { 
      	size[y]=size[y]+size[x];
      	sum[y]=sum[y]+sum[x]; 
      	size[x]=0;
      	sum[x]=0;
      	if (root[x]!=p[x]){
      		p[x]=y;
      	}
      	else{
      		root[x]=y;
      		p[x]=y;
      	}
      } 
    } 
  }
  public void move(int i,int j){
  	if(!isSameSet(i,j)){
  		int a=root[i],b=root[j];
  		int x=findSet(a), y=findSet(b);
  		root[i]=y;
  		size[x]=size[x]-1;
  		sum[x]=sum[x]-i;
 			size[y]=size[y]+1;
 			sum[y]=sum[y]+i;
  	}
  }
}

public class almostunionfind{
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String start="";
		try{
			while ((start=br.readLine())!=null){
		  	String[] strarr=start.split(" ");
		  	int n=Integer.parseInt(strarr[0]);
				int m=Integer.parseInt(strarr[1]);
				UnionFind Set = new UnionFind(n+1);
		  	for (int j=0;j<m;j++){
		    	String s=br.readLine();
	      	String[] store=s.split(" ");
		    	int command=Integer.parseInt(store[0]);
		    	if (command==1){
			    	int n1=Integer.parseInt(store[1]);
			    	int n2=Integer.parseInt(store[2]);
		      	Set.unionSet(n1,n2);
		    	}
			  	if (command==2){
			    	int n1=Integer.parseInt(store[1]);
			    	int n2=Integer.parseInt(store[2]);
		      	Set.move(n1,n2);
	    		}
	      	if(command==3){
				  	int n1=Integer.parseInt(store[1]);
				  	int c=Set.root[n1];
				  	int num=Set.findSet(c);
				  	System.out.println(Set.size[num]+" "+Set.sum[num]);
	    		}
	    	}
			}
		}
		catch(Exception endofFileException){
			br.close();
		}	
		finally{
			br.close();
		}
	}
}
