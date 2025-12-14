import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class SixBitsAlgorithm extends SortingMethod{
    private int firstDay;
    private String[] days;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    private static final Scanner scanner = new Scanner(System.in);

    public SixBitsAlgorithm(int lines){
        this.days = new String[lines];
    }

    public void readFile() {
        java.util.Locale.setDefault(java.util.Locale.US);
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            LocalDate Date = LocalDate.parse(sc.nextLine(), formatter); //a temp date to turn the String into date
            int counter = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replaceAll("\\s+", ""); //get the lines from first to last in file
                this.days[counter] = to6Bits(line); //add the result in it specific day
                counter++;
            }
            sc.close();
            this.firstDay = (int) Date.toEpochDay();
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
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0],endMin = range[1];
        int[] values = new int[endMin-startMin + 1];
        System.out.print("Enter the values: ");
        StringBuilder valString = new StringBuilder();
        for(int i=0; i<values.length; i++) {
            values[i] = Integer.parseInt(scanner.nextLine());
            valString.append(values[i]);
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

    public void displayZeros() {
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
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scanner.nextLine(); //user enter the day
        LocalDate Date = LocalDate.parse(day, formatter);//convert from string to the format
        int currentDay = (int) Date.toEpochDay();//convert from date to integer
        return currentDay - this.firstDay;
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
        StringBuilder binaryNum = new StringBuilder();
        String sixBits = this.days[index];
        for (int i = 0; i < this.days[index].length(); i++) {//convert all char to binary and add all of them to binaryNum
            if (sixBits.charAt(i) != '+') {
                char c = this.days[index].charAt(i);
                String binary = Integer.toBinaryString(toDecimal(c));
                binaryNum.append(String.format("%6s", binary).replace(" ", "0"));
            }else{
                sixBits = sixBits.replaceAll("\\+", "");
                for (int j=i; j<sixBits.length(); j++)
                    binaryNum.append(sixBits.charAt(j));
                break;
            }
        }
        return binaryNum.toString();
    }

    private String to6Bits(String edited){
        StringBuilder result = new StringBuilder(); //get the results per day
        for (int i = 0; i + 6 <= edited.length(); i += 6) { //take just 6 char from the line till the line ends
            int num = Integer.parseInt(edited.substring(i, i + 6), 2); //convert from binary to decimal
            if (num <= 35)//add to result char by convert decimal to 0..9 A B C ... Z which is 36 chars
                result.append(Integer.toString(num, 36).toUpperCase());
            else//if it was more than 35 then store a...z this is 62 we need 2 more which is '{' and '|' now it 64
                result.append((char) ('a' + (num - 36)));
        }
        if(edited.length()%6 !=0) {
            result.append("+");
            for(int i=(edited.length()%6);i>0;i--)
                result.append(edited.charAt(edited.length() - i));
        }
        return result.toString();
    }

    private int[] getRange(){
        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.print("Enter the end minute:  ");
        endMin = Integer.parseInt(scanner.nextLine()) - 1;
        return new int[] {startMin,endMin};
    }
}
