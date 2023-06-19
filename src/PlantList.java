import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class PlantList{

    ArrayList<Plant> plantList = new ArrayList<Plant>();

    public void saveData(File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Plant myPlant : plantList) {
                oos.writeObject(myPlant);
            }
        }
    }

    public Plant getPlant(String nameOfPlant){
        for(Plant myPlant : plantList){
            if (myPlant.name.equalsIgnoreCase(nameOfPlant)) {
                return myPlant;
            }
        }
        return null;
    }

    public boolean nameIsDouble(String nameOfPlant) {
        for (Plant myPlant : plantList) {
            if (myPlant.name.equalsIgnoreCase(nameOfPlant)) {
                return true;
            }
        }
        return false;
    }

        public boolean setNextWaterTime(Plant Plant, LocalDate newTime) {
        LocalDate currentTime = LocalDate.now();
        if (newTime.isBefore(currentTime)) {
            System.out.println("Your watering time is in the past!");
            return false;
        }
        else{
            Plant.userWateringTime = newTime;
            return true;
        }
    }

    public void wateringTimeForPrint(Plant myPlant) {
        LocalDate currentDate = LocalDate.now();
        LocalDate userWateringTime = myPlant.userWateringTime;

        long days = ChronoUnit.DAYS.between(currentDate, userWateringTime);

        System.out.println("You should water \u001B[32m" + myPlant.name + "\u001B[0m in " + days + " days : " + myPlant.userWateringTime + "\n");
    }

    public void viewAllPlants() {
        if (isListEmpty())
        {
            System.out.println("There is no plants on your list yet");
            return;
        }
        System.out.println("\nYou have these plants on your list: \n");
        for (Plant myPlant : plantList) {
            System.out.print("\u001B[32m" + myPlant.name + "\u001B[0m - ");
            wateringTimeForPrint(myPlant);
        }
    }

    public boolean isListEmpty(){
        return plantList.size() < 1;
    }

    public void deletePlant(Plant plant){
        Scanner input = new Scanner(System.in);
        System.out.println("You sure you want to remove " + "\u001B[32m" + plant.name + "\u001B[0m" + " from the list? yes/no: ");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            plantList.remove(plant);
            System.out.println("\u001B[32m" + plant.name + "\u001B[0m" + " is removed");
        }
    }

    public void deleteAllPlants() {

        Scanner input = new Scanner(System.in);
        System.out.println("You sure you want to remove all plants from the list? yes/no: ");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            Iterator<Plant> iterator = plantList.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
            System.out.println("All plants have been deleted.");
        }
    }

    public boolean todayIsTime(){
        LocalDate currentDate = LocalDate.now();
        for(Plant myPlant : plantList){
            if (myPlant.userWateringTime.equals(currentDate)) {
                return true;
            }
        }
        return false;
    }

    public Plant todayIsPlant(){
        LocalDate currentDate = LocalDate.now();
        for(Plant myPlant : plantList){
            if (myPlant.userWateringTime.equals(currentDate)) {
                return myPlant;
            }
        }
        return null;
    }
}
