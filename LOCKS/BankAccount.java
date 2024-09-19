import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;

    // Below is the method of intrinsic lock which uses synchronized keyword.

    /*
    public synchronized void withdraw(int amount) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+ ", attempting to withdraw, "+amount);
        if(amount <= balance) {
            System.out.println(Thread.currentThread().getName()+ ", proceedings withdrawals");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+", completed transaction.");
            balance -= amount;
        } else {
            System.out.println(Thread.currentThread().getName()+", Insufficient balance");
        }
    }
    */

    // To create an extrinsic lock we need to create an object of Lock class.

    private final Lock lock = new ReentrantLock();
    
    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName()+ ", attempting to withdraw, "+amount);
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) { // Here another thread will wait until the time completes in the tryLock() method. If the waiting time exceeds it will go to the else part.
                if(amount <= balance) {
                    try {
                        System.out.println(Thread.currentThread().getName()+ ", proceedings withdrawals");
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName()+", completed withdrawal. Remaining balance "+amount);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt(); 
                    }
                    finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName()+", Insufficient balance");
                }
            } else {
                System.out.println(Thread.currentThread().getName()+" Could not acquire the lock, will try later.");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt(); // We need to interrupt the thread other wise we will lose the data.
        }
    }

}
