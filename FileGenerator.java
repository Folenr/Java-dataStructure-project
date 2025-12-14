import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
public class FileGenerator {
    public static void newFile(int lines,String date) {
        // File name
        String fileName = "input.txt";
        // Number of random numbers per line (1440 for each minute of day)
        int numbersPerLine = 1440;
        int ran,ranOne=0,ranZero=0;

        // Random number generator
        Random random = new Random();
        try (FileWriter writer = new FileWriter(fileName)) {
            // Generate random data for each line
            StringBuilder line;
            line = new StringBuilder();
            line.append(date);
            writer.write(line.toString() + "\n");
            for (int i = 0; i < lines; i++) {
                line = new StringBuilder();
                for (int j = 0; j < numbersPerLine; j++) {
                    ran=(random.nextInt(10))/8;
                    line.append(ran); // Append 0 or 1
                    if (ran==1) {ranOne+=1;}
                    else ranZero+=1;
                }
                writer.write(line.toString() + "\n"); // Write the line to the file
            }
            System.out.println("File '" + fileName + "' created successfully with random data.");
            System.out.println("Ones = "+ranOne+" And Zeroes = "+ranZero);
        } catch (IOException e) {
            System.err.println("An error occurred while writing the file: "
                    + e.getMessage());
        }
    }
}
