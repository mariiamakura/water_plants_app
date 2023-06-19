import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;

public class MenuMethods {

    static void addNewPlant(Scanner inputScanner, PlantList myList) {
        System.out.println("Please enter plant name: ");
        String plantName = inputScanner.nextLine();
        String answer;

        //check double name
        if (myList.nameIsDouble(plantName)){
            System.out.println("Seems you already have \u001B[32m" + plantName + " \u001B[0mon your list!");
            System.out.println("Do you want to set another name for the plant? yes/no: ");
            answer = inputScanner.nextLine();
            if (answer.equalsIgnoreCase("yes")){
                addNewPlant(inputScanner, myList);
            }
            else {
                return;
            }
        }
        Plant newPlant = new Plant(plantName);
        myList.plantList.add(newPlant);

        System.out.println("\nCongrats! Now \u001B[32m" + myList.getPlant(plantName).name + " \u001B[0mis on your list!\n");
        System.out.println("Please set the watering time for \u001B[32m" + plantName + "\u001B[0m");
        setWaterTime(inputScanner, newPlant, myList);
    }

    static void setWaterTime(Scanner input, Plant newPlant, PlantList myList){
        System.out.println("In how many days do you want to water \u001B[32m" + newPlant.name + "\u001B[0m? Enter the number of days: ");
        if (input.hasNextInt()) {
            int userTime = input.nextInt();
            input.nextLine();

            LocalDate currentDate = LocalDate.now();
            LocalDate wateringDate = currentDate.plusDays(userTime);
            if (myList.setNextWaterTime(newPlant, wateringDate)) {
                myList.wateringTimeForPrint(newPlant);
            } else {
                System.out.println("Do you want to set watering time for \u001B[32m" + newPlant.name + "\u001B[0m again? yes/no: ");
                String answer = input.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    setWaterTime(input, newPlant, myList);
                }
            }
        }
        else {
            System.out.println("Please, enter the number next time :)");
            input.nextLine();
        }
    }
    static Plant askPlantName(Scanner inputScanner, PlantList myList)
    {
        System.out.println("Enter the plant name: ");
        String plantName = inputScanner.nextLine();

        if (myList.getPlant(plantName) == null){
            System.out.println("There is no plant with the name \u001B[32m" + plantName + "\u001B[0m\n");
            return null;
        }
        return myList.getPlant(plantName);
    }
}