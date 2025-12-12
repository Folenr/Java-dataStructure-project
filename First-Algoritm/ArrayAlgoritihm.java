import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

public class ArrayAlgoritihm {
    private final int firstDay;
    private final int line;
    private String[] days;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    private static final Scanner scanner = new Scanner(System.in);

    public ArrayAlgoritihm(int line){
        this.line=line;
        this.days = new String[this.line];
        this.firstDay = readFile();
    }

    private int readFile() {
        java.util.Locale.setDefault(java.util.Locale.US);
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            LocalDate Date = LocalDate.parse(sc.nextLine(), formatter); //a temp date to turn the String into date
            int counter = 0;
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                line = line.replaceAll("\\s+", ""); //delete all spaces
                this.days[counter] = toHexa(line); //add the result in it specific day
                counter++;
            }
            sc.close();
            return (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
            return 0;
        }
    }

    //اذا شغال تصلحش
    public void search(){
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0],endMin = range[1];
        String binaryNum = toBinary(this.days[index]);
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
        String binaryNum = toBinary(this.days[index]);
        //add the value to the zeros and ones
        String edited = binaryNum.substring(0,startMin) + valString + binaryNum.substring(endMin + 1);
        //add the edited result to the array
        this.days[index] = toHexa(edited);
    }

    public void delete(){
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0],endMin = range[1];

        String binaryNum = toBinary(this.days[index]);
        StringBuilder binaryNumB = new StringBuilder(binaryNum);//this object make me able to delete a part of the string
        binaryNumB.delete(startMin,endMin);
        binaryNum = binaryNumB.toString();

        this.days[index] = toHexa(binaryNum);
    }
    public void displayDays(){
        System.out.println("The number of days is :  " + this.days.length);
    }
    public void displayMin(){
        int min =0;
        for(int i=0;i< this.days.length;i++)
            min += toBinary(this.days[i]).length();
        System.out.println("The total number of minutes is :  " + min);
    }
    public void displayZeros(){
        int zeros = 0;
        for (String day : this.days)
            for (int j = 0; j < toBinary(day).length(); j++)
                if (toBinary(day).charAt(j) == '0')
                    zeros++;

        System.out.println("The total number of zeros is :  " + zeros);
    }

    public void displayOnes(){
        int ones = 0;
        for (String day : this.days)
            for (int j = 0; j < toBinary(day).length(); j++)
                if (toBinary(day).charAt(j) == '1')
                    ones++;

        System.out.println("The total number of ones is :  " + ones);
    }


    private int getIndex(){
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scanner.nextLine(); //user enter the day
        LocalDate Date = LocalDate.parse(day, formatter);//convert from string to the format
        int currentDay = (int)Date.toEpochDay();//convert from date to integer
        return currentDay-this.firstDay;
    }

    private String toHexa(String binary){ //turn a binary to hexa
        StringBuilder result = new StringBuilder(); //get the results per day
        for(int i = 0; i+4 <= binary.length(); i+=4) {
            result.append(Integer.toHexString(Integer.parseInt(binary.substring(i, i + 4), 2)).toUpperCase());
        }
        if(binary.length()%4 !=0) {
            result.append("+");
            for(int i=(binary.length()%4);i>0;i--)
                result.append(binary.charAt(binary.length() - i));
        }
        return result.toString();
    }
    private String toBinary(String hexa){//turn a hexa to binary
        StringBuilder binaryNum = new StringBuilder();
        for (int i=0; i<hexa.length(); i++) {//convert all hexadecimal char to binary and add all of them to binaryNum
            if(hexa.charAt(i) != '+')
                binaryNum.append(String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(hexa.charAt(i)), 16).toString(2))));
            else{
                hexa = hexa.replaceAll("\\+", "");
                for (int j=i; j<hexa.length(); j++)
                    binaryNum.append(hexa.charAt(j));
                break;
            }
        }
        return binaryNum.toString();
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
