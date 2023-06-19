import java.io.Serializable;
import java.time.*;

public class Plant implements Serializable{

    String name;
    LocalDate userWateringTime;

    LocalDate lastWateringTime;

    int duration;

    public Plant(String name) {
        this.name = name;
    }

    public void setWateringTime(LocalDate userWateringTime) {

        this.userWateringTime = userWateringTime;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setLastWatering(LocalDate lastWateringTime) {

        this.lastWateringTime = lastWateringTime;
    }

    public void lastDateUpdate()
    {
        this.lastWateringTime = userWateringTime;
    }
}