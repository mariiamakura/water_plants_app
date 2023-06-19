import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.*;

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
        System.out.println("Do you want to set watering time for \u001B[32m" + plantName + "\u001B[0m? yes/no: ");
        answer = inputScanner.nextLine();
        if (answer.equalsIgnoreCase("yes")){
            setWaterTime(inputScanner, newPlant, myList);
        }
    }

    static void setWaterTime(Scanner inputScanner, Plant newPlant, PlantList myList) {
        System.out.println("Enter watering time in format yyyy mm dd hh:mm: ");
        String userTime = inputScanner.nextLine();
        DateTimeFormatter userWateringPlant = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm"); //formatting variable
        LocalDateTime timeOfWatering = LocalDateTime.parse(userTime, userWateringPlant); //already formatted variable

        if (myList.setNextWaterTime(newPlant, timeOfWatering)){
            myList.wateringTimeForPrint(newPlant);
        }
        else {
            System.out.println("Do you want to set watering time for \u001B[32m" + newPlant.name + "\u001B[0m again? yes/no: ");
            String answer = inputScanner.nextLine();
            if (answer.equalsIgnoreCase("yes")){
                setWaterTime(inputScanner, newPlant, myList);
            }
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