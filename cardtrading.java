import java.util.*;
import java.io.*;

class card{
	public int freq;
	public int buy;
	public int sell;

	public card(int freq,int buy,int sell){
		this.freq=freq;
		this.buy=buy;
		this.sell=sell;
	}
	public int getfreq(){
		return freq;
	}
	public int getbuy(){
		return buy;
	}
	public int getsell(){
		return sell;
	}
}

class CostComparator implements Comparator<card> {
	public int compare(card c1, card c2){
		int valuec1=c1.getfreq()*c1.getsell()+(2-c1.getfreq())*c1.getbuy();
		int valuec2=c2.getfreq()*c2.getsell()+(2-c2.getfreq())*c2.getbuy();
		if (valuec1==valuec2){
			return c1.getbuy()-c2.getbuy();
		}
		else{
			return valuec1-valuec2;
		}
	}
}

public class cardtrading{
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String storage=br.readLine();
		String[] store=storage.split(" ");
		int numofcards=Integer.parseInt(store[0]);
		int numoftypes=Integer.parseInt(store[1]);
		int numofcombos=Integer.parseInt(store[2]);
		String cardstr=br.readLine();
		String[] tempstore=cardstr.split(" ");
		int[] cardstorage=new int[100001];
		for (int i=0;i<numofcards;i++){
			int cnumber=Integer.parseInt(tempstore[i]);
			cardstorage[cnumber]++;
		}

		ArrayList<card> cardlist=new ArrayList<card>();
		for (int j=1;j<(numoftypes+1);j++){
			String cardj=br.readLine();
			String[] cardstats=cardj.split(" ");
			int jbuy=Integer.parseInt(cardstats[0]);
			int jsell=Integer.parseInt(cardstats[1]);
			cardlist.add(new card(cardstorage[j],jbuy,jsell));
		}

		CostComparator cardval=new CostComparator();
		Collections.sort(cardlist,cardval);

		long profit=0;
		for (int k=0;k<numofcombos;k++){
		    int spend=(2-cardlist.get(k).getfreq())*cardlist.get(k).getbuy();
		    long s=spend;
			profit=profit-s;
		}
		for (int m=numofcombos;m<numoftypes;m++){
		    int earn=cardlist.get(m).getfreq()*cardlist.get(m).getsell();
		    long e=earn;
			profit=profit+e;
		}
		pw.println(profit);
		pw.close();
		br.close();
	}
}