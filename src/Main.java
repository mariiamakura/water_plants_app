import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        PlantList myList = new PlantList();
        Plant plant;
        File file = null;
        String answer;

        System.out.println("\nHi! This little program will help you to water \u001B[32myour plants\u001B[0m on time :)");

        System.out.println("Can you tell me your name? Enter: ");
        String userName = input.nextLine();

        file = DataManage.findFile(input, myList, userName);
        if (file == null) {
            System.out.println("I could not find your account. Do you want to create one? yes/no: ");
            answer = input.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                file = DataManage.createFile(userName);
                System.out.println("Account created");
            } else {
                System.out.println("Ok, see you next time!");
                return;
            }
        } else {
            myList.viewAllPlants();
        }

        DataManage.popUpMessage(myList);
        while (true) {
            System.out.println("==============================================");
            System.out.println("You can do the following actions. Please, enter the number to start it:");
            System.out.println("1: add new plant");
            System.out.println("2: view all plants");
            System.out.println("3: change watering time");
            System.out.println("4: remove a plant");
            System.out.println("5: remove all plants");
            System.out.println("0: exit");
            System.out.println("Enter your choice: ");

            String user_choice = input.nextLine();

            switch (user_choice) {
                case "1" -> {
                    MenuMethods.addNewPlant(input, myList);
                }
                case "2" -> myList.viewAllPlants();
                case "3" -> {
                    plant = MenuMethods.askPlantName(input, myList);
                    if (plant != null) {
                        MenuMethods.setWaterTime(input, plant, myList);
                    }
                }
                case "4" -> {
                    plant = MenuMethods.askPlantName(input, myList);
                    if (plant != null) {
                        myList.deletePlant(plant);
                    }
                }
                case "5" -> {
                    if (myList.isListEmpty()) {
                        System.out.println("The is no plants on your list");
                    } else {
                        myList.deleteAllPlants();
                    }
                }
                case "0" -> {
                    System.out.println("Thanks for using this program. See you soon! :)");
                }
            }
            myList.saveData(file);
        }
    }
}
