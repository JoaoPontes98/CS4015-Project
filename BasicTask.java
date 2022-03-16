public class BasicTask extends Task {
    private String description;
    private boolean done;

    public BasicTask(String description) {
        this.description = description;
        this.done = false;
    }

    @Override
    public String display() {
        return description;
    }

    @Override
    public void completeTask() {
        this.done = !done;
    }
}