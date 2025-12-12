import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class LinkedListArrayALgoritihm {

    private int firstDay;
    private int line;
    private LinkedList<Integer>[] arr;
    private int deleted = 0;

    public LinkedListArrayALgoritihm(int line){
        this.line = line;
        readFile();
    }

    public void readFile() {
        java.util.Locale.setDefault(Locale.US);
        arr = new LinkedList[line];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = new LinkedList<>();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);

            String line = sc.nextLine();
            LocalDate date = LocalDate.parse(line, formatter);

            this.firstDay = (int) date.toEpochDay();
            int index = 0;

            while (sc.hasNextLine()) {
                line = sc.nextLine().replaceAll("\\s+", "");

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '1') {
                        arr[index].add(i + 1);
                    }
                }
                index++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
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
        int element = list.get(0);

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
        Scanner scan = new Scanner(System.in);
        int index = getIndex();

        int[] range = getRange();
        int startMin = range[0];
        int endMin = range[1];

        int length = endMin - startMin + 1;
        int[] newValues = new int[length];

        System.out.println("Enter the values : ");
        for (int i = 0; i < length; i++) {
            newValues[i] = scan.nextInt();
        }


        LinkedList<Integer> list = arr[index];
        getIndexRemove(list, startMin, endMin);

        for (int i = 0; i < length; i++) {
            if (newValues[i] == 1)
                list.add(startMin + i);
        }

        Collections.sort(list);
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
    }

    public void displayDays() {
        System.out.println("The number of days is : " + this.arr.length);
    }

    public void displayMin() {
        System.out.println("The total number of minutes is : " + ((this.arr.length*24*60) - deleted));
    }

    public void displayOnes() {
        int counter = 0;
        for(int i=0; i<this.arr.length; i++) {
            counter += this.arr[i].size();
        }
        System.out.println("number of ones : " + counter);
    }

    public void displayZeros() {
        int counter = 0;
        for(int i=0; i<this.arr.length; i++) {
            counter += this.arr[i].size();
        }
        System.out.println("number of zeros : " + (((this.arr.length*24*60) - deleted) - counter));
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
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        System.out.print("Enter date (DD MM YYYY): ");
        String day = scan.nextLine();

        LocalDate date = LocalDate.parse(day, formatter);
        return (int) date.toEpochDay() - firstDay;
    }

    private int[] getRange() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter startMin: ");
        int startMin = scan.nextInt();
        System.out.print("Enter endMin: ");
        int endMin = scan.nextInt();
        return new int[]{startMin, endMin};
    }
}
