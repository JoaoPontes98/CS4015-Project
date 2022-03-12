import java.util.ArrayList;
import java.util.Date;

public class List implements Item {
    private Date date;
    private ArrayList<Item> items;

    public List(Date date) {
        this.date = date;
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Date getDate() {
        return date;
    }

    public String display() {
        String str = "";

        for (Item item : items) {
            str += item.display();
        }

        return str;
    }
}