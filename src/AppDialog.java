import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class AppDialog extends JDialog implements ActionListener {
    App app;

    public AppDialog(JFrame frame, String title) {
        super(frame, title);
        this.app = App.getInstance();

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

    protected abstract JPanel createInputsPanel();

    protected abstract JPanel createButtonsPanel();

    public abstract void actionPerformed(ActionEvent e);
}