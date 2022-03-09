import java.util.ArrayList;
import java.util.Date;

public class List implements Item {
    private Date date;
    private String title;
    private String description;
    private ArrayList<Item> items;

    public List(Date date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String display() {
        String str = "";

        for (Item item : items) {
            str += item.display();
        }

        return str;
    }
}