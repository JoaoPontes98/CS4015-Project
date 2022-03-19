import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class CreateListDialog extends AppDialog {
    private JTextField dateField;

    public CreateListDialog(App app, JFrame frame) {
        super(frame, app);
    }

    protected JPanel createInputsPanel() {
        JPanel inputs = new JPanel();
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.PAGE_AXIS));

        JLabel dateLabel = new JLabel("Please enter a date:", JLabel.CENTER);
        dateLabel.setAlignmentX(0.5f);

        dateField = new JTextField(10);
        dateField.setMaximumSize(new Dimension(150, 25));
        dateField.setAlignmentX(0.5f);

        inputs.add(dateLabel);
        inputs.add(Box.createRigidArea(new Dimension(0, 10)));
        inputs.add(dateField);
        inputs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return inputs;
    }

    protected JPanel createButtonsPanel() {
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
            String dateString = dateField.getText();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

            if (dateString != null) {
                try {
                    Date date = format.parse(dateString);
                    app.createNewList(date);
                }
                catch (ParseException e) {
                    System.out.println("Error: Could not parse date.");
                }

                close();
            }
        }
        else if (event.getActionCommand().equals("Cancel")) {
            close();
        }

    }

    private void close() {
        dateField.setText("");
        setVisible(false);
    }


}