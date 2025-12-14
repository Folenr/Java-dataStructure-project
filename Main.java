import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to the java data structure project.\nPlease enter the number of days :  ");
        int lines = sc.nextInt();
        sc.nextLine(); // This line consumes the leftover "Enter" key
        System.out.print("Please enter the first day date (DD MM YYYY) :  ");
        String date = sc.nextLine();
        SortingMethod[] sortingMethods = new SortingMethod[4];
        sortingMethods[0] = new LinkedListArrayALgoritihm(lines);
        sortingMethods[1] = new ArrayAlgoritihm(lines);
        sortingMethods[2] = new FiveBitsAlgorithm(lines);
        sortingMethods[3] = new SixBitsAlgorithm(lines);

        FileGenerator.newFile(lines,date);
        while (true) {
            System.out.println("1. LinkedList inside an Array.");
            System.out.println("2. 4bit encrypting.");
            System.out.println("3. 5bit encrypting.");
            System.out.println("4. 6bit encrypting.");
            System.out.println("5. Exist the program.");
            System.out.print("Chose your storing method :  ");
            int choice = sc.nextInt();
            if(choice !=5)
                sortingMethodsOperations(choice-1,sortingMethods);
            else
                break;
        }
    }
    static void sortingMethodsOperations(int choice,SortingMethod[] sortingMethods){
        sortingMethods[choice].readFile();
        while (true) {
            switch (operationChoice()) {
                case 1:
                    sortingMethods[choice].displayDays();
                    break;
                case 2:
                    sortingMethods[choice].displayMin();
                    break;
                case 3:
                    sortingMethods[choice].displayOnes();
                    break;
                case 4:
                    sortingMethods[choice].displayZeros();
                    break;
                case 5:
                    sortingMethods[choice].delete();
                    break;
                case 6:
                    sortingMethods[choice].search();
                    break;
                case 7:
                    sortingMethods[choice].edit();
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
        System.out.println("1. Display number of days.");
        System.out.println("2. Display number of minutes.");
        System.out.println("3. Display number of ones.");
        System.out.println("4. Display number of zeros.");
        System.out.println("5. Delete any set of numbers.");
        System.out.println("6. Search for any set of numbers.");
        System.out.println("7. Edit any set of numbers.");
        System.out.println("8. Go back.");
        System.out.print("Chose your operation :  ");
        return sc.nextInt();
    }
}
