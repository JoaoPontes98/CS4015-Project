import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class CreateListDialog extends JDialog implements ActionListener {
    private App app;
    private JLabel mainLabel;
    private JTextField dateField;
    private JButton okButton;
    private JButton cancelButton;

    public CreateListDialog(App app, JFrame frame) {
        super(frame);
        this.app = app;

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        mainLabel = new JLabel("Please enter a date:", JLabel.CENTER);
        mainLabel.setAlignmentX(0.5f);

        dateField = new JTextField(10);
        dateField.setMaximumSize(new Dimension(150, 25));
        dateField.setAlignmentX(0.5f);

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
        inputPanel.add(dateField);
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
            String dateString = dateField.getText();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");

            if (dateString != null) {
                try {
                    Date date = format.parse(dateString);
                    date.setHours(0);
                    date.setMinutes(0);
                    date.setSeconds(0);

                    app.createNewList(date);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }

                dateField.setText("");
                setVisible(false);
            }
        }
        else if (event.getActionCommand().equals("Cancel")) {
            dateField.setText("");
            setVisible(false);
        }

    }


}