
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Author: Roger Moccia
 * Course: COP3503
 * Project #2
 * Title: Speed Data IO
 * Due Date: 10/30/23
 * Reads data in from a csv file containing sensor data along with the date and times that the sensors collected the
 * data. Takes the difference of two of the sensors and stores them into a new dataset. Takes the average of all
 * the sensors and stores that in a new dataset. Finally, writes the modified data to a new file.
 */
public class Project_2 {
    /**
     * The constant dates.
     */
    public static ArrayList<String> dates = new ArrayList<String>();
    /**
     * The constant times.
     */
    public static ArrayList<String> times = new ArrayList<String>();
    /**
     * The constant sensor2278.
     */
    public static ArrayList<Double> sensor2278 = new ArrayList<Double>();
    /**
     * The constant sensor3276.
     */
    public static ArrayList<Double> sensor3276 = new ArrayList<Double>();
    /**
     * The constant sensor4689.
     */
    public static ArrayList<Double> sensor4689 = new ArrayList<Double>();
    /**
     * The constant sensor5032.
     */
    public static ArrayList<Double> sensor5032 = new ArrayList<Double>();
    /**
     * The constant section1Diff.
     */
    public static ArrayList<Double> section1Diff = new ArrayList<Double>();
    /**
     * The constant section2Diff.
     */
    public static ArrayList<Double> section2Diff = new ArrayList<Double>();
    /**
     * The constant totalAvg.
     */
    public static ArrayList<Double> totalAvg = new ArrayList<Double>();

    /**
     * Load data to new file with new name _Difference.csv
     *
     * @param fileName the file name
     * @throws FileNotFoundException the file not found exception
     * @throws IOException           the io exception
     */
    public static void loadDataToNewFile(String fileName) throws FileNotFoundException, IOException {
        String revisedFileName = fileName.replace(".csv", "_Difference.csv");
        PrintWriter printWriter = new PrintWriter(revisedFileName);
        printWriter.println("Date, Time, Sensor_2278, Sensor_3276, Sensor_4689, Sensor_5032, Section1Diff," +
                "Section2Diff, totalAvg");
        for(int i = 0; i < dates.size(); i++){
            printWriter.print(dates.get(i) + ",");
            printWriter.print(times.get(i) + ",");
            printWriter.print(sensor2278.get(i) + ",");
            printWriter.print(sensor3276.get(i) + ",");
            printWriter.print(sensor4689.get(i) + ",");
            printWriter.print(sensor5032.get(i) + ",");
            printWriter.print(section1Diff.get(i) + ",");
            printWriter.print(section2Diff.get(i) + ",");
            printWriter.println(totalAvg.get(i));
        }
        printWriter.close();
    }

    /**
     * Clear arrays if exception is thrown. 
     */
    public static void clearArrays() {
        dates.clear();
        times.clear();
        sensor5032.clear();
        sensor4689.clear();
        sensor3276.clear();
        sensor2278.clear();
    }

    /**
     * Find sensor avg array list.
     *
     * @param sensor1 the sensor 1
     * @param sensor2 the sensor 2
     * @param sensor3 the sensor 3
     * @param sensor4 the sensor 4
     * @return the array list
     */
    public static ArrayList<Double> findSensorAvg(ArrayList<Double> sensor1, ArrayList<Double> sensor2,
                                                  ArrayList<Double> sensor3, ArrayList<Double> sensor4) {
        ArrayList<Double> average = new ArrayList<Double>();
        for (int i = 0; i < sensor1.size(); i++) {
            average.add((sensor1.get(i) + sensor2.get(i) + sensor3.get(i) + sensor4.get(i)) / 4);
        }
        return average;
    }

    /**
     * Subtract sensor data array list.
     *
     * @param sensor1 the sensor 1
     * @param sensor2 the sensor 2
     * @return the array list
     */
    public static ArrayList<Double> subtractSensorData(ArrayList<Double> sensor1, ArrayList<Double> sensor2) {

        ArrayList<Double> difference = new ArrayList<Double>();
        for (int i = 0; i < sensor1.size(); i++) {
            difference.add(sensor1.get(i) - sensor2.get(i));
        }


        return difference;
    }

    /**
     * Read data.
     *
     * @param fileName the file name
     * @throws IOException           the io exception
     * @throws NumberFormatException the number format exception
     * @throws ParseException        the parse exception
     */
    public static void readData(String fileName) throws IOException, NumberFormatException, ParseException {
        File file = new File(fileName);
        Scanner fileScan = new Scanner(file);

        String header = fileScan.nextLine();
        while (fileScan.hasNextLine()) {

            String line = fileScan.nextLine();

            String[] lineData = line.split(",");

            dates.add(ConvertDate.formatDate(lineData[0]));
            times.add(lineData[1]);
            Double newSensor2278 = Double.parseDouble(lineData[2]);
            sensor2278.add(newSensor2278);
            Double newSensor3276 = Double.parseDouble(lineData[3]);
            sensor3276.add(newSensor3276);
            Double newSensor4689 = Double.parseDouble(lineData[4]);
            sensor4689.add(newSensor4689);
            Double newSensor5032 = Double.parseDouble(lineData[5]);
            sensor5032.add(newSensor5032);

        }
        fileScan.close();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        System.out.println("Starting Project 2\n.\n.\n.\nHello!");

        boolean keepGoing = true;

        Scanner in = new Scanner(System.in);

        while (keepGoing) {
            System.out.print("Please enter a valid file!\n-> ");
            try {
                String fileName = in.nextLine();

                readData(fileName);

                section1Diff = subtractSensorData(sensor3276, sensor2278);
                section2Diff = subtractSensorData(sensor5032, sensor4689);
                totalAvg = findSensorAvg(sensor5032, sensor4689, sensor2278, sensor3276);
                System.out.print("Loading data, please wait\n.\n.\n.\nFile loaded successfully!\n");

                loadDataToNewFile(fileName);
                System.out.print("your file has been created and the data has been transferred!\nThank you, Goodbye!");
                keepGoing = false;


            } catch (FileNotFoundException e) {
                System.out.println("The file does not exist or incorrect file path!");
            } catch (NumberFormatException e) {
                System.out.println("Bad number data, please try again!");
                clearArrays();
            } catch (ParseException e) {
                System.out.println("Bad date data, please try again!");
                clearArrays();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    in.close();
    } //end main
}
