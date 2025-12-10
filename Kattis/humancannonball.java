import java.util.*;
import java.io.*;
import java.math.*;

public class humancannonball{
	public static void main(String[] args){
		Kattio io=new Kattio(System.in);
		DoublePair start=new DoublePair(io.getDouble(),io.getDouble());
		DoublePair end=new DoublePair(io.getDouble(),io.getDouble());
		int n=io.getInt();
		ArrayList<DoublePair> vertices=new ArrayList<DoublePair>();
		for (int i=0;i<n;i++){
			vertices.add(new DoublePair(io.getDouble(),io.getDouble()));
		}
		vertices.add(start);
		vertices.add(end);
		ArrayList< ArrayList< IDPair > > AdjList = new ArrayList< ArrayList< IDPair > >();
		for (int i = 0; i<(n+2); i++) {
			ArrayList< IDPair > Neighbor =  new ArrayList < IDPair >();
			AdjList.add(Neighbor);
		}
		for(int v1=0;v1<(n+2);v1++){
			for (int v2=0;v2<(n+2);v2++){
				if(v1!=v2){
					double a=Math.abs(vertices.get(v1).first()-vertices.get(v2).first());
			    	double b=Math.abs(vertices.get(v1).second()-vertices.get(v2).second());
			    	double dist=Math.hypot(a,b);
			    	double time1=dist/5;
			    	double time2=1000000000;
			    	if(v1<n){
						time2=2+Math.abs(dist-50)/5;
					}
					AdjList.get(v1).add(new IDPair(v2,Math.min(time1,time2)));
			    }
			}
		}
		double[] D=new double[n+2];
		for (int i = 0; i<(n+2); i++) {
			D[i]=1000000000;
		}
		D[n]=0;
		PriorityQueue<IDPair> pq=new PriorityQueue<IDPair>();
		pq.add(new IDPair(n,0));
		while(!pq.isEmpty()){
			IDPair now=pq.poll();
			if (D[now.first()]==now.second()){
				for(int i=0;i<AdjList.get(i).size();i++){
					IDPair v = AdjList.get(now.first()).get(i);
					if(D[now.first()]+v.second()<D[v.first()]){
						D[v.first()]=D[now.first()]+v.second();
						pq.add(new IDPair(v.first(),D[v.first()]));
					}
				}
			}
		}
		System.out.println(D[n+1]);
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

class IDPair implements Comparable<IDPair> {
  Double _second;
  Integer _first;

  public IDPair(int f, double s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IDPair o) {
    if (this.second()>o.second()){
      return 1;
    }
    else if(this.second()<o.second()){
      return -1;
    }
    else return 0;
  }

  Integer first() { return _first; }
  Double second() { return _second; }
}

class DoublePair {
  Double _first, _second;
  
  public DoublePair(Double f, Double s) {
    _first = f;
    _second = s;
  }

  Double first() { return _first; }
  Double second() { return _second; }
}
