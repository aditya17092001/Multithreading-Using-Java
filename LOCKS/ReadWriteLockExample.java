import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private int count = 0;

    /*
     * ReadWriteLock:
        It is a special type of lock in Java that allows multiple threads to read shared data (concurrent reads) but only one thread to write or modify the data at any given time (exclusive writes).
        This lock improves concurrency performance in scenarios where reads are more frequent than writes because multiple threads can hold the read lock simultaneously, while the write lock is exclusive.
     */
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /*
     * Lock readLock = lock.readLock():
        The readLock allows multiple threads to hold the lock at the same time if they are only reading the shared resource.
        When the readLock is acquired, no thread can acquire the writeLock, which ensures that the shared resource is not being modified during the read operations.
        Multiple threads can acquire the readLock concurrently.
     */
    private final Lock readLock = lock.readLock();

    /*
     * Lock writeLock = lock.writeLock():
        The writeLock ensures that only one thread can modify the shared resource at a time.
        When the writeLock is held by a thread, no other thread can acquire either the readLock or the writeLock.
        It ensures exclusive access to the shared resource for write operations, preventing race conditions.
     */
    private final Lock writeLock = lock.writeLock();

    public void increment() throws InterruptedException {
        writeLock.lock();
        try {
            count++;
            Thread.sleep(50);
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample counter = new ReadWriteLockExample();

        Runnable writeTask = new Runnable() {

            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    try {
                        counter.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" incremented");
                }
            }
        };

        Runnable readTask = new Runnable() {

            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName()+" "+counter.getCount());
                }
                
            }
        };

        Thread writeThread = new Thread(writeTask, "Write thread");
        Thread readThread1 = new Thread(readTask, "Read thread");
        Thread readThread2 = new Thread(readTask, "Read thread");


        writeThread.start();
        readThread1.start();
        readThread2.start();

        writeThread.join();
        readThread1.join();
        readThread2.join();

        System.out.println("Final count: "+counter.getCount());
    }
}
