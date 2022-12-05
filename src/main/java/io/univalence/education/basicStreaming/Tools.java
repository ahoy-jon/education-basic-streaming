package io.univalence.education.basicStreaming;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Tools {
    interface StockPriceProcessor {
        void process(String name, LocalDate date, BigDecimal close , Long volume ,BigDecimal open, BigDecimal high,BigDecimal low);
    }

    public static class CSVParser {
        public static void parse(String filename, StockPriceProcessor processor) throws Exception {
            // Read the input CSV file
            try (InputStream resourceAsStream = CSVParser.class.getClassLoader().getResourceAsStream(filename);){
                assert resourceAsStream != null;
                Reader ir = new InputStreamReader(resourceAsStream);

                CSVReader reader = new CSVReaderBuilder(ir).withSkipLines(1).build();


                // Parse and process the CSV data

                String[] line = null;

                while ((line = reader.readNext()) != null) {

                    long volume = Long.parseLong(line[2]);

                    //Date,Close/Last,Volume,Open,High,Low
                    processor.process(filename, null, null, volume, null, null, null);

                }
            } catch (IOException ignored) {
            }
        }
    }



    public static class ConsoleGraphPlotter {
        public static void plot() {
            // Define the data to plot
            int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            // Find the maximum value in the data
            int max = Integer.MIN_VALUE;
            for (int value : data) {
                max = Math.max(max, value);
            }

            // Create an empty grid with the same number of rows and columns as the data
            int rows = data.length + 2; // Add 2 rows for the labels
            int cols = data.length;
            char[][] grid = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = ' ';
                }
            }

            // Populate the grid with the data
            for (int i = 0; i < data.length; i++) {
                int value = data[i];
                int row = rows - 3 - (value * (rows - 3) / max); // Subtract 3 to account for the labels
                grid[row][i] = '*';
            }

            // Add the x-axis labels
            for (int i = 0; i < data.length; i++) {
                grid[rows - 2][i] = (char) ('0' + i);
            }

            // Add the y-axis labels
            for (int i = 0; i < rows - 2; i++) {
                grid[i][data.length] = (char) ('0' + (max * (rows - 2 - i) / (rows - 2)));
            }

            // Print the grid
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(grid[i][j]);
                }
                System.out.println();
            }
        }
    }
}