import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
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

    //Footer
    private JPanel footer;
    private JButton browseButton;
    private JButton createButton;

    private int WIDTH = 300;
    private int HEIGHT = 600;

    public ListScreen(JFrame frame){
        super(frame);

        // Header init
        header = new JPanel();
        prevButton = new JButton("<");
        dateLabel = new JLabel("02/05/2022");
        nextButton = new JButton(">");
        header.setBackground(Color.red);
        header.add(prevButton);
        header.add(dateLabel);
        header.add(nextButton);

        // List init
        list = new JPanel();
        list.setBackground(Color.yellow);

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
