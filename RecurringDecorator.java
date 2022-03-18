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

    public RecurringDecorator(RecurringDecorator recDec) {
        super(recDec.getTask());
        this.app = recDec.getApp();
        this.recurrence = recDec.getRecurrence();
        this.type = recDec.getType();
    }

    @Override
    public void completeTask() {
        super.task.completeTask();
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
            // System.out.println("This Recurrence = " + this.getRecurrence());
            RecurringDecorator rd = (RecurringDecorator) this.clone();
            rd.RecMinusOne();
            // System.out.println("Recurrence = " + rd.getRecurrence());
            rd.uncompleteTask();

            ArrayList<List> allLists = app.getMainList();
            for (List list : allLists) {
                if (list.getDate().getYear() == currentDate.getYear() &&
                        list.getDate().getMonth() == currentDate.getMonth() &&
                        list.getDate().getDate() == currentDate.getDate()) {
                    list.addItem(rd);
                    return;
                }
            }

            List newList = new List(currentDate);
            newList.addItem(rd);
            allLists.add(newList);
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

    public int getRecurrence() {
        return recurrence;
    }

    public String getType() {
        return type;
    }

    public App getApp() {
        return app;
    }

    public void RecMinusOne() {
        this.recurrence = recurrence - 1;
    }
}