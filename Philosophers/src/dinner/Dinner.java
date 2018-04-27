package dinner;

import java.util.Scanner;

public class Dinner {

    public static void main(String[] args) throws InterruptedException {

        System.out.print("Number of Philosophers: ");
        Scanner in     = new Scanner(System.in);
        int numberPhil = in.nextInt();

        System.out.print("Time to Run the Application: ");
        int timeInSec = in.nextInt();

        System.out.print("\n");

        Main main = new Main(numberPhil, timeInSec);

        Thread.sleep(timeInSec * 1000);

        Philosopher philosopher[] = main.getPhilosopher();

        Philosopher philosopher1;

        System.out.print("\n");

        for (int i = 0; i < philosopher.length; i++) {

            philosopher1 = philosopher[i];

            System.out.println( philosopher1.getName() + ", Ate: " + philosopher1.timesAte
                    + ", Thought:" + philosopher1.timesThought
                    + ", Tried:" + philosopher1.timesTried);
            System.out.print("\n");

        }

        System.exit(0);

    }

}