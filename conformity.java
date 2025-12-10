import java.util.*;
import java.io.*;

public class conformity{
	public static void main(String[] args){
		Kattio io=new Kattio(System.in);
		int n=io.getInt();
		int currmax=1;
		HashMap<String,Integer> h=new HashMap<String,Integer>();
		for (int i=0;i<n;i++){
			int[] courses=new int[5];
			courses[0]=io.getInt();
			courses[1]=io.getInt();
			courses[2]=io.getInt();
			courses[3]=io.getInt();
			courses[4]=io.getInt();
			Arrays.sort(courses);
			String key="";
			for (int k=0;k<5;k++){
			    key+=Integer.toString(courses[k]);
			}
			if (h.containsKey(key)){
				h.replace(key,h.get(key)+1);
				if(h.get(key)>currmax){
					currmax=h.get(key);
				}
			}
			else{
				h.put(key,1);
			}
		}
		int counter=0;
		List<Integer> vlist = new ArrayList<Integer>(h.values());
		for (int j=0;j<vlist.size();j++){
			if (vlist.get(j)==currmax){
				counter++;
			}
		}
		System.out.println(counter*currmax);
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