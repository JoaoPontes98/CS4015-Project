public interface Item {
    String display();

    boolean isComplete();

    void completeTask();

    void uncompleteTask();
}