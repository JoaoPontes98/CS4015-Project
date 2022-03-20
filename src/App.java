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
    }

    public App() {
        mainList = importData();
        mainFrame = new JFrame();

        Date today = new Date();
        List listToDisplay = null;

        for (List list : mainList) {
            if (list.getDate().getYear() == today.getYear() &&
                list.getDate().getMonth() == today.getMonth() &&
                list.getDate().getDate() == today.getDate()) {
                    listToDisplay = list;
                    break;
                }
        }
        if (listToDisplay == null) {
            listToDisplay = new List(today);
            mainList.add(listToDisplay);
        }

        displayList(listToDisplay);
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

    public void openBrowseListsDialog() {
        BrowseListsDialog dialog = new BrowseListsDialog(this, getMainFrame());
    }

    public void openCreateTaskDialog(List list) {
        CreateTaskDialog dialog = new CreateTaskDialog(this, getMainFrame(), list);
    }

    public void createNewList(Date date) {
        List list = new List(date);
        mainList.add(list);
        displayList(list);
    }

    public void createNewSublist(List list, String description) {
        List subList = new List(description);
        list.addItem(subList);
        displayList(currentList);
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

    private ArrayList<List> importData() {
        /* 
        This method is a placeholder. In the future we would implement it to
        import real data from whatever source we decide, such as json files stored
        somewhere or a database. So long as this method creates an ArrayList out of
        the data, the App doesn't care where the data comes from. For the purpose of this project, 
        we are creating the method but implementing it with testing data.
        */

        ArrayList<List> dataImported = new ArrayList<List>();

        Date date = new Date();
        List list = new List(date);
        BasicTask task1 = new BasicTask("Assignment Due");
        BasicTask task2 = new BasicTask("Statistics Midterm");
        BasicTask task3 = new BasicTask("Clean Dishes");
        BasicTask task4 = new BasicTask("Take out Trash");
        BasicTask task5 = new BasicTask("Meet Friend");
        BasicTask task6 = new BasicTask("Doctor's Appointment");
        BasicTask task7 = new BasicTask("Meet Mom");

        AppointmentDecorator task5AppDec = new AppointmentDecorator(task5, "12:00", "Coffee Shop");
        AppointmentDecorator task6AppDec = new AppointmentDecorator(task6, "3:00", "450 Hospital Street");
        AppointmentDecorator task7AppDec = new AppointmentDecorator(task7, "6:00", "200 Mom Street");

        RecurringDecorator task3RecDec = new RecurringDecorator(this, task3, 30, "Daily");
        RecurringDecorator task4RecDec = new RecurringDecorator(this, task4, 2, "Weekly");
        RecurringDecorator task7RecDec = new RecurringDecorator(this, task7AppDec, 10, "Weekly");

        List sublist = new List("Chores");
        List sublist2 = new List("Meetings");

        sublist.addItem(task3RecDec);
        sublist.addItem(task4RecDec);

        sublist2.addItem(task5AppDec);
        sublist2.addItem(task6AppDec);
        sublist2.addItem(task7RecDec);

        list.addItem(task1);
        list.addItem(task2);
        list.addItem(sublist);
        list.addItem(sublist2);

        dataImported.add(list);
        return dataImported;
    }
}
