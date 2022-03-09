import java.util.ArrayList;
import java.util.Date;

public class List {
    private Date date;
    private String title;
    private String description;
    private ArrayList<Task> tasks;

    public List(Date date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}