import java.util.ArrayList;

public class ShowScreen {
    void printShowScreen(ArrayList<List> lists) {
        for (List list : lists) {
            System.out.println(list.getDate());
        }

    }
}