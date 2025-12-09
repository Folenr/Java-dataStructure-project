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
                for(int i = 0; i+6 <= line.length(); i+=6) { //take just 6 char from the line till the line ends
                    int num = Integer.parseInt(line.substring(i, i+6),2); //convert from binary to decimal
                    if(num<=35)//add to result char by convert decimal to 0..9 A B C ... Z which is 36 chars
                            result += Integer.toString(num,36).toUpperCase();
                    else//if it was more than 35 then store a...z this is 62 we need 2 more which is '{' and '|' now it 64
                        result +=(char) ('a' + (num-36));
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
