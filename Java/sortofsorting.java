import java.util.*;
import java.io.*;

class person{
	public String name;

	public person(String name){
		this.name=name;
	}
	public char getsec(){
		return name.charAt(1);
	}
	public char getfirst(){
		return name.charAt(0);
	}
}

class SecondComparator implements Comparator<person> {

	public int compare(person p1, person p2) {
		return p1.getsec()-p2.getsec();
	}
	public boolean equals(Object obj) {
		return this == obj;
	}
}

class FirstComparator implements Comparator<person> {

	public int compare(person p1, person p2) {
		return p1.getfirst()-p2.getfirst();
	}
	public boolean equals(Object obj) {
		return this == obj;
	}
}

public class sortofsorting{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		SecondComparator bysec=new SecondComparator();
		FirstComparator byfirst=new FirstComparator();
		int numofnames=Integer.parseInt(br.readLine());
		for (int i=0;i<500;i++){
			person[] names=new person[numofnames];
			for (int j=0;j<numofnames;j++){
				String nameofperson=br.readLine();
				names[j]=new person(nameofperson);
			}
			List<person> namelist=Arrays.asList(names);
			Collections.sort(namelist,bysec);
			Collections.sort(namelist,byfirst);
			for (int j=0;j<numofnames;j++){
				pw.println(namelist.get(j).name);
			}
			numofnames=Integer.parseInt(br.readLine());
			if (numofnames==0){
				break;
			}
			else{
				pw.println();
			}
		}
		pw.close();
		br.close();
	}
}
