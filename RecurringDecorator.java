public class RecurringDecorator extends TaskDecorator {
    private int recurrence;
    private String type;

    public RecurringDecorator(Task task) {
        super(task);
    }

    @Override
    public void completeTask() {
        //recurring functions
        super.task.completeTask();
    }

}