public class TaskDecorator extends Task {
    protected Task task;

    public TaskDecorator(Task task) {
        this.task = task;
    }

    public TaskDecorator(TaskDecorator tasDec) {
        this.task = tasDec.getTask().clone();
    }

    public Task getTask() {
        return task;
    }

    public String display() {
        return task.display();
    }

    public boolean isComplete() {
        return task.isComplete();
    }

    public void completeTask() {
        task.completeTask();
    }

    public void uncompleteTask() {
        task.uncompleteTask();
    }

    @Override
    public TaskDecorator clone() {
        return new TaskDecorator(this);
    }
}