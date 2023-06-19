import java.io.*;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class DataManage {

    static void loadData(File file, PlantList myList) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        try {
            while (true) {
                Plant plant = (Plant) ois.readObject();
                myList.plantList.add(plant);
            }
        } catch (EOFException e) {
            System.out.println("All data has been loaded");
        } finally {
            ois.close();
        }
    }

    static void popUpMessage(PlantList myList) {
        if (!myList.todayIsTime()) {
            return;
        }
        Plant todayPlant = myList.todayIsPlant();
        String message = "Don't forget to water " + todayPlant.name + " today!";
        JOptionPane.showMessageDialog(null, message);

        Scanner input = new Scanner(System.in);
        System.out.println("Have you watered " + todayPlant.name + " today? yes/no: ");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            repeatWatering(todayPlant, myList);
        }
    }

    static void repeatWatering(Plant todayPlant, PlantList myList) {
        System.out.println("Do you want to water " + todayPlant.name + " again in " + todayPlant.duration + " days? yes/no: ");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            LocalDate currentDate = LocalDate.now();
            LocalDate wateringDate = currentDate.plusDays(todayPlant.duration);
            todayPlant.lastDateUpdate();
            myList.setNextWaterTime(todayPlant, wateringDate);
            myList.wateringTimeForPrint(todayPlant);
        }
    }
}