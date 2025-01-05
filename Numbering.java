import java.util.Scanner;

public class Numbering {
    public static void main(String[] args) {
        String word;
        Scanner input;
        do
        {
            System.out.print("I'm still working. plz put word 'stop'.\n");
            input = new Scanner(System.in);
            word = input.nextLine();
        } while(!"stop".equals(word));
        System.out.print("now I can go home. Thank You :-)");
        input.close();
    }
}
