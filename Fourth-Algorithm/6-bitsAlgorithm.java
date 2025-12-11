import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String[] days = new String[10]; //initial an array with size 10 days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            String line = sc.nextLine(); //first line for the date
            LocalDate Date = LocalDate.parse(line, formatter); //a temp date to turn the String into date
            int firstDay = (int) Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015
            int counter = 0;
            while (sc.hasNextLine()) {
                line = sc.nextLine().replaceAll("\\s+", ""); //get the lines from first to last in file
                String result = ""; //get the results per day
                for (int i = 0; i + 6 <= line.length(); i += 6) { //take just 6 char from the line till the line ends
                    int num = Integer.parseInt(line.substring(i, i + 6), 2); //convert from binary to decimal
                    if (num <= 35)//add to result char by convert decimal to 0..9 A B C ... Z which is 36 chars
                        result += Integer.toString(num, 36).toUpperCase();
                    else//if it was more than 35 then store a...z this is 62 we need 2 more which is '{' and '|' now it 64
                        result += (char) ('a' + (num - 36));
                }
                days[counter] = result; //add the result in it specific day
                counter++;
            }
            search(days, firstDay);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void search(String[] days, int firstDay) {
        Scanner scan = new Scanner(System.in);
        int index = getIndex(days, firstDay);

        int startMin, endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;//get the start min index
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt() - 1;//get the end min index

        String binaryNum = "";
        for (int i = 0; i < days[index].length(); i++) {//convert all char to binary and add all of them to binaryNum
            char c = days[index].charAt(i);
            String binary = Integer.toBinaryString(toDecimal(c));
            binaryNum += String.format("%6s", binary).replace(" ", "0");
        }
        //take a substring and print it
        System.out.println(binaryNum.substring(startMin, endMin + 1));
    }

    private static int getIndex(String[] days, int firstDay) {
        Scanner scan = new Scanner(System.in);//scanner for the user input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//formating the date
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scan.nextLine(); //user enter the day

        LocalDate Date = LocalDate.parse(day, formatter);//convert from string to the format
        int currentDay = (int) Date.toEpochDay();//convert from date to integer
        int index = currentDay - firstDay; //get the index of the day in days array
        return index;
    }

    private static int toDecimal(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'A' && c <= 'Z') {
            return 10 + (c - 'A');
        } else if (c >= 'a' && c <= 'z') {
            return 36 + (c - 'a');
        } else if (c == '{') {// {
            return 62;
        }
        return 63;// |
    }
}
