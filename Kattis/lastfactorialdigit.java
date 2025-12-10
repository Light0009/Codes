import java.util.Scanner;

public class lastfactorialdigit{
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for (int i=0;i<n;i++){
			int a=sc.nextInt();
			if (a<=4 && a!=3){
				System.out.println(a);
			}
			if (a==3){
				System.out.println(6);
			}
			if (a>=5){
				System.out.println(0);
			}
		}
	}
}
