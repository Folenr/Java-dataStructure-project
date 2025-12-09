import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String[] days = new String[10]; //initial an array with size 10 days
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            int counter = 0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine().replaceAll("\\s+", ""); //get the lines from first to last in file
                String result = ""; //get the results per day
                for(int i = 0; i+5 <= line.length(); i+=5) { //take just 5 char from the line till the line ends
                    result += Integer.toString(Integer.parseInt(line.substring(i, i+5),2), 32).toUpperCase(); //take a binary substring and convert it to decimal and finaly convert it to Base-32 system and make it uppercase
                }
                days[counter] = result; //add the result in it specific day
                counter++;
            }

            for(int i = 0; i < days.length; i++) {
                System.out.println(days[i]);
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
