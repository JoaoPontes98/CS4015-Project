import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;


public class ListScreen extends JDialog implements ActionListener{
    //Header
    private JPanel header;
    private JLabel dateLabel;
    private JButton prevButton;
    private JButton nextButton;

    //List
    private JPanel list;
    private JButton newTaskButton;

    //Footer
    private JPanel footer;
    private JButton browseButton;
    private JButton createButton;

    private int WIDTH = 300;
    private int HEIGHT = 600;

    private ArrayList<Item> mainList = new ArrayList<Item>();
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();

    public ListScreen(JFrame frame){
        super(frame);

        // Header init
        header = new JPanel();
        prevButton = new JButton("<");
        dateLabel = new JLabel("02/05/2022"); // Needs to be updated with current date
        nextButton = new JButton(">");
        header.setBackground(Color.red);
        header.add(prevButton);
        header.add(dateLabel);
        header.add(nextButton);

        // List init
        list = new JPanel(new FlowLayout(FlowLayout.LEFT));
        list.setBackground(Color.yellow);
        BasicTask task1 = new BasicTask("Feed Cat");
        BasicTask task2 = new BasicTask("Fake Death");
        BasicTask task3 = new BasicTask("Lick Door Knob");
        mainList.add(task1);
        mainList.add(task2);
        mainList.add(task3);
        //Make the checkBoxes
        for (Item task: mainList) {
            checkBoxes.add(new JCheckBox(task.display()));
        }
        //put the check boxes on the screen
        for (JCheckBox checkBox: checkBoxes){
            list.add(checkBox);
        }
        //Print the add new task button
        newTaskButton = new JButton("+");
        list .add(newTaskButton);

        // footer init
        footer = new JPanel();
        footer.setBackground(Color.blue);
        browseButton = new JButton("Browse");
        createButton = new JButton("Create New Todo List");
        footer.add(browseButton);
        footer.add(createButton);

        frame.setSize(WIDTH,HEIGHT);
        frame.add(header,BorderLayout.NORTH);
        frame.add(list,BorderLayout.CENTER);
        frame.add(footer,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
