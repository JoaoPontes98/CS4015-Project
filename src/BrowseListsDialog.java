import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;

public class BrowseListsDialog extends JDialog implements ActionListener {
    private App app;
    private JList<String> datesList;

    public BrowseListsDialog(App app, JFrame frame) {
        super(frame);
        this.app = app;

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        JPanel inputs = createInputsPanel();
        JPanel buttons = createButtonsPanel();

        contentPane.add(inputs);
        contentPane.add(buttons);

        setContentPane(contentPane);
        pack();
        setVisible(true);
    }

    private JPanel createInputsPanel() {
        JPanel inputs = new JPanel();
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.PAGE_AXIS));

        ArrayList<String> mainListAsStrings = new ArrayList<String>();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        for (List list : app.getMainList()) {
            String date = format.format(list.getDate());
            mainListAsStrings.add(date);
        }

        String[] arr = new String[mainListAsStrings.size()];
        mainListAsStrings.toArray(arr);

        JLabel mainLabel = new JLabel("Please select a date:", JLabel.CENTER);
        mainLabel.setAlignmentX(0.5f);

        datesList = new JList<String>(arr);

        JScrollPane scroller = new JScrollPane(datesList);
        scroller.setAlignmentX(0.5f);
        scroller.setPreferredSize(new Dimension(150, 150));

        inputs.add(mainLabel);
        inputs.add(Box.createRigidArea(new Dimension(0, 10)));
        inputs.add(scroller);
        inputs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return inputs;
    }

    private JPanel createButtonsPanel() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(this);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(this);

        buttons.add(okButton);
        buttons.add(Box.createRigidArea(new Dimension(20, 0)));
        buttons.add(cancelButton);
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return buttons;
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("OK")) {
            String dateString = datesList.getSelectedValue();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date;

            if (dateString != null) {
                try {
                    date = format.parse(dateString);

                    for (List list : app.getMainList()) {
                        if (list.getDate().getYear() == date.getYear() &&
                            list.getDate().getMonth() == date.getMonth() &&
                            list.getDate().getDate() == date.getDate()) {
                            app.displayList(list);
                            break;
                        }
                    }
                }
                catch (ParseException e) {
                    System.out.println("Error: Could not parse date.");
                }
            }

            close();
        }
        else if (event.getActionCommand().equals("Cancel")) {
            close();
        }
    }

    private void close() {
        datesList.setSelectedIndex(0);
        setVisible(false);
    }
}