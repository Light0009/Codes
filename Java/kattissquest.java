import java.util.*;
import java.io.*;

class Quest{
	public int E;
	public int G;
	public int index;

	public Quest(int E,int G,int index){
		this.E=E;
		this.G=G;
		this.index=index;
	}
	public int getE(){
		return E;
	}
	public int getG(){
		return G;
	}
	public int getindex(){
		return index;
	}
}
class EComparator implements Comparator<Quest> {

	public int compare(Quest q1, Quest q2) {
		int first= q1.getE() - q2.getE();
		if (first!=0){
			return first;
		}
		first=q1.getG()-q2.getG();
		if (first!=0){
			return first;
		}
		return q1.getindex()-q2.getindex();
	}

	public boolean equals(Object obj) {
		return this == obj;
	}
}

public class kattissquest{
	public static void main(String[] args){
		Kattio io=new Kattio(System.in);
		int n=io.getInt();
		TreeSet<Quest> ts= new TreeSet<Quest>(new EComparator());
		for (int i=0;i<n;i++){
			String command=io.getWord();
			if (command.equals("add")){
				ts.add(new Quest(io.getInt(),io.getInt(),i));
			}
			if (command.equals("query")){
				int X=io.getInt();
				if (ts.size()==0){
				    System.out.println(0);
				}
				else{
				    long earn=0;
	                while(ts.floor(new Quest(X+1,0,0))!=null){
	                    Quest curquest=ts.floor(new Quest(X+1,0,0));
					    X=X-curquest.getE();
					   	earn=earn+curquest.getG();
					    ts.remove(curquest);
	                }
	                System.out.println(earn);
				}
			}
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
