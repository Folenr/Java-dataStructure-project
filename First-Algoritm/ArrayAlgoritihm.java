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
            System.out.println("Enter the day date to display exp : 19 10 2024");
            String day = scan.nextLine();
            Date = LocalDate.parse(day, formatter);
            int currentDay = (int)Date.toEpochDay();
            //don't touch this it just works
            System.out.println(days[currentDay-firstDay]); //subtract the first day from the current day to get the index

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
