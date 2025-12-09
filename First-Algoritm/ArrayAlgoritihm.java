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
            Scanner scan = new Scanner(System.in); //scanner for user input
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
            diplay(days,firstDay);

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    //اذا شغال تصلحش
    public static void diplay(String[] days,int firstDay){
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scan.nextLine(); //user enter the day

        LocalDate Date = LocalDate.parse(day, formatter);
        int currentDay = (int)Date.toEpochDay();
        int index = currentDay-firstDay; //turn the day into integer

        int startMin,endMin;
        System.out.print("Enter the start minutes:  ");
        startMin = scan.nextInt() - 1;
        System.out.print("Enter the end minutes:  ");
        endMin = scan.nextInt() - 1;

        String hexaNum = ""; //the hexadecimal number we want to print
        for(int i=startMin/4;i<=endMin/4;i++)
            hexaNum += days[index].charAt(i);

        char currentHexa = hexaNum.charAt(0); //the current hexadecimal number, it will turn into binary
        String binary;
        binary = String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(currentHexa), 16).toString(2)));
        //this is 3 parts
        //1 print the first hexadecimal number (only print the needed part)
        //2 print the numbers in between
        //3 print the last hexadecimal number (only print the needed part)
        if(endMin/4 != startMin/4) //check if we have more than 1 hexadecimal number
            for(int i=startMin%4;i<4;i++)
                System.out.print(binary.charAt(i));
        else{ //if we have 1 hexadecimal number it will print it and stop
            for(int i=startMin%4;i<=endMin%4;i++)
                System.out.print(binary.charAt(i));
            return;
        }
        for(int i=1;i<hexaNum.length()-1;i++){
            currentHexa = hexaNum.charAt(i);
            binary = String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(currentHexa), 16).toString(2)));
            for(int j=0;j<4;j++)
                System.out.print(binary.charAt(j));
        }
        currentHexa = hexaNum.charAt(hexaNum.length()-1);
        binary = String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(currentHexa), 16).toString(2)));
        for(int i=0;i<=endMin%4;i++)
            System.out.print(binary.charAt(i));
    }
}
