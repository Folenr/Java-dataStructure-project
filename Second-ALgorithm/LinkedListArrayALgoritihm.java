import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class LinkedListArrayALgoritihm {
    public static void main(String[] args) {
        LinkedList<Integer>[] arr = new LinkedList[10]; // linked lists in array
        for(int j=0;j< arr.length;j++){
            arr[j]= new LinkedList<>();//create new linked list in array
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//format a string to be a date
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            String line=sc.nextLine();
            LocalDate Date = LocalDate.parse(line, formatter); // turn the String into date
            int firstDay = (int)Date.toEpochDay(); //store the date as Integer : 19 10 2024 -> 20015
            int index =0;
            while (sc.hasNextLine()) { //loop from first line to last line in file
                line = sc.nextLine();
                line = line.replaceAll("\\s+", "");
                for (int i = 0; i <line.length(); i++){ //loop in the line from first char to last char
                    if (line.charAt(i) == '1') {//make sure its one
                        arr[index].add(i + 1);
                    }
                }
                index++;
            }
            display(arr,firstDay);

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void display( LinkedList<Integer>[] arr,int firstDay){
        Scanner scan = new Scanner(System.in);//scanner for the user input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");//formating the date
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scan.nextLine(); //user enter the day

        LocalDate Date = LocalDate.parse(day, formatter);//convert from string to the format
        int currentDay = (int)Date.toEpochDay();//convert from date to integer
        int index = currentDay-firstDay; //get the index of the day in days array

        int startMin,endMin;
        System.out.print("Enter the start minutes:  ");
        startMin = scan.nextInt();//get the start min index
        System.out.print("Enter the end minutes:  ");
        endMin = scan.nextInt();//get the end min index

        int listIndex = 0;
        int element = arr[index].get(listIndex);

        while(element < startMin){
            listIndex++;
            element = arr[index].get(listIndex);
        }
        for(int i = startMin;i<=endMin;i++){
            if(i==element){
                listIndex++;
                element = arr[index].get(listIndex);
                System.out.print(1);
            }else
                System.out.print(0);
        }
    }
}