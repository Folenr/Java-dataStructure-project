import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

public class ArrayAlgoritihm {
    private String[] days;
    private int firstDay;
    private void readFile() {
        java.util.Locale.setDefault(java.util.Locale.US);
        this.days = new String[10]; //initial an array with size 10 days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            int counter = 0;
            String line = sc.nextLine(); //first line for the date
            LocalDate Date = LocalDate.parse(line, formatter); //a temp date to turn the String into date
            this.firstDay = (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015

            while(sc.hasNextLine()) {
                line = sc.nextLine();
                line = line.replaceAll("\\s+", ""); //delete all spaces
                this.days[counter] = toHexa(line); //add the result in it specific day
                counter++;
            }

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public ArrayAlgoritihm(){
        readFile();
    }
    //اذا شغال تصلحش
    public void search(){
        Scanner scan = new Scanner(System.in);
        int index = getIndex();

        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;//get the start min index
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt() - 1;//get the end min index

        String binaryNum = toBinary(this.days[index]);
        //take a substring and print it
        System.out.println(binaryNum.substring(startMin, endMin + 1));
    }

    public void edit(){
        Scanner scan = new Scanner(System.in);
        int index = getIndex();

        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt() - 1;
        int[] values = new int[endMin-startMin + 1];
        System.out.print("Enter the values: ");
        String valString = "";
        for(int i=0; i<values.length; i++) {
            values[i] = scan.nextInt();
            valString += Integer.toString(values[i]);
        }
        String binaryNum = toBinary(this.days[index]);
        //add the value to the zeros and ones
        String edited = binaryNum.substring(0,startMin) + valString + binaryNum.substring(endMin + 1);
        //add the edited result to the array
        this.days[index] = toHexa(edited);
    }

    public void delete(){
        Scanner scan = new Scanner(System.in);
        int index = getIndex();

        int startMin,endMin;
        System.out.print("Enter the start minute:  ");
        startMin = scan.nextInt() - 1;//get the start min index
        System.out.print("Enter the end minute:  ");
        endMin = scan.nextInt();//get the end min index

        String binaryNum = toBinary(this.days[index]);
        StringBuilder binaryNumB = new StringBuilder(binaryNum);//this object make me able to delete a part of the string
        binaryNumB.delete(startMin,endMin);
        binaryNum = binaryNumB.toString();

        this.days[index] = toHexa(binaryNum);
    }
    public void displayDays(){
        System.out.println("The number of days is :  " + this.days.length);
    }
    public void displayMin(String[] days){
        int min =0;
        for (int i=0;i< this.days.length;i++)
            min += toBinary(this.days[i]).length();
        System.out.println("The total number of minutes is :  " + min);
    }
    public void displayZerosOnes(){
        int zeros = 0;
        int ones = 0;
        for (String day : this.days)
            for (int j = 0; j < toBinary(day).length(); j++)
                if (toBinary(day).charAt(j) == '1')
                    ones++;
                else
                    zeros++;

        System.out.println("The total number of ones is :  " + ones);
        System.out.println("The total number of zeros is :  " + zeros);
    }


    private int getIndex(){
        Scanner scan = new Scanner(System.in);//scanner for the user input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//formating the date
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scan.nextLine(); //user enter the day

        LocalDate Date = LocalDate.parse(day, formatter);//convert from string to the format
        int currentDay = (int)Date.toEpochDay();//convert from date to integer
        int index = currentDay-this.firstDay; //get the index of the day in days array
        return index;
    }

    private String toHexa(String binary){ //turn a binary to hexa
        String result = ""; //get the results per day
        for(int i = 0; i+4 <= binary.length(); i+=4) {
                result += Integer.toHexString(Integer.parseInt(binary.substring(i, i+4),2)).toUpperCase();
        }
        if(binary.length()%4 !=0) {
            result += "+";
            for(int i=(binary.length()%4);i>0;i--)
                result += binary.charAt(binary.length()-i);
        }
        return result;
    }
    private String toBinary(String hexa){//turn a hexa to binary
        String binaryNum = "";
        for (int i=0; i<hexa.length(); i++) {//convert all hexadecimal char to binary and add all of them to binaryNum
            if(hexa.charAt(i) != '+')
                binaryNum += String.format("%04d", Integer.parseInt(new BigInteger(String.valueOf(hexa.charAt(i)), 16).toString(2)));
            else{
                hexa = hexa.replaceAll("\\+", "");
                for (int j=i; j<hexa.length(); j++)
                    binaryNum += hexa.charAt(j);
                break;
            }
        }
        return binaryNum;
    }
}
