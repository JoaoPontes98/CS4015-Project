import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class UITest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button = new JButton("test");
        frame.add(button);

        frame.setVisible(true);
        frame.pack();

        CreateListDialog dialog1 = new CreateListDialog(frame);
        CreateTaskDialog dialog2 = new CreateTaskDialog(frame);
        BrowseListsDialog dialog3 = new BrowseListsDialog(frame);
        AddTaskMenu menu = new AddTaskMenu(button, null);

        //SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");

        /* 
        String[] lists = allLists.toArray();
        */
        //String[] lists = {"2022/01/01", "2023/01/01"};
        //String s1 = (String) JOptionPane.showInputDialog(frame, "Please select a date:", "Browse Lists", JOptionPane.PLAIN_MESSAGE, null, lists, null);

        //public List browseLists(String input) {}
        //if (s1 != null) {
           // try {
            //        Date date = format.parse(s1);
              //      System.out.println(date);
                    /*
                    for (List list : allLists) {
                        if (date == list.getDate()) {
                            return list;
                        }
                    }
                    */

                //}
                //catch (ParseException e) {
                  //  e.printStackTrace();
            //}
        //}

        //String s2 = (String) JOptionPane.showInputDialog(frame, "Please enter a date (yyyy/mm/dd):", "Create New List", JOptionPane.QUESTION_MESSAGE, null, null, null);

        //if (s2 != null) {
          //  try {
            //    Date date = format.parse(s2);
              //  System.out.println(date);

                /*
                public List createList(String input, ArrayList<List> allLists) {
                    for (List list : allLists) {
                        if (date == list.getDate()) {
                            return list;
                        }
                    }
                }
                */

                //List newList = new List(date);
            //}
            //catch (ParseException e) {
              //  e.printStackTrace();
            //}
        //}
    }
}