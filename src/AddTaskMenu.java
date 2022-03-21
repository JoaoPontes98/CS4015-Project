import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddTaskMenu extends JPopupMenu implements ActionListener {
    private App app;
    private List list;

    public AddTaskMenu(Component component, List list) {
        this.list = list;
        this.app = app.getInstance();

        JMenuItem taskItem = new JMenuItem("New Task");
        JMenuItem sublistItem = new JMenuItem("New Sublist");

        taskItem.setActionCommand("newTask");
        sublistItem.setActionCommand("newSublist");

        taskItem.addActionListener(this);
        sublistItem.addActionListener(this);

        add(taskItem);
        add(sublistItem);

        component.addMouseListener(new PopupListener());
    }

    private class PopupListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            AddTaskMenu.this.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("newTask")) {
            app.openCreateTaskDialog(list);
        } else if (e.getActionCommand().equals("newSublist")) {
            String description = (String) JOptionPane.showInputDialog(this, "Please enter a description:",
                    "Create New Sublist", JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (description != null) {
                if (description.equals("")) {
                    System.out.println("Error: Description must be filled out");
                    return;
                }
                app.createNewSublist(list, description);
            }
        }
    }

}