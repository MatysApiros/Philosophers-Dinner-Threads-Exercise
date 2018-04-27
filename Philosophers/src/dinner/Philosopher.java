package dinner;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private int id;

    Semaphore leftFork                  = null;
    Semaphore rightFork                 = null;
    int       totalNumberOfPhilosophers = 0;

    public int timesAte     = 0;
    public int timesThought = 0;
    public int timesTried   = 0;

    public Philosopher(String name, int id, Semaphore leftFork, Semaphore rightFork, int totalNumbPhil) {

        super(name);
        this.leftFork                  = leftFork;
        this.rightFork                 = rightFork;
        this.id                        = id;
        this.totalNumberOfPhilosophers = totalNumbPhil;
        
    }

    public void Eat () {

        Main.state[this.id] = 2;

        try {

            Thread.sleep(5000);
            System.out.println("Person: " + id + " is Eating!");
            timesAte++;
            
        } catch (InterruptedException ex) {

            System.out.println("ERROR>" + ex.getMessage());
            
        }
        
    }

    public void Think () {

        Main.state[this.id] = 1;

        try {

            Thread.sleep(2000);
            System.out.println("Person: " + id + " is Thinking!");
            timesThought++;
            
        } catch (InterruptedException ex) {

            System.out.println("ERROR>" + ex.getMessage());
            
        }
        
    }

    public synchronized void TryToeEat() throws InterruptedException {

        boolean leftFork1, rightFork1;

        leftFork1 = leftFork.tryAcquire();

        if (leftFork1 == true) {
            
            rightFork1 = rightFork.tryAcquire();
            if (rightFork1 == true) {
                
                Eat();
                leftFork .release();
                rightFork.release();
                Think();
                
            } else {
                
                leftFork.release();
                System  .out.println("Person: " + id + " Tried to Eat!");
                timesTried++;

                Random random = new Random();
                int number    = random.nextInt(4000);

                Thread.sleep(number);

            }
            
        }else {
            
            leftFork.release();
            System  .out.println("Person: " + id + " Tried to Eat!");
            timesTried++;

            Random random = new Random();
            int number    = random.nextInt(4000);

            Thread.sleep(number);
            
        }
        
    }

    @Override
    public void run() {

        try {
            
            Think();

            do {
                
                TryToeEat();

            } while (true);
            
        } catch (InterruptedException ex) {

            System.out.println("ERROR>" + ex.getMessage());

            return;
            
        }

    }

}
