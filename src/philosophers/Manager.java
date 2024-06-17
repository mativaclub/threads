package philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Manager {

    private enum StatesOfPhilosophers {
        HUNGRY,
        EATING,
        THINKING
    }

    private List<Philosopher> philosopherList;
    private List<StatesOfPhilosophers> statesOfPhilosophers;
    private List<Condition> conditions;
    private Lock locker;

    public Manager(List<Philosopher> philosopherList) {
        this.philosopherList = philosopherList;
        statesOfPhilosophers = new ArrayList<>();

        conditions = new ArrayList<>();
        locker = new ReentrantLock(); //it is blocking the object when the object is busy and unblock when it's free.

    }

    public void initStates() {
        for (int i = 0; i < philosopherList.size(); i++) {
            statesOfPhilosophers.add(StatesOfPhilosophers.THINKING);
        }
        for (int i = 0; i < philosopherList.size(); i++) {
            conditions.add(locker.newCondition());
        }
    }


    public void takeForks(String name, Fork left, Fork right) {
        int id = 0;
        for (int i = 0; i < philosopherList.size(); i++) {
            if (philosopherList.get(i).getNameOfPhilosopher().equals(name)) {
                id = i;
            }
        }
        try {
            statesOfPhilosophers.set(id, StatesOfPhilosophers.HUNGRY);
//            locker.lock();
            while (!philosopherList.get(id).getStopped().get() && (left.isUsing() || right.isUsing())) {
//                conditions.get(id).await();
            }
            right.setUsing(true);
            left.setUsing(true);
            statesOfPhilosophers.set(id, StatesOfPhilosophers.EATING);
            philosopherList.get(id).startEat();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
//            locker.unlock();
        }
    }

    public void putForks(String name, Fork left, Fork right) {
//        locker.lock();
        int id = 0;
        for (int i = 0; i < philosopherList.size(); i++) {
            if (philosopherList.get(i).getNameOfPhilosopher().equals(name)) {
                id = i;
            }
        }
        statesOfPhilosophers.set(id, StatesOfPhilosophers.THINKING);
        philosopherList.get(id).endEat();
        right.setUsing(false);
        left.setUsing(false);
//        conditions.get((id + 1) % philosopherList.size()).signalAll();
//        conditions.get((id - 1 + philosopherList.size()) % philosopherList.size()).signalAll();
//        locker.unlock();
    }


}
