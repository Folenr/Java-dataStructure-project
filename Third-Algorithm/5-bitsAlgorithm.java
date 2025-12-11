import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.math.BigInteger;

public class FiveBitsAlgorithm {
    private String[] days;
    private int firstDay;

    public FiveBitsAlgorithm() {
        readFile();
    }

    private void readFile() {
        java.util.Locale.setDefault(java.util.Locale.US);
        this.days = new String[10]; //initial an array with size 10 days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            String line = sc.nextLine(); //first line for the date
            LocalDate Date = LocalDate.parse(line, formatter); //a temp date to turn the String into date
            this.firstDay = (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015
            int counter = 0;
            while(sc.hasNextLine()) {
                line = sc.nextLine().replaceAll("\\s+", ""); //get the lines from first to last in file
                this.days[counter] = to5Bits(line); //add the result in it specific day
                counter++;
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void search(){
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0],endMin = range[1];
        String binaryNum = toBinary(index);
        //take a substring and print it
        System.out.println(binaryNum.substring(startMin, endMin + 1));
    }

    public void edit(){
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
        //add the value to the zeros and ones
        String edited = binaryNum.substring(0,startMin) + valString + binaryNum.substring(endMin + 1);
        //add the edited result to the array
        this.days[index] = to5Bits(edited);
    }

    public void delete(){
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0],endMin = range[1];
        String binaryNum = toBinary(index);
        String edited = binaryNum.substring(0,startMin) + binaryNum.substring(endMin + 1);
        this.days[index] = to5Bits(edited);
    }

    public void displayDays(){
        System.out.println("The number of days is :  " + this.days.length);
    }

    public void displayMin(){
        int counter = 0;
        for(int i=0; i<this.days.length; i++)
            counter += toBinary(i).length();
        System.out.println("The total number of minutes is : " + counter);
    }

    public void displayZerosOnes() {
        int zeros = 0;
        int ones = 0;
        for (int i = 0; i < this.days.length; i++) {
            String binary = toBinary(i);
            for (int j = 0; j < binary.length(); j++)
                if (binary.charAt(j) == '0')
                    zeros++;
                else
                    ones++;
        }

        System.out.println("The total number of ones is: " + ones);
        System.out.println("The total number of zeros is: " + zeros);
    }


    private int getIndex() {
        Scanner scan = new Scanner(System.in);//scanner for the user input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//formating the date
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scan.nextLine(); //user enter the day

        LocalDate Date = LocalDate.parse(day, formatter);//convert from string to the format
        int currentDay = (int)Date.toEpochDay();//convert from date to integer
        int index = currentDay-this.firstDay; //get the index of the day in days array
        return index;
    }

    private String toBinary(int index) {
        String binaryNum = "";
        for (int i=0; i<this.days[index].length(); i++) {//convert all 5-bits char to binary and add all of them to binaryNum
            binaryNum += String.format("%05d", Integer.parseInt(new BigInteger(String.valueOf(this.days[index].charAt(i)), 32).toString(2)));
        }
        return binaryNum;
    }

    private String to5Bits(String edited){
        //convert it to 5-bits system
        String result = "";
        for(int i=0; i+5<=edited.length(); i+=5) {
            result += Integer.toString(Integer.parseInt(edited.substring(i, i+5),2), 32).toUpperCase();
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
