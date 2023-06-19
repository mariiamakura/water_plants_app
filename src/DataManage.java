import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class DataManage {
    static File createFile (String userName) throws IOException {

        Path file = Path.of("data/", userName + ".txt");
        return file.toFile();
    }

    static File findFile(Scanner inputScanner, PlantList myList, String name) throws IOException, ClassNotFoundException {
        String targetFileName = name + ".txt";

        File directory = new File("data/");
        File[] files = directory.listFiles();

        if (files != null){
            for (File file : files){
                if (file.getName().equalsIgnoreCase(targetFileName)){
                    System.out.println("I guess, I found you data, " + name);
                    loadData(file, myList);
                    return file;
                }
            }
        }
        return null;
    }

    static void loadData(File file, PlantList myList) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        try {
            while (true){
                Plant plant = (Plant)ois.readObject();
                myList.plantList.add(plant);
            }
        } catch (EOFException e){
            System.out.println("All data has been loaded");
        } finally {
            ois.close();
        }
    }
}