import java.util.Scanner;

public class App {
    // Scanner for user inputs
    Scanner input = new Scanner(System.in);
    char command = ' ';

    // List of items

    // Screen objects
    Homescreen hs = new Homescreen();

    void run() {
        while (command != 'q') {
            hs.printHomescreen();
            command = input.next().charAt(0);

            if (command == 's') {
                // show list

            } else if (command == 'c') {
                // create task
                // newTask = createScreen.runForm();
                // list.push(newTask);
            } else if (command == 't') {
                // todays tasks

            } else if (command == 'q') {
                // quit
                System.out.println("Shutting Down");
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
