import java.util.*;

class Person{
	public String name;
	public Double start;
	public Double after;

	public Person(String name, Double start, Double after){
		this.name=name;
		this.start=start;
		this.after=after;
	}
	public String getname(){
		return name;
	}
	public Double getstart(){
		return start;
	}
	public Double getafter(){
		return after;
	}
}	

class AfterComparator implements Comparator<Person> {

	public int compare(Person p1, Person p2) {
		return p1.getafter().compareTo(p2.getafter());
	}

	public boolean equals(Object obj) {
		return this == obj;
	}
}

public class bestrelayteam{
	public static void main(String[] args){
		AfterComparator aftercomp=new AfterComparator();
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Person[] p=new Person[n];
		for (int i=0;i<n;i++){
			String c1=sc.next();
			Double c2=sc.nextDouble();
			Double c3=sc.nextDouble();
			p[i]=new Person(c1,c2,c3);
		}
		List<Person> list=Arrays.asList(p);
		Collections.sort(list, aftercomp);

		ArrayList<String> best=new ArrayList<String>();
		Double besttime=10000.0000;
		for (int j=0;j<n;j++){
			ArrayList<String> test=new ArrayList<String>();
			test.add(list.get(j).name);
			Double testtime=list.get(j).start;
			for (int k=0;k<4;k++){
				if (test.size()==4){
					break;
				}
				if (k==j){
					continue;
				}
				else{
					testtime+=list.get(k).after;
					test.add(list.get(k).name);
				}
			}
			if (testtime<besttime){
				besttime=testtime;
				best=test;
			}
		}
		System.out.println(besttime);
		for (int a=0;a<4;a++){
			System.out.println(best.get(a));
		}

	}
}
