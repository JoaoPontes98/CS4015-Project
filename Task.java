public abstract class Task implements Item {
    public abstract String display();

    public abstract void completeTask();

    public abstract void uncompleteTask();

    public abstract boolean isComplete();

    @Override
    public abstract Task clone();
}