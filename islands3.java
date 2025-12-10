import java.util.*;
import java.io.*;

public class islands3{
	public static void main(String[] args){
		Kattio io=new Kattio(System.in);
		int R=io.getInt();
		int C=io.getInt();
		char[][] adjMatrix=new char[R][C];
		boolean[][] visit=new boolean[R][C];
		Queue<landinfo> landq=new ArrayDeque<landinfo>();
		for (int i=0;i<R;i++){
		    String newin=io.getWord();
			for(int j=0;j<C;j++){
				char newchar=newin.charAt(j);
				adjMatrix[i][j]=newchar;
				if (newchar=='L')
					landq.offer(new landinfo(i,j));
			}
		}
		int[] moverow={1,-1,0,0};
		int[] movecolumn={0,0,1,-1};
		int counter=0;
		while(!landq.isEmpty()){
			landinfo u=landq.poll();
			if(visit[u.row][u.col]==true) continue;
			else{
				visit[u.row][u.col]=true;
				Queue<landinfo> neighbourq=new ArrayDeque<landinfo>();
				neighbourq.offer(u);
				while(!neighbourq.isEmpty()){
					landinfo k=neighbourq.poll();
					for(int i=0;i<4;i++){
						landinfo v=new landinfo(k.row+moverow[i],k.col+movecolumn[i]);
						if (v.row==-1 || v.col==-1 || v.row>=R || v.col>=C || adjMatrix[v.row][v.col]=='W') continue;
						else{
							if (visit[v.row][v.col]==false){
								visit[v.row][v.col]=true;
								neighbourq.offer(new landinfo(v.row,v.col));
							}
						}
					}
				}
				counter++;
			}
		}
		System.out.println(counter);
	}
}

class landinfo {
	public int row,col;

	public landinfo(int row, int col) {
		this.row = row;
		this.col = col;
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
