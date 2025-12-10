import java.util.*;
import java.io.*;

public class lostmap {
  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String instore=br.readLine();
    int nofvillages=Integer.parseInt(instore);
    int[][] table=new int[nofvillages+1][nofvillages+1];
    for(int i=1;i<=nofvillages;i++){
      String[] store=br.readLine().split(" ");
      for(int j=1;j<=nofvillages;j++){
        table[i][j]=Integer.parseInt(store[j-1]);
      }
    }
    ArrayList < IntegerTriple > EdgeList = new ArrayList < IntegerTriple >();
    for (int i = 1; i < nofvillages; i++){
      for(int j=(i+1);j<=nofvillages;j++){
        int w = table[i][j];
        EdgeList.add(new IntegerTriple(w, i, j));
      }
    }
    Collections.sort(EdgeList);
    UnionFind UF = new UnionFind(nofvillages+1);
    for (int i = 0; i < EdgeList.size(); i++) {
      IntegerTriple e = EdgeList.get(i);
      int u = e.second(), v = e.third(), w = e.first();
      if (!UF.isSameSet(u, v)) { 
        UF.unionSet(u, v);
        System.out.println(u+" "+v);
      }
    }
  }
}



class IntegerTriple implements Comparable<IntegerTriple> {
  public Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else if (!this.second().equals(o.second()))
      return this.second() - o.second();
    else
      return this.third() - o.third();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }

  public String toString() { return first() + " " + second() + " " + third(); }
}



// Union-Find Disjoint Sets Library, using both path compression and union by rank heuristics
class UnionFind {
  public int[] p;
  public int[] rank;
  public int[] setSize;
  public int numSets;

  public UnionFind(int N) {
    p = new int[N];
    rank = new int[N];
    setSize = new int[N];
    numSets = N;
    for (int i = 0; i < N; i++) {
      p[i] = i;
      rank[i] = 0;
      setSize[i] = 1;
    }
  }

  public int findSet(int i) { 
    if (p[i] == i) return i;
    else {
      p[i] = findSet(p[i]);
      return p[i]; 
    } 
  }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { 
      numSets--; 
      int x = findSet(i), y = findSet(j);
      // rank is used to keep the tree short
      if (rank[x] > rank[y]) { 
        p[y] = x; 
        setSize[x] = setSize[x] + setSize[y]; 
      }
      else { 
        p[x] = y; 
        setSize[y] = setSize[x] + setSize[y];
        if (rank[x] == rank[y]) 
          rank[y] = rank[y]+1; 
      } 
    } 
  }

  public int numDisjointSets() { return numSets; }

  public int sizeOfSet(int i) { return setSize[findSet(i)]; }
}
