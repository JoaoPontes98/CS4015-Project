import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddTaskMenu extends JPopupMenu implements ActionListener {
    private JMenuItem taskItem;
    private JMenuItem sublistItem;
    private List list;

    public AddTaskMenu(Component component, List list) {
        this.list = list;

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

        }
        else if (e.getActionCommand().equals("newSublist")) {
            String s = (String) JOptionPane.showInputDialog(this, "Please enter a description:", "Create New Sublist", JOptionPane.QUESTION_MESSAGE, null, null, null);
            //List subList = new List(s);
            //list.addItem(subList);
        }
    }

}