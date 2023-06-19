import java.io.Serializable;
import java.time.*;

public class Plant implements Serializable{

    String name;
    LocalDateTime userWateringTime;

    public Plant(String name) {
        this.name = name;
    }

    public void setWateringTime(LocalDateTime userWateringTime) {
        this.userWateringTime = userWateringTime;
    }
}