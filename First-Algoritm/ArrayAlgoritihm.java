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
        String day = scan.nextLine();
        LocalDate Date = LocalDate.parse(day, formatter);
        int currentDay = (int)Date.toEpochDay();
        int index = currentDay-firstDay;
        System.out.println(days[index]); //subtract the first day from the current day to get the index
        //don't touch the code above, it just works
        int startMin,endMin; //integers for start min and end min, will be used for display (print)
        startMin = scan.nextInt() - 1;
        endMin = scan.nextInt() - 1;
        String hexaNum = "";
        for(int i=startMin/4;i<=endMin/4;i++)
            hexaNum += days[index].charAt(i);
        char currentHexa = hexaNum.charAt(0);
        String deciamel;
        deciamel = String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(currentHexa), 16).toString(2)));
        if(endMin/4 != startMin/4)
            for(int i=startMin%4;i<4;i++)
                System.out.print(deciamel.charAt(i));
        else{
            for(int i=startMin%4;i<=endMin%4;i++)
                System.out.print(deciamel.charAt(i));
            return;
        }
        for(int i=1;i<hexaNum.length()-1;i++){
            currentHexa = hexaNum.charAt(i);
            deciamel = String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(currentHexa), 16).toString(2)));
            for(int j=0;j<4;j++)
                System.out.print(deciamel.charAt(j));
        }
        currentHexa = hexaNum.charAt(hexaNum.length()-1);
        deciamel = String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(currentHexa), 16).toString(2)));
        for(int i=0;i<=endMin%4;i++)
            System.out.print(deciamel.charAt(i));
    }
}
