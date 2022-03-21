import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateTaskDialog extends AppDialog {
    private JTextField descriptionField;
    private JCheckBox recurringCheckBox;
    private JCheckBox appointmentCheckBox;
    private JTextField timeField;
    private JTextField placeField;
    private JTextField recurrenceNumberField;
    private JComboBox<String> recurrenceTypeComboBox;
    private JPanel recurrence;
    private JPanel appointment;
    private List list;

    public CreateTaskDialog(App app, JFrame frame, List list) {
        super(frame, app);
        this.list = list;
    }

    protected JPanel createInputsPanel() {
        JPanel inputs = new JPanel();
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.PAGE_AXIS));
        inputs.setAlignmentX(0.0f);

        JLabel descriptionLabel = new JLabel("Please enter a decription:", JLabel.LEFT);
        descriptionLabel.setAlignmentX(0.0f);

        descriptionField = new JTextField(20);
        descriptionField.setMaximumSize(new Dimension(200, 20));
        descriptionField.setPreferredSize(new Dimension(200, 20));
        descriptionField.setAlignmentX(0.0f);

        recurringCheckBox = new JCheckBox("Recurring");
        recurringCheckBox.setAlignmentX(0.0f);
        recurringCheckBox.setActionCommand("Recurring");
        recurringCheckBox.addActionListener(this);

        appointmentCheckBox = new JCheckBox("Appointment");
        appointmentCheckBox.setAlignmentX(0.0f);
        appointmentCheckBox.setActionCommand("Appointment");
        appointmentCheckBox.addActionListener(this);

        inputs.add(descriptionLabel);
        inputs.add(Box.createRigidArea(new Dimension(0, 5)));
        inputs.add(descriptionField);
        inputs.add(Box.createRigidArea(new Dimension(0, 10)));
        inputs.add(recurringCheckBox);
        inputs.add(appointmentCheckBox);

        createRecurrencePanel();
        createAppointmentPanel();
        inputs.add(recurrence);
        inputs.add(appointment);

        inputs.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return inputs;
    }

    protected JPanel createButtonsPanel() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
        buttons.setAlignmentX(0.0f);

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

    private JPanel createAppointmentPanel() {
        appointment = new JPanel();
        appointment.setLayout(new BoxLayout(appointment, BoxLayout.PAGE_AXIS));
        appointment.setAlignmentX(0.0f);

        JLabel timeLabel = new JLabel("Enter the time:");
        timeLabel.setAlignmentX(0.0f);

        timeField = new JTextField(20);
        timeField.setAlignmentX(0.0f);
        timeField.setMaximumSize(new Dimension(200, 20));
        timeField.setPreferredSize(new Dimension(200, 20));

        JLabel placeLabel = new JLabel("Enter the place:");
        placeLabel.setAlignmentX(0.0f);

        placeField = new JTextField(20);
        placeField.setAlignmentX(0.0f);
        placeField.setMaximumSize(new Dimension(200, 20));
        placeField.setPreferredSize(new Dimension(200, 20));

        appointment.add(timeLabel);
        appointment.add(Box.createRigidArea(new Dimension(0, 5)));
        appointment.add(timeField);
        appointment.add(Box.createRigidArea(new Dimension(0, 10)));
        appointment.add(placeLabel);
        appointment.add(Box.createRigidArea(new Dimension(0, 5)));
        appointment.add(placeField);
        appointment.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        appointment.setVisible(false);

        return appointment;
    }

    private JPanel createRecurrencePanel() {
        recurrence = new JPanel();
        recurrence.setLayout(new BoxLayout(recurrence, BoxLayout.PAGE_AXIS));
        recurrence.setAlignmentX(0.0f);

        JLabel recurrenceNumberLabel = new JLabel("Enter the number of recurrences:");
        recurrenceNumberLabel.setAlignmentX(0.0f);

        recurrenceNumberField = new JTextField(20);
        recurrenceNumberField.setAlignmentX(0.0f);
        recurrenceNumberField.setMaximumSize(new Dimension(200, 20));
        recurrenceNumberField.setPreferredSize(new Dimension(200, 20));

        JLabel recurrenceTypeLabel = new JLabel("Select the type of recurrence:");

        String[] recurrenceTypes = {"Daily", "Weekly", "Biweekly", "Monthly"};
        recurrenceTypeComboBox = new JComboBox<String>(recurrenceTypes);
        recurrenceTypeComboBox.setMaximumSize(new Dimension(150, 25));
        recurrenceTypeComboBox.setPreferredSize(new Dimension(150, 25));
        recurrenceTypeComboBox.setAlignmentX(0.0f);

        recurrence.add(recurrenceTypeLabel);
        recurrence.add(Box.createRigidArea(new Dimension(0, 5)));
        recurrence.add(recurrenceTypeComboBox);
        recurrence.add(Box.createRigidArea(new Dimension(0, 10)));
        recurrence.add(recurrenceNumberLabel);
        recurrence.add(Box.createRigidArea(new Dimension(0, 5)));
        recurrence.add(recurrenceNumberField);
        recurrence.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        recurrence.setVisible(false);

        return recurrence;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            String description = descriptionField.getText();
            if (description.equals("")) {
                System.out.println("Error: Description must be filled out");
                return;
            }

            if (recurringCheckBox.isSelected() && recurrenceNumberField.getText().equals("")) {
                System.out.println("Error: Recurrence number must be filled out");
                return;
            }
            int recurrenceNumber = recurringCheckBox.isSelected() ? Integer.parseInt(recurrenceNumberField.getText()) : 0;
            String recurrenceType = recurringCheckBox.isSelected() ? (String) recurrenceTypeComboBox.getSelectedItem() : null;

            if (appointmentCheckBox.isSelected() && (timeField.getText().equals("")|| placeField.getText().equals(""))) {
                System.out.println("Error: Time and location must be filled out");
                return;
            }
            String time = appointmentCheckBox.isSelected() ? timeField.getText() : null;
            String place = appointmentCheckBox.isSelected() ? placeField.getText() : null;

            app.createNewTask(list, description, recurringCheckBox.isSelected(), appointmentCheckBox.isSelected(),
                                recurrenceNumber, recurrenceType, time, place);

            close();
        }
        else if (e.getActionCommand().equals("Cancel")) {
            close();
        }
        else if (e.getActionCommand().equals("Recurring")) {
            if (recurringCheckBox.isSelected()) {
                recurrence.setVisible(true);
                pack();
            }
            else {
                recurrence.setVisible(false);
                pack();
            }
        }
        else if (e.getActionCommand().equals("Appointment")) {
            if (appointmentCheckBox.isSelected()) {
                appointment.setVisible(true);
                pack();
            }
            else {
                appointment.setVisible(false);
                pack();
            }
        }

    }

    private void close() {
        setVisible(false);
        descriptionField.setText("");
        recurringCheckBox.setSelected(false);
        appointmentCheckBox.setSelected(false);
        timeField.setText("");
        placeField.setText("");
        recurrenceNumberField.setText("");
        recurrenceTypeComboBox.setSelectedIndex(0);
    }

}