package dinner;

import java.util.concurrent.Semaphore;


public class Main {

    final int thinking = 1;
    final int eating = 2;

    public static int       numberOfPhilosophers;
    public static Semaphore semaphores[];
    public static int       state[];

    static Philosopher philosopher[];

    public Main(int numberPhilosofers, int timeInSec) {

        Main.numberOfPhilosophers = numberPhilosofers;
        Main.semaphores           = new Semaphore  [numberOfPhilosophers];
        Main.state                = new int        [numberOfPhilosophers];
        Main.philosopher          = new Philosopher[numberOfPhilosophers];

        for (int i = 0; i < numberOfPhilosophers; i++) {

            state     [i] = 1;
            semaphores[i] = new Semaphore(1);

        }

        for (int i = 0; i < numberOfPhilosophers; i++) {

            if ((i + 1) < numberOfPhilosophers) {

                philosopher[i] = new Philosopher("Person: " + i, i, semaphores[i], semaphores[i + 1], numberOfPhilosophers);

            } else {

                philosopher[i] = new Philosopher("Person: " + i, i, semaphores[i], semaphores[0], numberOfPhilosophers);

            }

        }

        for (int i = 0; i < numberOfPhilosophers; i++) {

            philosopher[i].start();

        }

    }

    public Philosopher[] getPhilosopher() {

        return philosopher;

    }

}