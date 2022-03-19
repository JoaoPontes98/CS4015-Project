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
    private ArrayList<JComponent> checkBoxes = new ArrayList<JComponent>();

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
        dateLabel = new JLabel(date);
        dateLabel.setAlignmentX(0.5f);
        header.add(dateLabel);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // List init
        list = new JPanel();
        list.setLayout(new BoxLayout(list, BoxLayout.PAGE_AXIS));
        // Make the checkBoxes
        for (Item task : listToDisplay.getItems()) {
            if (task instanceof Task) {
                list.add(new TaskCheckBox((Task)task));
            }
            else if (task instanceof List) {
                JPanel sublistHolder = new JPanel();
                sublistHolder.setLayout(new BoxLayout(sublistHolder, BoxLayout.LINE_AXIS));
                sublistHolder.add(Box.createRigidArea(new Dimension(25, 0)));
                sublistHolder.add(new SubListPanel((List)task, null, app));
                sublistHolder.setAlignmentX(0.0f);
                list.add(sublistHolder);
            }
        }

        // Print the add new task button
        list.add(new PlusButton(listToDisplay, app));
        list.add(Box.createRigidArea(new Dimension(0, 10)));

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

    class TaskCheckBox extends JCheckBox implements ActionListener {
        private Item task;

        public TaskCheckBox(Item task) {
            super(task.display());
            this.task = task;
            setActionCommand("check");
            addActionListener(this);

            if (task.isComplete()) {
                setSelected(true);
            }
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("check")) {
                JCheckBox source = (JCheckBox) e.getSource();
                if (source.isSelected()) {
                    setSelected(true);
                    task.completeTask();
                }
                else {
                    setSelected(false);
                    task.uncompleteTask();
                }
            }
        }
    }

    class SubListPanel extends JPanel {
        public SubListPanel(List list, TaskCheckBox parentList, App app) {
            super();
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            TaskCheckBox desc = new TaskCheckBox(list);
            add(desc);
            if (parentList != null) {
                parentList.addActionListener(desc);
            }

            for (Item task : list.getItems()) {
                if (task instanceof Task) {
                    TaskCheckBox checkBox = new TaskCheckBox(task);
                    add(checkBox);
                    desc.addActionListener(checkBox);
                    if (parentList != null) {
                        parentList.addActionListener(checkBox);
                    }
                }
                else if (task instanceof List) {
                    JPanel sublistHolder = new JPanel();
                    sublistHolder.setLayout(new BoxLayout(sublistHolder, BoxLayout.LINE_AXIS));
                    sublistHolder.add(Box.createRigidArea(new Dimension(25, 0)));
                    sublistHolder.add(new SubListPanel((List)task, desc, app));
                    sublistHolder.setAlignmentX(0.0f);
                    add(sublistHolder);
                }
            }
            add(new PlusButton(list, app));
            add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    class PlusButton extends JButton {
        List list;

        public PlusButton(List list, App app) {
            super("Add");
            this.list = list;
            AddTaskMenu addTaskMenu = new AddTaskMenu(app, this, list); 
            Font font = new Font("Arial", Font.PLAIN, 8);
            setFont(font);
            setPreferredSize(new Dimension(20, 20));
        }
    }

}
