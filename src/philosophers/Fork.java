package philosophers;

import java.util.concurrent.locks.ReentrantLock;

public class Fork extends Thread {
    private boolean isUsing = false;
    private ReentrantLock locker = new ReentrantLock();

    public boolean isUsing() {
        return isUsing;
    }

    public void setUsing(boolean using) {
        isUsing = using;
        if (isUsing) {
            locker.lock();
        } else {
            locker.unlock();
        }

    }
}
