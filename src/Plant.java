import java.io.Serializable;
import java.time.*;

public class Plant implements Serializable{

    String name;
    LocalDate userWateringTime;

    public Plant(String name) {
        this.name = name;
    }

    public void setWateringTime(LocalDate userWateringTime) {
        this.userWateringTime = userWateringTime;
    }
}