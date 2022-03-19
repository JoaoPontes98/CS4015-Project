public abstract class Task implements Item {
    public abstract String display();

    public abstract boolean isComplete();

    public abstract void completeTask();

    public abstract void uncompleteTask();

    public abstract Task clone();
}