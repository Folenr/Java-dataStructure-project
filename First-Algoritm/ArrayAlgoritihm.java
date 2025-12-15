import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

public class ArrayAlgoritihm extends SortingMethod{
    private int firstDay;
    private String[] days;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    private static final Scanner scanner = new Scanner(System.in);

    public ArrayAlgoritihm(){
        this.days = new String[0];
    }

    public void readFile() {
        java.util.Locale.setDefault(java.util.Locale.US);
        try {
            File file = new File("input.txt");
            if (!file.exists()) {
                System.out.println("File 'input.txt' not found. Please create data first (option 2 in main menu).");
                return;
            }
            int counter = 0;
            try (Scanner lineCounter = new Scanner(file)) {
                if (lineCounter.hasNextLine()) {
                    lineCounter.nextLine(); // Skip date line
                }
                while(lineCounter.hasNextLine()){
                    counter++;
                    lineCounter.nextLine();
                }
            }
            this.days = new String[counter];

            try (Scanner sc = new Scanner(file)) {
                LocalDate Date = LocalDate.parse(sc.nextLine(), formatter);
                this.firstDay = (int)Date.toEpochDay();

                counter = 0;
                while(sc.hasNextLine()) {
                    String line = sc.nextLine();
                    this.days[counter] = toHexa(line);
                    counter++;
                }
                System.out.println("Successfully loaded " + this.days.length + " days of data.");
            }catch (FileNotFoundException e) {
                System.out.println("File 'input.txt' not found. Please create data first (option 2 in main menu).");
            }
        }catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
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
        System.out.println("The Range Edited Successfully.");
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
        System.out.println("The Range Deleted Successfully.");
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

    public void save() {
        try (FileWriter writer = new FileWriter("input.txt")) {
            writer.write(LocalDate.ofEpochDay(firstDay).format(formatter)+"\n");
            for(int i=0;i<days.length;i++){
                writer.write(toBinary(this.days[i])+"\n");
            }

            System.out.println("All new Values are saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
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
