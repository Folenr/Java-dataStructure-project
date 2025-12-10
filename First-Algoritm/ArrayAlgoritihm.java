import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

public class ArrayAlgoritihm {
    public static void main(String[] args) {
        String[] days = new String[10]; //initial an array with size 10 days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            int counter = 0;
            String line = sc.nextLine(); //first line for the date
            LocalDate Date = LocalDate.parse(line, formatter); //a temp date to turn the String into date
            int firstDay = (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015

            while(sc.hasNextLine()) {
                line = sc.nextLine();
                line = line.replaceAll("\\s+", ""); //delete all spaces
                String result = ""; //get the results per day
                for(int i = 0; i+4 <= line.length(); i+=4) {
                    result += Integer.toHexString(Integer.parseInt(line.substring(i, i+4),2)).toUpperCase();
                }
                days[counter] = result; //add the result in it specific day
                counter++;
            }
            delete(days,firstDay);
            display(days,firstDay);

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    //اذا شغال تصلحش
    public static void display(String[] days,int firstDay){
        Scanner scan = new Scanner(System.in);
        int index = getIndex(days,firstDay);

        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;//get the start min index
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt() - 1;//get the end min index

        String binaryNum = "";
        for (int i=0; i<days[index].length(); i++) {//convert all hexadecimal char to binary and add all of them to binaryNum
            binaryNum += String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(days[index].charAt(i)), 16).toString(2)));
        }
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


        String binaryNum = "";
        for (int i=0; i<days[index].length(); i++) {//convert all hexadecimal char to binary and add all of them to binaryNum
            binaryNum += String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(days[index].charAt(i)), 16).toString(2)));
        }
        //add the value to the zeros and ones
        String edited = binaryNum.substring(0,startMin) + valString + binaryNum.substring(endMin + 1);

        //convert it to hexadecimal
        String result = "";
        for(int i=0; i+4<=edited.length(); i+=4) {
            result += Integer.toHexString(Integer.parseInt(edited.substring(i, i+4),2)).toUpperCase();
        }
        //add the edited result to the array
        days[index] = result;
    }
    public static void delete(String[] days,int firstDay){
        Scanner scan = new Scanner(System.in);
        int index = getIndex(days,firstDay);

        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;//get the start min index
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt();//get the end min index

        String binaryNum = "";
        for (int i=0; i<days[index].length(); i++) {//convert all hexadecimal char to binary and add all of them to binaryNum
            binaryNum += String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(days[index].charAt(i)), 16).toString(2)));
        }
        StringBuilder binaryNumB = new StringBuilder(binaryNum);//this object make me able to delete a part of the string
        binaryNumB.delete(startMin,endMin);
        binaryNum = binaryNumB.toString();

        String result = "";
        for(int i=0; i+4<=binaryNum.length(); i+=4) {
            result += Integer.toHexString(Integer.parseInt(binaryNum.substring(i, i+4),2)).toUpperCase();
        }
        //add the edited result to the array
        days[index] = result;

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
}