import java.util.*;
import java.io.*;

public class millionairemadness{
    public static ArrayList < ArrayList < IntegerPair > > AdjList;
    public static ArrayList < Boolean > taken;
    public static PriorityQueue < IntegerPair > pq;

    public static void process(int vtx) {
        taken.set(vtx, true);
        for (int j = 0; j < AdjList.get(vtx).size(); j++) {
            IntegerPair v = AdjList.get(vtx).get(j);
            if (!taken.get(v.first())) {
                pq.offer(new IntegerPair(v.second(), v.first()));  // we sort by weight then by adjacent vertex
            }
        }
    }
    public static void main(String[] args){
        Kattio io=new Kattio(System.in);
        int m=io.getInt();
        int n=io.getInt();
        int[][] inputstore=new int[m][n];
        int[][] numbering=new int[m][n];
        int counter=0;
        AdjList = new ArrayList<ArrayList<IntegerPair>>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                inputstore[i][j]=io.getInt();
                numbering[i][j]=counter++;
                ArrayList < IntegerPair > Neighbor = new ArrayList < IntegerPair >();
                AdjList.add(Neighbor);
            }
        }
        int[] moveLR={1,-1,0,0};
        int[] moveUD={0,0,1,-1};
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<4;k++){
                    int nextcindex=j+moveLR[k];
                    int nextrindex=i+moveUD[k];
                    if (nextrindex==-1 || nextcindex==-1 || nextcindex>=n || nextrindex>=m) continue;
                    else{
                        int number=numbering[i][j];
                        int nextnumber=numbering[nextrindex][nextcindex];
                        int height=inputstore[nextrindex][nextcindex]-inputstore[i][j];
                        if(height<0) height=0;
                        AdjList.get(number).add(new IntegerPair(nextnumber,height));
                    }
                }
            }
        }
        taken = new ArrayList < Boolean >(); 
        taken.addAll(Collections.nCopies(m*n, false));
        pq = new PriorityQueue < IntegerPair > ();
        // take any vertex of the graph, for simplicity, vertex 0, to be included in the MST
        process(0);
        int topheight = 0;
    
        while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edges are taken)
            IntegerPair front = pq.poll();
            if(front.second()==(n*m-1)) break;
            if (!taken.get(front.second())) { // we have not connected this vertex yet
                if (front.first()>topheight)topheight=front.first();
                process(front.second());
            }
        }
        System.out.println(topheight);
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

class IntegerPair implements Comparable<IntegerPair> {
  public Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IntegerPair o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else
      return this.second() - o.second();
  }

  Integer first() { return _first; }

  Integer second() { return _second; }
}
