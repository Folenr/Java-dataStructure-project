import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        java.util.Locale.setDefault(java.util.Locale.US);
        String[] days = new String[10]; //initial an array with size 10 days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            String line = sc.nextLine(); //first line for the date
            LocalDate Date = LocalDate.parse(line, formatter); //a temp date to turn the String into date
            int firstDay = (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015
            int counter = 0;
            while(sc.hasNextLine()) {
                line = sc.nextLine().replaceAll("\\s+", ""); //get the lines from first to last in file
                days[counter] = to5Bits(line); //add the result in it specific day
                counter++;
            }
            displayZerosOnes(days);

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void search(String[] days,int firstDay){
        Scanner scan = new Scanner(System.in);
        int index = getIndex(days,firstDay);

        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;//get the start min index
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt() - 1;//get the end min index

        String binaryNum = toBinary(days,index);
        //take a substring and print it
        System.out.println(binaryNum.substring(startMin, endMin + 1));
    }

    public static void edit(String[] days,int firstDay){
        Scanner scan = new Scanner(System.in);
        int index = getIndex(days,firstDay);

        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt() - 1;
        int[] values = new int[endMin-startMin + 1];
        System.out.print("Enter the values: ");
        String valString = "";
        for(int i=0; i<values.length; i++) {
            values[i] = scan.nextInt();
            valString += Integer.toString(values[i]);
        }

        String binaryNum = toBinary(days,index);
        //add the value to the zeros and ones
        String edited = binaryNum.substring(0,startMin) + valString + binaryNum.substring(endMin + 1);
        //add the edited result to the array
        days[index] = to5Bits(edited);
    }

    public static void delete(String[] days,int firstDay){
        Scanner scan = new Scanner(System.in);
        int index = getIndex(days,firstDay);

        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt() - 1;

        String binaryNum = toBinary(days,index);
        String edited = binaryNum.substring(0,startMin) + binaryNum.substring(endMin + 1);
        days[index] = to5Bits(edited);
    }

    public static void displayDays(String[] days){
        System.out.println("The number of days is :  " + days.length);
    }

    public static void displayMin(String[] days){
        int counter = 0;
        for(int i=0; i<days.length; i++)
            counter += toBinary(days,i).length();
        System.out.println("The total number of minutes is : " + counter);
    }

    public static void displayZerosOnes(String[] days) {
        int zeros = 0;
        int ones = 0;
        for (int i = 0; i < days.length; i++) {
            String binary = toBinary(days, i);
            for (int j = 0; j < binary.length(); j++)
                if (binary.charAt(j) == '0')
                    zeros++;
                else
                    ones++;
        }

        System.out.println("The total number of ones is: " + ones);
        System.out.println("The total number of zeros is: " + zeros);
    }


    private static int getIndex(String[] days,int firstDay){
        Scanner scan = new Scanner(System.in);//scanner for the user input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//formating the date
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scan.nextLine(); //user enter the day

        LocalDate Date = LocalDate.parse(day, formatter);//convert from string to the format
        int currentDay = (int)Date.toEpochDay();//convert from date to integer
        int index = currentDay-firstDay; //get the index of the day in days array
        return index;
    }

    private static String toBinary(String[] days,int index) {
        String binaryNum = "";
        for (int i=0; i<days[index].length(); i++) {//convert all 5-bits char to binary and add all of them to binaryNum
            binaryNum += String.format("%05d", Integer.parseInt(new BigInteger(String.valueOf(days[index].charAt(i)), 32).toString(2)));
        }
        return binaryNum;
    }

    private static String to5Bits(String edited){
        //convert it to 5-bits system
        String result = "";
        for(int i=0; i+5<=edited.length(); i+=5) {
            result += Integer.toString(Integer.parseInt(edited.substring(i, i+5),2), 32).toUpperCase();
        }
        return result;
    }
}
