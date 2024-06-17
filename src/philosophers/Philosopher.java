package philosophers;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher extends Thread {
    private String nameOfPhilosopher;
    private Manager manager;
    private Fork leftFork;
    private Fork rightFork;

    private CountDownLatch countDownLatch;

    private AtomicBoolean stopped = new AtomicBoolean();
    private int EAT_LIMIT = 3;
    private AtomicInteger countEat = new AtomicInteger(0);

    public Philosopher(String nameOfPhilosopher, Fork leftFork, Fork rightFork, Manager manager, CountDownLatch countDownLatch) {
        this.nameOfPhilosopher = nameOfPhilosopher;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.manager = manager;
        this.countDownLatch = countDownLatch;
    }

    public String getNameOfPhilosopher() {
        return nameOfPhilosopher;
    }

    public AtomicBoolean getStopped() {
        return stopped;
    }

    @Override
    public void run() {
        while (countEat.get() < EAT_LIMIT) {
            thinking();
            manager.takeForks(nameOfPhilosopher, leftFork, rightFork);
            countDownLatch.countDown();
            manager.putForks(nameOfPhilosopher, leftFork, rightFork);
            countEat.incrementAndGet();
        }
    }

    public void startEat() throws InterruptedException {
        int temp = new Random().nextInt(2, 5)* 1000;
        System.out.println("Philosopher " + nameOfPhilosopher + " is eating " + temp);
        sleep(temp);
    }

    public void endEat() {

        System.out.println("Philosopher " + nameOfPhilosopher + " stopped eating");
    }

    public void thinking() {

        System.out.println("Philosopher " + nameOfPhilosopher + " is thinking");
    }


}
