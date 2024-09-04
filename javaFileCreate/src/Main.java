import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "/Users/salihcsr/Desktop/file.txt"; // Replace with your input file path
        String outputFilePath = "/Users/salihcsr/Desktop/big-file.txt"; // Replace with your desired output file path
        long targetSizeInGB = 2;  // Target size in GB

        // Convert target size from GB to bytes
        long targetSizeInBytes = targetSizeInGB * 1024L * 1024L * 1024L;

        try {
            // Read the content of the input file
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
            }

            // Calculate the size of the input content in bytes
            byte[] contentBytes = content.toString().getBytes();
            long contentSize = contentBytes.length;

            // Open the output file in write mode
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                long totalBytesWritten = 0;
                // Write content repeatedly until the desired file size is reached
                while (totalBytesWritten < targetSizeInBytes) {
                    writer.write(content.toString());
                    totalBytesWritten += contentSize;
                }
            }

            System.out.println("Large file created successfully: " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}