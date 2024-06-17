package philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    private static CountDownLatch countDownLatch = new CountDownLatch(15);

    public static void main(String[] args) {

        ArrayList<Philosopher> philosophers = new ArrayList<>();
        Manager manager = new Manager(philosophers);
        List<Fork> forks = new ArrayList<>() {{
            add(new Fork());
            add(new Fork());
            add(new Fork());
            add(new Fork());
            add(new Fork());
        }};

        philosophers.add(new Philosopher("A", forks.get(0), forks.get(1), manager, countDownLatch));
        philosophers.add(new Philosopher("B", forks.get(1), forks.get(2), manager, countDownLatch));
        philosophers.add(new Philosopher("C", forks.get(2), forks.get(3), manager, countDownLatch));
        philosophers.add(new Philosopher("D", forks.get(3), forks.get(4), manager, countDownLatch));
        philosophers.add(new Philosopher("E", forks.get(4), forks.get(0), manager, countDownLatch));

        manager.initStates();

        List<Thread> threads = new ArrayList<>(philosophers.size());


        for (int i = 0; i < philosophers.size(); i++) {
            threads.add(philosophers.get(i));
            threads.get(i).start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        System.out.println("Все сыты");

    }

}
