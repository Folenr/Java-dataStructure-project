import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String[] day = new String[10]; //initial an array with size 10 days
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            int counter = 0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String result = ""; //get the results per day
                for(int i = 0; i+4 <= line.length(); i+=4) {
                    String sub = line.substring(i, i+4); //take 4 chars per loop
                    int decimal = Integer.parseInt(sub.replaceAll("\\s+", ""),2); //convert from binary to decimal
                    String hex = Integer.toHexString(decimal).toUpperCase(); //convert from decimal to hexadecimal
                    result += hex;
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