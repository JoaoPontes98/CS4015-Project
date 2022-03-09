import java.util.Date;

public class AppointmentDecorator extends TaskDecorator {
    private Date time;
    private String location;

    public AppointmentDecorator(Task task) {
        super(task);
    }

    @Override
    public String display() {
        String str = super.task.display();
        str += "[" + location + "-" + time + "]";
        return str;
    }
}