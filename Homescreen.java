import java.util.Scanner;

public class Homescreen {
    Scanner input = new Scanner(System.in);
    char command = ' ';

    void printHomescreen() {
        System.out.println("\n=====Daily Planner=====");
        System.out.println("(S)how Lists");
        System.out.println("(C)reat Task");
        System.out.println("(T)oday's Tasks");
        System.out.println("(Q)uit\n");
    }

    void run() {
        while (command != 'q') {
            printHomescreen();
            command = input.next().charAt(0);
        }
    }
}