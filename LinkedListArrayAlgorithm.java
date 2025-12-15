import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class LinkedListArrayAlgorithm extends SortingMethod{

    private int firstDay;
    private LinkedList[] arr;
    private int deleted = 0;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
    private static final Scanner scanner = new Scanner(System.in);

    public LinkedListArrayAlgorithm(){
        this.arr = new LinkedList[0];
    }

    public void readFile() {
        java.util.Locale.setDefault(Locale.US);
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

            this.arr = new LinkedList[counter];

            for (int j = 0; j < arr.length; j++) {
                arr[j] = new LinkedList<>();
            }

            try (Scanner sc = new Scanner(file)) {
                LocalDate Date = LocalDate.parse(sc.nextLine(), formatter);
                this.firstDay = (int) Date.toEpochDay();
                counter=0;
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == '1') {
                            arr[counter].add(i + 1);
                        }
                    }
                    counter++;
                }
                System.out.println("File Size: " + file.length() + " Bytes.");
                System.out.println("Successfully loaded " + this.arr.length + " days of data.");
            }catch (FileNotFoundException e) {
                System.out.println("File 'input.txt' not found. Please create data first (option 2 in main menu).");
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void search() {
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0];
        int endMin = range[1];

        LinkedList<Integer> list = arr[index];

        if (list.isEmpty()) {
            for (int i = startMin; i <= endMin; i++)
                System.out.print(0);
            System.out.println();
            return;
        }

        int listIndex = 0;
        int element = list.getFirst();

        while (listIndex < list.size() && element < startMin) {
            listIndex++;
            if (listIndex < list.size())
                element = list.get(listIndex);
        }

        for (int i = startMin; i <= endMin; i++) {
            if (listIndex < list.size() && i == element) {
                System.out.print(1);
                listIndex++;
                if (listIndex < list.size())
                    element = list.get(listIndex);
            } else {
                System.out.print(0);
            }
        }
        System.out.println();
    }

    public void edit() {
        int index = getIndex();
        int[] range = getRange();
        int startMin = range[0];
        int endMin = range[1];
        int length = endMin - startMin + 1;
        int[] newValues = new int[length];

        System.out.print("Enter the values: ");
        for (int i = 0; i < length; i++) {
            newValues[i] = Integer.parseInt(scanner.nextLine());
        }

        LinkedList<Integer> list = arr[index];
        getIndexRemove(list, startMin, endMin);

        for (int i = 0; i < length; i++) {
            if (newValues[i] == 1)
                list.add(startMin + i);
        }

        Collections.sort(list);
        System.out.println("The Range Edited Successfully.");
    }

    public void delete() {
        int index = getIndex();

        int[] range = getRange();
        int startMin = range[0];
        int endMin = range[1];
        deleted+=endMin - startMin;
        LinkedList<Integer> list = arr[index];
        getIndexRemove(list, startMin, endMin);
        Collections.sort(list);
        System.out.println("The Range Deleted Successfully.");
    }

    public void displayDays() {
        System.out.println("The number of days is : " + this.arr.length);
    }

    public void displayMin() {
        System.out.println("The total number of minutes is : " + ((this.arr.length*1440) - deleted));
    }

    public void displayOnes() {
        int counter = 0;
        for(int i=0; i<this.arr.length; i++) {
            counter += this.arr[i].size();
        }
        System.out.println("The total number of ones is :  " + counter);
    }

    public void displayZeros() {
        int counter = 0;
        for(int i=0; i<this.arr.length; i++) {
            counter += this.arr[i].size();
        }
        System.out.println("The total number of zeros is :  " + (((this.arr.length*1440) - deleted) - counter));
    }

    public void save(){
        try (FileWriter writer = new FileWriter("input.txt")) {
            writer.write(LocalDate.ofEpochDay(firstDay).format(formatter)+"\n");
            for (int day = 0; day < arr.length; day++) {
                StringBuilder dayData = new StringBuilder();

                // Initialize all minutes to '0'
                for (int minute = 1; minute <= 1440; minute++) {
                    dayData.append('0');
                }

                // Set '1' for minutes that exist in the linked list
                LinkedList<Integer> list = arr[day];
                for (Integer minute : list) {
                    // Adjust index since minutes start from 1, but string index from 0
                    if (minute >= 1 && minute <= 1440) {
                        dayData.setCharAt(minute - 1, '1');
                    }
                }

                writer.write(dayData.toString());

                // Add newline except for the last day
                if (day < arr.length - 1) {
                    writer.write("\n");
                }
            }
            System.out.println("All new Values are saved.");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getIndexRemove(LinkedList<Integer> list, int s, int e) {
        for (int i = 0; i < list.size(); i++) {
            int minute = list.get(i);
            if (minute >= s && minute <= e) {
                list.remove(i);
                i--;
            }
        }
    }

    private int getIndex(){
        System.out.print("Enter the Date of format (DD MM YYYY):  ");
        String day = scanner.nextLine();
        LocalDate date = LocalDate.parse(day, formatter);
        return (int) date.toEpochDay() - firstDay;
    }

    private int[] getRange() {
        System.out.print("Enter the start minute:  ");
        int startMin = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the end minute:  ");
        int endMin = Integer.parseInt(scanner.nextLine());
        return new int[]{startMin, endMin};
    }
}
