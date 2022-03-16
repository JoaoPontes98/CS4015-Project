import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddTaskMenu extends JPopupMenu implements ActionListener {
    private App app;
    private JMenuItem taskItem;
    private JMenuItem sublistItem;

    public AddTaskMenu(App app, Component component) {
        this.app = app;

        taskItem = new JMenuItem("New Task");
        sublistItem = new JMenuItem("New Sublist");

        taskItem.setActionCommand("newTask");
        sublistItem.setActionCommand("newSublist");

        taskItem.addActionListener(this);
        sublistItem.addActionListener(this);

        add(taskItem);
        add(sublistItem);

        component.addMouseListener(new PopupListener());
    }

    class PopupListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            AddTaskMenu.this.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("newTask")) {
            app.openCreateTaskDialog();
        } else if (e.getActionCommand().equals("newSublist")) {
            String description = (String) JOptionPane.showInputDialog(this, "Please enter a description:",
                    "Create New Sublist", JOptionPane.QUESTION_MESSAGE, null, null, null);
            app.createNewSublist(description);
        }
    }

}