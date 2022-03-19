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
        Date date = new Date();
        date.setDate(date.getDate() + 1);
        List list = new List(date);
        BasicTask task1 = new BasicTask("Feed Cat");
        BasicTask task2 = new BasicTask("Feed Friend's Cat");
        BasicTask task3 = new BasicTask("Feed Mom's Cat");
        AppointmentDecorator task2dec = new AppointmentDecorator(task2, "12:00", "Friend's House");
        RecurringDecorator task3dec = new RecurringDecorator(app, task3, 5, "Daily");
        List sublist = new List("Sublist 1");
        List sublist2 = new List("Sublist 2");
        sublist2.addItem(task3dec);
        sublist.addItem(sublist2);
        sublist.addItem(task1);
        list.addItem(task1);
        list.addItem(task2dec);
        list.addItem(sublist);
        app.getMainList().add(list);
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

    public void openCreateTaskDialog(List list) {
        CreateTaskDialog dialog = new CreateTaskDialog(this, getMainFrame(), list);
    }

    public void createNewTask(List list, String description, boolean isRecurring, boolean isAppointment, int recurrenceNumber, 
                                String recurrenceType, String time, String place) {

        Task newTask = new BasicTask(description);

        if (isAppointment) {
            newTask = new AppointmentDecorator(newTask, time, place);
        }
        if (isRecurring) {
            newTask = new RecurringDecorator(this, newTask, recurrenceNumber, recurrenceType);
        }

        list.addItem(newTask);
        displayList(currentList);
    }

    public void createNewSublist(List list, String description) {
        List subList = new List(description);
        list.addItem(subList);
        displayList(currentList);
    }

    
}
