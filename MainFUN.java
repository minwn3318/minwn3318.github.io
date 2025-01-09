import java.util.Scanner;

enum BloodType{
    A,
    B,
    O,
    AB
}
public class MainFUN {
    public void main(String[] args) {
        Integer dad = null;
        Integer mom = null;

        try(Scanner dadInput = new Scanner(System.in); Scanner momInput = new Scanner(System.in);)
        {
            while(dad == null || mom == null)
            {
                System.out.print("choose your Father's Blood Type (check number) \n 1. A  2. B  3. O  4. AB \n");
                dad = dadInput.nextInt();
                System.out.print(dad+ "\n");
    
                System.out.print("choose your Mother's Blood Type (check number) \n 1. A  2. B  3. O  4. AB \n");
                mom = momInput.nextInt();    
                System.out.print(mom+ "\n");
                if((dad < 1 || dad > 4)||(mom < 1 || mom > 4))
                {
                    dad = null;
                    mom = null;
                    System.out.print("Please check vaild number \n");
                }
            }
        }
        System.out.print("end");
    }
}