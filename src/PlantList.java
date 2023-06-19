import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;

public class PlantList{

    ArrayList<Plant> plantList = new ArrayList<Plant>();

    public void saveData(File file) throws IOException {
        if (isListEmpty()){
            System.out.println("Your plant list is empty. There is nothing to save");
            if (!file.delete()){
                System.out.println("Error occurred while deleting the file");
            }
            return;
        }
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

    public boolean setNextWaterTime(Plant Plant, LocalDateTime newTime) {
        LocalDateTime currentTime = LocalDateTime.now();
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
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime userWateringTime = myPlant.userWateringTime;

        Duration duration = Duration.between(currentDate, userWateringTime);
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        System.out.println("You should water \u001B[32m" + myPlant.name + "\u001B[0m in " + days + " days, " + hours + " hours, and " + minutes + " minutes.\n");
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
            System.out.println("Next watering time : " + myPlant.userWateringTime);
            wateringTimeForPrint(myPlant);
        }
    }

    public boolean isListEmpty(){
        return plantList.size() < 1;
    }

    public void deletePlant(Plant plant){
        plantList.remove(plant);
        System.out.println("\u001B[32m" + plant.name + "\u001B[0m" + " is removed");
    }

    public void deleteAllPlants() {
        Iterator<Plant> iterator = plantList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println("All plants have been deleted.");
    }

}
