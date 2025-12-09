import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class LinkedListArrayALgoritihm {
    public static void main(String[] args) {
        LinkedList<LinkedList<Integer>> arr = new LinkedList<>(); //nested linked list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            String line = sc.nextLine(); //first line for the date
            LocalDate Date = LocalDate.parse(line, formatter); // turn the String into date
            int FirstDay = (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015

            while (sc.hasNextLine()){ //loop from first line to last line in file
                line = sc.nextLine();
                line = line.replaceAll("\\s+", ""); //delete all spaces
                LinkedList<Integer> temp = new LinkedList<>(); //temp linked list to put the index of ones
                for(int i = 0; i<line.length(); i++) //loop in the line from first char to last char
                    if(line.charAt(i) == '1') //make sure its one
                        temp.add(i+1); //add the index of it to the temp linked list
                arr.add(temp); //add the temp linked list to the main linked list
            }

            System.out.println(arr); //print all lists

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}