import java.util.Date;
import java.util.ArrayList;

public class RecurringDecorator extends TaskDecorator {
    private int recurrence;
    private String type;
    private App app;

    public RecurringDecorator(App app, Task task, int recurrence, String type) {
        super(task);
        this.app = app;
        this.recurrence = recurrence;
        this.type = type;
    }

    @Override
    public void completeTask() {
        super.task.completeTask();
        if (recurrence > 0) {
            recurrence -= 1;
            Date currentDate = app.getCurrentList().getDate();

            switch(type) {
                case "Daily":
                    currentDate.setDate(currentDate.getDate() + 1);
                case "Weekly":
                    currentDate.setDate(currentDate.getDate() + 7);
                case "Bi-Weekly":
                    currentDate.setDate(currentDate.getDate() + 14);
                case "Monthly":
                    currentDate.setMonth(currentDate.getMonth() + 1);
            }

            ArrayList<List> allLists = app.getMainList();

            for (List list : allLists) {
                if (list.getDate().getYear() == currentDate.getYear() &&
                    list.getDate().getMonth() == currentDate.getMonth() &&
                    list.getDate().getDate() == currentDate.getDate()) {
                    list.addItem(this);
                    return;
                }
            }

            List newList = new List(currentDate);
            newList.addItem(this);
            allLists.add(newList);
        }
    }

    @Override
    public String display() {
        String str = super.task.display();
        str += " [ " + type + " - " + recurrence + " left ] ";
        return str;
    }

}