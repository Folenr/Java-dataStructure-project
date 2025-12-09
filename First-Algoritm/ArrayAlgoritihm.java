import java.util.*;
import java.io.*;

public class ArrayAlgoritihm {
    public static void main(String[] args) {
        String[] day = new String[10]; //initial an array with size 10 days
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            int counter = 0;
            String line = sc.nextLine(); //first line for the date
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
