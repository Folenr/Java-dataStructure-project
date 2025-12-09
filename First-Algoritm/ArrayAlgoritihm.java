import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

public class ArrayAlgoritihm {
    public static void main(String[] args) {
        String[] day = new String[10]; //initial an array with size 10 days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            int counter = 0;
            String line = sc.nextLine(); //first line for the date
            LocalDate Date = LocalDate.parse(line, formatter); // turn the String into date
            int FirstDay = (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015

            while(sc.hasNextLine()) {
                line = sc.nextLine();
                line = line.replaceAll("\\s+", ""); //delete all spaces
                String result = ""; //get the results per day
                for(int i = 0; i+4 <= line.length(); i+=4) {
                    result += Integer.toHexString(Integer.parseInt(line.substring(i, i+4),2)).toUpperCase();
                }
                day[counter] = result; //add the result in it specific day
                counter++;
            }
            for(int i = 0; i < day.length; i++) {
                System.out.println(day[i]);
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
