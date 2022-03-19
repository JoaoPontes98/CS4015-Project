import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateTaskDialog extends JDialog implements ActionListener {
    private App app;
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    private JCheckBox recurringCheckBox;
    private JCheckBox appointmentCheckBox;
    private JLabel timeLabel;
    private JTextField timeField;
    private JLabel placeLabel;
    private JTextField placeField;
    private JLabel recurrenceNumberLabel;
    private JTextField recurrenceNumberField;
    private JLabel recurrenceTypeLabel;
    private JComboBox<String> recurrenceTypeComboBox;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel recurrencePanel;
    private JPanel appointmentPanel;
    private List list;

    public CreateTaskDialog(App app, JFrame frame, List list) {
        super(frame);
        this.app = app;
        this.list = list;

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        descriptionLabel = new JLabel("Please enter a decription:", JLabel.LEFT);
        descriptionLabel.setAlignmentX(0.0f);

        descriptionField = new JTextField(20);
        descriptionField.setMaximumSize(new Dimension(200, 20));
        descriptionField.setPreferredSize(new Dimension(200, 20));
        descriptionField.setAlignmentX(0.0f);

        recurringCheckBox = new JCheckBox("Recurring Task");
        recurringCheckBox.setAlignmentX(0.0f);
        recurringCheckBox.setActionCommand("recurring");
        recurringCheckBox.addActionListener(this);

        appointmentCheckBox = new JCheckBox("Appointment");
        appointmentCheckBox.setAlignmentX(0.0f);
        appointmentCheckBox.setActionCommand("appointment");
        appointmentCheckBox.addActionListener(this);

        timeLabel = new JLabel("Enter the time:");
        timeLabel.setAlignmentX(0.0f);
        timeField = new JTextField(20);
        timeField.setAlignmentX(0.0f);
        timeField.setMaximumSize(new Dimension(200, 20));
        timeField.setPreferredSize(new Dimension(200, 20));

        placeLabel = new JLabel("Enter the place:");
        placeLabel.setAlignmentX(0.0f);
        placeField = new JTextField(20);
        placeField.setAlignmentX(0.0f);
        placeField.setMaximumSize(new Dimension(200, 20));
        placeField.setPreferredSize(new Dimension(200, 20));

        recurrenceNumberLabel = new JLabel("Enter the number of recurrences:");
        recurrenceNumberLabel.setAlignmentX(0.0f);
        recurrenceNumberField = new JTextField(20);
        recurrenceNumberField.setAlignmentX(0.0f);
        recurrenceNumberField.setMaximumSize(new Dimension(200, 20));
        recurrenceNumberField.setPreferredSize(new Dimension(200, 20));

        recurrenceTypeLabel = new JLabel("Select the type of recurrence:");

        String[] recurrenceTypes = {"Daily", "Weekly", "Biweekly", "Monthly"};
        recurrenceTypeComboBox = new JComboBox<String>(recurrenceTypes);
        recurrenceTypeComboBox.setMaximumSize(new Dimension(150, 25));
        recurrenceTypeComboBox.setPreferredSize(new Dimension(150, 25));
        recurrenceTypeComboBox.setAlignmentX(0.0f);


        okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(this);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(new BoxLayout(basicPanel, BoxLayout.PAGE_AXIS));
        basicPanel.setAlignmentX(0.0f);
        basicPanel.add(descriptionLabel);
        basicPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        basicPanel.add(descriptionField);
        basicPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        basicPanel.add(recurringCheckBox);
        basicPanel.add(appointmentCheckBox);
        basicPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        recurrencePanel = new JPanel();
        recurrencePanel.setLayout(new BoxLayout(recurrencePanel, BoxLayout.PAGE_AXIS));
        recurrencePanel.setAlignmentX(0.0f);
        recurrencePanel.add(recurrenceTypeLabel);
        recurrencePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        recurrencePanel.add(recurrenceTypeComboBox);
        recurrencePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        recurrencePanel.add(recurrenceNumberLabel);
        recurrencePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        recurrencePanel.add(recurrenceNumberField);
        recurrencePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        recurrencePanel.setVisible(false);


        appointmentPanel = new JPanel();
        appointmentPanel.setLayout(new BoxLayout(appointmentPanel, BoxLayout.PAGE_AXIS));
        appointmentPanel.setAlignmentX(0.0f);
        appointmentPanel.add(timeLabel);
        appointmentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        appointmentPanel.add(timeField);
        appointmentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        appointmentPanel.add(placeLabel);
        appointmentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        appointmentPanel.add(placeField);
        appointmentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        appointmentPanel.setVisible(false);

        inputPanel.add(basicPanel);
        inputPanel.add(recurrencePanel);
        inputPanel.add(appointmentPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setAlignmentX(0.0f);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            String description = descriptionField.getText();

            recurringCheckBox.isSelected();
            int recurrenceNumber = recurringCheckBox.isSelected() ? Integer.parseInt(recurrenceNumberField.getText()) : 0;
            String recurrenceType = recurringCheckBox.isSelected() ? (String) recurrenceTypeComboBox.getSelectedItem() : null;

            String time = appointmentCheckBox.isSelected() ? timeField.getText() : null;
            String place = appointmentCheckBox.isSelected() ? placeField.getText() : null;

            app.createNewTask(list, description, recurringCheckBox.isSelected(), appointmentCheckBox.isSelected(),
                                recurrenceNumber, recurrenceType, time, place);

            setVisible(false);
            descriptionField.setText("");
            recurringCheckBox.setSelected(false);
            appointmentCheckBox.setSelected(false);
            timeField.setText("");
            placeField.setText("");
            recurrenceNumberField.setText("");
            recurrenceTypeComboBox.setSelectedIndex(0);
        }
        else if (e.getActionCommand().equals("Cancel")) {
            setVisible(false);
            descriptionField.setText("");
            recurringCheckBox.setSelected(false);
            appointmentCheckBox.setSelected(false);
            timeField.setText("");
            placeField.setText("");
            recurrenceNumberField.setText("");
            recurrenceTypeComboBox.setSelectedIndex(0);
        }
        else if (e.getActionCommand().equals("recurring")) {
            if (recurringCheckBox.isSelected()) {
                recurrencePanel.setVisible(true);
                pack();
            }
            else {
                recurrencePanel.setVisible(false);
                pack();
            }
        }
        else if (e.getActionCommand().equals("appointment")) {
            if (appointmentCheckBox.isSelected()) {
                appointmentPanel.setVisible(true);
                pack();
            }
            else {
                appointmentPanel.setVisible(false);
                pack();
            }
        }

    }


}