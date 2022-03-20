import java.util.Date;
import java.util.ArrayList;

public class RecurringDecorator extends TaskDecorator {
    private int recurrence;
    private String type;
    private App app;
    private ArrayList<List> recurred;

    public RecurringDecorator( Task task, int recurrence, String type) {
        super(task);
        this.app = App.getInstance();
        this.recurrence = recurrence;
        this.type = type;
        this.recurred = new ArrayList<List>();
    }

    public RecurringDecorator(RecurringDecorator recDec) {
        super(recDec.getTask().clone());
        this.app = App.getInstance();
        this.recurrence = recDec.getRecurrence();
        this.type = recDec.getType();
        this.recurred = new ArrayList<List>();
    }

    public int getRecurrence() {
        return recurrence;
    }

    public String getType() {
        return type;
    }

    public App getApp() {
        return app;
    }

    public void recMinusOne() {
        this.recurrence = recurrence - 1;
    }

    @Override
    public void completeTask() {
        task.completeTask();
        if (recurrence > 0) {
            Date listDate = app.getCurrentList().getDate();
            Date currentDate = (Date) listDate.clone();

            switch (type) {
                case "Daily":
                    currentDate.setDate(currentDate.getDate() + 1);
                    break;
                case "Weekly":
                    currentDate.setDate(currentDate.getDate() + 7);
                    break;
                case "Bi-Weekly":
                    currentDate.setDate(currentDate.getDate() + 14);
                    break;
                case "Monthly":
                    currentDate.setMonth(currentDate.getMonth() + 1);
                    break;
            }
            RecurringDecorator rd = (RecurringDecorator) this.clone();
            rd.recMinusOne();
            rd.uncompleteTask();

            ArrayList<List> allLists = app.getMainList();
            for (List list : allLists) {
                if (list.getDate().getYear() == currentDate.getYear() &&
                        list.getDate().getMonth() == currentDate.getMonth() &&
                        list.getDate().getDate() == currentDate.getDate()) {
                    if (!recurred.contains(list)) {
                        list.addItem(rd);
                        recurred.add(list);
                    }
                    return;
                }
            }

            List newList = new List(currentDate);
            newList.addItem(rd);
            allLists.add(newList);
            recurred.add(newList);
        }
    }

    @Override
    public String display() {
        String str = super.task.display();
        str += " [ " + type + " - " + recurrence + " left ] ";
        return str;
    }

    @Override
    public RecurringDecorator clone() {
        return new RecurringDecorator(this);
    }
}