import java.util.Objects;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to the java data structure project.\nPlease enter the number of days :  ");
        int lines = sc.nextInt();
        System.out.print("Please enter the first day date (DD MM YYYY) :  ");
        String date = sc.nextLine();

        FileGenerator.newFile(lines,date);
        while (true) {
            System.out.println("Chose your storing method : ");
            System.out.println("1. LinkedList inside an Array.");
            System.out.println("2. 4bit encrypting.");
            System.out.println("3. 5bit encrypting.");
            System.out.println("4. 6bit encrypting.");
            System.out.println("5. Exist the program.");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    linkedListArrayALgoritihm(lines);
                    break;
                case 2:
                    arrayAlgoritihm(lines);
                    break;
                case 3:
                    fiveBitsAlgorithm(lines);
                    break;
                case 4:
                    sixBitsAlgorithm(lines);
                    break;
                case 5:
                    System.out.println("Have a nice day.");
                    return;
                default:
                    System.out.println("Unknown value, Please try again.");
            }
        }

    }
    static void linkedListArrayALgoritihm(int lines){
        LinkedListArrayALgoritihm linkedList = new LinkedListArrayALgoritihm();
        while (true) {
            switch (operationChoice()) {
                case 1:
                    linkedList.displayDays();
                    break;
                case 2:
                    linkedList.displayMin();
                    break;
                case 3:
                    linkedList.displayZeros();
                    break;
                case 4:
                    linkedList.displayOnes();
                    break;
                case 5:
                    linkedList.delete();
                    break;
                case 6:
                    linkedList.search();
                    break;
                case 7:
                    linkedList.edit();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Unknown value, Please try again.");
            }
        }
    }
    static void arrayAlgoritihm(int lines){
        ArrayAlgoritihm array= new ArrayAlgoritihm(lines);
        while (true) {
            switch (operationChoice()) {
                case 1:
                    array.displayDays();
                    break;
                case 2:
                    array.displayMin();
                    break;
                case 3:
                    array.displayZeros();
                    break;
                case 4:
                    array.displayOnes();
                    break;
                case 5:
                    array.delete();
                    break;
                case 6:
                    array.search();
                    break;
                case 7:
                    array.edit();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Unknown value, Please try again.");
            }
        }
    }
    static void fiveBitsAlgorithm(int lines){
        FiveBitsAlgorithm fiveBits= new FiveBitsAlgorithm(lines);
        while (true) {
            switch (operationChoice()) {
                case 1:
                    fiveBits.displayDays();
                    break;
                case 2:
                    fiveBits.displayMin();
                    break;
                case 3:
                    fiveBits.displayZeros();
                    break;
                case 4:
                    fiveBits.displayOnes();
                    break;
                case 5:
                    fiveBits.delete();
                    break;
                case 6:
                    fiveBits.search();
                    break;
                case 7:
                    fiveBits.edit();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Unknown value, Please try again.");
            }
        }
    }
    static void sixBitsAlgorithm(int lines){
        SixBitsAlgorithm sixBits = new SixBitsAlgorithm(lines);
        while (true) {
            switch (operationChoice()) {
                case 1:
                    sixBits.displayDays();
                    break;
                case 2:
                    sixBits.displayMin();
                    break;
                case 3:
                    sixBits.displayZeros();
                    break;
                case 4:
                    sixBits.displayOnes();
                    break;
                case 5:
                    sixBits.delete();
                    break;
                case 6:
                    sixBits.search();
                    break;
                case 7:
                    sixBits.edit();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Unknown value, Please try again.");

            }
        }
    }
    static int operationChoice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Chose your operation : ");
        System.out.println("1. Display number of days.");
        System.out.println("2. Display number of minutes.");
        System.out.println("3. Display number of ones.");
        System.out.println("4. Display number of zeros.");
        System.out.println("5. Delete any set of numbers.");
        System.out.println("6. Search for any set of numbers.");
        System.out.println("7. Edit any set of numbers.");
        System.out.println("8. Chose a new sorting method.");
        return sc.nextInt();
    }
}
