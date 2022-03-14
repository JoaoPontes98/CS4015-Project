import java.util.Date;

public class AppointmentDecorator extends TaskDecorator {
    private String time;
    private String location;

    public AppointmentDecorator(Task task, String time, String location) {
        super(task);
        this.time = time;
        this.location = location;
    }

    @Override
    public String display() {
        String str = super.task.display();
        str += "[" + location + "-" + time + "]";
        return str;
    }
}