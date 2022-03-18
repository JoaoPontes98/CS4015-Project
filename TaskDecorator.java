public class TaskDecorator extends Task {
    protected Task task;

    public TaskDecorator(Task task) {
        this.task = task;
    }

    public String display() {
        return task.display();
    }

    public void completeTask() {
        task.completeTask();
    }

    public void uncompleteTask() {
        task.uncompleteTask();
    }

    public boolean isComplete() {
        return task.isComplete();
    }
}