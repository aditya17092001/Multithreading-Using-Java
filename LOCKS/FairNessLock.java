import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairNessLock {
    private final Lock lock = new ReentrantLock(true); // true means this lock should use a fair ordering policy
    
    // If the thread race occurs there may have situation that some thread may not get the chance this leads to starvation. To avoid this situation we'll use the fainess true.
    public void resources() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+", acquired the lock.");
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName()+", removed the lock.");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairNessLock lock = new FairNessLock();

        Runnable task = new Runnable() {

            @Override
            public void run() {
                lock.resources();
            }
        };

        Thread t1 = new Thread(task, "Thread1");
        Thread t2 = new Thread(task, "Thread2");
        Thread t3 = new Thread(task, "Thread3");

        t1.start();
        t2.start();
        t3.start();
    }
}
