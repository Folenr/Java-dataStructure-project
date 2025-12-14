import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.math.BigInteger;

public class FiveBitsAlgorithm extends SortingMethod{
    private int firstDay;
    private String[] days;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    private static final Scanner scanner = new Scanner(System.in);

    public FiveBitsAlgorithm(){
        this.days = new String[0];
    }

    public void readFile() {
        java.util.Locale.setDefault(java.util.Locale.US);
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            LocalDate Date = LocalDate.parse(sc.nextLine(), formatter); //a temp date to turn the String into date

            int counter = 0;
            while(sc.hasNextLine()){
                counter++;
                sc.nextLine();
            }
            this.days = new String[counter];
            sc.close();
            fis = new FileInputStream("input.txt");
            sc = new Scanner(fis);
            sc.nextLine();//skip the date line
            counter=0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine(); //get the lines from first to last in file
                this.days[counter] = to5Bits(line); //add the result in it specific day
                counter++;
            }
            sc.close();
            this.firstDay = (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015
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
        int currentDay = (int)Date.toEpochDay();//convert from date to integer
        return currentDay-this.firstDay;
    }

    private String toBinary(int index) {
        StringBuilder binaryNum = new StringBuilder();
        String fiveBits = this.days[index];
        for (int i=0; i<fiveBits.length(); i++) {//convert all 5-bits char to binary and add all of them to binaryNum
            if(fiveBits.charAt(i) != '+')
                binaryNum.append(String.format("%05d", Integer.parseInt(new BigInteger(String.valueOf(fiveBits.charAt(i)), 32).toString(2))));
            else{
                fiveBits = fiveBits.replaceAll("\\+", "");
                for (int j=i; j<fiveBits.length(); j++)
                    binaryNum.append(fiveBits.charAt(j));
                break;
            }
        }
        return binaryNum.toString();
    }

    private String to5Bits(String edited){
        //convert it to 5-bits system
        StringBuilder result = new StringBuilder();
        for(int i=0; i+5<=edited.length(); i+=5) {
            result.append(Integer.toString(Integer.parseInt(edited.substring(i, i + 5), 2), 32).toUpperCase());
        }
        if(edited.length()%5 !=0) {
            result.append("+");
            for(int i=(edited.length()%5);i>0;i--)
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
