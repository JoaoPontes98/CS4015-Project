import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class App {
    private JFrame mainFrame;
    private ArrayList<List> mainList;
    private List currentList;

    public static void main(String[] args) {
        App app = new App();
    }

    public App() {
        mainList = new ArrayList<List>();
        mainFrame = new JFrame();
        //check list of Lists
        //if list exists for today's date, display list
        //otherwise create new list and display it
        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public ArrayList<List> getMainList() {
        return mainList;
    }

    public void displayList(List list) {
        currentList = list;
        //DisplayListPage page = new DisplayListPage(list);
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
            newTask = new RecurringDecorator(newTask, recurrenceNumber, recurrenceType);
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
