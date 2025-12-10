import java.util.Scanner;

public class Autori{
	public static void main(String args[]){
		Scanner sc= new Scanner(System.in);
		char[] name= sc.nextLine().toCharArray();
		String shortvar="";
		for (int i=0;i<name.length;i++){
			if (Character.isUpperCase(name[i])){
				shortvar=shortvar+name[i];
			}
		}
		System.out.println(shortvar);
	}

}
