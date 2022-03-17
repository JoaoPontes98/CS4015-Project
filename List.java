import java.util.ArrayList;
import java.util.Date;

public class List implements Item {
    private Date date;
    private String description;
    private ArrayList<Item> items;

    public List(Date date) {
        this.date = date;
        this.description = null;
        this.items = new ArrayList<Item>();
    }

    public List(String description) {
        this.description = description;
        this.date = null;
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String display() {
        return description;
    }

    public boolean isComplete() {
        for (Item item : items) {
            if (!item.isComplete()) {
                return false;
            }
        }

        return true;
    }

    public void completeTask() {
        for (Item item: items) {
            item.completeTask();
        }
    }

    public void uncompleteTask() {
        for (Item item: items) {
            item.uncompleteTask();
        }
    }
}