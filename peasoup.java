import java.util.Scanner;

public class peasoup{
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int numofmenu=sc.nextInt();
        boolean foundanswer=false;
        for (int i=0;i<numofmenu;i++) {
            int numofdishes=sc.nextInt();
            sc.nextLine();
            String resname=sc.nextLine();
            boolean pancakes=false;
            boolean peasoup=false;
            for (int j=0;j<numofdishes;j++) {
                String food=sc.nextLine();
                if (food.equals("pancakes")){
                    pancakes=true;
                }
                if (food.equals("pea soup")){
                    peasoup=true;
                }
            }
            if (pancakes==true && peasoup==true) {
                foundanswer=true;
                System.out.println(resname);
                break;
            }
        }
        if (foundanswer==false) {
            System.out.println("Anywhere is fine I guess");
        }
    }
}