public class RecurringDecorator extends TaskDecorator {
    private int recurrence;
    private String type;

    public RecurringDecorator(Task task, int recurrence, String type) {
        super(task);
        this.recurrence = recurrence;
        this.type = type;
    }

    @Override
    public void completeTask() {
        //recurring functions
        super.task.completeTask();
    }

}