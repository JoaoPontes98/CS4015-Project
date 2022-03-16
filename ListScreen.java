import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;

public class ListScreen extends JPanel implements ActionListener {
    // Header
    private JPanel header;
    private JLabel dateLabel;
    private JButton prevButton;
    private JButton nextButton;

    // List
    private JPanel list;
    private JButton newTaskButton;

    // Footer
    private JPanel footer;
    private JButton browseButton;
    private JButton createButton;

    private App app;
    private List listToDisplay;

    private ArrayList<Item> mainList = new ArrayList<Item>();
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();

    public ListScreen(App app, JFrame frame, List listToDisplay) {
        this.app = app;
        this.listToDisplay = listToDisplay;
        app.setCurrentDisplay(this);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Header init
        header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.LINE_AXIS));
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String date = format.format(listToDisplay.getDate());
        dateLabel = new JLabel(date); // Needs to be updated with current date
        dateLabel.setAlignmentX(0.5f);
        header.add(dateLabel);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // List init
        list = new JPanel();
        list.setLayout(new BoxLayout(list, BoxLayout.PAGE_AXIS));
        BasicTask task1 = new BasicTask("Feed Cat");
        BasicTask task2 = new BasicTask("Fake Death");
        BasicTask task3 = new BasicTask("Lick Door Knob");
        mainList.add(task1);
        mainList.add(task2);
        mainList.add(task3);
        // Make the checkBoxes
        for (Item task : mainList) {
            checkBoxes.add(new JCheckBox(task.display()));
        }
        // put the check boxes on the screen
        for (JCheckBox checkBox : checkBoxes) {
            list.add(checkBox);
        }
        // Print the add new task button
        newTaskButton = new JButton("+");
        AddTaskMenu addTaskMenu = new AddTaskMenu(app, newTaskButton);
        list.add(newTaskButton);

        list.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        list.setAlignmentX(0.0f);

        // footer init
        footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.LINE_AXIS));
        browseButton = new JButton("Browse");
        browseButton.setActionCommand("Browse");
        browseButton.addActionListener(this);
        createButton = new JButton("Create New");
        createButton.setActionCommand("Create New");
        createButton.addActionListener(this);
        footer.add(browseButton);
        footer.add(Box.createRigidArea(new Dimension(20, 0)));
        footer.add(createButton);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel listHolder = new JPanel();
        listHolder.setLayout(new BoxLayout(listHolder, BoxLayout.LINE_AXIS));

        listHolder.add(list);
        listHolder.add(Box.createHorizontalGlue());
        listHolder.add(Box.createRigidArea(new Dimension(20, 0)));

        add(header);
        add(listHolder);
        add(Box.createVerticalGlue());
        add(footer);

        frame.add(this);
        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Browse")) {
            app.openBrowseListsDialog();
        } else if (e.getActionCommand().equals("Create New")) {
            app.openCreateListDialog();
        }
    }
}
