import java.util.Objects;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to the java data structure project\nPlease enter the number of days :  ");
        int lines = sc.nextInt();
        System.out.print("Please enter the first day date (DD MM YYYY) :  ");
        String date = sc.nextLine();

        FileGenerator.newFile(lines,date);
        System.out.println("Now chose your storing method : ");
        System.out.println("1. LinkedList inside an Array ");
        System.out.println("2. 4bit encrypting");
        System.out.println("3. 5bit encrypting");
        System.out.println("4. 6bit encrypting");
        int choice = sc.nextInt();
        switch (choice){
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
        }

    }
    static void linkedListArrayALgoritihm(int lines){
        LinkedListArrayALgoritihm linkedList = new LinkedListArrayALgoritihm();
        switch (operationChoice()){
            case 1:
                linkedList.displayDays();
                break;
            case 2:
                linkedList.displayMin();
                break;
            case 3:
                linkedList.displayZerosOnes();
                break;
            case 4:
                linkedList.displayZerosOnes();
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
        }
    }
    static void arrayAlgoritihm(int lines){
        ArrayAlgoritihm array= new ArrayAlgoritihm(lines);
        switch (operationChoice()){
            case 1:
                array.displayDays();
                break;
            case 2:
                array.displayMin();
                break;
            case 3:
                array.displayZerosOnes();
                break;
            case 4:
                array.displayZerosOnes();
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
        }
    }
    static void fiveBitsAlgorithm(int lines){
        FiveBitsAlgorithm fiveBits= new FiveBitsAlgorithm(lines);
        switch (operationChoice()){
            case 1:
                fiveBits.displayDays();
                break;
            case 2:
                fiveBits.displayMin();
                break;
            case 3:
                fiveBits.displayZerosOnes();
                break;
            case 4:
                fiveBits.displayZerosOnes();
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
        }
    }
    static void sixBitsAlgorithm(int lines){
        SixBitsAlgorithm sixBits = new SixBitsAlgorithm(lines);
        switch (operationChoice()){
            case 1:
                sixBits.displayDays();
                break;
            case 2:
                sixBits.displayMin();
                break;
            case 3:
                sixBits.displayZerosOnes();
                break;
            case 4:
                sixBits.displayZerosOnes();
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
        }
    }
    static int operationChoice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Chose your operation ");
        System.out.println("1. Display number of days.");
        System.out.println("2. Display number of minutes.");
        System.out.println("3. Display number of ones.");
        System.out.println("4. Display number of zeros.");
        System.out.println("5. Delete any set of numbers");
        System.out.println("6. Search for any set of numbers");
        System.out.println("7. Edit any set of numbers");
        return sc.nextInt();
    }
}
