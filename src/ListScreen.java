import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;

public class ListScreen extends JPanel implements ActionListener {
    private App app;
    private List listToDisplay;

    private ArrayList<Item> mainList = new ArrayList<Item>();
    private ArrayList<JComponent> checkBoxes = new ArrayList<JComponent>();

    public ListScreen(JFrame frame, List listToDisplay) {
        this.app = App.getInstance();
        this.listToDisplay = listToDisplay;
        app.setCurrentDisplay(this);
        setLayout(new BorderLayout());

        JPanel header = createHeader();
        JPanel contents = createContents();
        JPanel footer = createFooter();


        add(header, BorderLayout.PAGE_START);
        add(contents, BorderLayout.LINE_START);
        add(footer, BorderLayout.PAGE_END);

        frame.add(this);
        frame.pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Browse")) {
            app.openBrowseListsDialog();
        } else if (e.getActionCommand().equals("Create New")) {
            app.openCreateListDialog();
        }
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String dateAsString = format.format(listToDisplay.getDate());

        JLabel dateLabel = new JLabel(dateAsString);
        dateLabel.setAlignmentX(0.5f);

        header.add(dateLabel);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return header;
    }

    private JPanel createContents() {
        JPanel contents = new JPanel();
        contents.setLayout(new BoxLayout(contents, BoxLayout.PAGE_AXIS));

        for (Item task : listToDisplay.getItems()) {
            if (task instanceof Task) {
                contents.add(new TaskCheckBox(task));
            }
            else if (task instanceof List) {
                contents.add(new SubListPanel((List) task, null, app));
            }
        }

        contents.add(new PlusButton(listToDisplay, app));
        contents.add(Box.createRigidArea(new Dimension(0, 10)));

        contents.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contents.setAlignmentX(0.0f);

        return contents;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel();

        JButton browseButton = new JButton("Browse");
        browseButton.setActionCommand("Browse");
        browseButton.addActionListener(this);

        JButton createButton = new JButton("Create New");
        createButton.setActionCommand("Create New");
        createButton.addActionListener(this);

        footer.add(browseButton);
        footer.add(Box.createRigidArea(new Dimension(20, 0)));
        footer.add(createButton);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return footer;
    }

    private class TaskCheckBox extends JCheckBox implements ActionListener {
        private Item task;

        public TaskCheckBox(Item task) {
            super(task.display());
            this.task = task;

            if (task.isComplete()) {
                setSelected(true);
            }

            setActionCommand("check");
            addActionListener(this);
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

    private class SubListPanel extends JPanel {
        public SubListPanel(List list, TaskCheckBox parentList, App app) {
            super();
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            TaskCheckBox desc = new TaskCheckBox(list);
            add(desc);

            if (parentList != null) {
                parentList.addActionListener(desc);
            }

            JPanel tasks = new JPanel();
            tasks.setLayout(new BoxLayout(tasks, BoxLayout.PAGE_AXIS));
            tasks.setAlignmentX(0.0f);

            JPanel tasksHolder = new JPanel();
            tasksHolder.setLayout(new BoxLayout(tasksHolder, BoxLayout.LINE_AXIS));
            tasksHolder.add(Box.createRigidArea(new Dimension(25, 0)));
            tasksHolder.setAlignmentX(0.0f);
            tasksHolder.add(tasks);
            
            add(tasksHolder);


            for (Item task : list.getItems()) {
                if (task instanceof Task) {
                    TaskCheckBox checkBox = new TaskCheckBox(task);
                    tasks.add(checkBox);
                    desc.addActionListener(checkBox);

                    if (parentList != null) {
                        parentList.addActionListener(checkBox);
                    }
                }
                else if (task instanceof List) {
                    JPanel sublistHolder = new JPanel();
                    tasks.add(new SubListPanel((List)task, desc, app));
                }
            }

            tasks.add(new PlusButton(list, app));
            add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

    private class PlusButton extends JButton {
        List list;

        public PlusButton(List list, App app) {
            super("Add");
            this.list = list;
            AddTaskMenu addTaskMenu = new AddTaskMenu( this, list);

            Font font = new Font("Arial", Font.PLAIN, 8);
            setFont(font);
            setMinimumSize(new Dimension(20, 20));
        }
    }

}
