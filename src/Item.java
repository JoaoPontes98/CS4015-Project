public interface Item {
    String display();

    boolean isComplete();

    void completeTask();

    void uncompleteTask();

    // Item clone();
}