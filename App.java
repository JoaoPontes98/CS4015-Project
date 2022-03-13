import javax.swing.*;

public class App {
    private JFrame mainFrame;

    public static void main(String[] args) {
        App app = new App();
    }

    public App() {
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

    public void displayList(List list) {
        //initialize new display list page for the given List
    }

    public void openCreateListDialog() {
        CreateListDialog dialog = new CreateListDialog(this, getMainFrame());
    }

    public void openBrowseListsDialog() {
        BrowseListsDialog dialog = new BrowseListsDialog(this, getMainFrame());
    }

    public void openCreateTaskDialog() {
        CreateTaskDialog dialog = new CreateTaskDialog(this, getMainFrame());
    }

    
}
