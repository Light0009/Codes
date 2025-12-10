import java.util.*;
import java.io.*;

class researcher{
	public int arrival;
	public int usage;

	public researcher(int arrival,int usage){
		this.arrival=arrival;
		this.usage=usage;
	}
	public int getArrival(){
		return arrival;
	}
	public int getUsage(){
		return usage;
	}
}

class ArrivalComparator implements Comparator<researcher> {

	public int compare(researcher r1, researcher r2) {
		return r1.getArrival()-r2.getArrival();
	}

	public boolean equals(Object obj) {
		return this == obj;
	}
}

public class workstations{
	public static void main(String[] args){
		Kattio io=new Kattio(System.in);
		int n=io.getInt();
		int locktime=io.getInt();
		PriorityQueue<researcher> researcherq= new PriorityQueue<researcher>(n,new ArrivalComparator()) ;
		PriorityQueue<Integer> openedstation = new PriorityQueue<Integer>();
		for (int i=0;i<n;i++){
			researcherq.add(new researcher(io.getInt(),io.getInt()));
		}
		int savecounter=0;
		for (int j=0;j<n;j++){
			int currarrival=researcherq.peek().getArrival();
			int currusage=researcherq.poll().getUsage();
			while(openedstation.size()!=0 && currarrival>openedstation.peek()+locktime){
				openedstation.poll();
			}
			if (openedstation.size()!=0 && currarrival>=openedstation.peek() && currarrival<=openedstation.peek()+locktime){
				openedstation.poll();
				savecounter++;
				openedstation.add(currarrival+currusage);
			}
			else{
				openedstation.add(currarrival+currusage);
			}
		}
		System.out.print(savecounter);
		io.close();
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
