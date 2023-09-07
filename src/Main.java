import java.util.Scanner;
public class Main {

    public static int Reverse(int input)
    {
        int reversedNum = 0;
        while (input != 0) {
            int last_digit = input % 10;
            if (last_digit % 2 != 0) {
                reversedNum = reversedNum * 10 + last_digit;

            }
            input = input / 10;
        }
        return reversedNum;
    }
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Input number");

        Scanner sn = new Scanner(System.in);
        int input = sn.nextInt();
        int number = input;
        int reversed = Reverse(number);

        if (number == reversed) {
            System.out.println("Palyndrome");
        }
        else {
            System.out.println("Not palyndrome");
        }

        int num2 = number*number;
        int num2r = Reverse(num2);

        System.out.println("Sq = " + num2);

        if (num2 == num2r) {
            System.out.println("Sq palyndrome");
        }
        else {
            System.out.println("Sq not palyndrome");
        }
    }
}