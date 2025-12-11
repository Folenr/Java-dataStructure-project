import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class LinkedListArrayALgoritihm {

    private int firstDay;
    private int line;
    private LinkedList<Integer>[] arr;

    public LinkedListArrayALgoritihm(int line){
        this.line = line;
        readFile();
    }

    public void readFile() {
        java.util.Locale.setDefault(Locale.US);
        arr = new LinkedList[10];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = new LinkedList<>();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);

            String line = sc.nextLine();
            LocalDate date = LocalDate.parse(line, formatter);

            // store into class field
            this.firstDay = (int) date.toEpochDay();

            int counterOnes = 0;
            int counterMin = 0;
            int index = 0;

            while (sc.hasNextLine() && index < 10) {
                line = sc.nextLine().replaceAll("\\s+", "");

                for (int i = 0; i < line.length(); i++) {
                    counterMin++;
                    if (line.charAt(i) == '1') {
                        arr[index].add(i + 1);
                        counterOnes++;
                    }
                }
                index++;
            }

            int counterZeros = counterMin - counterOnes;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void search() {
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        System.out.print("Enter date (DD MM YYYY): ");
        String day = scan.nextLine();

        LocalDate date = LocalDate.parse(day, formatter);
        int index = (int) date.toEpochDay() - firstDay;

        int[] rang = getrang();
        int startMin = rang[0];
        int endMin = rang[1];

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        System.out.print("Enter date (DD MM YYYY): ");
        String day = scan.nextLine();

        int[] rang = getrang();
        int startMin = rang[0];
        int endMin = rang[1];

        int length = endMin - startMin + 1;
        int[] newValues = new int[length];

        System.out.println("Enter values (0/1): ");
        for (int i = 0; i < length; i++) {
            newValues[i] = scan.nextInt();
        }

        LocalDate date = LocalDate.parse(day, formatter);
        int index = (int) date.toEpochDay() - firstDay;

        LinkedList<Integer> list = arr[index];
        getindex_remove(list, startMin, endMin);

        for (int i = 0; i < length; i++) {
            if (newValues[i] == 1)
                list.add(startMin + i);
        }

        Collections.sort(list);
        System.out.println("edit complete!");
    }

    public void delete() {
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        System.out.print("Enter date (DD MM YYYY): ");
        String day = scan.nextLine();

        int[] rang = getrang();
        int startMin = rang[0];
        int endMin = rang[1];

        LocalDate date = LocalDate.parse(day, formatter);
        int index = (int) date.toEpochDay() - firstDay;

        LinkedList<Integer> list = arr[index];

        getindex_remove(list, startMin, endMin);

        Collections.sort(list);

        System.out.println("delete complete!");
    }


    public void displayMinute(int counterMin) {
        System.out.println("number of minutes : " + counterMin);
    }

    public void displayOnes(int counterones) {
        System.out.println("number of ones : " + counterones);
    }

    public void displayzeros(int counterzeros) {
        System.out.println("number of zeros : " + counterzeros);
    }

    private void getindex_remove(LinkedList<Integer> list, int s, int e) {
        for (int i = 0; i < list.size(); i++) {
            int minute = list.get(i);
            if (minute >= s && minute <= e) {
                list.remove(i);
                i--;
            }
        }
    }

    private int[] getrang() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter startMin: ");
        int startMin = scan.nextInt();
        System.out.print("Enter endMin: ");
        int endMin = scan.nextInt();
        return new int[]{startMin, endMin};
    }
}
