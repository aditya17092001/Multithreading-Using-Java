import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer Method");
            innerMethod();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println("Inner Method");
        } catch (Exception e) {
 
        } finally {
            lock.unlock();
            // lock.unlock(); If we close both at a time with out closing in the outer block another thread can enter the outer method. We need to unlock() equals to the count of lock().
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample lock = new ReentrantLockExample();
        lock.outerMethod();
    }
}
