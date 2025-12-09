import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        LinkedList<Integer>[] arr = new LinkedList[10]; // linked lists in array
        for(int j=0;j< arr.length;j++){
            arr[j]= new LinkedList<>();
}
        try {
            int k=0;
            int conunter=0;
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            String line= sc.nextLine();
            LocalDate Date = LocalDate.parse(line, formatter); // turn the String into date
            int FirstDay = (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015
            while (sc.hasNextLine()) { //loop from first line to last line in file
                line = sc.nextLine();
                line = line.replaceAll("\\s+", "");

                    for (int i = 0; i <line.length(); i++){ //loop in the line from first char to last char
                        if (line.charAt(i) == '1') {//make sure its one
                            arr[k].add(i + 1);
                            conunter++;
                        }
                    }
                k++;
            }

            for (int i =0;i<10;i++){
            System.out.println(arr[i]); //print all lists
            }
            System.out.println(conunter);

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
