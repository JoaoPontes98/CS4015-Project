public class Task implements Item {

    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String display() {
        String str = done ? "[x] " : "[ ] ";
        str += description;
        str += "\n";

        return str;
    }
}