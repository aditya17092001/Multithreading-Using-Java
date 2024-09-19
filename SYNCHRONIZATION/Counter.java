public class Counter {
    int count = 0;

    /* 
     * We are calling the below increment function from MyThread class for 1000 * 2 times then it should get 2000 when ever we call the getCount() after both the thread execution.
     * 
     * But here threads are working simultaneously we may or may not get the ans 2000.
     * 
     * To block the the thread while other thread is engaged we will synchorized the method or we will create the synchronized block
     * 
     * Synchronized block: 
     * synchronized(this) {
            count++;
        }
     */
    public synchronized void increment() { 
        this.count++;
    }

    public int getCount() {
        return count;
    }
}