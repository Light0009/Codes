import java.util.*;
import java.io.*;

public class dominos{
    public static int[] visited;
    public static ArrayList<ArrayList<Integer>> adjList;
    public static boolean[] knockdown;
	public static int[] p;
	public static Stack<Integer> stack;
	public static int counter;
    
	public static void DFS(int u){
		visited[u]=1;
		for(int k=0;k<adjList.get(u).size();k++){
			int v=adjList.get(u).get(k);
			if(visited[v]==0) {
				DFS(v);
			}
		}
		stack.push(u);
	}
	public static void knock(int u){
		knockdown[u]=true;
		for(int j=0;j<adjList.get(u).size();j++){
			int v=adjList.get(u).get(j);
			if(knockdown[v]==false) knock(v);
		}
	}
	public static void main(String[] args){
		Kattio io=new Kattio(System.in);
		int numofcases=io.getInt();
		for(int i=0;i<numofcases;i++){
			int numoftiles=io.getInt();
			int numoflines=io.getInt();
			adjList = new ArrayList<ArrayList<Integer>>();
			for(int j=0;j<numoftiles;j++){
				ArrayList < Integer > Neighbor = new ArrayList < Integer >();
				adjList.add(Neighbor);
			}
			for(int j=0;j<numoflines;j++){
				int u=io.getInt()-1;
				int v=io.getInt()-1;
				adjList.get(u).add(v);
			}
			visited=new int[numoftiles];
			knockdown=new boolean[numoftiles];
			p=new int[numoftiles];
			for(int j=0;j<numoftiles;j++){
				visited[j]=0;
				knockdown[j]=false;
			}
			stack = new Stack<Integer>();
			for(int j=0;j<numoftiles;j++){
				if (visited[j]==0) DFS(j);
			}
			int counter=0;
			while(stack.empty()==false){
				int v=stack.pop();
				if(knockdown[v]==false){
					knock(v);
					counter++;
				}
				else continue;
			}
			System.out.println(counter);
		}
	}
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}