import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class App {
    private JFrame mainFrame;
    private ArrayList<List> mainList;
    private List currentList;
    private JPanel currentDisplay;
    
    public static void main(String[] args) {
        App app = new App();
        List list = new List(new Date());
        app.displayList(list);
    }

    public App() {
        mainList = new ArrayList<List>();
        mainFrame = new JFrame();

        Date currentDate = new Date();
        currentDate.setHours(0);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);

        createNewList(currentDate);
        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public ArrayList<List> getMainList() {
        return mainList;
    }

    public List getCurrentList() {
        return currentList;
    }

    public void setCurrentListItems(ArrayList<Item> items) {
        for (Item item: items) {
            this.currentList.addItem(item);
        }
    }

    public void setCurrentDisplay(JPanel panel) {
        this.currentDisplay = panel;
    }

    public void displayList(List list) {
        if (currentDisplay != null) {
            currentDisplay.setVisible(false);
        }
        currentList = list;
        currentDisplay = new ListScreen(this, getMainFrame(), list);
    }

    public void openCreateListDialog() {
        CreateListDialog dialog = new CreateListDialog(this, getMainFrame());
    }

    public void createNewList(Date date) {
        List list = new List(date);
        mainList.add(list);
        displayList(list);
    }

    public void openBrowseListsDialog() {
        BrowseListsDialog dialog = new BrowseListsDialog(this, getMainFrame());
    }

    public void openCreateTaskDialog() {
        CreateTaskDialog dialog = new CreateTaskDialog(this, getMainFrame());
    }

    public void createNewTask(String description, boolean isRecurring, boolean isAppointment, int recurrenceNumber, 
                                String recurrenceType, String time, String place) {

        Task newTask = new BasicTask(description);

        if (isAppointment) {
            newTask = new AppointmentDecorator(newTask, time, place);
        }
        if (isRecurring) {
            newTask = new RecurringDecorator(this, newTask, recurrenceNumber, recurrenceType);
        }

        currentList.addItem(newTask);
        displayList(currentList);
    }

    public void createNewSublist(String description) {
        List list = new List(description);
        currentList.addItem(list);
        displayList(currentList);
    }

    
}
