import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class SixBitsAlgorithm {
    private int firstDay;
    private int line;
    private String[] days;

    public SixBitsAlgorithm(int line){
        this.line=line;
        readFile();
    }

    private void readFile() {
        java.util.Locale.setDefault(java.util.Locale.US);
        this.days = new String[line];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            String line = sc.nextLine(); //first line for the date
            LocalDate Date = LocalDate.parse(line, formatter); //a temp date to turn the String into date
            this.firstDay = (int) Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015
            int counter = 0;
            while (sc.hasNextLine()) {
                line = sc.nextLine().replaceAll("\\s+", ""); //get the lines from first to last in file
                this.days[counter] = to6Bits(line); //add the result in it specific day
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void search() {
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0],endMin = range[1];

        String binaryNum = toBinary(index);
        //take a substring and print it
        System.out.println(binaryNum.substring(startMin, endMin + 1));
    }

    public void edit() {
        Scanner scan = new Scanner(System.in);
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0],endMin = range[1];
        int[] values = new int[endMin-startMin + 1];
        System.out.print("Enter the values: ");
        String valString = "";
        for(int i=0; i<values.length; i++) {
            values[i] = scan.nextInt();
            valString += Integer.toString(values[i]);
        }

        String binaryNum = toBinary(index);
        String edited = binaryNum.substring(0,startMin) + valString + binaryNum.substring(endMin + 1);
        this.days[index] = to6Bits(edited);
    }

    public void delete() {
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0],endMin = range[1];

        String binaryNum = toBinary(index);
        String edited = binaryNum.substring(0,startMin) + binaryNum.substring(endMin + 1);
        this.days[index] = to6Bits(edited);
    }

    public void displayDays(){
        System.out.println("The number of days is :  " + days.length);
    }

    public void displayMin(){
        int counter = 0;
        for(int i=0; i<this.days.length; i++)
            counter += toBinary(i).length();
        System.out.println("The total number of minutes is : " + counter);
    }

    public void displayZerosOnes() {
        int zeros = 0;
        for (int i = 0; i < this.days.length; i++) {
            String binary = toBinary(i);
            for (int j = 0; j < binary.length(); j++)
                if (binary.charAt(j) == '0')
                    zeros++;
        }
        
        System.out.println("The total number of zeros is: " + zeros);
    }
    
    public void displayOnes(){
        int ones = 0;
        for (int i = 0; i < this.days.length; i++) {
            String binary = toBinary(i);
            for (int j = 0; j < binary.length(); j++)
                if (binary.charAt(j) == '1')
                    ones++;
        }

        System.out.println("The total number of ones is: " + ones);
    }

    private int getIndex() {
        Scanner scan = new Scanner(System.in);//scanner for the user input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//formating the date
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scan.nextLine(); //user enter the day

        LocalDate Date = LocalDate.parse(day, formatter);//convert from string to the format
        int currentDay = (int) Date.toEpochDay();//convert from date to integer
        int index = currentDay - this.firstDay; //get the index of the day in days array
        return index;
    }

    private int toDecimal(char c) {// from char to decimal
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

    private String toBinary(int index) {// from decimal to binary
        String binaryNum = "";
        for (int i = 0; i < this.days[index].length(); i++) {//convert all char to binary and add all of them to binaryNum
            char c = this.days[index].charAt(i);
            String binary = Integer.toBinaryString(toDecimal(c));
            binaryNum += String.format("%6s", binary).replace(" ", "0");
        }
        return binaryNum;
    }

    private String to6Bits(String edited){
        String result = ""; //get the results per day
        for (int i = 0; i + 6 <= edited.length(); i += 6) { //take just 6 char from the line till the line ends
            int num = Integer.parseInt(edited.substring(i, i + 6), 2); //convert from binary to decimal
            if (num <= 35)//add to result char by convert decimal to 0..9 A B C ... Z which is 36 chars
                result += Integer.toString(num, 36).toUpperCase();
            else//if it was more than 35 then store a...z this is 62 we need 2 more which is '{' and '|' now it 64
                result += (char) ('a' + (num - 36));
        }
        return result;
    }

    private int[] getRange(){
        Scanner scan = new Scanner(System.in);
        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt() - 1;
        return new int[] {startMin,endMin};
    }

}
