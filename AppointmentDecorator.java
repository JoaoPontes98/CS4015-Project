import java.util.Date;

public class AppointmentDecorator extends TaskDecorator {
    private String time;
    private String location;

    public AppointmentDecorator(Task task, String time, String location) {
        super(task);
        this.time = time;
        this.location = location;
    }

    public AppointmentDecorator(AppointmentDecorator appDec) {
        super(appDec.getTask().clone());
        this.time = appDec.getTime();
        this.location = appDec.getLocation();
    }

    @Override
    public String display() {
        String str = super.task.display();
        str += " [ " + location + " - " + time + " ] ";
        return str;
    }

    @Override
    public AppointmentDecorator clone() {
        return new AppointmentDecorator(this);
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }
}