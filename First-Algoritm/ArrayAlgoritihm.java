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
            display(days,firstDay);

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    //اذا شغال تصلحش
    public static void display(String[] days,int firstDay){
        Scanner scan = new Scanner(System.in);//scanner for the user input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//formating the date
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scan.nextLine(); //user enter the day

        LocalDate Date = LocalDate.parse(day, formatter);//convert from string to the format
        int currentDay = (int)Date.toEpochDay();//convert from date to integer
        int index = currentDay-firstDay; //get the index of the day in days array

        int startMin,endMin;
        System.out.print("Enter the start minutes:  ");
        startMin = scan.nextInt() - 1;//get the start min
        System.out.print("Enter the end minutes:  ");
        endMin = scan.nextInt() - 1;//get the end min

        String binaryNum = "";
        //convert every Hexadecimal char to 4 binary chars and add these 4 chars as string to binaryNum
        for(int i=startMin/4;i<=endMin/4;i++) {
            binaryNum += String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(days[index].charAt(i)), 16).toString(2)));
        }
        int result = (endMin - startMin+1)-1;//get just the length of zeros and ones needed
        for(int j=0;j<result;j++) {//print them
            System.out.print(binaryNum.charAt(j));
        }
    }
}
