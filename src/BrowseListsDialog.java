import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;

public class BrowseListsDialog extends JDialog implements ActionListener {
    private App app;
    private JLabel mainLabel;
    private JList<String> datesList;
    private JScrollPane scroller;
    private JButton okButton;
    private JButton cancelButton;

    public BrowseListsDialog(App app, JFrame frame) {
        super(frame);
        this.app = app;

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        

        ArrayList<List> mainList = app.getMainList();

        ArrayList<String> mainListAsStrings = new ArrayList<String>();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        for (List list : mainList) {
            String date = format.format(list.getDate());
            mainListAsStrings.add(date);
        }

        String[] arr = new String[mainListAsStrings.size()];
        mainListAsStrings.toArray(arr);

        mainLabel = new JLabel("Please select a date:", JLabel.CENTER);
        mainLabel.setAlignmentX(0.5f);

        datesList = new JList<String>(arr);

        scroller = new JScrollPane(datesList);
        scroller.setAlignmentX(0.5f);
        scroller.setPreferredSize(new Dimension(150, 150));
        
        okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(this);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
        inputPanel.add(mainLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        inputPanel.add(scroller);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(okButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(cancelButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPane.add(inputPanel);
        contentPane.add(buttonPanel);

        setContentPane(contentPane);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("OK")) {
            String dateString = datesList.getSelectedValue();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date;

            if (dateString != null) {
                try {
                    date = format.parse(dateString);
                    date.setHours(0);
                    date.setMinutes(0);
                    date.setSeconds(0);

                    for (List list : app.getMainList()) {
                        if (list.getDate().getYear() == date.getYear() &&
                            list.getDate().getMonth() == date.getMonth() &&
                            list.getDate().getDate() == date.getDate()) {
                            app.displayList(list);
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            datesList.setSelectedIndex(0);
            setVisible(false);
        }
        else if (event.getActionCommand().equals("Cancel")) {
            datesList.setSelectedIndex(0);
            setVisible(false);
        }

    }


}